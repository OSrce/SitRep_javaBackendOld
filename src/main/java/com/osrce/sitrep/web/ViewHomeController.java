package com.osrce.sitrep.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.osrce.sitrep.domain.*;

/*
import com.osrce.sitrep.domain.SrLayers;
import com.osrce.sitrep.domain.SrQueries;
import com.osrce.sitrep.domain.SrStylePresets;
import com.osrce.sitrep.domain.SrStyleRules;
import com.osrce.sitrep.domain.SrStyleSymbolizers;
import com.osrce.sitrep.domain.SrStyles;
import com.osrce.sitrep.domain.SrWindowLayout;
*/

@Controller
public class ViewHomeController {

    @RequestMapping( { "/", "/viewhome" }  )
    public String index(ModelMap model) {
    	String message = "Hello World, Spring 3.0!";
    	model.addAttribute("message", message);
    	
    	String theLayers = Layer.toJsonArray(Layer.findAllLayers() );
    	model.addAttribute("theLayers", theLayers );
    	
    	String theStyles = Style.toJsonArray(Style.findAllStyles() );
    	model.addAttribute("theStyles", theStyles);
    	
    	String theStyleSymbolizers = Ssymbolizer.toJsonArray(Ssymbolizer.findAllSsymbolizers() );
    	model.addAttribute("theStyleSymbolizers",theStyleSymbolizers);
    	
    	String theStyleRules = Srule.toJsonArray(Srule.findAllSrules() );
    	model.addAttribute("theStyleRules", theStyleRules);
    	
/*    	
    	String theQueries = SrQueries.toJsonArray(SrQueries.findAllSrQuerieses() );
    	model.addAttribute("theQueries", theQueries);
    	
    	String theLayers = SrLayers.toJsonArray(SrLayers.findAllSrLayerses() );
    	model.addAttribute("theLayers",theLayers);
    	
    	String theStyles = SrStyles.toJsonArray(SrStyles.findAllSrStyleses() );
    	model.addAttribute("theStyles", theStyles);
 
    	String theStyleSymbolizers = SrStyleSymbolizers.toJsonArray(SrStyleSymbolizers.findAllSrStyleSymbolizerses() );
    	model.addAttribute("theStyleSymbolizers",theStyleSymbolizers);
    	
    	String theStyleRules = SrStyleRules.toJsonArray(SrStyleRules.findAllSrStyleRuleses() );
    	model.addAttribute("theStyleRules", theStyleRules);
 */
    	
 /*   	String theWLayouts = SrWindowLayout.toJsonArray(SrWindowLayout.findAllSrWindowLayouts() );
    	model.addAttribute("theWLayouts", theWLayouts);
    	
    	String thePresets = SrStylePresets.toJsonArray(SrStylePresets.findAllSrStylePresetses() );
    	model.addAttribute("thePresets", thePresets);
 */   	
   	
        return "viewhome/index";
    }

    
    
    
}
