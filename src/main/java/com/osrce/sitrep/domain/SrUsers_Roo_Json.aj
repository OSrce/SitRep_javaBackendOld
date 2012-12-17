// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.SrUsers;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect SrUsers_Roo_Json {
    
    public String SrUsers.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static SrUsers SrUsers.fromJsonToSrUsers(String json) {
        return new JSONDeserializer<SrUsers>().use(null, SrUsers.class).deserialize(json);
    }
    
    public static String SrUsers.toJsonArray(Collection<SrUsers> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<SrUsers> SrUsers.fromJsonArrayToSrUserses(String json) {
        return new JSONDeserializer<List<SrUsers>>().use(null, ArrayList.class).use("values", SrUsers.class).deserialize(json);
    }
    
}