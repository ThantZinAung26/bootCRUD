package com.crud.blog.boot2jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "employes")
public class Employee {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "first_name", nullable = false)
	private String firstname;
	@Column(name = "last_namem", nullable = false)
	private String lastname;
	@Column(name = "email_address", nullable = false)
	private String emailId;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String firstname, String lastname, String emailId) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailId = emailId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", emailId=" + emailId
				+ "]";
	}
	


}
