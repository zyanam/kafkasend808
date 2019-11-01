package com.cccts.kafkasend808.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class HbaseUtil {



    public Connection getConnection() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "192.168.89.115:2181");
        Connection connection = ConnectionFactory.createConnection(configuration);
        return connection;
    }

    public byte[] getRowKeyBytes(String simeId, long time) {
        StringBuilder builder = new StringBuilder(simeId.substring(1).length());
        builder.append(simeId);
        builder.reverse();
        String pre_4 = builder.substring(0, 4);
        short pre_4_int = Short.valueOf(pre_4);
        byte[] pre_4_int_bytes = short2twoBytes(pre_4_int);
        String aft_8 = builder.substring(3);
        Integer aft_8_int = Integer.valueOf(aft_8);
        byte[] aft_8_int_bytes = int2fourBytes(aft_8_int);

        byte[] bytes = int2fourBytes((int) time);
        return concatAll(10, pre_4_int_bytes, aft_8_int_bytes, bytes);
    }


    public byte[] getRowKeyBytesFast(String simID, int time) {
//        StringBuilder sb = new StringBuilder(simID.length());
//        sb.append(simID);
//        sb.reverse();

        StringBuilder sb = new StringBuilder(simID);
        sb.reverse();

        ByteBuffer buf = ByteBuffer.allocate(10);
        buf.putShort(Short.valueOf(sb.substring(0, 4)));
        buf.putInt(Integer.valueOf(sb.substring(4, 12)));
        buf.putInt(time);

        return buf.array();
    }

    public byte[] concatAll(int length, byte[] first, byte[]... bytes) {

        int len = first.length;
        int byteslen = bytes.length;
        for (int i = 0; i < byteslen; i++) {
            len += bytes[i].length;
        }
        byte[] bytes1 = Arrays.copyOf(first, len);
        int index = first.length;
        for (byte[] aByte : bytes) {
            System.arraycopy(aByte, 0, bytes1, index, aByte.length);
            index += aByte.length;
        }
        return bytes1;
    }

    public byte[] short2twoBytes(short value) {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) ((value >> 8) & 0xFF);
        bytes[1] = (byte) value;
        return bytes;
    }

    public byte[] int2fourBytes(int value) {
        byte b1 = (byte) ((value >> 24) & 0xFF);
        byte b2 = (byte) ((value >> 16) & 0xFF);
        byte b3 = (byte) ((value >> 8) & 0xFF);
        byte b4 = (byte) (value & 0xFF);
        return new byte[]{b1, b2, b3, b4};
    }

    public ResultScanner getAllByStartRowAndEndRow(String tableName, byte[] startRow, byte[] endRow) throws IOException {
        Connection connection = getConnection();
        ResultScanner scanner = null;
        Table table = null;
        try {
            Scan scan = new Scan();
            scan.withStartRow(startRow);
            scan.withStopRow(endRow);
            table = connection.getTable(TableName.valueOf(tableName));
            scanner = table.getScanner(scan);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return scanner;
    }
}
