package org.bit.zookeeper;

public class StatDto {

	private String ip;
	private String name;
	private String port;
	private Integer num;
	private String status;
	private String node;
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

/*	@Override
	public String toString() {
		return "服务信息: [ip=" + ip + ", name=" + name + ", port=" + port
				+ ", num=" + num + "]";
	}*/

}
