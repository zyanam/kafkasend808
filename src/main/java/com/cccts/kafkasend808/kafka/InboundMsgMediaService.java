package com.cccts.kafkasend808.kafka;

import com.zjts.beidou.agreement.Message808;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class InboundMsgMediaService {
    @Autowired
    private KafkaTemplate<String, Message808> msg808KafkaTemplate;

    @Autowired
    private KafkaTemplate<String, byte[]> byteArrayKafkaTemplate;

    @Value("${kafka-topics.inbound-msg-media.topic-name}")
    private String inboundMsgTopic;

    @Value("${kafka-topics.inbound-msg-media.group-id}")
    private String inboundMsgGroupID;

    @KafkaListener(topics = "${kafka-topics.inbound-msg-media.topic-name}",
            groupId = "${kafka-topics.inbound-msg-media.group-id}",
            containerFactory = "msg808ListenerContainerFactory",
            clientIdPrefix = "inbound-msg-media")
    public void listen(ConsumerRecord<String, Message808> record) {
        System.out.println("InboundMsgMediaService.listen,record.key=" + record.key() + ",record.value=" + record.value());
    }
}
