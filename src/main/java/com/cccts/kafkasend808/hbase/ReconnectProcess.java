package com.cccts.kafkasend808.hbase;

/**
 * 重连处理(补发1203)
 */
public class ReconnectProcess {
    public void reSend() {
        //从Redis获取需要重发的开始时间
        //数据库中获取需要重发的SimID
        int start = 1569662626;
        int end = 1572254626;

        String[] simIDs = new String[]{"018232684472"};


    }
}
