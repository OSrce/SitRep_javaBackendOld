// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.SrAclPermissions;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

privileged aspect SrAclPermissions_Roo_DbManaged {
    
    @Column(name = "role_type")
    @NotNull
    private String SrAclPermissions.roleType;
    
    @Column(name = "role_id")
    @NotNull
    private Integer SrAclPermissions.roleId;
    
    @Column(name = "resource_id")
    @NotNull
    private Integer SrAclPermissions.resourceId;
    
    @Column(name = "permission_create")
    @NotNull
    private String SrAclPermissions.permissionCreate;
    
    @Column(name = "permission_read")
    @NotNull
    private String SrAclPermissions.permissionRead;
    
    @Column(name = "permission_update")
    @NotNull
    private String SrAclPermissions.permissionUpdate;
    
    @Column(name = "permission_delete")
    @NotNull
    private String SrAclPermissions.permissionDelete;
    
    public String SrAclPermissions.getRoleType() {
        return roleType;
    }
    
    public void SrAclPermissions.setRoleType(String roleType) {
        this.roleType = roleType;
    }
    
    public Integer SrAclPermissions.getRoleId() {
        return roleId;
    }
    
    public void SrAclPermissions.setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    
    public Integer SrAclPermissions.getResourceId() {
        return resourceId;
    }
    
    public void SrAclPermissions.setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
    
    public String SrAclPermissions.getPermissionCreate() {
        return permissionCreate;
    }
    
    public void SrAclPermissions.setPermissionCreate(String permissionCreate) {
        this.permissionCreate = permissionCreate;
    }
    
    public String SrAclPermissions.getPermissionRead() {
        return permissionRead;
    }
    
    public void SrAclPermissions.setPermissionRead(String permissionRead) {
        this.permissionRead = permissionRead;
    }
    
    public String SrAclPermissions.getPermissionUpdate() {
        return permissionUpdate;
    }
    
    public void SrAclPermissions.setPermissionUpdate(String permissionUpdate) {
        this.permissionUpdate = permissionUpdate;
    }
    
    public String SrAclPermissions.getPermissionDelete() {
        return permissionDelete;
    }
    
    public void SrAclPermissions.setPermissionDelete(String permissionDelete) {
        this.permissionDelete = permissionDelete;
    }
    
}
