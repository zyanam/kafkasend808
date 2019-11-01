package com.cccts.kafkasend808;

import com.beidou.beidou_redis.model.p809.Platform809JE;
import com.beidou.beidou_redis.model.p809.VehicleInfo809JE;
import com.cccts.kafkasend808.redis.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void edit809PlarformRedis() {

        Platform809JE platform809JE = new Platform809JE();
        platform809JE.setPlatformId(5);
        platform809JE.setPlatformName("测试上级平台");
        platform809JE.setPlatformIp("192.168.89.121");
        platform809JE.setPlatformPort(19100);
        platform809JE.setPlatformUserName(77777777);
        platform809JE.setPlatformPassWord("77777777");
        platform809JE.setLocalPort(0);
        platform809JE.setAccessCode((long) 62000007);
        platform809JE.setIsEncryption(1);
        platform809JE.setEncryptionKey(1234);
        platform809JE.setEncryptionM1(254632548);
        platform809JE.setEncryptionIa1(256325485);
        platform809JE.setEncryptionIc1(253654852);
        platform809JE.setVersionFlag("123");
        platform809JE.setProtocolType("51");
        platform809JE.setPlatformEnable(1);
        platform809JE.setUserPlatformId(1020);
        platform809JE.setUserPlatformIp("192.168.89.121");
        platform809JE.setPlatformCode("0");
        platform809JE.setSendRule(2);

        String k = "p809_" + platform809JE.getPlatformIp() + "-" + platform809JE.getAccessCode().toString();
        redisUtil.set(k, platform809JE);

        Platform809JE obj = (Platform809JE) redisUtil.get(k);
        System.out.println("Bd809GatewayApplication.run," + obj);

//        "{\"@type\":\"com.beidou.beidou_redis.model.p809.Platform809JE\"," +
//                "\"accessCode\":62000007," +
//                "\"encryptionIa1\":256325485," +
//                "\"encryptionIc1\":253654852," +
//                "\"encryptionKey\":1234," +
//                "\"encryptionM1\":254632548," +
//                "\"isEncryption\":1," +
//                "\"localPort\":123," +
//                "\"platformCode\":\"3\"," +
//                "\"platformEnable\":1," +
//                "\"platformId\":5," +
//                "\"platformIp\":\"192.168.89.121\"," +
//                "\"platformName\":\"809\xe7\xbd\x91\xe5\x85\xb3\xe6\xb5\x8b\xe8\xaf\x95\xe7\x94\xa8\xe4\xb8\x8d\xe8\xa6\x81\xe4\xbf\xae\xe6\x94\xb9\"," +
//                "\"platformPassWord\":\"77777777\"," +
//                "\"platformPort\":19100," +
//                "\"platformUserName\":77777777," +
//                "\"protocolType\":\"51\"," +
//                "\"userPlatformId\":1020," +
//                "\"userPlatformIp\":\"192.168.86.22\"}"

    }

    @Test
    public void editVehicleInfo() {
        VehicleInfo809JE vehicleInfo809JE = new VehicleInfo809JE();
        vehicleInfo809JE.setVehicleId(1);
        vehicleInfo809JE.setVehiclePlateNum("测A12345");
        vehicleInfo809JE.setVehiclePlateColor(2);
        vehicleInfo809JE.setManufacturerNo("a");
        vehicleInfo809JE.setTerminalDeviceNo("123456");
        vehicleInfo809JE.setDeviceModel("654321");
        vehicleInfo809JE.setUserPlatformId(1001);


        List<String> list = new ArrayList<>();
        list.add("p809_192.168.89.121-62000007");
        vehicleInfo809JE.setPlatform809Id(list);

        redisUtil.set("045645645611", vehicleInfo809JE);

        Object obj = redisUtil.get("045645645611");
        System.out.println(obj);
    }

    @Test
    public void setNfsPath() {
        redisUtil.set("nfs_path", "/mnt/nfs1");
    }
}
