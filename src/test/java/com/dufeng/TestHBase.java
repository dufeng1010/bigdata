package com.dufeng;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import com.google.protobuf.ServiceException;

public class TestHBase {
    
    private static final String tableName = "yandufeng_test";
    
    public static void main(String[] args) throws ServiceException {
        //Configuration config = HBaseConfiguration.create();
        HBaseConfiguration config = new HBaseConfiguration();
        config.clear();
        config.set("hbase.zookeeper.quorum", "hadoop251,hadoop252,hadoop253,hadoop254,hadoop255,hadoop66,hadoop65,hadoop64,hadoop63,hadoop62");
        config.set("hbase.zookeeper.property.clientPort", "2181");
        config.set("zookeeper.znode.parent", "/hbase-unsecure");
        config.set("hbase.master", "hadoop66:16000");
        
        try {
            HBaseAdmin.checkHBaseAvailable(config);
            HBaseAdmin admin = new HBaseAdmin(config);
            if (admin.tableExists(tableName)) {
                System.out.println("table already exsits");
            } else {
                HTableDescriptor tableDesc = new HTableDescriptor(tableName);
                tableDesc.addFamily(new HColumnDescriptor("feng"));
                admin.createTable(tableDesc);
                
                System.out.println("create table ok");
                
            }
        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
