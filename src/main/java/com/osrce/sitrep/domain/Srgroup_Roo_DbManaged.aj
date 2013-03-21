// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.Srgroup;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect Srgroup_Roo_DbManaged {
    
    @OneToMany(mappedBy = "parent")
    private Set<Srgroup> Srgroup.srgroups;
    
    @Column(name = "group_id")
    @NotNull
    private Long Srgroup.groupId;
    
    @Column(name = "name", length = 128)
    @NotNull
    private String Srgroup.name;
    
    @Column(name = "has_data")
    @NotNull
    private boolean Srgroup.hasData;
    
    @Column(name = "created", updatable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date Srgroup.created = new Date();
    
    public Set<Srgroup> Srgroup.getSrgroups() {
        return srgroups;
    }
    
    public void Srgroup.setSrgroups(Set<Srgroup> srgroups) {
        this.srgroups = srgroups;
    }
    
    public Long Srgroup.getGroupId() {
        return groupId;
    }
    
    public void Srgroup.setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    
    public String Srgroup.getName() {
        return name;
    }
    
    public void Srgroup.setName(String name) {
        this.name = name;
    }
    
    public boolean Srgroup.isHasData() {
        return hasData;
    }
    
    public void Srgroup.setHasData(boolean hasData) {
        this.hasData = hasData;
    }
    
    public Date Srgroup.getCreated() {
        return created;
    }
    
    public void Srgroup.setCreated(Date created) {
        this.created = created;
    }
    
}
