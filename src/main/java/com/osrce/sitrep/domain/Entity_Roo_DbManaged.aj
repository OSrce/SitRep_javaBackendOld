// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.Entity;
import com.osrce.sitrep.domain.EntityStatus;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect Entity_Roo_DbManaged {
    
    @OneToMany(mappedBy = "entity")
    private Set<EntityStatus> Entity.entityStatuses;
    
    @Column(name = "group_id")
    @NotNull
    private Long Entity.groupId;
    
    @Column(name = "name", length = 128)
    @NotNull
    private String Entity.name;
    
    @Column(name = "has_data")
    @NotNull
    private boolean Entity.hasData;
    
    @Column(name = "created", updatable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date Entity.created = new Date();
    
    @Column(name = "updated")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date Entity.updated;
    
    public Set<EntityStatus> Entity.getEntityStatuses() {
        return entityStatuses;
    }
    
    public void Entity.setEntityStatuses(Set<EntityStatus> entityStatuses) {
        this.entityStatuses = entityStatuses;
    }
    
    public Long Entity.getGroupId() {
        return groupId;
    }
    
    public void Entity.setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    
    public String Entity.getName() {
        return name;
    }
    
    public void Entity.setName(String name) {
        this.name = name;
    }
    
    public boolean Entity.isHasData() {
        return hasData;
    }
    
    public void Entity.setHasData(boolean hasData) {
        this.hasData = hasData;
    }
    
    public Date Entity.getCreated() {
        return created;
    }
    
    public void Entity.setCreated(Date created) {
        this.created = created;
    }
    
    public Date Entity.getUpdated() {
        return updated;
    }
    
    public void Entity.setUpdated(Date updated) {
        this.updated = updated;
    }
    
}
