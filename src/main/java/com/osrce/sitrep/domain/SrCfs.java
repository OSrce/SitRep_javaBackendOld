package com.osrce.sitrep.domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import com.vividsolutions.jts.geom.Geometry;

import flexjson.JSON;
import flexjson.JSONSerializer;
import flexjson.transformer.BasicDateTransformer;
import flexjson.transformer.DateTransformer;

@RooJavaBean
@RooToString
@RooDbManaged(automaticallyDelete = true)
@RooJson
@RooJpaActiveRecord(versionField = "", table = "sr_cfs", schema = "public")
public class SrCfs {

    @PersistenceContext
    transient EntityManager entityManager;

    @Id
    @Column(name = "id")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "cfs_location", referencedColumnName = "id")
    private SrLocations cfs_location;
       
    @Column(name = "cfs_date")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date cfs_date;

    @Column(name = "cfs_num")
    @NotNull
    private Integer cfs_num;

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
    @DateTimeFormat(pattern = "hh:mm:ss")
    private Date cfs_timecreated;

    @Column(name = "cfs_timeassigned")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "hh:mm:ss")
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
    
    @Transient
    private Geometry geometry;
    
    public Geometry getGeometry() {
    	return this.cfs_location.getSr_geom();
    }

    public static List<com.osrce.sitrep.domain.SrCfs> findAllSrCfsWithQuery(String theQuery) {
        return entityManager().createNativeQuery("SELECT * FROM sr_cfs where " + theQuery, SrCfs.class).getResultList();
    }
    
    public static String toJsonArray(Collection<SrCfs> collection) {
        return new JSONSerializer()
//        	.include("id", "cfs_date", "cfs_num", "cfs_letter", "cfs_pct", "cfs_sector", "cfs_code", "cfs_timeassigned", "cfs_finaldis", "geometry" )
        	.transform(new GeometryTransformer(), "geometry")
        	.transform(new DateTransformer("yyyy-MM-dd"), "cfs_date" )
        	.transform(new DateTransformer("hh:mm:ss"), "cfs_timecreated", "cfs_timeassigned")
        	.transform(new DateTransformer("yyyy-MM-dd hh:mm:ss"), "cfs_finaldisdate", "cfs_updated_on")
//        	.exclude("*")
        	.exclude("*.class", "cfs_location", "cfs_updated_on")
        	.serialize(collection);
    }
    
    
    
}
