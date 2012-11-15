package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrStyleSymbolizers;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srstylesymbolizerses")
@Controller
@RooWebScaffold(path = "srstylesymbolizerses", formBackingObject = SrStyleSymbolizers.class)
public class SrStyleSymbolizersController {
}
