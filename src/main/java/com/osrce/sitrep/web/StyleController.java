package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.Style;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/styles")
@Controller
@RooWebScaffold(path = "styles", formBackingObject = Style.class)
@RooWebJson(jsonObject = Style.class)
public class StyleController {
}
