package org.bit.client;

import java.util.List;
import java.util.Random;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;
import org.bit.zookeeper.ServerStatus;
import org.bit.zookeeper.BitZookeeperServer;
import org.bit.zookeeper.StatDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class BitClient implements Watcher {
	Logger logger = LoggerFactory.getLogger(BitClient.class);
	BitZookeeperServer bitZook = new BitZookeeperServer();
	private ZooKeeper zook;
	private String clientName;

	public StatDto useServer(List<String> data, int dom, int i)
			throws Exception {
		if (data.size() == i) {
			throw new Exception("没有可用的服务");
		}
		String node = data.get(dom);
		byte[] bytes = zook.getData(BitZookeeperServer.root + "/" + node,
				true, null);
		String datas = new String(bytes);
		StatDto dto = JSON.parseObject(datas, StatDto.class);
		if (ServerStatus.stop.equals(dto.getStatus())) {
			i++;
			useServer(data, dom, i);
		}
		dto.setNode(node);
		return dto;

	}

	public void subscirbe(String clientName) throws Exception {
		this.clientName = clientName;
		zook = bitZook.getConnection(this);
		List<String> data = zook.getChildren(BitZookeeperServer.root, true);
		if (data.isEmpty()) {
			System.out.println("没有服务可订阅");
			return;
		}
		int dom = new Random().nextInt(data.size());
		StatDto dto = useServer(data, dom, 0);
		dto.setNum(dto.getNum() + 1);
		dto.setStatus(ServerStatus.run);
		zook.setData(BitZookeeperServer.root + "/" + dto.getNode(), JSON
				.toJSONString(dto).getBytes(), -1);

	}

	public void call() throws InterruptedException {
		System.out.println("客户端开启,建立netty连接");
	}

	public void process(WatchedEvent event) {
		try {
			if (event.getType() == EventType.NodeChildrenChanged) {
				System.out.println("服务器发生改变,重新订阅");
				subscirbe(clientName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
/*
	public void resubscirbe(String clientName) throws KeeperException,
			InterruptedException {
		List<String> data = zook.getChildren(BitZookeeperServer.root, false);
		int num = 0;
		StatDto runDto = null;
		for (String node : data) {
			byte[] bytes = zook.getData(BitZookeeperServer.root + "/" + node,
					false, null);
			String datas = new String(bytes);
			StatDto dto = JSON.parseObject(datas, StatDto.class);
			if (ServerStatus.stop.equals(dto.getStatus())) {
				num = dto.getNum() + num;
			} else {
				runDto = dto;
				runDto.setNode(node);
			}

		}
		if (null != runDto) {
			runDto.setNum(runDto.getNum() + num);
			zook.setData(BitZookeeperServer.root + "/" + runDto.getNode(), JSON
					.toJSONString(runDto).getBytes(), -1);
		}

	}
*/
	public static void main(String[] args) {
		BitClient client = new BitClient();
		try {
			client.subscirbe("abc");
			client.call();
			Thread.sleep(Long.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
