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
@RooJpaActiveRecord(versionField = "", table = "sr_window_layout", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooJson
public class SrWindowLayout {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "view_x")
    @NotNull
    private Integer view_x;

    @Column(name = "view_y")
    @NotNull
    private Integer view_y;

    @Column(name = "view_data")
    private String view_data;
}
