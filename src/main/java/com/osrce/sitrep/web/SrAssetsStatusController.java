package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrAssetsStatus;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srassetsstatuses")
@Controller
@RooWebScaffold(path = "srassetsstatuses", formBackingObject = SrAssetsStatus.class)
@RooWebJson(jsonObject = SrAssetsStatus.class)
public class SrAssetsStatusController {
}
