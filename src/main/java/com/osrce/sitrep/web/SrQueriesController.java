package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrQueries;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srquerieses")
@Controller
@RooWebScaffold(path = "srquerieses", formBackingObject = SrQueries.class)
@RooWebJson(jsonObject = SrQueries.class)
public class SrQueriesController {
}
