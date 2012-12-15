package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrLayerStaticData;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srlayerstaticdatas")
@Controller
@RooWebScaffold(path = "srlayerstaticdatas", formBackingObject = SrLayerStaticData.class)
@RooWebJson(jsonObject = SrLayerStaticData.class)
public class SrLayerStaticDataController {
}
