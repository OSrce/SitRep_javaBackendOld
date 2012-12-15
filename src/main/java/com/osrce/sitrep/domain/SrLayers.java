package com.osrce.sitrep.domain;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "sr_layers", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooJson
public class SrLayers {

    @Id
    @Column(name = "id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = true, columnDefinition = "Layer_type")
    private String layerType;

    @Enumerated(EnumType.STRING)
    @Column(name = "format", nullable = true, columnDefinition = "Layer_format")
    private String layerFormat;
}
