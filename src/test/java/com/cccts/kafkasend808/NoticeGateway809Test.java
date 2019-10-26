package com.cccts.kafkasend808;

import com.cccts.kafkasend808.kafka.NoticeGateway809Service;
import com.zjts.noticemodels.InspectResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeGateway809Test {

    @Autowired
    private NoticeGateway809Service noticeGateway809Service;

    @Test
    public void response1() {
        //回复查岗1
        InspectResponse inspectResponse = new InspectResponse();
        inspectResponse.setObjectType((byte) 2);
        inspectResponse.setObjectID("001");
        inspectResponse.setInfoID(0);
        inspectResponse.setInfoContent("北京");
        inspectResponse.setSuperiorPlatformKey("p809_192.168.89.121-62000007");

        noticeGateway809Service.publish(null, inspectResponse);
    }

    @Test
    public void response2() {
        //回复查岗2
        InspectResponse inspectResponse = new InspectResponse();
        inspectResponse.setObjectType((byte) 1);
        inspectResponse.setObjectID("2011-01");
        inspectResponse.setInfoID(0);
        inspectResponse.setInfoContent("北京");
        inspectResponse.setSuperiorPlatformKey("p809_192.168.89.121-62000007");

        noticeGateway809Service.publish(null, inspectResponse);
    }
}
