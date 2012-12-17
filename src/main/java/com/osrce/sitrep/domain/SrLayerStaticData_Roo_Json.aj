// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.SrLayerStaticData;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect SrLayerStaticData_Roo_Json {
    
    public String SrLayerStaticData.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static SrLayerStaticData SrLayerStaticData.fromJsonToSrLayerStaticData(String json) {
        return new JSONDeserializer<SrLayerStaticData>().use(null, SrLayerStaticData.class).deserialize(json);
    }
    
    public static Collection<SrLayerStaticData> SrLayerStaticData.fromJsonArrayToSrLayerStaticDatas(String json) {
        return new JSONDeserializer<List<SrLayerStaticData>>().use(null, ArrayList.class).use("values", SrLayerStaticData.class).deserialize(json);
    }
    
}