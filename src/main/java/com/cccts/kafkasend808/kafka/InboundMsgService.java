package com.cccts.kafkasend808.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

public class InboundMsgService {

   @KafkaListener(topics = "${kafka-topics.inbound-msg.topic-name}",
           groupId = "kafka-topics.inbound-msg.group-id",
   containerFactory = "inboundMsgListenerContainerFactory")
   public void listen(ConsumerRecord<String,Object> record) {
      System.out.println("InboundMsgService.listen," + record.value());
   }

}
