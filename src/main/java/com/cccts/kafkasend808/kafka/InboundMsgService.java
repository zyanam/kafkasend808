package com.cccts.kafkasend808.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class InboundMsgService {

   private final KafkaTemplate<String,Object> template;

   public InboundMsgService(KafkaTemplate<String, Object> template) {
      this.template = template;
   }

   @KafkaListener(topics = "${kafka-topics.inbound-msg.topic-name}",
           groupId = "kafka-topics.inbound-msg.group-id",
   containerFactory = "inboundMsgListenerContainerFactory")
   public void listen(ConsumerRecord<String,Object> record) {
      System.out.println("InboundMsgService.listen," + record.value());
   }

}
