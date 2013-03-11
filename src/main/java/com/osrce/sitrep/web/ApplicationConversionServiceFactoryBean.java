package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.Hstore;
import com.osrce.sitrep.domain.HstoreUserType;
import com.osrce.sitrep.domain.Style;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.converter.RooConversionService;

/**
 * A central place to register application converters and formatters. 
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	
	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
		registry.addConverter(getGeomToStringConverter() );
		registry.addConverter(getStringToGeomConverter() );
		registry.addConverter(getHstoreToStringConverter() );
		registry.addConverter(getStringToHstoreConverter() );

	}

	public Converter<Hstore, String> getHstoreToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<Hstore, java.lang.String>() {
            public String convert(Hstore theHstore) {
              return new StringBuilder().append( Hstore.toString( theHstore )  ).append(' ').toString();
            }
        };
    }
	
	public Converter<String,Hstore> getStringToHstoreConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, Hstore>() {
            public Hstore convert(String theString) {
            	return Hstore.toMap(theString);
//            	return getObject().convert(getObject().convert(id, Long.class), Hstore.class);
//              return new StringBuilder().append( theHstore.toString()  ).append(' ').toString();
            }
        };
    }
	
	
	public Converter<Geometry, String> getGeomToStringConverter() {
	        return new org.springframework.core.convert.converter.Converter<Geometry, java.lang.String>() {
	            public String convert(Geometry geom) {
	              return new StringBuilder().append( geom.toString()  ).append(' ').toString();
	            }
	        };
	    }

	public Converter<String,Geometry> getStringToGeomConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, Geometry>() {
            public Geometry convert(String theString) {
                GeometryFactory geometryFactory = new GeometryFactory();
                WKTReader reader = new WKTReader( geometryFactory );
 /*               Point point = null;
                try {
                    point = (Point) reader.read("POINT (1 1)");
                } catch (ParseException e) {            
                }
                System.out.println( point );
                Geometry test = new Geometry();
                test.
*/
                try {
					return reader.read(theString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;

				}
            	
//            	return Hstore.toMap(theString);
//            	return getObject().convert(getObject().convert(id, Long.class), Hstore.class);
//              return new StringBuilder().append( theHstore.toString()  ).append(' ').toString();
            }
        };
    }
	
	
	
/*	public Converter<Geometry, String> getGeomToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<Geometry, java.lang.String>() {
            public String convert(Geometry geom) {
              return new StringBuilder().append( geom.toString()  ).append(' ').toString();
            }
        };
    }
*/
	
	
	
}
