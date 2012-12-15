package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrSession;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srsessions")
@Controller
@RooWebScaffold(path = "srsessions", formBackingObject = SrSession.class)
@RooWebJson(jsonObject = SrSession.class)
public class SrSessionController {
}
