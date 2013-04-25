package com.osrce.sitrep.domain;

import flexjson.JSONSerializer;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@RooJpaActiveRecord(versionField = "", table = "layer", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooJson
public class Layer {

    @Id
    @Column(name = "id", columnDefinition = "bigserial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "com.osrce.sitrep.domain.HstoreUserType")
    @Column(name = "urlparams", columnDefinition = "hstore")
    private Hstore urlparams;

    @Type(type = "com.osrce.sitrep.domain.HstoreUserType")
    @Column(name = "columndef", columnDefinition = "hstore")
    private Hstore columndef;

    @Type(type = "com.osrce.sitrep.domain.HstoreUserType")
    @Column(name = "columnlabel", columnDefinition = "hstore")
    private Hstore columnlabel;

    @Type(type = "com.osrce.sitrep.domain.HstoreUserType")
    @Column(name = "data", columnDefinition = "hstore")
    private Hstore data;

    @Column(name = "updated", updatable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date updated;

    public String toJsonWithStrategies() {
        return new JSONSerializer().include("lstrategies").exclude("*.class").serialize(this);
    }

    public static String toJsonArrayWithStrategies(Collection<com.osrce.sitrep.domain.Layer> collection) {
        return new JSONSerializer().include("lstrategies").exclude("*.class").serialize(collection);
    }
}
