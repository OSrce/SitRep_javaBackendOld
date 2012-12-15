package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrCfsUpdatequeue;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srcfsupdatequeues")
@Controller
@RooWebScaffold(path = "srcfsupdatequeues", formBackingObject = SrCfsUpdatequeue.class)
@RooWebJson(jsonObject = SrCfsUpdatequeue.class)
public class SrCfsUpdatequeueController {
}
