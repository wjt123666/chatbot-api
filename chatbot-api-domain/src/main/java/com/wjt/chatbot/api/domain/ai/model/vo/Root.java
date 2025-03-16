package com.wjt.chatbot.api.domain.ai.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Root {
    public String id;

    public String object;

    public int created;

    public String model;

    public List<Choices> choices;

    public Usage usage;

    public String system_fingerprint;
}