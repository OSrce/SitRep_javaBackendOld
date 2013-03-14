package com.osrce.sitrep.web;

import java.util.List;
import java.util.Map;

import com.osrce.sitrep.domain.Entity;
import com.osrce.sitrep.domain.Srmap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/entitys")
@Controller
@RooWebScaffold(path = "entitys", formBackingObject = Entity.class)
@RooWebJson(jsonObject = Entity.class)
public class EntityController {
	
	  
    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
//    public ResponseEntity<String> listJson(@RequestParam(value = "group_id", required = false) Long theGroupId,
//    										@RequestParam(value = "has_data", required = false) Boolean theHasData,
//    										@RequestParam(value = "has_status", required = false) Boolean theHasStatus) {	
    public ResponseEntity<String> listJson(org.springframework.web.context.request.WebRequest webRequest) {
//        System.out.println("TEST=== EntityControllerCalled===");
    	HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        
    	Map<String, String[]> theParams = webRequest.getParameterMap();
                
        List<Entity> result;
//        if (theGroupId != null) {
            result = Entity.findAllWithParams(theParams);
//        } else {
//            result = Entity.findAllEntitys();
//        }
        return new ResponseEntity<String>(Entity.toJsonArray(result), headers, HttpStatus.OK);
    }
    
	
	
    void populateEditForm(Model uiModel, Entity entity) {
        uiModel.addAttribute("entity", entity);
        addDateTimeFormatPatterns(uiModel);
//        uiModel.addAttribute("entitystatuses", EntityStatus.findAllEntityStatuses());
    }
    
    
    
	
}
