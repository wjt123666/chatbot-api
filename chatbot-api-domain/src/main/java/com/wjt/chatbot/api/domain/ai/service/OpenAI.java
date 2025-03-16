package com.wjt.chatbot.api.domain.ai.service;

import com.alibaba.fastjson.JSON;
import com.wjt.chatbot.api.domain.ai.IOpenAI;
import com.wjt.chatbot.api.domain.ai.model.vo.Choices;
import com.wjt.chatbot.api.domain.ai.model.vo.Root;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName OpenAI
 * @Description:
 * @Author 86178
 * @Date 2025/3/16 001613:59
 * @Version 1.0
 */
@Service
public class OpenAI implements IOpenAI {

    private Logger logger = LoggerFactory.getLogger(OpenAI.class);

    @Override
    public String doChatGPT(String openAiKey,String question) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 使用DeepSeek专用接口地址
        HttpPost post = new HttpPost("https://api.deepseek.com/chat/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer " + openAiKey);

        // 按DeepSeek格式构造请求体
        String paramJson = "{"
                + "\"model\": \"deepseek-chat\", " // 或 deepseek-reasoner（需逻辑推理时使用）[3](@ref)
                + "\"messages\": [{\"role\": \"user\", \"content\": \"" + question +"\"}], "
                + "\"temperature\": 0, "          // 保持确定性输出
                + "\"max_tokens\": 1024"
                + "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            Root root = JSON.parseObject(jsonStr, Root.class);
            StringBuilder answers = new StringBuilder();
            List<Choices> choices = root.getChoices();
            for (Choices choice : choices) {
                answers.append(choice.getMessage().getContent());
            }
            return answers.toString();
        } else {
            throw new RuntimeException("api.openai.com Err Code is " + response.getStatusLine().getStatusCode());

        }
    }
}
