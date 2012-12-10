package com.osrce.sitrep.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "sr_layers", schema = "public")
@RooDbManaged(automaticallyDelete = true)
public class SrLayers {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "type", nullable = true, columnDefinition = "Layer_type")
    private Layer_type layerType;

    @Column(name = "format", nullable = true, columnDefinition = "Layer_format")
    private Layer_format layerFormat;

    public Layer_type getType() {
        return this.layerType;
    }

    public void setType(Layer_type layerType) {
        this.layerType = layerType;
    }

    public Layer_format getFormat() {
        return this.layerFormat;
    }

    public void setFormat(Layer_format layerFormat) {
        this.layerFormat = layerFormat;
    }
}
