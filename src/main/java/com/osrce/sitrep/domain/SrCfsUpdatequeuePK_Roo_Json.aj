// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.SrCfsUpdatequeuePK;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect SrCfsUpdatequeuePK_Roo_Json {
    
    public String SrCfsUpdatequeuePK.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static SrCfsUpdatequeuePK SrCfsUpdatequeuePK.fromJsonToSrCfsUpdatequeuePK(String json) {
        return new JSONDeserializer<SrCfsUpdatequeuePK>().use(null, SrCfsUpdatequeuePK.class).deserialize(json);
    }
    
    public static String SrCfsUpdatequeuePK.toJsonArray(Collection<SrCfsUpdatequeuePK> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<SrCfsUpdatequeuePK> SrCfsUpdatequeuePK.fromJsonArrayToSrCfsUpdatequeuePKs(String json) {
        return new JSONDeserializer<List<SrCfsUpdatequeuePK>>().use(null, ArrayList.class).use("values", SrCfsUpdatequeuePK.class).deserialize(json);
    }
    
}
