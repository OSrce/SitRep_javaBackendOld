package com.osrce.sitrep.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(identifierType = SrCfsPK.class, versionField = "", table = "sr_cfs", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooJson
public class SrCfs {

    @PersistenceContext
    transient EntityManager entityManager;

    @Column(name = "cfs_letter")
    @NotNull
    private Character cfs_letter;
    
    @Column(name = "cfs_pct")
    @NotNull
    private Integer cfs_pct;
    
    @Column(name = "cfs_sector")
    @NotNull
    private Character cfs_sector;
    
    @Column(name = "cfs_addr", length = 128)
    private String cfs_addr;
    
    @Column(name = "cfs_cross1", length = 128)
    private String cfs_cross1;
    
    @Column(name = "cfs_cross2", length = 128)
    private String cfs_cross2;
    
    @Column(name = "cfs_location")
    private Integer cfs_location;
    
    @Column(name = "cfs_code")
    @NotNull
    private Integer cfs_code;
    
    @Column(name = "cfs_codesup1", length = 8)
    private String cfs_codesup1;
    
    @Column(name = "cfs_codesup2", length = 128)
    private String cfs_codesup2;
    
    @Column(name = "cfs_createdby", length = 16)
    private String cfs_createdby;
    
    @Column(name = "cfs_dispatcherid", length = 8)
    private String cfs_dispatcherid;
    
    @Column(name = "cfs_timecreated")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(style = "M-")
    private Date cfs_timecreated;
    
    @Column(name = "cfs_timeassigned")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date cfs_timeassigned;
    
    @Column(name = "cfs_assignedunit", length = 16)
    private String cfs_assignedunit;
    
    @Column(name = "cfs_priority")
    private Integer cfs_priority;
    
    @Column(name = "cfs_routenotifications")
    private Boolean cfs_routenotifications;
    
    @Column(name = "cfs_finaldis")
    private Integer cfs_finaldis;
    
    @Column(name = "cfs_finaldissup1", length = 32)
    private String cfs_finaldissup1;
    
    @Column(name = "cfs_finaldisdate")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date cfs_finaldisdate;
    
    @Column(name = "cfs_finaldisunit", length = 16)
    private String cfs_finaldisunit;
    
    @Column(name = "cfs_callback")
    private Boolean cfs_callback;
    
    @Column(name = "cfs_dup")
    private Boolean cfs_dup;
    
    @Column(name = "cfs_dupletter")
    private Character cfs_dupletter;
    
    @Column(name = "cfs_dupnum")
    private Integer cfs_dupnum;
    
    @Column(name = "cfs_body")
    private String cfs_body;
    
    @Column(name = "cfs_bodylastline", length = 100)
    private String cfs_bodylastline;
    
    @Column(name = "cfs_created_on")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date cfs_created_on;
    
    @Column(name = "cfs_updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date cfs_updated_on;

    
    
    
    
    public static List<com.osrce.sitrep.domain.SrCfs> findAllSrCfsWithQuery(String theQuery) {
        return entityManager().createNativeQuery("SELECT * FROM sr_cfs where " + theQuery, SrCfs.class).getResultList();
    }
}
