package com.osrce.sitrep.domain;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(identifierType = SrCfsUpdatequeuePK.class, versionField = "", table = "sr_cfs_updatequeue", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooJson
public class SrCfsUpdatequeue {
}
