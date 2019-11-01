package com.cccts.kafkasend808.hbase;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.ByteBuffer;

@Component
public class CustomizeHbaseUtil {

    @Autowired
    private CustomizeHbaseConfiguration customizeHbaseConfiguration;

    private Connection connection;

    public ResultScanner getRows(String tableName, byte[] startRow, byte[] stopRow) {
        if (connection == null) {
            connection = customizeHbaseConfiguration.getConnection();
        }

        ResultScanner scanner = null;
        Table table = null;
        try {
            Scan scan = new Scan();
            scan.withStartRow(startRow);
            scan.withStopRow(stopRow);
            table = connection.getTable(TableName.valueOf(tableName));
            scanner = table.getScanner(scan);
        } catch (Exception e) {
            System.out.println("CustomizeHbaseUtil.getRows");
        } finally {
            if (table != null) {
                try {
                    table.close();
                } catch (IOException e) {
                    System.out.println("CustomizeHbaseUtil.getRows");
                }
            }
        }
        return scanner;
    }

    public byte[] getRowKeyBytes(String simID, int time) {
        StringBuilder sb = new StringBuilder(simID);
        sb.reverse();

        ByteBuffer buf = ByteBuffer.allocate(10);
        buf.putShort(Short.valueOf(sb.substring(0, 4)));
        buf.putInt(Integer.valueOf(sb.substring(4, 12)));
        buf.putInt(time);

        return buf.array();
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (IOException e) {
            System.out.println("CustomizeHbaseUtil.test");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("CustomizeHbaseUtil.test");
        }
    }

}
