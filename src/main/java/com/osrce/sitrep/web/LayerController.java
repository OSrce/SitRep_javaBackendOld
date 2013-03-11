package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.Layer;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/layers")
@Controller
@RooWebScaffold(path = "layers", formBackingObject = Layer.class)
@RooWebJson(jsonObject = Layer.class)
public class LayerController {
}
