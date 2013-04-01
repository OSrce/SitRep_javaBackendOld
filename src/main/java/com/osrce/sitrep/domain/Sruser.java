package com.osrce.sitrep.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.security.core.context.SecurityContextHolder;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "sruser", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooJson
public class Sruser {

    @PersistenceContext
    transient EntityManager entityManager;

    @Id
    @Column(name = "id", columnDefinition = "bigserial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "hstore")
    @Column(name = "data", columnDefinition = "hstore")
    private Hstore data;

    @Column(name = "updated", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date updated;

    public static com.osrce.sitrep.domain.Sruser findSruserByName(String theUsername) {
        if (theUsername == null) return null;
        try {
            return entityManager().createQuery("SELECT o FROM Sruser o WHERE name=:theUsername", Sruser.class).setParameter("theUsername", theUsername).getSingleResult();
        } catch (Throwable e) {
            return null;
        }
    }

    public com.osrce.sitrep.domain.Sruser getSessionUserRecord() {
        String theUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("BEFORE findSruserByName");
        Sruser theUser = Sruser.findSruserByName(theUsername);
        System.out.println("AFTER findSruserByName");
        if (theUser == null) {
            Srgroup theGroup = null;
            if (theGroup == null) {
                theGroup = Srgroup.findSrgroup(new Long(33));
            }
            theUser = new Sruser();
            theUser.setData(theGroup.getData());
            theUser.setGroupId(new Long(2050));
            theUser.setName(theUsername);
            theUser.persist();
        }
        return theUser;
    }
}
