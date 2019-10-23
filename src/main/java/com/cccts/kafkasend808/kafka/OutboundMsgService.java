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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class OutboundMsgService {

    @Autowired
    private KafkaTemplate<String, byte[]> msg808KafkaTemplate;

    @Value("${kafka-topics.outbound-msg.topic-name}")
    private String topicName;

    @KafkaListener(topics = "${kafka-topics.outbound-msg.topic-name}",
            groupId = "${kafka-topics.outbound-msg.group-id}",
            containerFactory = "msg808ListenerContainerFactory",
            clientIdPrefix = "outbound-msg")
    public void listen(ConsumerRecord<String, Message808> record) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(new Date()) + ",OutboundMsgService.listen," + record.value());
    }

    public void publish(byte[] data) {
        ListenableFuture<SendResult<String, byte[]>> future = msg808KafkaTemplate.send(topicName, data);
        future.addCallback(new ListenableFutureCallback<SendResult<String, byte[]>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("OutboundMsgService.onFailure,发送失败");
            }

            @Override
            public void onSuccess(SendResult<String, byte[]> stringSendResult) {
                System.out.println("OutboundMsgService.onSuccess,发送成功");
            }
        });
    }
}
