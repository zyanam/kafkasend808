package com.cccts.kafkasend808.kafka;

import com.zjts.noticemodels.CommonNotice;
import com.zjts.noticemodels.NoticeModelUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class NoticeSignalService {
    @Value("${kafka-topics.notice-signal.topic-name}")
    private String topciName;

    @Value("${kafka-topics.notice-signal.group-id}")
    private String groupID;

    private KafkaTemplate<String, byte[]> kafkaTemplate;

    @Autowired
    public NoticeSignalService(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "${kafka-topics.notice-signal.topic-name}",
            groupId = "${kafka-topics.notice-signal.group-id}",
            containerFactory = "byteArrayListenerContainerFactory",
            clientIdPrefix = "notice-signal")
    public void listen(ConsumerRecord<String, byte[]> record) throws Exception {
        CommonNotice commonNotice = NoticeModelUtil.deserialize(record.value());
        System.out.println("NoticeSignalService.listen," + commonNotice);
    }

    public void publish(String key, CommonNotice commonNotice) {
        byte[] bs = NoticeModelUtil.serialize(commonNotice);
        ListenableFuture<SendResult<String, byte[]>> future = kafkaTemplate.send(this.topciName, bs);
        future.addCallback(new ListenableFutureCallback<SendResult<String, byte[]>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("NoticeSignalService.onFailure,发送成功");
            }

            @Override
            public void onSuccess(SendResult<String, byte[]> stringSendResult) {
                System.out.println("NoticeSignalService.onSuccess,发送失败");
            }
        });
    }
}
