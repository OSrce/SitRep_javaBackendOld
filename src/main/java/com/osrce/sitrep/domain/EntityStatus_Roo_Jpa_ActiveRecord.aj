// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.EntityStatus;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect EntityStatus_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager EntityStatus.entityManager;
    
    public static final EntityManager EntityStatus.entityManager() {
        EntityManager em = new EntityStatus().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long EntityStatus.countEntityStatuses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM EntityStatus o", Long.class).getSingleResult();
    }
    
    public static List<EntityStatus> EntityStatus.findAllEntityStatuses() {
        return entityManager().createQuery("SELECT o FROM EntityStatus o", EntityStatus.class).getResultList();
    }
    
    public static EntityStatus EntityStatus.findEntityStatus(Long id) {
        if (id == null) return null;
        return entityManager().find(EntityStatus.class, id);
    }
    
    public static List<EntityStatus> EntityStatus.findEntityStatusEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM EntityStatus o", EntityStatus.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void EntityStatus.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void EntityStatus.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            EntityStatus attached = EntityStatus.findEntityStatus(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void EntityStatus.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void EntityStatus.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public EntityStatus EntityStatus.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        EntityStatus merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
