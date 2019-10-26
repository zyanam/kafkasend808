package com.cccts.kafkasend808;

import com.cccts.kafkasend808.kafka.OutboundMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Kafkasend808Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Kafkasend808Application.class, args);
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private OutboundMsgService outboundMsgService;


    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
//        String s = scanner.nextLine();

//        changeNoticeService.publish("k", "v");

////        String msg = "7E 02 00 00 57 04 56 45 64 56 11 01 06 00 00 00 00 00 00 00 01 01 5A E0 E7 06 CA 13 3E 00 00 00 00 00 00 19 05 29 09 33 51 01 04 00 00 00 00 02 02 00 00 03 02 00 00 14 04 00 00 00 00 15 04 00 00 00 00 16 04 00 00 00 00 17 02 00 00 18 03 00 00 00 25 04 00 00 00 00 2B 04 00 00 00 00 30 01 00 31 01 00 92 7E";
//        String msg = "02 00 00 57 04 56 45 64 56 11 01 06 00 00 00 00 00 00 00 01 01 5A E0 E7 06 CA 13 3E 00 00 00 00 00 00 19 05 29 09 33 51 01 04 00 00 00 00 02 02 00 00 03 02 00 00 14 04 00 00 00 00 15 04 00 00 00 00 16 04 00 00 00 00 17 02 00 00 18 03 00 00 00 25 04 00 00 00 00 2B 04 00 00 00 00 30 01 00 31 01 00";
//        msg = "01 02 00 06 04 56 45 64 56 11 00 01 61 62 63 61 62 63";
//        byte[] bs = getBytesByStr(msg);
//
//        Scanner scanner = new Scanner(System.in);
//
        if (applicationContext.containsBeanDefinition("testing")) {
            return;
        }
        while (true) {
            String s = scanner.next();
            switch (s) {
                case "8103":
                    String msg8103 = "81030013045645645612000b020000007004000000020000007404000000c8";
                    byte[] bs8103 = getBytes(msg8103);
                    outboundMsgService.publish(bs8103);
                    break;
                case "8104":
                    String msg8104 = "81040000045645645612000d";
                    byte[] bs8104 = getBytes(msg8104);
                    outboundMsgService.publish(bs8104);
                    break;
                case "8300":
                    String msg8300 = "8300000d06462062390300033dcec4b1becffbcfa2b2e2cad4";
                    byte[] bs8300 = getBytes(msg8300);
                    outboundMsgService.publish(bs8300);
                    break;

            }

        }
    }

    public byte[] getBytesBySpace(String str) {
        String[] strs = str.split(" ");
        return getBytes(strs);
    }

    public byte[] getBytes(String str) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i += 2) {
            String s = str.substring(i, i + 2);
            list.add(s);
        }
        return getBytes(list.toArray(new String[list.size()]));
    }

    private byte[] getBytes(String[] strs) {

        byte[] bs = new byte[strs.length];

        for (int i = 0; i < strs.length; i++) {
            bs[i] = (byte) Integer.parseInt(strs[i], 16);
        }
        return bs;
    }


}
