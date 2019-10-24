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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
        int n = 1;
        String msg = "02 00 00 57 04 56 45 64 56 11 01 06 00 00 00 00 00 00 00 01 01 5A E0 E7 06 CA 13 3E 00 00 00 00 00 00 19 10 22 17 33 51 01 04 00 00 00 00 02 02 00 00 03 02 00 00 14 04 00 00 00 00 15 04 00 00 00 00 16 04 00 00 00 00 17 02 00 00 18 03 00 00 00 25 04 00 00 00 00 2B 04 00 00 00 00 30 01 00 31 01 00";
        byte[] bs = util.getBytesBySpace(msg);

        sendMsgLocation(n, bs);
    }

    @Test
    public void test0704() {
        int n = 1;
        //解析错误的数据
        String msg = "07 04 01 D3 01 33 03 13 56 01 01 E9 00 05 01 00 51 00 00 00 00 00 0C 02 03 02 5B F5 D7 06 F4 EF 59 00 09 01 39 00 55 19 10 22 08 08 14 01 04 00 00 0D 3B 02 02 00 00 03 02 01 18 30 01 09 31 01 15 25 04 00 00 00 00 14 04 00 00 00 01 15 04 00 00 00 C0 16 04 00 00 00 00 17 02 00 00 18 03 00 00 00 00 51 00 00 00 00 00 0C 02 03 02 5B F6 E7 06 F4 F6 BA 00 08 01 E2 00 51 19 10 22 08 08 29 01 04 00 00 0D 3D 02 02 00 00 03 02 01 CC 30 01 09 31 01 16 25 04 00 00 00 00 14 04 00 00 00 01 15 04 00 00 00 C0 16 04 00 00 00 00 17 02 00 00 18 03 00 00 00 00 82 00 00 00 00 00 0C 02 03 02 5B F7 F9 06 F4 FE 60 00 07 02 5D 00 4E 19 10 22 08 08 39 01 04 00 00 0D 3F 02 02 00 00 03 02 02 58 30 01 09 31 01 16 25 04 00 00 00 00 14 04 00 00 00 01 15 04 00 00 00 C0 16 04 00 00 00 00 17 02 00 00 18 03 00 00 00 64 2F 00 00 00 14 00 02 02 00 00 02 00 00 3C 00 07 02 5B F7 DC 06 F4 FD 9F 19 10 22 08 08 38 04 01 31 31 32 30 35 31 33 19 10 22 08 08 38 00 05 00 00 51 00 00 00 00 00 0C 02 03 02 5B F9 7D 02 06 F5 07 AC 00 08 01 1E 00 4A 19 10 22 08 08 54 01 04 00 00 0D 41 02 02 00 00 03 02 01 54 30 01 09 31 01 16 25 04 00 00 00 00 14 04 00 00 00 01 15 04 00 00 00 C0 16 04 00 00 00 00 17 02 00 00 18 03 00 00 00 00 51 00 00 00 00 00 0C 02 03 02 5B F9 BE 06 F5 08 D1 00 07 00 00 00 3C 19 10 22 08 09 09 01 04 00 00 0D 41 02 02 00 00 03 02 00 00 30 01 09 31 01 16 25 04 00 00 00 10 14 04 00 00 00 01 15 04 00 00 00 C0 16 04 00 00 00 00 17 02 00 00 18 03 00 00 00";
        msg = "070400c50456456456110010000301003c0000002000000000000000000000000000000000000019102319564901040000004e03020000250400000000310100020200003001002b040001000100400000010000000002015ae0f306ca133e003300000000191023194105010400000000030200002504000000002a020002310107020200003001002b0400010002004000000100000000000000000000000000000000000000191023211903010400000000030200002504000000002a020002310107020200003001002b040001000059";
        byte[] bs = util.getBytes(msg);
        System.out.println("bs.length=" + bs.length);

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

    @Test
    public void testDateTime() {
        Date d1 = new Date();
        Date d2 = new Date(1571706767000L);

        long l = d2.getTime() - d1.getTime();
        int h = (int) (l / 1000 / 60 / 60);

        System.out.println();

        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(d1);
        gc.add(Calendar.DATE, 1);

        int year = gc.get(Calendar.YEAR);
        int month = gc.get(Calendar.MONTH);
        int date = gc.get(Calendar.DATE);


        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date, 0, 0, 0);
        Date d = calendar.getTime();

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
