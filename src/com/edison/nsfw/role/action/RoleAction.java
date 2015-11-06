package com.edison.nsfw.role.action;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import com.edison.core.action.BaseAction;
import com.edison.core.constant.Constant;
import com.edison.nsfw.role.entity.Role;
import com.edison.nsfw.role.entity.RolePrivilege;
import com.edison.nsfw.role.entity.RolePrivilegeId;
import com.edison.nsfw.role.service.RoleService;
import com.opensymphony.xwork2.ActionContext;

public class RoleAction extends BaseAction {
	
	@Resource
	private RoleService roleService;
	private List<Role> roleList;
	private Role role;
	private String[] privilegeIds;//获取checkbox的value，就是每个权限的code
	
	
	public String listUI() throws Exception{
		
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		try {
			roleList = roleService.findObjects();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return "listUI";
	}
	
	public String addUI(){
		
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		
		return "addUI";
	}
	
	public String add(){
		try {
			if(role != null){
				
				if(privilegeIds != null){
					HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
					for(int i = 0; i < privilegeIds.length; i++){
						set.add(new RolePrivilege(new RolePrivilegeId(role, privilegeIds[i])));
					}
					role.setRolePrivileges(set);
				}
				roleService.save(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	public String editUI(){
		//把所有的权限名字传递到编辑页面
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		if (role != null && role.getRoleId() != null) {
			role = roleService.findObjectById(role.getRoleId());
			
			if(role.getRolePrivileges() != null){
				privilegeIds = new String[role.getRolePrivileges().size()];
				int i = 0;
				for(RolePrivilege rp: role.getRolePrivileges()){
					privilegeIds[i++] = rp.getId().getCode();
				}
			}
		}
		return "editUI";
	}
	
	public String edit(){
		try {
			if(role != null){
				
				if(privilegeIds != null){
					HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
					for(int i = 0; i < privilegeIds.length; i++){
						set.add(new RolePrivilege(new RolePrivilegeId(role, privilegeIds[i])));
					}
					role.setRolePrivileges(set);
				}
				roleService.update(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	public String delete(){
		if(role != null && role.getRoleId() != null){
			roleService.delete(role.getRoleId());
		}
		return "list";
	}
	
	public String deleteSelected(){
		if(selectedRow != null){
			for(String id: selectedRow){
				roleService.delete(id);
			}
		}
		return "list";
	}
	
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String[] getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	
}
