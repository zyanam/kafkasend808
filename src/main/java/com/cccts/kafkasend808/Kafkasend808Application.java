package com.cccts.kafkasend808;

import com.cccts.kafkasend808.kafka.ChangeNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Kafkasend808Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Kafkasend808Application.class, args);
    }

    @Autowired
    private ChangeNoticeService changeNoticeService;

    @Override
    public void run(String... args) throws Exception {
//        Scanner scanner = new Scanner(System.in);
//        String s = scanner.nextLine();

//        changeNoticeService.publish("k", "v");

////        String msg = "7E 02 00 00 57 04 56 45 64 56 11 01 06 00 00 00 00 00 00 00 01 01 5A E0 E7 06 CA 13 3E 00 00 00 00 00 00 19 05 29 09 33 51 01 04 00 00 00 00 02 02 00 00 03 02 00 00 14 04 00 00 00 00 15 04 00 00 00 00 16 04 00 00 00 00 17 02 00 00 18 03 00 00 00 25 04 00 00 00 00 2B 04 00 00 00 00 30 01 00 31 01 00 92 7E";
//        String msg = "02 00 00 57 04 56 45 64 56 11 01 06 00 00 00 00 00 00 00 01 01 5A E0 E7 06 CA 13 3E 00 00 00 00 00 00 19 05 29 09 33 51 01 04 00 00 00 00 02 02 00 00 03 02 00 00 14 04 00 00 00 00 15 04 00 00 00 00 16 04 00 00 00 00 17 02 00 00 18 03 00 00 00 25 04 00 00 00 00 2B 04 00 00 00 00 30 01 00 31 01 00";
//        msg = "01 02 00 06 04 56 45 64 56 11 00 01 61 62 63 61 62 63";
//        byte[] bs = getBytesByStr(msg);
//
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            String s = scanner.next();
//            try {
//                int n = Integer.valueOf(s);
//                for (int i = 0; i < n; i++) {
//                    inboundMsgLocationService.publish(bs);
//                    inboundMsgService.publish(bs);
//                }
//            } catch (Exception e) {
//                System.out.println("请输入数字");
//            }
//        }
    }


}
