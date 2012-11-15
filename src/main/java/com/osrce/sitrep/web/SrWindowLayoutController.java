package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrWindowLayout;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/srwindowlayouts")
@Controller
@RooWebScaffold(path = "srwindowlayouts", formBackingObject = SrWindowLayout.class)
public class SrWindowLayoutController {
}
