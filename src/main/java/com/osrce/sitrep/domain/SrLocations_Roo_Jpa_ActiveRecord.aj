// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.SrLocations;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SrLocations_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager SrLocations.entityManager;
    
    public static final EntityManager SrLocations.entityManager() {
        EntityManager em = new SrLocations().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long SrLocations.countSrLocationses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM SrLocations o", Long.class).getSingleResult();
    }
    
    public static List<SrLocations> SrLocations.findAllSrLocationses() {
        return entityManager().createQuery("SELECT o FROM SrLocations o", SrLocations.class).getResultList();
    }
    
    public static SrLocations SrLocations.findSrLocations(Integer id) {
        if (id == null) return null;
        return entityManager().find(SrLocations.class, id);
    }
    
    public static List<SrLocations> SrLocations.findSrLocationsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM SrLocations o", SrLocations.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void SrLocations.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void SrLocations.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            SrLocations attached = SrLocations.findSrLocations(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void SrLocations.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void SrLocations.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public SrLocations SrLocations.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        SrLocations merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
