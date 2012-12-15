package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrStyleRules;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srstyleruleses")
@Controller
@RooWebScaffold(path = "srstyleruleses", formBackingObject = SrStyleRules.class)
@RooWebJson(jsonObject = SrStyleRules.class)
public class SrStyleRulesController {
}
