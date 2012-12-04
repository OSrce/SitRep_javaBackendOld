package com.osrce.sitrep.web;

import com.vividsolutions.jts.geom.Geometry;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.converter.RooConversionService;

import com.osrce.sitrep.domain.SrLayerStaticData;
import com.osrce.sitrep.domain.SrLocations;

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
	}
	
	public Converter<Geometry, String> getGeomToStringConverter() {
	        return new org.springframework.core.convert.converter.Converter<Geometry, java.lang.String>() {
	            public String convert(Geometry geom) {
	              return new StringBuilder().append( geom.toString()  ).append(' ').toString();
	            }
	        };
	    }
	
	
	
}
