// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.Entity;
import flexjson.JSONDeserializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Entity_Roo_Json {
    
    public static Entity Entity.fromJsonToEntity(String json) {
        return new JSONDeserializer<Entity>().use(null, Entity.class).deserialize(json);
    }
    
    public static Collection<Entity> Entity.fromJsonArrayToEntitys(String json) {
        return new JSONDeserializer<List<Entity>>().use(null, ArrayList.class).use("values", Entity.class).deserialize(json);
    }
    
}
