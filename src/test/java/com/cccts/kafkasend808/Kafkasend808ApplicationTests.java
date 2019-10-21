package com.cccts.kafkasend808;

import com.cccts.kafkasend808.kafka.ChangeNoticeService;
import com.cccts.kafkasend808.kafka.InboundMsgLocationService;
import com.cccts.kafkasend808.kafka.InboundMsgService;
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


    @Test
    public void test0200() {
        int n = 5;
        String msg = "02 00 00 57 04 56 45 64 56 11 01 06 00 00 00 00 00 00 00 01 01 5A E0 E7 06 CA 13 3E 00 00 00 00 00 00 19 05 29 09 33 51 01 04 00 00 00 00 02 02 00 00 03 02 00 00 14 04 00 00 00 00 15 04 00 00 00 00 16 04 00 00 00 00 17 02 00 00 18 03 00 00 00 25 04 00 00 00 00 2B 04 00 00 00 00 30 01 00 31 01 00";
        byte[] bs = util.getBytesBySpace(msg);

        sendMsgLocation(n, bs);
    }

    /**
     * 报警、进区域
     */
    @Test
    public void test0200AlarmIn() {
        int n = 1;

        String msg = "02000039045645645611000a149420071efb78c301cb998e068251210322029e001e19101413560201040001e848020200640302029e110501000000011206010000000100";
        byte[] bs = util.getBytes(msg);

        sendMsgLocation(n, bs);
    }

    /**
     * 报警、出区域
     */
    @Test
    public void test0200AlarmOut() {
        int n = 1;

        String msg = "02000039045645645611000e149420071efb78c301cb998e068251210322029e001e19101413570201040001e848020200640302029e110501000000011206010000000101";
        byte[] bs = util.getBytes(msg);

        sendMsgLocation(n, bs);
    }

    @Test
    public void test0701() {
        int n = 1;

        String msg = "0701001D04564564561100E600000019B5E7D7D3D4CBB5A5CAFDBEDDA3BABFAACDF9CCECBDF2313233";
        byte[] bs = util.getBytes(msg);

        sendMsg(n, bs);
    }

    /**
     * 2011版
     */
    @Test
    public void test0702() {
        int n = 1;

        //2011版
        String msg = "0702004804564564561100E904D5C5C1C131303100000000000000000000000000000000004131000000000000000000000000000000000000000000000000000000000000000000000000000006BDBBCDA8B2BF";
        byte[] bs = util.getBytes(msg);

        sendMsg(n, bs);
    }

    /**
     * 2013版
     */
    @Test
    public void test0102() {
        int n = 1;

        String msg = "01 02 00 06 04 56 45 64 56 11 00 01 61 62 63 61 62 63";
        byte[] bs = util.getBytesBySpace(msg);

        sendMsg(n, bs);
    }

    @Autowired
    private ChangeNoticeService changeNoticeService;

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
//        InspectRequest inspectRequest = new InspectRequest();
//        inspectRequest.setObjectType((byte) 0x01);
//        inspectRequest.setObjectID("test");
//        inspectRequest.setInfoID(1234);
//        inspectRequest.setInfoContent("中国首都是?");
//
//        String json = JSON.toJSONString(inspectRequest);
//        Byte b = ChanageNoticeHelper.getKeyByClass(InspectRequest.class);
//        changeNoticeService.publish(b, json);
    }

    @Autowired
    private InboundMsgLocationService inboundMsgLocationService;

    private void sendMsgLocation(int n, byte[] bs) {
        for (int i = 0; i < n; i++) {
            inboundMsgLocationService.publish(bs);
        }
    }

    @Autowired
    private InboundMsgService inboundMsgService;

    private void sendMsg(int n, byte[] bs) {
        for (int i = 0; i < n; i++) {
            inboundMsgService.publish(bs);
        }
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
