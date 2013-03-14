// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.Sruser;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Sruser_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Sruser.entityManager;
    
    public static final EntityManager Sruser.entityManager() {
        EntityManager em = new Sruser().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Sruser.countSrusers() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Sruser o", Long.class).getSingleResult();
    }
    
    public static List<Sruser> Sruser.findAllSrusers() {
        return entityManager().createQuery("SELECT o FROM Sruser o", Sruser.class).getResultList();
    }
    
    public static Sruser Sruser.findSruser(Long id) {
        if (id == null) return null;
        return entityManager().find(Sruser.class, id);
    }
    
    public static List<Sruser> Sruser.findSruserEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Sruser o", Sruser.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Sruser.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Sruser.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Sruser attached = Sruser.findSruser(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Sruser.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Sruser.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Sruser Sruser.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Sruser merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}