
package com.osrce.sitrep.domain;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import org.springframework.util.StringUtils;
import java.util.HashMap;
import java.util.Map;



//public class Hstore implements Map {
//public class Hstore<K,V> extends AbstractMap<K,V> implements Map<K,V>, Cloneable, Serializable	{


//	private Map<K,V> map;

//@SuppressWarnings("serial")
public class Hstore extends HashMap<String, String> {
	
	
	public Hstore() {
		super();
	}
	
	public HashMap<String, String> getHashMap() {
		return (HashMap<String,String>) this;
		
	}
	
	public Hstore(int initialCapacity) {
		super(initialCapacity);
	}
	
	public Hstore(int initialCapacity, float loadFactor) {
		super(initialCapacity,loadFactor);
	}
	
	public Hstore( Hstore h) {
		super( (HashMap<String,String>) h);
		//		HashMap<String,String> m = (HashMap<String,String>) h;
//		super( h.getHashMap() );
//		 HashMap<String,String> m = new HashMap<String,String>();
	}
	

    private static final String K_V_SEPARATOR = "=>";
 
    public static String toString(Hstore m) {
/*
    	System.out.println("TEST2### STARTING LINE");
    	for (Map.Entry entry : m.entrySet()) {
            System.out.println("TEST2### Key = " + entry.getKey() + ", Value = " +
                               entry.getValue());
        }
*/    	
        if (m.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int n = m.size();
        for (String key : m.keySet()) {
            sb.append("\"" + key + "\"" + K_V_SEPARATOR + "\"" + m.get(key) + "\"" );
            if (n > 1) {
                sb.append(", ");
                n--;
            }
        }
        return sb.toString();
    }
 
    public static Hstore toMap(String s) {
//    	System.out.println("TEST1###"+s);
        Hstore m = new Hstore();
        if (! StringUtils.hasText(s)) {
            return m;
        }
        String[] tokens = s.split(", ");
        for (String token : tokens) {
            String[] kv = token.split(K_V_SEPARATOR);
            String k = kv[0];
            k = k.trim().substring(1, k.length() - 1);
            String v = kv[1];
            v = v.trim().substring(1, v.length() - 1);
//            System.out.println("TEST1### KeyK = " + k + ", ValueV = " + v);
            m.put(k, v);
        }
        
/*        for (Map.Entry entry : m.entrySet()) {
            System.out.println("TEST1### Key = " + entry.getKey() + ", Value = " +
                               entry.getValue());
        }
*/        
        return m;
    }



}