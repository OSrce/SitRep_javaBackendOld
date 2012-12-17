// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.SrSession;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect SrSession_Roo_Json {
    
    public String SrSession.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static SrSession SrSession.fromJsonToSrSession(String json) {
        return new JSONDeserializer<SrSession>().use(null, SrSession.class).deserialize(json);
    }
    
    public static String SrSession.toJsonArray(Collection<SrSession> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<SrSession> SrSession.fromJsonArrayToSrSessions(String json) {
        return new JSONDeserializer<List<SrSession>>().use(null, ArrayList.class).use("values", SrSession.class).deserialize(json);
    }
    
}