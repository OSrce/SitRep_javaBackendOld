// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.Hstore;
import com.osrce.sitrep.domain.Srmap;
import com.vividsolutions.jts.geom.Geometry;

privileged aspect Srmap_Roo_JavaBean {
    
    public Long Srmap.getId() {
        return this.id;
    }
    
    public void Srmap.setId(Long id) {
        this.id = id;
    }
    
    public Hstore Srmap.getData() {
        return this.data;
    }
    
    public void Srmap.setData(Hstore data) {
        this.data = data;
    }
    
    public Geometry Srmap.getGeometry() {
        return this.geometry;
    }
    
    public void Srmap.setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
    
}