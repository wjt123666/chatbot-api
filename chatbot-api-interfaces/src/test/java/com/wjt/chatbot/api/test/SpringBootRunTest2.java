package com.wjt.chatbot.api.test;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.VoidType;
import com.wjt.chatbot.api.domain.ai.IOpenAI;
import com.wjt.chatbot.api.domain.zsxq.IZsxqApi;
import com.wjt.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.wjt.chatbot.api.domain.zsxq.model.vo.Topics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName SpringBootRunTest2
 * @Description:
 * @Author 86178
 * @Date 2025/3/16 001612:58
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest2 {

    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest2.class);

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

    @Test
    public void test_zsxqApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));

        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for (Topics topic : topics) {
            String topicId = topic.getTopic_id();
            String text = topic.getQuestion().getText();
            logger.info("topicId:{},text:{}", topicId, text);
            // 回答问题
            //zsxqApi.answer(groupId,cookie,topicId,text,false);
        }
    }


    @Test
    public void test_openAi() throws Exception {
        String res = openAI.doChatGPT(openAiKey, "帮我写一个java冒泡排序");
        logger.info("测试结果：{}", res);
    }
}
