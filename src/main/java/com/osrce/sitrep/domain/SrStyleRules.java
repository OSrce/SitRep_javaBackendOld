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
@RooJpaActiveRecord(versionField = "", table = "sr_style_rules", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooJson
public class SrStyleRules {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "style_id")
    @NotNull
    private Integer style_id;

    @Column(name = "symbolizer_id")
    @NotNull
    private Integer symbolizer_id;

    @Column(name = "filter_data")
    private String filter_data;
}
