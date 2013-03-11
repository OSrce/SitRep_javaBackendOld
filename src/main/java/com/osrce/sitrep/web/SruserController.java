package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.Sruser;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srusers")
@Controller
@RooWebScaffold(path = "srusers", formBackingObject = Sruser.class)
@RooWebJson(jsonObject = Sruser.class)
public class SruserController {
}
