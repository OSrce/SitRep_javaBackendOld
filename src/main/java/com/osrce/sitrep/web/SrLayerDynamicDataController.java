package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrCfs;
import com.osrce.sitrep.domain.SrLayerDynamicData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

@RequestMapping("/srlayerdynamicdatas")
@Controller
@RooWebScaffold(path = "srlayerdynamicdatas", formBackingObject = SrLayerDynamicData.class)
@RooWebJson(jsonObject = SrLayerDynamicData.class)
public class SrLayerDynamicDataController {

    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<java.lang.String> listJson(@RequestParam(value = "layer_id", required = false) Integer theLayerId, @RequestParam(value = "feature_end", required = false) String theFeatureEndStr) {
        HttpHeaders headers = new HttpHeaders();
        Date theFeatureEnd = null;
        System.out.println("TEST===" + theLayerId + "===" + theFeatureEndStr);
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<SrLayerDynamicData> result;
        if (theLayerId != null) {
            result = SrLayerDynamicData.findAllWithLayerId(theLayerId);
        } else {
            result = SrLayerDynamicData.findAllSrLayerDynamicDatas();
        }
        return new ResponseEntity<String>(SrLayerDynamicData.toJsonArray(result), headers, HttpStatus.OK);
    }
}
