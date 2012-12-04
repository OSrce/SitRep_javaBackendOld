package com.osrce.sitrep.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "sr_assets_status", schema = "public")
@RooDbManaged(automaticallyDelete = true)
public class SrAssetsStatus {

    @Id
    @Column(name = "id")
    private Integer id;
}
