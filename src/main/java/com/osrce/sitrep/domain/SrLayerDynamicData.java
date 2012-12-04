package com.osrce.sitrep.domain;

import com.vividsolutions.jts.geom.Geometry;
import javax.persistence.Column;
import javax.persistence.Id;
import org.hibernate.annotations.Type;
import org.hibernate.spatial.GeometryType;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "sr_layer_dynamic_data", schema = "public")
@RooDbManaged(automaticallyDelete = true)
public class SrLayerDynamicData {

    @Id
    @Column(name = "id")
    private Integer id;

    @Type(type = "org.hibernate.spatial.GeometryType")
    @Column(name = "sr_geom", nullable = true, columnDefinition = "Geometry")
    private Geometry sr_geom;
}
