package com.cccts.kafkasend808.kafka.common;

import com.zjts.beidou.agreement.Message808;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.apache.kafka.common.serialization.Deserializer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


public class Msg808Deserializer implements Deserializer<Message808> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }



    @Override
    public Message808 deserialize(String topic, byte[] bs) {
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(df.format(now) + "," +ByteBufUtil.hexDump(bs));
        ByteBuf buf = Unpooled.copiedBuffer(bs);

        try {

            buf.writerIndex(buf.writerIndex() - 1);

            Message808 msg808 = new Message808();
            msg808.read(buf);
            return msg808;
        } catch (Exception e) {
            System.out.println("Msg808Deserializer.deserialize,错误,e=");
        } finally {
            buf.release();
        }
        return null;
    }

    @Override
    public void close() {

    }
}
