// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.SrStyleRules;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect SrStyleRules_Roo_Json {
    
    public String SrStyleRules.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static SrStyleRules SrStyleRules.fromJsonToSrStyleRules(String json) {
        return new JSONDeserializer<SrStyleRules>().use(null, SrStyleRules.class).deserialize(json);
    }
    
    public static String SrStyleRules.toJsonArray(Collection<SrStyleRules> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<SrStyleRules> SrStyleRules.fromJsonArrayToSrStyleRuleses(String json) {
        return new JSONDeserializer<List<SrStyleRules>>().use(null, ArrayList.class).use("values", SrStyleRules.class).deserialize(json);
    }
    
}