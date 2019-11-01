package com.cccts.kafkasend808.hbase;

import com.zjts.beidou.agreement.up.X0200;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.function.Function;

/**
 * 定位数据的Dao层
 */
@Repository
public class LocationDao {

    @Autowired
    private CustomizeHbaseUtil customizeHbaseUtil;

    @Value("${hbase.location-table.name}")
    private String tableName;

    public void getLocation(String simID, int startTime, int endTime, Function<X0200, Void> callback) {
        byte[] startRow = customizeHbaseUtil.getRowKeyBytes(simID, startTime);
        byte[] stopRow = customizeHbaseUtil.getRowKeyBytes(simID, endTime);

        ResultScanner scanner = customizeHbaseUtil.getRows(this.tableName, startRow, stopRow);
        Iterator<Result> iterator = scanner.iterator();
        while (iterator.hasNext()) {
            Result result = iterator.next();
            X0200 s0200 = new X0200();

//            byte[] alarmSign = resultValue(result, "body", "warnFlag");
            byte[] family = Bytes.toBytes("body");
            byte[] alarmSign = result.getValue(family, Bytes.toBytes("warnFlag"));
            s0200.setAlarmSign(Bytes.toInt(alarmSign));

//            byte[] status = resultValue(result, "body", "state");
            byte[] status = result.getValue(family, Bytes.toBytes("state"));
            s0200.setStatus(Bytes.toInt(status));

//            byte[] lat = resultValue(result, "body", "latitude");
            byte[] lat = result.getValue(family, Bytes.toBytes("latitude"));
            s0200.setLat(Bytes.toDouble(lat));

//            byte[] lng = resultValue(result, "body", "longitude");
            byte[] lng = result.getValue(family, Bytes.toBytes("longitude"));
            s0200.setLon(Bytes.toDouble(lng));

//            byte[] altitude = resultValue(result, "body", "height");
            byte[] altitude = result.getValue(family, Bytes.toBytes("height"));
            s0200.setAltitude(Bytes.toShort(altitude));

//            byte[] speed = resultValue(result, "body", "speed");
            byte[] speed = result.getValue(family, Bytes.toBytes("speed"));
            s0200.setSpeed(Bytes.toShort(speed));

//            byte[] direction = resultValue(result, "body", "direction");
            byte[] direction = result.getValue(family, Bytes.toBytes("direction"));
            s0200.setDirection(Bytes.toShort(direction));

            byte[] bytes = Arrays.copyOfRange(result.getRow(), 6, 10);
            Date da = new Date(Bytes.toInt(bytes) * 1000);
            s0200.setDate(da);

            callback.apply(s0200);
        }
    }
}
