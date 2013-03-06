package com.osrce.sitrep.domain;

import com.vividsolutions.jts.geom.Geometry;
import flexjson.JSONSerializer;
import flexjson.transformer.StringTransformer;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.hibernate.Query;
import org.hibernate.annotations.Type;
import org.hibernate.spatial.GeometryType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "sr_layer_dynamic_data", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooJson
public class SrLayerDynamicData {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "layer_id")
    @NotNull
    private Integer layer_id;

    @Column(name = "feature_data")
    private String feature_data;

    @Column(name = "feature_start")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date feature_start;

    @Column(name = "feature_end")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date feature_end;

    @Type(type = "org.hibernate.spatial.GeometryType")
    @Column(name = "sr_geom", nullable = true, columnDefinition = "Geometry")
    private Geometry geometry;

    public static List<com.osrce.sitrep.domain.SrLayerDynamicData> findAllWithEndTime(Integer theLayerId, Date theFeatureEnd) {
        TypedQuery<SrLayerDynamicData> theQuery = entityManager().createQuery("SELECT o FROM SrLayerDynamicData o where layer_id=:theLayerId", SrLayerDynamicData.class);
        theQuery.setParameter("theLayerId", theLayerId);
        return theQuery.getResultList();
    }

    public static List<com.osrce.sitrep.domain.SrLayerDynamicData> findAllWithLayerId(Integer theLayerId) {
        TypedQuery<SrLayerDynamicData> theQuery = entityManager().createQuery("SELECT o FROM SrLayerDynamicData o where layer_id=:theLayerId", SrLayerDynamicData.class);
        theQuery.setParameter("theLayerId", theLayerId);
        return theQuery.getResultList();
    }

    public static String toJsonArray(Collection<com.osrce.sitrep.domain.SrLayerDynamicData> collection) {
        return new JSONSerializer().transform(new GeometryTransformer(), "geometry").exclude("*.class").serialize(collection);
    }
}
