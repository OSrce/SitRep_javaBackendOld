package com.osrce.sitrep.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "ssymbolizer", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooJson
public class Ssymbolizer {

    @Id
    @Column(name = "id", columnDefinition = "bigserial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fillcolor", length = 32)
    private String fillColor;

    @Column(name = "fillopacity", precision = 8, scale = 8)
    private Float fillOpacity;

    @Column(name = "strokecolor", length = 32)
    private String strokeColor;

    @Column(name = "strokeopacity", precision = 8, scale = 8)
    private Float strokeOpacity;

    @Column(name = "strokewidth")
    private Integer strokeWidth;

    @Column(name = "pointradius")
    private Integer pointRadius;

    @Column(name = "fontcolor", length = 32)
    private String fontColor;

    @Column(name = "fontsize")
    private Integer fontSize;

    @Column(name = "fontfamily", length = 64)
    private String fontFamily;

    @Column(name = "fontweight", length = 32)
    private String fontWeight;

    @Column(name = "fontopacity", precision = 8, scale = 8)
    private Float fontOpacity;

    @Column(name = "labelalign", length = 32)
    private String labelAlign;

    @Column(name = "labelxoffset")
    private Integer labelXOffset;

    @Column(name = "labelyoffset")
    private Integer labelYOffset;

    @Column(name = "externalgraphic", length = 128)
    private String externalGraphic;

    @Column(name = "graphicwidth")
    private Integer graphicWidth;

    @Column(name = "graphicheight")
    private Integer graphicHeight;

    @Column(name = "graphicopacity", precision = 8, scale = 8)
    private Float graphicOpacity;

    @Column(name = "updated", updatable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date updated;
}
