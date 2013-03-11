package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.Srgroup;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srgroups")
@Controller
@RooWebScaffold(path = "srgroups", formBackingObject = Srgroup.class)
@RooWebJson(jsonObject = Srgroup.class)
public class SrgroupController {
}
