package com.osrce.sitrep.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "sr_styles", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooJson
public class SrStyles {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "stylemap_id")
    @NotNull
    private Integer stylemap_id;

    @Column(name = "render_type", length = 128)
    @NotNull
    private String render_type;

    @Column(name = "defaultsymbolizer_id")
    private Integer defaultsymbolizer_id;
}
