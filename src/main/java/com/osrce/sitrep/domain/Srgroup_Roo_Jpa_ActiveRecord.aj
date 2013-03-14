// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.Srgroup;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Srgroup_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Srgroup.entityManager;
    
    public static final EntityManager Srgroup.entityManager() {
        EntityManager em = new Srgroup().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Srgroup.countSrgroups() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Srgroup o", Long.class).getSingleResult();
    }
    
    public static List<Srgroup> Srgroup.findAllSrgroups() {
        return entityManager().createQuery("SELECT o FROM Srgroup o", Srgroup.class).getResultList();
    }
    
    public static Srgroup Srgroup.findSrgroup(Long id) {
        if (id == null) return null;
        return entityManager().find(Srgroup.class, id);
    }
    
    public static List<Srgroup> Srgroup.findSrgroupEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Srgroup o", Srgroup.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Srgroup.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Srgroup.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Srgroup attached = Srgroup.findSrgroup(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Srgroup.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Srgroup.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Srgroup Srgroup.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Srgroup merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}