package com.cccts.kafkasend808.kafka;

import io.netty.buffer.ByteBufUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class InboundMsgService {
    @Value("${kafka-topics.inbound-msg.topic-name}")
    private String topicName;

    @Value("${kafka-topics.inbound-msg.group-id}")
    private String groupId;

    @Bean
    private String inboundMsgTopicName() {
        return topicName;
    }

    @Bean
    private String inboundMsgGroupId() {
        return groupId;
    }


    private KafkaTemplate<String, byte[]> kafkaTemplate;

    @Autowired
    public InboundMsgService(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String key, byte[] data) {
        ListenableFuture<SendResult<String, byte[]>> future = kafkaTemplate.send(topicName, key, data);
        future.addCallback(new ListenableFutureCallback<SendResult<String, byte[]>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("InboundMsgService.onFailure,data=" + ByteBufUtil.hexDump(data));
            }

            @Override
            public void onSuccess(SendResult<String, byte[]> integerStringSendResult) {
                System.out.println("InboundMsgService.onSuccess,data=" + ByteBufUtil.hexDump(data));
            }
        });
    }

    public void publish(byte[] data) {
        publish(null, data);
    }
}
