// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.SrModules;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

privileged aspect SrModules_Roo_DbManaged {
    
    @Column(name = "name", length = 128)
    @NotNull
    private String SrModules.name;
    
    public String SrModules.getName() {
        return name;
    }
    
    public void SrModules.setName(String name) {
        this.name = name;
    }
    
}
