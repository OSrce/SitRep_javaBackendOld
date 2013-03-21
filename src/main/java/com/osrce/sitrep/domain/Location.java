package com.osrce.sitrep.domain;

import com.vividsolutions.jts.geom.Geometry;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "location", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooJson
@TypeDef(name = "hstore", typeClass = HstoreUserType.class)
public class Location {

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

    @Type(type = "org.hibernate.spatial.GeometryType")
    @Column(name = "geometry", nullable = true, columnDefinition = "Geometry")
    private Geometry geometry;
}
