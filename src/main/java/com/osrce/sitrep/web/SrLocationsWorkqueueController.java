package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrLocationsWorkqueue;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srlocationsworkqueues")
@Controller
@RooWebScaffold(path = "srlocationsworkqueues", formBackingObject = SrLocationsWorkqueue.class)
@RooWebJson(jsonObject = SrLocationsWorkqueue.class)
public class SrLocationsWorkqueueController {
}
