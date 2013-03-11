package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.Ssymbolizer;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/ssymbolizers")
@Controller
@RooWebScaffold(path = "ssymbolizers", formBackingObject = Ssymbolizer.class)
@RooWebJson(jsonObject = Ssymbolizer.class)
public class SsymbolizerController {
}
