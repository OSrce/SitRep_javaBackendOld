// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.Location;
import javax.persistence.Entity;
import javax.persistence.Table;

privileged aspect Location_Roo_Jpa_Entity {
    
    declare @type: Location: @Entity;
    
    declare @type: Location: @Table(schema = "public", name = "location");
    
}
