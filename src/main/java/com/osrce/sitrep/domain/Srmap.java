package com.osrce.sitrep.domain;

import com.vividsolutions.jts.geom.Geometry;
import flexjson.JSONSerializer;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.annotations.Type;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.format.annotation.DateTimeFormat;
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

    public static List<com.osrce.sitrep.domain.Srmap> findAllWithParams(Map<java.lang.String, java.lang.String[]> theParams) {
        HibernateEntityManager hem = entityManager().unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria theCriteria = session.createCriteria(Srmap.class);
        if (theParams.containsKey("groupId")) {
            theCriteria.add(Restrictions.eq("groupId", Long.valueOf(theParams.get("groupId")[0])));
        }
        return theCriteria.list();
    }
}
