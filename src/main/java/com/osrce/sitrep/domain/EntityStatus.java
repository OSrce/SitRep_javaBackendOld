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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.annotations.Type;
import org.hibernate.criterion.Property;
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
@RooJpaActiveRecord(versionField = "", table = "entity_status", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooJson
public class EntityStatus {

    @Id
    @Column(name = "id", columnDefinition = "bigserial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "hstore")
    @Column(name = "data", columnDefinition = "hstore")
    private Hstore data = new Hstore();

    @Column(name = "updated", updatable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date updated;

    public String toJson() {
        System.out.println("TEST=== EntityStatus toJson called!");
        return new JSONSerializer().transform(new GeometryTransformer(), Geometry.class).serialize(this);
    }

    public static String toJsonArray(Collection<com.osrce.sitrep.domain.EntityStatus> collection) {
        Iterator<com.osrce.sitrep.domain.EntityStatus> iterator = collection.iterator();
        while (iterator.hasNext()) {
            EntityStatus theEnt = iterator.next();
        }
        return new JSONSerializer().transform(new GeometryTransformer(), Geometry.class).serialize(collection);
    }

    public static List<com.osrce.sitrep.domain.EntityStatus> findAllWithParams(Map<java.lang.String, java.lang.String[]> theParams) {
        HibernateEntityManager hem = entityManager().unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria theCriteria = session.createCriteria(EntityStatus.class);
        theCriteria.createAlias("entity", "entity");
        if (theParams.containsKey("entity.groupId")) {
            theCriteria.add(Restrictions.eq("entity.groupId", Long.valueOf(theParams.get("entity.groupId")[0])));
        }
        if (theParams.containsKey("hasEnd")) {
            theCriteria.add(Restrictions.eq("hasEnd", Boolean.valueOf(theParams.get("hasEnd")[0])));
        }
        if (theParams.containsKey("hasLocation")) {
            theCriteria.add(Restrictions.eq("hasLocation", Boolean.valueOf(theParams.get("hasLocation")[0])));
        }
        return theCriteria.list();
    }
}
