package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrGroups;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srgroupses")
@Controller
@RooWebScaffold(path = "srgroupses", formBackingObject = SrGroups.class)
public class SrGroupsController {
}
