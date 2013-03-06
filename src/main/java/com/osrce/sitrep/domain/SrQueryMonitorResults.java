package com.osrce.sitrep.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "sr_query_monitor_results", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooJson
public class SrQueryMonitorResults {

    @Id
    @Column(name = "id")
    private Long id;
}
