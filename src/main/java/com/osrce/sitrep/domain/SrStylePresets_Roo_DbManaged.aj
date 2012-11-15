// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.SrStylePresets;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect SrStylePresets_Roo_DbManaged {
    
    @Column(name = "name", length = 128)
    @NotNull
    private String SrStylePresets.name;
    
    @Column(name = "style_id")
    @NotNull
    private Integer SrStylePresets.styleId;
    
    @Column(name = "layer_id")
    @NotNull
    private Integer SrStylePresets.layerId;
    
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date SrStylePresets.createdOn;
    
    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date SrStylePresets.updatedOn;
    
    @Column(name = "created_by")
    private Integer SrStylePresets.createdBy;
    
    @Column(name = "updated_by")
    private Integer SrStylePresets.updatedBy;
    
    public String SrStylePresets.getName() {
        return name;
    }
    
    public void SrStylePresets.setName(String name) {
        this.name = name;
    }
    
    public Integer SrStylePresets.getStyleId() {
        return styleId;
    }
    
    public void SrStylePresets.setStyleId(Integer styleId) {
        this.styleId = styleId;
    }
    
    public Integer SrStylePresets.getLayerId() {
        return layerId;
    }
    
    public void SrStylePresets.setLayerId(Integer layerId) {
        this.layerId = layerId;
    }
    
    public Date SrStylePresets.getCreatedOn() {
        return createdOn;
    }
    
    public void SrStylePresets.setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
    
    public Date SrStylePresets.getUpdatedOn() {
        return updatedOn;
    }
    
    public void SrStylePresets.setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
    
    public Integer SrStylePresets.getCreatedBy() {
        return createdBy;
    }
    
    public void SrStylePresets.setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }
    
    public Integer SrStylePresets.getUpdatedBy() {
        return updatedBy;
    }
    
    public void SrStylePresets.setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }
    
}
