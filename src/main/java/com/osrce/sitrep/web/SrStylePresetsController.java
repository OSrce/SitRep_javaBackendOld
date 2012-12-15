package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrStylePresets;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srstylepresetses")
@Controller
@RooWebScaffold(path = "srstylepresetses", formBackingObject = SrStylePresets.class)
@RooWebJson(jsonObject = SrStylePresets.class)
public class SrStylePresetsController {
}
