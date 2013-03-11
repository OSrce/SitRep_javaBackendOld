package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.Srule;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srules")
@Controller
@RooWebScaffold(path = "srules", formBackingObject = Srule.class)
@RooWebJson(jsonObject = Srule.class)
public class SruleController {
}
