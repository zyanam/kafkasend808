package com.cccts.kafkasend808.hbase;


import lombok.Data;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Data
@Configuration
public class CustomizeHbaseConfiguration {

    @Autowired
    private CustomizeHbaseProperties hbaseProperties;


    @Bean
    public org.apache.hadoop.conf.Configuration configuration() {
        org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", hbaseProperties.Zookeeper.Quorum);
        configuration.set("hbase.master", hbaseProperties.Master);
        return configuration;
    }

    public Connection getConnection() {
        try {
            Connection connection = ConnectionFactory.createConnection(configuration());
            return connection;
        } catch (IOException ioe) {
            System.out.println("CustomizeHbaseConfiguration.getConnection");
        } catch (Exception e) {
            System.out.println("CustomizeHbaseConfiguration.getConnection");
        }
        return null;
    }
}
