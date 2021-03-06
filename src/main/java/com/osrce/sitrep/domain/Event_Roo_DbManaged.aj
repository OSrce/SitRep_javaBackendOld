// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.Event;
import com.osrce.sitrep.domain.Location;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect Event_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "location", referencedColumnName = "id")
    private Location Event.location;
    
    @Column(name = "group_id")
    @NotNull
    private Long Event.groupId;
    
    @Column(name = "has_location")
    @NotNull
    private boolean Event.hasLocation;
    
    @Column(name = "has_data")
    @NotNull
    private boolean Event.hasData;
    
    @Column(name = "has_begin")
    @NotNull
    private boolean Event.hasBegin;
    
    @Column(name = "has_end")
    @NotNull
    private boolean Event.hasEnd;
    
    @Column(name = "created", updatable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date Event.created = new Date();
    
    @Column(name = "data_begin")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date Event.dataBegin;
    
    @Column(name = "data_end")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date Event.dataEnd;
    
    public Location Event.getLocation() {
        return location;
    }
    
    public void Event.setLocation(Location location) {
        this.location = location;
    }
    
    public Long Event.getGroupId() {
        return groupId;
    }
    
    public void Event.setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    
    public boolean Event.isHasLocation() {
        return hasLocation;
    }
    
    public void Event.setHasLocation(boolean hasLocation) {
        this.hasLocation = hasLocation;
    }
    
    public boolean Event.isHasData() {
        return hasData;
    }
    
    public void Event.setHasData(boolean hasData) {
        this.hasData = hasData;
    }
    
    public boolean Event.isHasBegin() {
        return hasBegin;
    }
    
    public void Event.setHasBegin(boolean hasBegin) {
        this.hasBegin = hasBegin;
    }
    
    public boolean Event.isHasEnd() {
        return hasEnd;
    }
    
    public void Event.setHasEnd(boolean hasEnd) {
        this.hasEnd = hasEnd;
    }
    
    public Date Event.getCreated() {
        return created;
    }
    
    public void Event.setCreated(Date created) {
        this.created = created;
    }
    
    public Date Event.getDataBegin() {
        return dataBegin;
    }
    
    public void Event.setDataBegin(Date dataBegin) {
        this.dataBegin = dataBegin;
    }
    
    public Date Event.getDataEnd() {
        return dataEnd;
    }
    
    public void Event.setDataEnd(Date dataEnd) {
        this.dataEnd = dataEnd;
    }
    
}
