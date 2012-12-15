// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.SrCfs;
import com.osrce.sitrep.domain.SrCfsPK;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SrCfs_Roo_Jpa_ActiveRecord {
    
    public static final EntityManager SrCfs.entityManager() {
        EntityManager em = new SrCfs().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long SrCfs.countSrCfses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM SrCfs o", Long.class).getSingleResult();
    }
    
    public static List<SrCfs> SrCfs.findAllSrCfses() {
        return entityManager().createQuery("SELECT o FROM SrCfs o", SrCfs.class).getResultList();
    }
    
    public static SrCfs SrCfs.findSrCfs(SrCfsPK id) {
        if (id == null) return null;
        return entityManager().find(SrCfs.class, id);
    }
    
    public static List<SrCfs> SrCfs.findSrCfsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM SrCfs o", SrCfs.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void SrCfs.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void SrCfs.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            SrCfs attached = SrCfs.findSrCfs(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void SrCfs.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void SrCfs.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public SrCfs SrCfs.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        SrCfs merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
