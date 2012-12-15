package com.osrce.sitrep.web;

import java.util.List;

import com.osrce.sitrep.domain.SrCfs;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/srcfses")
@Controller
@RooWebScaffold(path = "srcfses", formBackingObject = SrCfs.class)
@RooWebJson(jsonObject = SrCfs.class)
public class SrCfsController {
	
    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson( @RequestParam(value="SREXPR", required=false) String srexpr ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<SrCfs> result;
        if( srexpr != null ) {
        	result = SrCfs.findAllSrCfsWithQuery(srexpr);
        } else  {
        	result = SrCfs.findAllSrCfses();
        }
        return new ResponseEntity<String>(SrCfs.toJsonArray(result), headers, HttpStatus.OK);
    }	
	
	
	
}
