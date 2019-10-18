package com.cccts.kafkasend808.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class ChangeNoticeService {
    private final KafkaTemplate<String, Object> template;

    public ChangeNoticeService(final KafkaTemplate template) {
        this.template = template;
    }

    @KafkaListener(topics = "${kafka-topics.change-notice.topic-name}",
            groupId = "${kafka-topics.change-notice.group-id}",
            containerFactory = "changeNoticeListenerContainerFactory")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("ChangeNoticeService.listen," + record.value());
    }

    public void publish(String key, Object data) {
        ListenableFuture<SendResult<String, Object>> future = template.send("change-notice", key, data);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("ChangeNoticeService.onFailure");
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringStringSendResult) {
                System.out.println("ChangeNoticeService.onSuccess");
            }
        });
    }
}
