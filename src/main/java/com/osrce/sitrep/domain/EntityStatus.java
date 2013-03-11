package com.osrce.sitrep.domain;

import com.vividsolutions.jts.geom.Geometry;
import flexjson.JSONSerializer;
import java.util.Collection;
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
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.annotations.Type;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
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

    @OneToMany(mappedBy = "status")
    private Set<Entity> entitys;

    @Column(name = "entity")
    private Long entity;

    @Id
    @Column(name = "id", columnDefinition = "bigserial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "hstore")
    @Column(name = "data", columnDefinition = "hstore")
    private Hstore data = new Hstore();

    public String toJson() {
        System.out.println("TEST=== EntityStatus toJson called!");
        return new JSONSerializer().transform(new GeometryTransformer(), Geometry.class).serialize(this);
    }

    public static String toJsonArray(Collection<com.osrce.sitrep.domain.EntityStatus> collection) {
        System.out.println("TEST=== EntityStatus toJsonArray called!");
        Iterator<com.osrce.sitrep.domain.EntityStatus> iterator = collection.iterator();
        while (iterator.hasNext()) {
            EntityStatus theEnt = iterator.next();
            System.out.println("Test2= " + theEnt.getId());
            System.out.println("Test3= " + theEnt.getData().toString());
        }
        return new JSONSerializer().transform(new GeometryTransformer(), Geometry.class).serialize(collection);
    }

    public static List<com.osrce.sitrep.domain.EntityStatus> findAllWithParams(Map<java.lang.String, java.lang.String[]> theParams) {
        System.out.println("TEST=== Entity findAllWithParams called!");
        Boolean firstOne = true;
        String theQueryStr = "SELECT o FROM Entity o ";
        Long theGroupId = null;
        Boolean theHasData = null;
        Boolean theHasStatus = null;
        if (theParams.containsKey("group_id")) {
            if (firstOne == true) {
                theQueryStr += " where";
            } else {
                theQueryStr += " AND";
            }
            theQueryStr += " group_id=:theGroupId";
            firstOne = false;
            theGroupId = Long.valueOf(theParams.get("group_id")[0]);
        }
        if (theParams.containsKey("has_data")) {
            if (firstOne == true) {
                theQueryStr += " where";
            } else {
                theQueryStr += " AND";
            }
            theQueryStr += " has_data =:theHasData";
            firstOne = false;
            theHasData = Boolean.valueOf(theParams.get("has_data")[0]);
        }
        if (theParams.containsKey("has_status")) {
            if (firstOne == true) {
                theQueryStr += " where";
            } else {
                theQueryStr += " AND";
            }
            theQueryStr += " has_status =:theHasStatus";
            firstOne = false;
            theHasStatus = Boolean.valueOf(theParams.get("has_status")[0]);
        }
        HibernateEntityManager hem = entityManager().unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria theCriteria = session.createCriteria(Entity.class);
        theCriteria.add(Restrictions.eq("groupId", new Long(1)));
        theCriteria.add(Restrictions.eq("status.hasEnd", false));
        theCriteria.add(Restrictions.sqlRestriction("{alias}.status.data  @> '\"inservice\"=>\"t\"'::hstore "));
        return theCriteria.list();
    }
}
