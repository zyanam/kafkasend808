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
public class InboundMsgService {

    @Autowired
    private KafkaTemplate<String, Message808> msg808KafkaTemplate;

    @Autowired
    private KafkaTemplate<String, byte[]> byteArrayKafkaTemplate;

    @Value("${kafka-topics.inbound-msg.topic-name}")
    private String inboundMsgTopic;

    @Value("${kafka-topics.inbound-msg.group-id}")
    private String inboundMsgGroupID;

    @KafkaListener(topics = "${kafka-topics.inbound-msg.topic-name}",
            groupId = "${kafka-topics.inbound-msg.group-id",
            containerFactory = "msg808ListenerContainerFactory",
            clientIdPrefix = "inbound-msg")
    public void listen(ConsumerRecord<String, Message808> record) {
        System.out.println("InboundMsgService.listen,record.key=" + record.key() + ",record.value=" + record.value());
    }

    public void publish(Message808 msg808) {
        ListenableFuture<SendResult<String, Message808>> future = msg808KafkaTemplate.send(this.inboundMsgTopic, msg808);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Message808>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("InboundMsgService.onFailure," + "发送错误");
            }

            @Override
            public void onSuccess(SendResult<String, Message808> stringMessage808SendResult) {
                System.out.println("InboundMsgService.onSuccess," + "发送失败");
            }
        });
    }

    public void publish(byte[] bs) {
        ListenableFuture<SendResult<String, byte[]>> future = byteArrayKafkaTemplate.send(this.inboundMsgTopic, bs);
        future.addCallback(new ListenableFutureCallback<SendResult<String, byte[]>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("InboundMsgService.onFailure," + "发送失败");
            }

            @Override
            public void onSuccess(SendResult<String, byte[]> stringSendResult) {
                System.out.println("InboundMsgService.onSuccess," + "发送成功");
            }
        });
    }
}
