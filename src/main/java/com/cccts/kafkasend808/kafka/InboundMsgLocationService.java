package com.cccts.kafkasend808.kafka;

import com.zjts.beidou.agreement.Message808;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class InboundMsgLocationService {
    @Autowired
    private KafkaTemplate<String, byte[]> byteArrayKafkaTemplate;

    @Value("${kafka-topics.inbound-msg-location.topic-name}")
    private String inboundMsgTopic;

    @Value("${kafka-topics.inbound-msg-location.group-id}")
    private String inboundMsgGroupID;

    @KafkaListener(topics = "${kafka-topics.inbound-msg-location.topic-name}",
            groupId = "${kafka-topics.inbound-msg-location.group-id}",
            containerFactory = "msg808ListenerContainerFactory",
            clientIdPrefix = "inbound-location-msg")
    public void listen(ConsumerRecord<String, Message808> record) {
        System.out.println("InboundMsgLocationService.listen," + record.value());
    }

    public void publish(byte[] bs) {
        ListenableFuture<SendResult<String, byte[]>> future = byteArrayKafkaTemplate.send(this.inboundMsgTopic, bs);
        future.addCallback(new ListenableFutureCallback<SendResult<String, byte[]>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("InboundMsgLocationService.onFailure," + "发送失败");
            }

            @Override
            public void onSuccess(SendResult<String, byte[]> stringSendResult) {
                System.out.println("InboundMsgLocationService.onSuccess," + "发送成功");
            }
        });
    }
}
