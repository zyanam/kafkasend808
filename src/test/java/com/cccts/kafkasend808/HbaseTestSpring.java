package com.cccts.kafkasend808;


import com.cccts.kafkasend808.hbase.LocationDao;
import com.zjts.beidou.agreement.up.X0200;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HbaseTestSpring {
    @Autowired
    private LocationDao locationDao;

    @Test
    public void test() {
        locationDao.getLocation("018232684472", 1569662626, 1572254626, (s0200) -> pro(s0200));
        locationDao.getLocation("018232684472", 1569662626, 1572254626, (s0200) -> pro(s0200));
        locationDao.getLocation("018232684472", 1569662626, 1572254626, (s0200) -> pro(s0200));
        locationDao.getLocation("018232684472", 1569662626, 1572254626, (s0200) -> pro(s0200));
    }

    private Void pro(X0200 s0200) {
        System.out.println("转发：" + s0200);
        return null;
    }
}

