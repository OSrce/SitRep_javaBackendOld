package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrUsers;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/sruserses")
@Controller
@RooWebScaffold(path = "sruserses", formBackingObject = SrUsers.class)
public class SrUsersController {
}
