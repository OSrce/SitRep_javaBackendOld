// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.Srmap;
import flexjson.JSONDeserializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Srmap_Roo_Json {
    
    public static Srmap Srmap.fromJsonToSrmap(String json) {
        return new JSONDeserializer<Srmap>().use(null, Srmap.class).deserialize(json);
    }
    
    public static Collection<Srmap> Srmap.fromJsonArrayToSrmaps(String json) {
        return new JSONDeserializer<List<Srmap>>().use(null, ArrayList.class).use("values", Srmap.class).deserialize(json);
    }
    
}
