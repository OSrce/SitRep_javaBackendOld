package com.osrce.sitrep.domain;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "sr_acl_permissions", schema = "public")
@RooDbManaged(automaticallyDelete = true)
public class SrAclPermissions {

    @Column(name = "role_type", columnDefinition="Roletype")
    @NotNull
    private Roletype roleType;
	
    @Column(name = "permission_create", columnDefinition="Permittype")
    @NotNull
    private Permittype permissionCreate;
    
    @Column(name = "permission_read", columnDefinition="Permittype")
    @NotNull
    private Permittype permissionRead;
    
    @Column(name = "permission_update", columnDefinition="Permittype")
    @NotNull
    private Permittype permissionUpdate;
    
    @Column(name = "permission_delete", columnDefinition="Permittype")
    @NotNull
    private Permittype permissionDelete;
    
	
}
