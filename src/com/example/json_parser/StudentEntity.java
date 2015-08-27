package com.example.json_parser;

public class StudentEntity {
	private String name;
	private String className;
	private int age;

	public StudentEntity(String name, String className, int age) {
		super();
		this.name = name;
		this.className = className;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
	@Override
	public String toString() {
		return "Ãû×Ö£º"+name;
	}

	public StudentEntity() {

	}
}
