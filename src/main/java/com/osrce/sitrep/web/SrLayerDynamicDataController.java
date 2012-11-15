package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrLayerDynamicData;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srlayerdynamicdatas")
@Controller
@RooWebScaffold(path = "srlayerdynamicdatas", formBackingObject = SrLayerDynamicData.class)
public class SrLayerDynamicDataController {
}
