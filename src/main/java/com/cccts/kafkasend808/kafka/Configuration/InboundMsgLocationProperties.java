package com.cccts.kafkasend808.kafka.Configuration;

import lombok.Data;

import java.util.List;

@Data
//@Component
//@ConfigurationProperties(prefix = "kafka.inbound-msg-location")
public class InboundMsgLocationProperties {
    private List<String> bootstrapServers;

    public Producer producer;

    private final Consumer consumer;
    private Topic topic;
}
