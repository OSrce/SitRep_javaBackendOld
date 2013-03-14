// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.domain;

import com.osrce.sitrep.domain.Layer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Layer_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Layer.entityManager;
    
    public static final EntityManager Layer.entityManager() {
        EntityManager em = new Layer().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Layer.countLayers() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Layer o", Long.class).getSingleResult();
    }
    
    public static List<Layer> Layer.findAllLayers() {
        return entityManager().createQuery("SELECT o FROM Layer o", Layer.class).getResultList();
    }
    
    public static Layer Layer.findLayer(Long id) {
        if (id == null) return null;
        return entityManager().find(Layer.class, id);
    }
    
    public static List<Layer> Layer.findLayerEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Layer o", Layer.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Layer.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Layer.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Layer attached = Layer.findLayer(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Layer.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Layer.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Layer Layer.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Layer merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}