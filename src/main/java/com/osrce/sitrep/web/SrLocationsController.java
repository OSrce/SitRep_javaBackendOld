package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrLocations;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srlocationses")
@Controller
@RooWebScaffold(path = "srlocationses", formBackingObject = SrLocations.class)
@RooWebJson(jsonObject = SrLocations.class)
public class SrLocationsController {
}
