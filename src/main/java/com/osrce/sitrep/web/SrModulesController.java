package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrModules;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srmoduleses")
@Controller
@RooWebScaffold(path = "srmoduleses", formBackingObject = SrModules.class)
@RooWebJson(jsonObject = SrModules.class)
public class SrModulesController {
}
