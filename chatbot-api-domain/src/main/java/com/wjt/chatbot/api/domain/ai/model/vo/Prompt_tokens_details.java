package com.wjt.chatbot.api.domain.ai.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prompt_tokens_details {
    public int cached_tokens;
}