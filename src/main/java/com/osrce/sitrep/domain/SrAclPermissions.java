package com.osrce.sitrep.domain;

import javax.persistence.Column;
import javax.persistence.Id;
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

    @Id
    @Column(name = "permission_id")
    private Integer permissionId;

    @Column(name = "role_type", columnDefinition = "Roletype")
    private Roletype roleType;

    @Column(name = "permission_create", columnDefinition = "Permittype")
    private Permittype permissionCreate;

    @Column(name = "permission_read", columnDefinition = "Permittype")
    private Permittype permissionRead;

    @Column(name = "permission_update", columnDefinition = "Permittype")
    private Permittype permissionUpdate;

    @Column(name = "permission_delete", columnDefinition = "Permittype")
    private Permittype permissionDelete;
}
