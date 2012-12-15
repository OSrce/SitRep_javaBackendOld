package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrAssets;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srassetses")
@Controller
@RooWebScaffold(path = "srassetses", formBackingObject = SrAssets.class)
@RooWebJson(jsonObject = SrAssets.class)
public class SrAssetsController {
}
