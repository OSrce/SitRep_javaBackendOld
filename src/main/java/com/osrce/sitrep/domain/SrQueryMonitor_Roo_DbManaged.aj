// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.SrQueries;
import com.osrce.sitrep.domain.SrQueryMonitor;
import com.osrce.sitrep.domain.SrQueryMonitorResults;
import java.util.Set;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

privileged aspect SrQueryMonitor_Roo_DbManaged {
    
    @OneToMany(mappedBy = "monitorid")
    private Set<SrQueryMonitorResults> SrQueryMonitor.srQueryMonitorResultss;
    
    @ManyToOne
    @JoinColumn(name = "queryid", referencedColumnName = "id", nullable = false)
    private SrQueries SrQueryMonitor.queryid;
    
    public Set<SrQueryMonitorResults> SrQueryMonitor.getSrQueryMonitorResultss() {
        return srQueryMonitorResultss;
    }
    
    public void SrQueryMonitor.setSrQueryMonitorResultss(Set<SrQueryMonitorResults> srQueryMonitorResultss) {
        this.srQueryMonitorResultss = srQueryMonitorResultss;
    }
    
    public SrQueries SrQueryMonitor.getQueryid() {
        return queryid;
    }
    
    public void SrQueryMonitor.setQueryid(SrQueries queryid) {
        this.queryid = queryid;
    }
    
}
