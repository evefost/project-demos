package org.bit.server;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.bit.zookeeper.BitZookeeperServer;
import org.bit.zookeeper.ServerStatus;
import org.bit.zookeeper.StatDto;

import com.alibaba.fastjson.JSON;

public class BitServer implements Watcher {

	BitZookeeperServer bitZook = new BitZookeeperServer();
	private ZooKeeper zook;

	void register(String serverName) throws Exception {
		zook = bitZook.getConnection(this);
		//如果主节点不存，则创建
		if(zook.exists(BitZookeeperServer.root,false) == null){
			zook.create(BitZookeeperServer.root,"root-node".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
		}
		//注册服务信息
		String node = zook.create(BitZookeeperServer.root+"/server" ,
				serverName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
				CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println("servers:" + serverName + "node:{}" + node);
	}

	public void invoke(String name) throws InterruptedException {
		System.out.println(name + "服务,netty socket提供order查询服务");

	}


	public void process(WatchedEvent event) {
		try {
			//zook.getChildren("/", true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		BitServer server = new BitServer();
		try {
			StatDto dto = new StatDto();
			int i = 0;
			dto.setIp(args[i++]);
			dto.setPort(args[i++]);
			dto.setName(args[i++]);
			dto.setNum(0);
			dto.setStatus(ServerStatus.wait);
			server.register(JSON.toJSONString(dto));
			server.invoke(dto.getName());
			Thread.sleep(Long.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
