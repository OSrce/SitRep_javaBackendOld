package com.osrce.sitrep.domain;

import com.vividsolutions.jts.geom.Geometry;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.spatial.GeometryType;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "sr_locations", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooJson
public class SrLocations {

    @Id
    @Column(name = "id")
    private Integer id;

    @Type(type = "org.hibernate.spatial.GeometryType")
    @Column(name = "sr_geom", nullable = true, columnDefinition = "GeometryType")
    private Geometry sr_geom;
}
