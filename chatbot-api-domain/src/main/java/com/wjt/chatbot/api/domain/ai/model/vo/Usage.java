package com.wjt.chatbot.api.domain.ai.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usage {


    public int prompt_tokens;

    public int completion_tokens;

    public int total_tokens;

    public Prompt_tokens_details prompt_tokens_details;

    public int prompt_cache_hit_tokens;

    public int prompt_cache_miss_tokens;
}