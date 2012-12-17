package com.osrce.sitrep.domain;

import java.util.Collection;
import java.util.List;

import com.vividsolutions.jts.geom.Geometry;

import flexjson.JSONSerializer;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.spatial.GeometryType;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "sr_layer_static_data", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooJson
public class SrLayerStaticData {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "layer_id")
    @NotNull
    private Integer layer_id;
    
    @Column(name = "feature_style")
    private Integer feature_style;
    
    @Column(name = "feature_data")
    private String feature_data;
    
    @Type(type = "org.hibernate.spatial.GeometryType")
    @Column(name = "sr_geom", nullable = true, columnDefinition = "GeometryType")
    private Geometry geometry;
    
    public static List<com.osrce.sitrep.domain.SrLayerStaticData> findAllWithLayerId(Integer theLayerId) {
        TypedQuery<SrLayerStaticData> theQuery = entityManager().createQuery("SELECT o FROM SrLayerStaticData o where layer_id=:theLayerId", SrLayerStaticData.class);
        theQuery.setParameter("theLayerId", theLayerId);
        return theQuery.getResultList();
    }

    public static String toJsonArray(Collection<com.osrce.sitrep.domain.SrLayerStaticData> collection) {
        return new JSONSerializer().transform(new GeometryTransformer(), "geometry").exclude("*.class").serialize(collection);
    }
    
    
}
