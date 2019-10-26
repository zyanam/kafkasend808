package com.cccts.kafkasend808.kafka;

import com.zjts.noticemodels.CommonNotice;
import com.zjts.noticemodels.NoticeModelUtil;
import io.lettuce.core.dynamic.annotation.CommandNaming;
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
public class NoticeGateway809Service {
    @Value("${kafka-topics.notice-gateway809.topic-name}")
    private String topicName;

    @Value("${kafka-topics.notice-gateway809.group-id}")
    private String groupID;

    private KafkaTemplate<String, byte[]> kafkaTemplate;

    @Autowired
    public NoticeGateway809Service(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "${kafka-topics.notice-gateway809.topic-name}",
            groupId = "${kafka-topics.notice-gateway809.group-id}",
            containerFactory = "byteArrayListenerContainerFactory",
            clientIdPrefix = "notice-gateway809")
    public void listen(ConsumerRecord<String, byte[]> record) throws Exception {
        CommonNotice commonNotice = NoticeModelUtil.deserialize(record.value());
        System.out.println("NoticeGateway809Test.listen," + commonNotice);
    }

    public void publish(String key, CommonNotice commonNotice) {
        byte[] bs = NoticeModelUtil.serialize(commonNotice);
        ListenableFuture<SendResult<String, byte[]>> future = kafkaTemplate.send(this.topicName, bs);
        future.addCallback(new ListenableFutureCallback<SendResult<String, byte[]>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("NoticeGateway809Test.onFailure,发送失败");
            }

            @Override
            public void onSuccess(SendResult<String, byte[]> stringSendResult) {
                System.out.println("NoticeGateway809Test.onSuccess,发送成功");
            }
        });

    }

}
