package com.bit.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.bit.zookeeper.BitZookeeperServer;
import org.bit.zookeeper.ServerStatus;
import org.bit.zookeeper.StatDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/admin")
public class BitAdminController {

	BitZookeeperServer bitZook = new BitZookeeperServer();
	private ZooKeeper zook;

	@RequestMapping("/list")
	public String getList(HttpServletRequest request) throws Exception {
		zook = bitZook.getConnection(new Watcher() {
			public void process(WatchedEvent event) {
			}
		});
		List<String> data = zook.getChildren(BitZookeeperServer.root, true);
		List<StatDto> serverList = new ArrayList<StatDto>();
		for (String server : data) {
			byte[] bytes = zook.getData(BitZookeeperServer.root + "/" + server,
					false, null);
			String datas = new String(bytes);
			StatDto dto = JSON.parseObject(datas, StatDto.class);
			serverList.add(dto);
		}
		request.setAttribute("serverList", serverList);
		return "admin";

	}

	@RequestMapping("/stop")
	public String stop(HttpServletRequest request) throws Exception {
		zook = bitZook.getConnection(new Watcher() {
			public void process(WatchedEvent event) {
				
			}
		});
		String server = request.getParameter("server");
		byte[] bytes = zook.getData(BitZookeeperServer.root + "/" + server,
				false, null);
		String datas = new String(bytes);
		StatDto dto = JSON.parseObject(datas, StatDto.class);
		dto.setStatus(ServerStatus.stop);
		zook.setData(BitZookeeperServer.root + "/" + server,
				JSON.toJSONString(dto).getBytes(), -1);
		
		return "redirect:/admin/list";
	}
	
	@RequestMapping("/run")
	public String run(HttpServletRequest request) throws Exception {
		zook = bitZook.getConnection(new Watcher() {
			public void process(WatchedEvent event) {
				
			}
		});
		String server = request.getParameter("server");
		byte[] bytes = zook.getData(BitZookeeperServer.root + "/" + server,
				false, null);
		String datas = new String(bytes);
		StatDto dto = JSON.parseObject(datas, StatDto.class);
		dto.setStatus(ServerStatus.run);
		zook.setData(BitZookeeperServer.root + "/" + server,
				JSON.toJSONString(dto).getBytes(), -1);
		
		return "redirect:/admin/list";
	}
	
	

	@RequestMapping("/delete")
	public String delete(HttpServletRequest request) throws Exception {
		zook = bitZook.getConnection(new Watcher() {
			public void process(WatchedEvent event) {
				
			}
		});
		String server = request.getParameter("server");
		byte[] bytes = zook.getData(BitZookeeperServer.root + "/" + server,
				false, null);
		String datas = new String(bytes);
		StatDto dto = JSON.parseObject(datas, StatDto.class);
		dto.setStatus(ServerStatus.stop);
		zook.delete(BitZookeeperServer.root + "/" + server, -1);
		
		
		return "redirect:/admin/list";
	}

}
