package com.cccts.kafkasend808;

import com.cccts.kafkasend808.hbase.CustomizeHbaseProperties;
import com.cccts.kafkasend808.hbase.HbaseUtil;
import com.zjts.beidou.agreement.up.X0200;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HbaseTest {

    @Autowired
    private CustomizeHbaseProperties hbaseProperties;

    @Test
    public void getData() throws Exception {
        HbaseUtil hbaseConfiguration = new HbaseUtil();
        byte[] startRow = hbaseConfiguration.getRowKeyBytes("018232684472", 1572393600L);
        byte[] endRow = hbaseConfiguration.getRowKeyBytes("018232684472", 1572429600L);
        ResultScanner scanner = hbaseConfiguration.getAllByStartRowAndEndRow("x0110", startRow, endRow);

        Iterator<Result> iterator = scanner.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Result result = iterator.next();
            X0200 s0200 = new X0200();

            byte[] alarmSign = resultValue(result, "body", "warnFlag");
            s0200.setAlarmSign(Bytes.toInt(alarmSign));

            byte[] status = resultValue(result, "body", "state");
            s0200.setStatus(Bytes.toInt(status));

            byte[] lat = resultValue(result, "body", "latitude");
            s0200.setLat(Bytes.toDouble(lat));

            byte[] lng = resultValue(result, "body", "longitude");
            s0200.setLon(Bytes.toDouble(lng));

            byte[] altitude = resultValue(result, "body", "height");
            s0200.setAltitude(Bytes.toShort(altitude));

            byte[] speed = resultValue(result, "body", "speed");
            s0200.setSpeed(Bytes.toShort(speed));

            byte[] direction = resultValue(result, "body", "direction");
            s0200.setDirection(Bytes.toShort(direction));

//            byte[] date = new byte[4];
//            System.arraycopy(result.getRow(), 6, date, 0, 4);
            byte[] bytes = Arrays.copyOfRange(result.getRow(), 6, 10);
            Date da = new Date(Bytes.toInt(bytes) * 1000);
            s0200.setDate(da);

//            byte[] timeBytes = Sub.sublen(row, 6, 10);
//            x0200.setTime(EncoderUtils.fourBytes2Int(timeBytes,0));
//            Date date = new Date();
//            date.setTime(buf.readUnsignedInt());
//            x0200.setDate(date);
//
//            byte[] warnFlagvalue = resultValue(result, "body", "warnFlag");
//            x0200.setAlarmSign(Bytes.toInt(warnFlagvalue) & 0xFFFFFFFFFFFFFFFFL);
//            x0200.setDirection(Bytes.toShort(resultValue(result, "body", "direction")) & 0xFFFF);
//            x0200.setLat(Bytes.toDouble(resultValue(result, "body", "latitude")));
//            x0200.setLon(Bytes.toDouble(resultValue(result, "body", "longitude")));
//            x0200.setMileage((Double.valueOf(setExtras(resultValue(result, "body", "extra")).get("mileage").toString())));
//            x0200.setSpeed(Bytes.toShort(resultValue(result, "body", "speed")) & 0xFFFF);
//
//            x0200List.add(x0200);
            i++;
        }
        scanner.close();

        System.out.println("HbaseTest.getData,count=" + i);
    }

    public byte[] resultValue(Result result, String columnFamily, String qualifier) {
        byte[] value = result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(qualifier));
        return value;
    }

    @Test
    public void testGetKey() {

        HbaseUtil hbaseConfiguration = new HbaseUtil();
        int times = 100000;

        long s1 = new Date().getTime();
        for (int i = 0; i < times; i++) {
            byte[] startRow = hbaseConfiguration.getRowKeyBytes("018232684472", 1569662626L);
            byte[] endRow = hbaseConfiguration.getRowKeyBytes("018232684472", 1572254626L);
        }

        long e1 = new Date().getTime();
        System.out.println("r1=" + (e1 - s1));

        long s2 = new Date().getTime();
        for (int i = 0; i < times; i++) {
            byte[] start = hbaseConfiguration.getRowKeyBytesFast("018232684472", 1569662626);
            byte[] end = hbaseConfiguration.getRowKeyBytesFast("018232684472", 1572254626);
        }

        long e2 = new Date().getTime();
        System.out.println("r2=" + (e2 - s2));
    }

    @Test
    public void test() {
        hbaseProperties.getZookeeper().getQuorum();
        String simID = "123456789012";

        int count = 1000000;
        long i1 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            StringBuilder sb = new StringBuilder(1);
            sb.append(simID);
            sb.reverse();
        }
        long i2 = System.currentTimeMillis();
        System.out.println("1=" + (i2 - i1));

        long i3 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            StringBuilder sb1 = new StringBuilder(simID);
            sb1.reverse();
        }
        long i4 = System.currentTimeMillis();
        System.out.println("2=" + (i4 - i3));
    }
}
