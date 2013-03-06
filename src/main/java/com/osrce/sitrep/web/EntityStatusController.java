package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.EntityStatus;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/entitystatuses")
@Controller
@RooWebScaffold(path = "entitystatuses", formBackingObject = EntityStatus.class)
@RooWebJson(jsonObject = EntityStatus.class)
public class EntityStatusController {
}
