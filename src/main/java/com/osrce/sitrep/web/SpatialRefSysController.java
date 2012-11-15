package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SpatialRefSys;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/spatialrefsyses")
@Controller
@RooWebScaffold(path = "spatialrefsyses", formBackingObject = SpatialRefSys.class)
public class SpatialRefSysController {
}
