package com.osrce.sitrep.domain;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import com.vividsolutions.jts.geom.Geometry;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "entity", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooJson
public class Entity {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private SrLocations location;

    public static List<com.osrce.sitrep.domain.Entity> findAllCurrentEntitysWithStatus() {
        return entityManager().createQuery("SELECT o FROM Entity o WHERE o.hasLocation='TRUE' ", Entity.class).getResultList();
    }

    public static String toJsonArray(Collection<com.osrce.sitrep.domain.Entity> collection) {
      return new JSONSerializer().transform(new GeometryTransformer(), Geometry.class).serialize(collection);
//      return new JSONSerializer().transform(new GeometryTransformer(), "location.sr_geom").exclude("*.class", "createdOn", "updatedOn").serialize(collection);
    }
}
