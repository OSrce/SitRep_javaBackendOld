package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrQueryMonitorResults;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srquerymonitorresultses")
@Controller
@RooWebScaffold(path = "srquerymonitorresultses", formBackingObject = SrQueryMonitorResults.class)
@RooWebJson(jsonObject = SrQueryMonitorResults.class)
public class SrQueryMonitorResultsController {
}
