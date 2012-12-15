package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrAclPermissions;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/sraclpermissionses")
@Controller
@RooWebScaffold(path = "sraclpermissionses", formBackingObject = SrAclPermissions.class)
@RooWebJson(jsonObject = SrAclPermissions.class)
public class SrAclPermissionsController {
}
