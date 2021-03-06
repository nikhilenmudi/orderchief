package com.orderchief.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class UserVendor {
     
    @Id
    @GeneratedValue
    private Integer id;
     
    private String login;
     
    private String password;
    
    private int vendorId;
     
//    @OneToOne(cascade=CascadeType.ALL)
//    @JoinTable(name="USER_ROLES",
//        joinColumns = 
//        @JoinColumn(name="USER_ID", referencedColumnName="ID"),
//        inverseJoinColumns=
//          @JoinColumn(name="ROLE_ID", referencedColumnName="ID")
//    )
//    private Role role;
 
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getLogin() {
        return login;
    }
 
    public void setLogin(String login) {
        this.login = login;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
 
//    public Role getRole() {
//        return role;
//    }
// 
//    public void setRole(Role role) {
//        this.role = role;
//    }   
// 
}