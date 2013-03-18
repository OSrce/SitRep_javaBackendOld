package com.osrce.sitrep.domain;

import org.hibernate.HibernateException;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;


import org.springframework.util.StringUtils;
import java.util.HashMap;
import java.util.Map;

/*
@TypeDefs(
{
	@TypeDef(name="HstoreUserType", typeClass=com.osrce.sitrep.domain.HstoreUserType.class )
} ) package com.osrce.sitrep.domain;
*/

public class HstoreUserType implements UserType {

	
/*
 	
public static class HstoreHelper {
 
    private static final String K_V_SEPARATOR = "=>";
 
    public static String toString(Map<String, String> m) {
    	System.out.println("TEST2### STARTING LINE");
    	for (Map.Entry entry : m.entrySet()) {
            System.out.println("TEST2### Key = " + entry.getKey() + ", Value = " +
                               entry.getValue());
        }
    	
        if (m.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int n = m.size();
        for (String key : m.keySet()) {
            sb.append(key + K_V_SEPARATOR + m.get(key));
            if (n > 1) {
                sb.append(", ");
                n--;
            }
        }
        return sb.toString();
    }
 
    public static Map<String, String> toMap(String s) {
    	System.out.println("TEST1###"+s);
        Map<String, String> m = new HashMap<String, String>();
        if (! StringUtils.hasText(s)) {
            return m;
        }
        String[] tokens = s.split(", ");
        for (String token : tokens) {
            String[] kv = token.split(K_V_SEPARATOR);
            String k = kv[0];
            k = k.trim().substring(1, k.length() - 2);
            String v = kv[1];
            v = v.trim().substring(1, v.length() - 2);
            System.out.println("TEST1### KeyK = " + k + ", ValueV = " + v);
            m.put(k, v);
        }
        
        for (Map.Entry entry : m.entrySet()) {
            System.out.println("TEST1### Key = " + entry.getKey() + ", Value = " +
                               entry.getValue());
        }
        
        return m;
    }
}
*/

    public Object assemble(Serializable cached, Object owner)
            throws HibernateException {
        return cached;
    }

    public Object deepCopy(Object o) throws HibernateException {
        // It's not a true deep copy, but we store only String instances, and they
        // are immutable, so it should be OK
//        Map m = (Map) o;
        Hstore m = (Hstore) o;
        if(m == null) {
        	return new Hstore();
        } else {
//      return new HashMap(m);
        	return new Hstore(m);
        }
    }

    public Serializable disassemble(Object o) throws HibernateException {
        return (Serializable) o;
    }

    public boolean equals(Object o1, Object o2) throws HibernateException {
 //       Map m1 = (Map) o1;
 //       Map m2 = (Map) o2;
        Hstore m1 = (Hstore) o1;
        Hstore m2 = (Hstore) o2;
        return m1.equals(m2);
    }

    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SessionImplementor sessionImplementor, Object o) throws HibernateException, SQLException {
//        System.out.println("HstoreUerType: nullSafeGet called!");

    	String col = strings[0];
        String val = resultSet.getString(col);
//        System.out.println("HstoreUerType: nullSafeGet VAL="+val);

        return Hstore.toMap(val);
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SessionImplementor sessionImplementor) throws HibernateException, SQLException {
//        System.out.println("HstoreUerType: nullSafeSet called!");

    	String s = Hstore.toString((Hstore) o);
        preparedStatement.setObject(i, s, Types.OTHER);
    }

    public boolean isMutable() {
        return true;
    }

    public Object replace(Object original, Object target, Object owner)
            throws HibernateException {
        return original;
    }

    public Class returnedClass() {
        return Hstore.class;
    }

    public int[] sqlTypes() {
        /*
         * i'm not sure what value should be used here, but it works, AFAIK only
         * length of this array matters, as it is a column span (1 in our case)
         */
        return new int[] { Types.INTEGER };
    }
}