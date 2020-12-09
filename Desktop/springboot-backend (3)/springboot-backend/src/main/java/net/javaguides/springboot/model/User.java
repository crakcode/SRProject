package net.javaguides.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "accountid")
	private String accountid;

	@Column(name = "name")
	private String name;
	
	@Column(name = "password")
	private String password;
	

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}


	public User() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(String accountid, String name, String password) {
		super();
		this.accountid = accountid;
		this.name = name;
		this.password = password;
	}
}
