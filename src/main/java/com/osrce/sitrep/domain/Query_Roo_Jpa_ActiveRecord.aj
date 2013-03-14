// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.Query;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Query_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Query.entityManager;
    
    public static final EntityManager Query.entityManager() {
        EntityManager em = new Query().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Query.countQuerys() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Query o", Long.class).getSingleResult();
    }
    
    public static List<Query> Query.findAllQuerys() {
        return entityManager().createQuery("SELECT o FROM Query o", Query.class).getResultList();
    }
    
    public static Query Query.findQuery(Long id) {
        if (id == null) return null;
        return entityManager().find(Query.class, id);
    }
    
    public static List<Query> Query.findQueryEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Query o", Query.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Query.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Query.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Query attached = Query.findQuery(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Query.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Query.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Query Query.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Query merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}