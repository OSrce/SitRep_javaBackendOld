package com.osrce.sitrep.web;

import java.util.List;
import java.util.Map;

import com.osrce.sitrep.domain.Entity;
import com.osrce.sitrep.domain.EntityStatus;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/entitystatuses")
@Controller
@RooWebScaffold(path = "entitystatuses", formBackingObject = EntityStatus.class)
@RooWebJson(jsonObject = EntityStatus.class)
public class EntityStatusController {
	
    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
//    public ResponseEntity<String> listJson(@RequestParam(value = "group_id", required = false) Long theGroupId,
//    										@RequestParam(value = "has_data", required = false) Boolean theHasData,
//    										@RequestParam(value = "has_status", required = false) Boolean theHasStatus) {	
    public ResponseEntity<String> listJson(org.springframework.web.context.request.WebRequest webRequest) {
//        System.out.println("TEST=== EntityStatusControllerCalled===");
    	HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        
    	Map<String, String[]> theParams = webRequest.getParameterMap();
                
        List<EntityStatus> result;
//        if (theGroupId != null) {
            result = EntityStatus.findAllWithParams(theParams);
//        } else {
//            result = Entity.findAllEntitys();
//        }
        return new ResponseEntity<String>(EntityStatus.toJsonArray(result), headers, HttpStatus.OK);
    }
	
	
	
}
