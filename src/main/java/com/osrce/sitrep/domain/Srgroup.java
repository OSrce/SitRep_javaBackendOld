package com.osrce.sitrep.domain;

import com.vividsolutions.jts.geom.Geometry;
import flexjson.JSONSerializer;
import flexjson.transformer.AbstractTransformer;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "srgroup", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooJson
public class Srgroup {

    @Id
    @Column(name = "id", columnDefinition = "bigserial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "hstore")
    @Column(name = "data", columnDefinition = "hstore")
    private Hstore data;

    @Column(name = "updated", updatable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date updated;

    @Column(name = "parent")
    private Long parent;

    @ManyToOne
    @JoinColumn(name = "parent", referencedColumnName = "id", insertable = false, updatable = false)
    private com.osrce.sitrep.domain.Srgroup parentGroup;

    @OneToMany(mappedBy = "parent")
    private Set<com.osrce.sitrep.domain.Srgroup> children;

    public String toJsonFlatTreeArray() {
        String retStr = "[";
        retStr += this.toJsonTree();
        retStr = retStr.substring(0, retStr.length() - 1);
        retStr += "]";
        return retStr;
    }

    public String toJsonTree() {
        String retStr = "";
        retStr = new JSONSerializer().exclude("parentGroup", "*.class").serialize(this);
        retStr += ",";
        Iterator<Srgroup> iter = this.children.iterator();
        while (iter.hasNext()) {
            retStr += iter.next().toJsonTree();
        }
        return retStr;
    }
}
