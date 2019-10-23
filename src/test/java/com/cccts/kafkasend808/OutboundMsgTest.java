package com.cccts.kafkasend808;

import com.cccts.kafkasend808.kafka.OutboundMsgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OutboundMsgTest {

    @Autowired
    private OutboundMsgService outboundMsgService;

    @Autowired
    private Util util;

    @Test
    public void test8001() {
        String msg = "8300000d06462062390300033dcec4b1becffbcfa2b2e2cad4";
        byte[] bs = util.getBytes(msg);
        outboundMsgService.publish(bs);
    }
}
