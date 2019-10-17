package com.cccts.kafkasend808.kafka;

import com.zjts.beidou.agreement.Message808;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class Protocol808Deserializer implements Deserializer {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                return null;
            } else {
                Message808 msg808 = new Message808();
                ByteBuf buf = Unpooled.copiedBuffer(data);
                msg808.read(buf);
                return msg808;
            }
        } catch (Exception e) {
            throw new SerializationException("Kafka，808协议反序列化异常1" + e.getMessage());
        }
    }

    @Override
    public void close() {

    }
}
