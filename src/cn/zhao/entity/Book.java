package cn.zhao.entity;

public class Book {
	   private int id;
	   private String name;
	   private String des;
	   private double price;
	   private int sum;
	   private String imgUrl;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	   @Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.id;
	}
	   
	   @Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		   Book b = (Book) obj ;
		return this.id==b.getId();
	}
}
