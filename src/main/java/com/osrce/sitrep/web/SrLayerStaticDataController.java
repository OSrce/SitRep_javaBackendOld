package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrLayerDynamicData;
import com.osrce.sitrep.domain.SrLayerStaticData;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/srlayerstaticdatas")
@Controller
@RooWebScaffold(path = "srlayerstaticdatas", formBackingObject = SrLayerStaticData.class)
@RooWebJson(jsonObject = SrLayerStaticData.class)
public class SrLayerStaticDataController {

    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<java.lang.String> listJson(@RequestParam(value = "layer_id", required = false) Integer theLayerId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<SrLayerStaticData> result;
        if (theLayerId != null) {
            result = SrLayerStaticData.findAllWithLayerId(theLayerId);
        } else {
            result = SrLayerStaticData.findAllSrLayerStaticDatas();
        }
        System.out.println("TEST=Static==" + SrLayerStaticData.toJsonArray(result));
        return new ResponseEntity<String>(SrLayerStaticData.toJsonArray(result), headers, HttpStatus.OK);
    }
}
