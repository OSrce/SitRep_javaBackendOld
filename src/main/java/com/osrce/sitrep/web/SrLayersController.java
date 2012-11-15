package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrLayers;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srlayerses")
@Controller
@RooWebScaffold(path = "srlayerses", formBackingObject = SrLayers.class)
public class SrLayersController {
}
