package com.osrce.sitrep.domain;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "ltype", nullable = true, columnDefinition = "Layer_type")
    private String ltype;

    @Enumerated(EnumType.STRING)
    @Column(name = "lformat", nullable = true, columnDefinition = "Layer_format")
    private String lformat;

    @Type(type = "com.osrce.sitrep.domain.HstoreUserType")
    @Column(name = "urlparams", columnDefinition = "hstore")
    private Hstore urlparams;
}
