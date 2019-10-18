package com.cccts.kafkasend808.kafka;

import com.zjts.beidou.agreement.Message808;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

public class InboundMsgLocationService {


    @KafkaListener(topics = "${kafka-topics.inbound-msg-location.topic-name}",
            groupId = "${kafka-topics.inbound-msg-location.group-id}",
            containerFactory = "inboundMsgLocationListenerContainerFactory")
    public void listen(ConsumerRecord<String, Message808> record) {
        System.out.println("InboundMsgLocationService.listen," + record.value());
    }
}
