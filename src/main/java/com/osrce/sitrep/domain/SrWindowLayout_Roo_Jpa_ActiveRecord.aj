// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.SrWindowLayout;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SrWindowLayout_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager SrWindowLayout.entityManager;
    
    public static final EntityManager SrWindowLayout.entityManager() {
        EntityManager em = new SrWindowLayout().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long SrWindowLayout.countSrWindowLayouts() {
        return entityManager().createQuery("SELECT COUNT(o) FROM SrWindowLayout o", Long.class).getSingleResult();
    }
    
    public static List<SrWindowLayout> SrWindowLayout.findAllSrWindowLayouts() {
        return entityManager().createQuery("SELECT o FROM SrWindowLayout o", SrWindowLayout.class).getResultList();
    }
    
    public static SrWindowLayout SrWindowLayout.findSrWindowLayout(Integer id) {
        if (id == null) return null;
        return entityManager().find(SrWindowLayout.class, id);
    }
    
    public static List<SrWindowLayout> SrWindowLayout.findSrWindowLayoutEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM SrWindowLayout o", SrWindowLayout.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void SrWindowLayout.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void SrWindowLayout.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            SrWindowLayout attached = SrWindowLayout.findSrWindowLayout(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void SrWindowLayout.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void SrWindowLayout.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public SrWindowLayout SrWindowLayout.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        SrWindowLayout merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
