package com.edison.nsfw.role.entity;

import java.io.Serializable;
import java.util.Set;

import org.omg.CORBA.PRIVATE_MEMBER;

public class Role implements Serializable {
	
	private String roleId;
	private String name;
	private String state;
	private Set<RolePrivilege> rolePrivileges;
	
	
	public Role() {
	}
	public Role(String roleId, String name, String state,
			Set<RolePrivilege> rolePrivileges) {
		this.roleId = roleId;
		this.name = name;
		this.state = state;
		this.rolePrivileges = rolePrivileges;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Set<RolePrivilege> getRolePrivileges() {
		return rolePrivileges;
	}
	public void setRolePrivileges(Set<RolePrivilege> rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}
	
}
