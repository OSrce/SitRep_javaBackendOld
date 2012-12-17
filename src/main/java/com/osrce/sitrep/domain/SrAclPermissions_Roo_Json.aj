// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.SrAclPermissions;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect SrAclPermissions_Roo_Json {
    
    public String SrAclPermissions.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static SrAclPermissions SrAclPermissions.fromJsonToSrAclPermissions(String json) {
        return new JSONDeserializer<SrAclPermissions>().use(null, SrAclPermissions.class).deserialize(json);
    }
    
    public static String SrAclPermissions.toJsonArray(Collection<SrAclPermissions> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<SrAclPermissions> SrAclPermissions.fromJsonArrayToSrAclPermissionses(String json) {
        return new JSONDeserializer<List<SrAclPermissions>>().use(null, ArrayList.class).use("values", SrAclPermissions.class).deserialize(json);
    }
    
}