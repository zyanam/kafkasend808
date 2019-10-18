package com.cccts.kafkasend808;

import com.alibaba.fastjson.JSON;
import com.cccts.kafkasend808.kafka.ChangeNoticeService;
import com.zjts.commonmessagemodels.changenotice.ChanageNoticeHelper;
import com.zjts.commonmessagemodels.changenotice.InspectRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Kafkasend808ApplicationTests {

    @Autowired
    Util util;


    @Test
    public void contextLoads() {
    }

//    @Test
//    public void test() {
//        String key = getVehicleKey("冀R12345", (short) 2);
//        String vehicle = getVehicleNO(key);
//        System.out.println(key);
//        System.out.println(vehicle);
//    }

//    @Autowired
//    private ChangeNoticeService changeNoticeService;

    @Test
    public void testChangeNoticeTopic() {
        InspectRequest inspectRequest = new InspectRequest();
        inspectRequest.setObjectType((byte) 0x01);
        inspectRequest.setObjectID("test");
        inspectRequest.setInfoID(1234);
        inspectRequest.setInfoContent("中国首都是?");

        String json = JSON.toJSONString(inspectRequest);
        Byte b = ChanageNoticeHelper.getKeyByClass(InspectRequest.class);
    }


    @Autowired
    private ChangeNoticeService changeNoticeService;

    @Test
    public void testKafkaProperties() {
        //changeNoticeService.publish("kkkk", "vvvv");
        InspectRequest inspectRequest = new InspectRequest();
        inspectRequest.setObjectType((byte) 0x01);
        inspectRequest.setObjectID("test");
        inspectRequest.setInfoID(1234);
        inspectRequest.setInfoContent("中国首都是?");

        changeNoticeService.publish("key", "vvvv");
    }


    /**
     * 根据车牌号和车牌颜色生成可以作为redis的key的字符串
     *
     * @param vehicleNO
     * @param vehicleColor
     * @return
     */
    public String getVehicleKey(String vehicleNO, short vehicleColor) {
        String s = vehicleNO + "_" + Integer.valueOf(vehicleColor);
        byte[] bs = s.getBytes(Charset.forName("utf-8"));
        StringBuilder sb = new StringBuilder();
        for (byte b :
                bs) {
            sb.append(Integer.toHexString(b & 0xFF));
        }
        return sb.toString();
    }

    public String getVehicleNO(String sn) {
        int lenth = sn.length() / 2;
        byte[] bytes = new byte[lenth];
        for (int i = 0; i < lenth; i++) {
            bytes[i] = (byte) Integer.parseInt(sn.substring(i * 2, i * 2 + 2), 16);
        }
        return new String(bytes, Charset.forName("utf-8"));
    }
}
