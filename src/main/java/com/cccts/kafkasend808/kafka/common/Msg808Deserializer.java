package com.cccts.kafkasend808.kafka.common;

import com.zjts.beidou.agreement.Message808;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class Msg808Deserializer implements Deserializer<Message808> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Message808 deserialize(String topic, byte[] bs) {
        ByteBuf buf = Unpooled.copiedBuffer(bs);
        try {
            Message808 msg808 = new Message808();
            msg808.read(buf);
            return msg808;
        } catch (Exception e) {
            System.out.println("Msg808Deserializer.deserialize,错误");
        } finally {
            buf.release();
        }
        return null;
    }

    @Override
    public void close() {

    }
}
