package org.bit.zookeeper;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class BitZookeeperServer {
	private ZooKeeper zk;
	public static String root = "/bit";
	private static final String host="172.16.165.150:2181";
	
	public ZooKeeper getConnection(Watcher watch) throws Exception{
		zk = new ZooKeeper(host, 5000, watch);
		return zk;
	}
	
	
}
