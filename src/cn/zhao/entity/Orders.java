package cn.zhao.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Orders {
	private int id;
	private int userId;
	private String status;
	private Date date;
	private double price;
	private String receiver;
	private Map<Book,Integer> map;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public Map<Book, Integer> getMap() {
		return map;
	}
	public void setMap(Map<Book, Integer> map) {
		this.map = map;
	}
	
}
