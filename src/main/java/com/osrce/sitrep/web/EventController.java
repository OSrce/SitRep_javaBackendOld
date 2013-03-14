package com.osrce.sitrep.web;

import java.util.List;
import java.util.Map;

import com.osrce.sitrep.domain.EntityStatus;
import com.osrce.sitrep.domain.Event;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/events")
@Controller
@RooWebScaffold(path = "events", formBackingObject = Event.class)
@RooWebJson(jsonObject = Event.class)
public class EventController {
	
    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson(org.springframework.web.context.request.WebRequest webRequest) {
//        System.out.println("TEST=== EventControllerCalled===");
    	HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        
    	Map<String, String[]> theParams = webRequest.getParameterMap();
                
        List<Event> result;
//        if (theGroupId != null) {
            result = Event.findAllWithParams(theParams);
//        } else {
//            result = Entity.findAllEntitys();
//        }
        return new ResponseEntity<String>(Event.toJsonArray(result), headers, HttpStatus.OK);
    }
	
	
	
}
