package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrStyles;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srstyleses")
@Controller
@RooWebScaffold(path = "srstyleses", formBackingObject = SrStyles.class)
@RooWebJson(jsonObject = SrStyles.class)
public class SrStylesController {
}
