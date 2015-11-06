package com.edison.nsfw.role.entity;

import java.io.Serializable;

public class RolePrivilegeId implements Serializable {
	private Role role;
	private String code;
	
	public RolePrivilegeId() {
	}
	public RolePrivilegeId(Role role, String code) {
		this.role = role;
		this.code = code;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
