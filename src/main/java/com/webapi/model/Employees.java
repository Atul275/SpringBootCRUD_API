package com.webapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employees")
public class Employees {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private long salary;
	private int age;
	private String email;
	private String address;
	private String mobile;
	private String gender;
	
	public Employees() {
		super();
	}
	
	public Employees(String name, long salary, int age, String moblie, String email, String address, String gender) {
		super();
		this.name = name;
		this.salary = salary;
		this.age = age;
		this.mobile= moblie;
		this.email= email;
		this.address= address;
		this.gender= gender;
	}

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
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return "Employees [id=" + id + ", name=" + name + ", salary=" + salary + ", age=" + age + ", email=" + email
				+ ", address=" + address + ", mobile=" + mobile + ", gender=" + gender + "]";
	}
}
