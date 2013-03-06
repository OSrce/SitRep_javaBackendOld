package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrCfs;
import com.osrce.sitrep.domain.SrLayerDynamicData;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/srcfses")
@Controller
@RooWebScaffold(path = "srcfses", formBackingObject = SrCfs.class)
@RooWebJson(jsonObject = SrCfs.class)
public class SrCfsController {

    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<java.lang.String> listJson(@RequestParam(value = "SREXPR", required = false) String srexpr, @RequestParam(value = "cfs_date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date cfs_date, @RequestParam(value = "cfs_num", required = false) Integer cfs_num) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<SrCfs> result;
        if (srexpr != null) {
            result = SrCfs.findAllSrCfsWithQuery(srexpr);
        } else if (cfs_date != null && cfs_num != null) {
            srexpr = "( cfs_date='" + cfs_date + "' AND cfs_num=" + cfs_num + " ) ";
            result = SrCfs.findAllSrCfsWithQuery(srexpr);
        } else {
            result = SrCfs.findAllSrCfses();
        }
        return new ResponseEntity<String>(SrCfs.toJsonArray(result), headers, HttpStatus.OK);
    }
}
