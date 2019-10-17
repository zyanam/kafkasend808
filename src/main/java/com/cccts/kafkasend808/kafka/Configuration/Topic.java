package com.cccts.kafkasend808.kafka.Configuration;

import lombok.Data;

@Data
public class Topic {
    private String groupId;
    private String topicName;
}
