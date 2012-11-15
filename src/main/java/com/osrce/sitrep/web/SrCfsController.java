package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrCfs;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srcfses")
@Controller
@RooWebScaffold(path = "srcfses", formBackingObject = SrCfs.class)
public class SrCfsController {
}
