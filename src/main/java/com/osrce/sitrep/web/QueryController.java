package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.Query;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/querys")
@Controller
@RooWebScaffold(path = "querys", formBackingObject = Query.class)
@RooWebJson(jsonObject = Query.class)
public class QueryController {
}
