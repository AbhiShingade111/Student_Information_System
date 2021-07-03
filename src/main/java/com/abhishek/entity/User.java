package com.abhishek.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "firstname")
	@NotBlank(message = "Required!!!!!!")
	private String firstname;

	@Column(name = "lastname")
	@NotBlank(message = "Required!!!!!!")
	private String lastname;

	@Column(name = "address")
	@NotBlank(message = "Required!!!!!!")
	private String address;

	@Column(name = "email")
	@NotBlank(message = "Must contain unique !!!!!!")
	private String email;

	@Column(name = "state")
	@NotBlank(message = "Required!!!!!!")
	private String state;

	@Column(name = "city")
	@NotBlank(message = "Required!!!!!!")
	private String city;

	@Column(name = "password")
	@NotBlank(message = "Required!!!!!!!")
	private String password;
	
	@Column(name = "role")
	@NotBlank(message = "Required field")
	private String role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", address=" + address
				+ ", email=" + email + ", state=" + state + ", city=" + city + ", password="
				+ password + ", role=" + role + "]";
	}
	
	

}
