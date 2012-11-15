// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.SrStyleSymbolizers;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SrStyleSymbolizers_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager SrStyleSymbolizers.entityManager;
    
    public static final EntityManager SrStyleSymbolizers.entityManager() {
        EntityManager em = new SrStyleSymbolizers().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long SrStyleSymbolizers.countSrStyleSymbolizerses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM SrStyleSymbolizers o", Long.class).getSingleResult();
    }
    
    public static List<SrStyleSymbolizers> SrStyleSymbolizers.findAllSrStyleSymbolizerses() {
        return entityManager().createQuery("SELECT o FROM SrStyleSymbolizers o", SrStyleSymbolizers.class).getResultList();
    }
    
    public static SrStyleSymbolizers SrStyleSymbolizers.findSrStyleSymbolizers(Integer id) {
        if (id == null) return null;
        return entityManager().find(SrStyleSymbolizers.class, id);
    }
    
    public static List<SrStyleSymbolizers> SrStyleSymbolizers.findSrStyleSymbolizersEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM SrStyleSymbolizers o", SrStyleSymbolizers.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void SrStyleSymbolizers.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void SrStyleSymbolizers.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            SrStyleSymbolizers attached = SrStyleSymbolizers.findSrStyleSymbolizers(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void SrStyleSymbolizers.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void SrStyleSymbolizers.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public SrStyleSymbolizers SrStyleSymbolizers.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        SrStyleSymbolizers merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
