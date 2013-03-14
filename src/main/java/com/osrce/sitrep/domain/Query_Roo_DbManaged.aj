// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.Query;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect Query_Roo_DbManaged {
    
    @Column(name = "name", length = 128)
    @NotNull
    private String Query.name;
    
    @Column(name = "notes")
    private String Query.notes;
    
    @Column(name = "data")
    private String Query.data;
    
    @Column(name = "created", updatable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date Query.created = new Date();
    
    @Column(name = "updated")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date Query.updated;
    
    public String Query.getName() {
        return name;
    }
    
    public void Query.setName(String name) {
        this.name = name;
    }
    
    public String Query.getNotes() {
        return notes;
    }
    
    public void Query.setNotes(String notes) {
        this.notes = notes;
    }
    
    public String Query.getData() {
        return data;
    }
    
    public void Query.setData(String data) {
        this.data = data;
    }
    
    public Date Query.getCreated() {
        return created;
    }
    
    public void Query.setCreated(Date created) {
        this.created = created;
    }
    
    public Date Query.getUpdated() {
        return updated;
    }
    
    public void Query.setUpdated(Date updated) {
        this.updated = updated;
    }
    
}