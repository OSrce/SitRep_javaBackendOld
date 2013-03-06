package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrQueryMonitor;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srquerymonitors")
@Controller
@RooWebScaffold(path = "srquerymonitors", formBackingObject = SrQueryMonitor.class)
@RooWebJson(jsonObject = SrQueryMonitor.class)
public class SrQueryMonitorController {
}
