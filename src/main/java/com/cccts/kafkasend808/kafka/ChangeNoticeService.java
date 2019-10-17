package com.cccts.kafkasend808.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class ChangeNoticeService {

    @Value("${kafka-topics.change-notice.topic-name}")
    private String topicName;

    @Value("${kafka-topics.change-notice.group-id}")
    private String groupID;

    KafkaTemplate<Byte, String> kafkaTemplate;

    public ChangeNoticeService(KafkaTemplate<Byte, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "${kafka-topics.change-notice.topic-name}",
            groupId = "${kafka-topics.change-notice.group-id}")
    public void listen(ConsumerRecord<Byte, String> record) {
        System.out.println("ChangeNoticeService.listen,key=" + record.key() + ";value=" + record.value());
    }

    public void publish(Byte key, String data) {
        ListenableFuture<SendResult<Byte, String>> future = kafkaTemplate.send(this.topicName, key, data);
        future.addCallback(new ListenableFutureCallback<SendResult<Byte, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("ChangeNoticeService.onFailure,发送失败");
            }

            @Override
            public void onSuccess(SendResult<Byte, String> byteStringSendResult) {
                System.out.println("ChangeNoticeService.onSuccess,发送成功");
            }
        });
    }
}
