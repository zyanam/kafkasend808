package com.cccts.kafkasend808.hbase;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "hbase")
public class CustomizeHbaseProperties {
    public String Master;
    public final Zookeeper Zookeeper = new Zookeeper();

    public static class Zookeeper {
        public String Quorum;
    }
}
