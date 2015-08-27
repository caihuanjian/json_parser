package com.example.json_parser;

import java.io.Serializable;

/**����ר����ʵ���࣬�ڽ���xml����ʱ������װ����*/
public class CDEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1692431541356358640L;
	/**ר������*/
	private String title;
	/**ר������*/
	private String artist;
	/**ר������*/
	private String country;
	/**��Ƭ��˾*/
	private String company;
	/**�۸�*/
	private float price;
	/**���*/
	private int year;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public CDEntity() {
		super();
	}
	public CDEntity(String title, String artist, String country,
			String company, float price, int year) {
		super();
		this.title = title;
		this.artist = artist;
		this.country = country;
		this.company = company;
		this.price = price;
		this.year = year;
	}
	@Override
	public String toString() {
		return "CD���֣�"+title;
	}
	
	
}
