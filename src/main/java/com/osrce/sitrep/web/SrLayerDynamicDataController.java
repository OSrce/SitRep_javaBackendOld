package com.osrce.sitrep.web;

import java.util.List;

import com.osrce.sitrep.domain.SrCfs;
import com.osrce.sitrep.domain.SrLayerDynamicData;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/srlayerdynamicdatas")
@Controller
@RooWebScaffold(path = "srlayerdynamicdatas", formBackingObject = SrLayerDynamicData.class)
@RooWebJson(jsonObject = SrLayerDynamicData.class)
public class SrLayerDynamicDataController {
	

    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson(@RequestParam(value="layer_id", required=false) Integer theLayerId ) {        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");        
        List<SrLayerDynamicData> result; 
        if( theLayerId != null ) {
        	result = SrLayerDynamicData.findAllWithLayerId(theLayerId);
        } else  {
        	result = SrLayerDynamicData.findAllSrLayerDynamicDatas();
        }
        System.out.println( "TEST==="+ SrLayerDynamicData.toJsonArray(result)  );
        
        return new ResponseEntity<String>(SrLayerDynamicData.toJsonArray(result), headers, HttpStatus.OK);
    }
		
	
	
}
