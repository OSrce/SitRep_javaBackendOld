package com.osrce.sitrep.domain;

import com.vividsolutions.jts.geom.Geometry;
import flexjson.JSONSerializer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TypedQuery;
import org.hibernate.annotations.Type;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "srmap", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooJson
public class Srmap {

    @Id
    @Column(name = "id", columnDefinition = "bigserial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "hstore")
    @Column(name = "data", columnDefinition = "hstore")
    private Hstore data = new Hstore();

    @Type(type = "org.hibernate.spatial.GeometryType")
    @Column(name = "geometry", nullable = true, columnDefinition = "Geometry")
    private Geometry geometry;

    public String toJson() {
        System.out.println("TEST=== Srmap toJson called!");
        return new JSONSerializer().transform(new GeometryTransformer(), Geometry.class).serialize(this);
    }

    public static String toJsonArray(Collection<com.osrce.sitrep.domain.Srmap> collection) {
        System.out.println("TEST=== Srmap toJsonArray called!");
        return new JSONSerializer().transform(new GeometryTransformer(), Geometry.class).serialize(collection);
    }

    public static List<com.osrce.sitrep.domain.Srmap> findAllWithGroupId(Long theGroupId) {
        System.out.println("TEST=== Srmap findAllWithGroupId called!");
        TypedQuery<Srmap> theQuery = entityManager().createQuery("SELECT o FROM Srmap o where group_id=:theGroupId", Srmap.class);
        theQuery.setParameter("theGroupId", theGroupId);
        return theQuery.getResultList();
    }
}
