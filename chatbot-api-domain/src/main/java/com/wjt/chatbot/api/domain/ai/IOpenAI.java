package com.wjt.chatbot.api.domain.ai;

import java.io.IOException;

/**
 * @ClassName IOpenAI
 * @Description:
 * @Author 86178
 * @Date 2025/3/16 001613:59
 * @Version 1.0
 */
public interface IOpenAI {

    String doChatGPT(String openAiKey,String question) throws IOException;
}
