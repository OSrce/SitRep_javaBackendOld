// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.Entity;
import com.osrce.sitrep.domain.EntityStatus;
import com.osrce.sitrep.domain.SrLocations;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect EntityStatus_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "entity_id", referencedColumnName = "id", nullable = false)
    private Entity EntityStatus.entityId;
    
    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private SrLocations EntityStatus.locationId;
    
    @Column(name = "has_status")
    @NotNull
    private boolean EntityStatus.hasStatus;
    
    @Column(name = "has_data")
    @NotNull
    private boolean EntityStatus.hasData;
    
    @Column(name = "has_begin")
    @NotNull
    private boolean EntityStatus.hasBegin;
    
    @Column(name = "has_end")
    @NotNull
    private boolean EntityStatus.hasEnd;
    
    @Column(name = "status", length = 128)
    private String EntityStatus.status;
    
    @Column(name = "data")
    private String EntityStatus.data;
    
    @Column(name = "data_begin")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date EntityStatus.dataBegin;
    
    @Column(name = "data_end")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date EntityStatus.dataEnd;
    
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date EntityStatus.createdOn;
    
    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date EntityStatus.updatedOn;
    
    public Entity EntityStatus.getEntityId() {
        return entityId;
    }
    
    public void EntityStatus.setEntityId(Entity entityId) {
        this.entityId = entityId;
    }
    
    public SrLocations EntityStatus.getLocationId() {
        return locationId;
    }
    
    public void EntityStatus.setLocationId(SrLocations locationId) {
        this.locationId = locationId;
    }
    
    public boolean EntityStatus.isHasStatus() {
        return hasStatus;
    }
    
    public void EntityStatus.setHasStatus(boolean hasStatus) {
        this.hasStatus = hasStatus;
    }
    
    public boolean EntityStatus.isHasData() {
        return hasData;
    }
    
    public void EntityStatus.setHasData(boolean hasData) {
        this.hasData = hasData;
    }
    
    public boolean EntityStatus.isHasBegin() {
        return hasBegin;
    }
    
    public void EntityStatus.setHasBegin(boolean hasBegin) {
        this.hasBegin = hasBegin;
    }
    
    public boolean EntityStatus.isHasEnd() {
        return hasEnd;
    }
    
    public void EntityStatus.setHasEnd(boolean hasEnd) {
        this.hasEnd = hasEnd;
    }
    
    public String EntityStatus.getStatus() {
        return status;
    }
    
    public void EntityStatus.setStatus(String status) {
        this.status = status;
    }
    
    public String EntityStatus.getData() {
        return data;
    }
    
    public void EntityStatus.setData(String data) {
        this.data = data;
    }
    
    public Date EntityStatus.getDataBegin() {
        return dataBegin;
    }
    
    public void EntityStatus.setDataBegin(Date dataBegin) {
        this.dataBegin = dataBegin;
    }
    
    public Date EntityStatus.getDataEnd() {
        return dataEnd;
    }
    
    public void EntityStatus.setDataEnd(Date dataEnd) {
        this.dataEnd = dataEnd;
    }
    
    public Date EntityStatus.getCreatedOn() {
        return createdOn;
    }
    
    public void EntityStatus.setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
    
    public Date EntityStatus.getUpdatedOn() {
        return updatedOn;
    }
    
    public void EntityStatus.setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
    
}