package com.wjt.chatbot.api.application.job;

import cn.hutool.core.util.ObjUtil;
import com.alibaba.fastjson.JSON;
import com.wjt.chatbot.api.domain.ai.IOpenAI;
import com.wjt.chatbot.api.domain.zsxq.IZsxqApi;
import com.wjt.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.wjt.chatbot.api.domain.zsxq.model.vo.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * @ClassName ChatbotScheule
 * @Description:
 * @Author 86178
 * @Date 2025/3/16 001616:02
 * @Version 1.0
 */
@EnableScheduling // 开启定时任务
@Configuration
public class ChatbotScheule {

    private Logger logger = LoggerFactory.getLogger(ChatbotScheule.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;
    @Value("${chatbot-api.openAiKey}")
    private String openAiKey;

    @Resource
    private IZsxqApi zsxqApi;
    @Resource
    private IOpenAI openAI;

    @Scheduled(cron = "0/30 * * * * ?")
    public void run() throws IOException {
        try {
            if (new Random().nextBoolean()) {
                logger.info("随机打烊中...");
                return;
            }

            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour > 22 || hour < 7) {
                logger.info("打烊时间不工作，AI下班了");
                return;
            }

            // 1.检索问题
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
            logger.info("查询到待回答问题：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));
            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if (ObjUtil.isNull(topics) || topics.isEmpty()) {
                logger.info("本次检索无待回答问题");
                return;
            }

            // 2.AI 回答问题
            Topics topic = topics.get(0);
            String answer = openAI.doChatGPT(openAiKey, topic.getQuestion().getText());

            // 3.问题回复
            boolean status = zsxqApi.answer(groupId, cookie, topic.getTopic_id(), answer, false);
            logger.info("编号：{} 问题：{} 回答：{} 状态：{}", topic.getTopic_id(), topic.getQuestion().getText(), answer, status);
        } catch (Exception e) {
            logger.error("自动回答问题异常", e);
        }

    }
}
