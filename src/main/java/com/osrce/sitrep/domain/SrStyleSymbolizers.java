package com.osrce.sitrep.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "sr_style_symbolizers", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooJson
public class SrStyleSymbolizers {

    @Id
    @Column(name = "id")
    private Integer id;

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
     
    
}
