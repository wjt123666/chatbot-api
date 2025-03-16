package com.wjt.chatbot.api.domain.ai.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Choices
 * @Description:
 * @Author 86178
 * @Date 2025/3/16 001614:00
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Choices {

    public int index;
    public Message message;
    public String logprobs;
    public String finish_reason;
}
