package com.osrce.sitrep.web;

import java.util.List;

import com.osrce.sitrep.domain.Entity;
import com.osrce.sitrep.domain.SrLayerStaticData;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
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
    public ResponseEntity<String> listJson( @RequestParam(value = "layer_id", required = false) String layerid) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
//        List<Entity> result = Entity.findAllEntitys();
        
        List<Entity> result = Entity.findAllCurrentEntitysWithStatus();
        
        
        System.out.println("TEST=Entitys==" + Entity.toJsonArray(result));

        return new ResponseEntity<String>(Entity.toJsonArray(result), headers, HttpStatus.OK);
    }
    
	
}
