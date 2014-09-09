package com.orderchief.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role {
     
    @Id
    @GeneratedValue
    private Integer id;
     
    private String role;
     
    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="USER_ROLES",
        		joinColumns = 
                @JoinColumn(name="ROLE_ID", referencedColumnName="ID"),
                inverseJoinColumns=
                  @JoinColumn(name="USER_ID", referencedColumnName="ID")
    )
    private Set<UserVendor> userRoles;
 
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getRole() {
        return role;
    }
 
    public void setRole(String role) {
        this.role = role;
    }

	public Set<UserVendor> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserVendor> userRoles) {
		this.userRoles = userRoles;
	}
 
       
}
