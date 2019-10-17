package com.cccts.kafkasend808.kafka;

import com.zjts.beidou.agreement.Message808;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class Protocol808Serializer implements Serializer {
    @Override
    public void configure(Map configs, boolean isKey) {
//        String propertyName = isKey ? "key.serializer.encoding" : "value.serializer.encoding";
//        Object encodingValue = configs.get(propertyName);
//        if (encodingValue == null)
//            encodingValue = configs.get("serializer.encoding");
//        if (encodingValue instanceof String)
//            encoding = (String) encodingValue;
    }

    @Override
    public byte[] serialize(String topic, Object data) {
        try {
            if (data instanceof Message808) {
                Message808 msg808 = (Message808) data;
                ByteBuf buf = buf = msg808.write();
                byte[] bs = ByteBufUtil.getBytes(buf);
                buf.release();
                return bs;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new SerializationException("Kafka，808协议序列化异常1" + e.getMessage());
        }
    }

    @Override
    public void close() {

    }
}
