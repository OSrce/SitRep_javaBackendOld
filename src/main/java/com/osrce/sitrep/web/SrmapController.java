package com.osrce.sitrep.web;


import java.util.Date;
import java.util.List;

import com.osrce.sitrep.domain.Srmap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/srmaps")
@Controller
@RooWebScaffold(path = "srmaps", formBackingObject = Srmap.class)
@RooWebJson(jsonObject = Srmap.class)
public class SrmapController {

    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<java.lang.String> listJson(@RequestParam(value = "group_id", required = false) Long theGroupId, @RequestParam(value = "has_data", required = false) Boolean theHasData) {
        System.out.println("TEST=== SrmapControllerCalled===" + theGroupId + "===" + theHasData);
    	HttpHeaders headers = new HttpHeaders();
//        Date theFeatureEnd = null;
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<Srmap> result;
        if (theGroupId != null) {
            result = Srmap.findAllWithGroupId(theGroupId);
        } else {
            result = Srmap.findAllSrmaps();
        }
        return new ResponseEntity<String>(Srmap.toJsonArray(result), headers, HttpStatus.OK);
    }	
	
	
	
	
}