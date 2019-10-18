package com.cccts.kafkasend808.kafka.common;

import com.zjts.beidou.agreement.Message808;
import io.netty.buffer.ByteBuf;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class Msg808Serializer implements Serializer<Message808> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Message808 msg808) {
        try {
            ByteBuf buf = msg808.write();
            byte[] bs = buf.array();
            buf.release();
            return bs;
        } catch (Exception e) {
            System.out.println("Msg808Serializer.serialize,错误");
        }
        return new byte[0];
    }

    @Override
    public void close() {

    }
}
