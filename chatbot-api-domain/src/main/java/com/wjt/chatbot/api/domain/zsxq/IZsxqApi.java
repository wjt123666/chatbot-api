package com.wjt.chatbot.api.domain.zsxq;

import com.wjt.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;

import java.io.IOException;

/**
 * @ClassName IZsxqApi
 * @Description: 知识星球 API 接口
 * @Author 86178
 * @Date 2025/3/15 001518:52
 * @Version 1.0
 */
public interface IZsxqApi {

    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException;

    boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException;
}
