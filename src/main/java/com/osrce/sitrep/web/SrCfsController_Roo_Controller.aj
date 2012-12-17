// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrCfs;
import com.osrce.sitrep.domain.SrLocations;
import com.osrce.sitrep.web.SrCfsController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect SrCfsController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String SrCfsController.create(@Valid SrCfs srCfs, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, srCfs);
            return "srcfses/create";
        }
        uiModel.asMap().clear();
        srCfs.persist();
        return "redirect:/srcfses/" + encodeUrlPathSegment(srCfs.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String SrCfsController.createForm(Model uiModel) {
        populateEditForm(uiModel, new SrCfs());
        return "srcfses/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String SrCfsController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("srcfs", SrCfs.findSrCfs(id));
        uiModel.addAttribute("itemId", id);
        return "srcfses/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String SrCfsController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("srcfses", SrCfs.findSrCfsEntries(firstResult, sizeNo));
            float nrOfPages = (float) SrCfs.countSrCfses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("srcfses", SrCfs.findAllSrCfses());
        }
        addDateTimeFormatPatterns(uiModel);
        return "srcfses/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String SrCfsController.update(@Valid SrCfs srCfs, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, srCfs);
            return "srcfses/update";
        }
        uiModel.asMap().clear();
        srCfs.merge();
        return "redirect:/srcfses/" + encodeUrlPathSegment(srCfs.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String SrCfsController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, SrCfs.findSrCfs(id));
        return "srcfses/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String SrCfsController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        SrCfs srCfs = SrCfs.findSrCfs(id);
        srCfs.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/srcfses";
    }
    
    void SrCfsController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("srCfs_cfs_date_date_format", "yyyy-mm-dd");
        uiModel.addAttribute("srCfs_cfs_timecreated_date_format", "hh:mm:ss");
        uiModel.addAttribute("srCfs_cfs_timeassigned_date_format", "hh:mm:ss");
        uiModel.addAttribute("srCfs_cfs_finaldisdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("srCfs_cfs_created_on_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("srCfs_cfs_updated_on_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void SrCfsController.populateEditForm(Model uiModel, SrCfs srCfs) {
        uiModel.addAttribute("srCfs", srCfs);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("srlocationses", SrLocations.findAllSrLocationses());
    }
    
    String SrCfsController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
