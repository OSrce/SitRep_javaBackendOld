// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.Srgroup;
import com.osrce.sitrep.web.SrgroupController;
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

privileged aspect SrgroupController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String SrgroupController.create(@Valid Srgroup srgroup, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, srgroup);
            return "srgroups/create";
        }
        uiModel.asMap().clear();
        srgroup.persist();
        return "redirect:/srgroups/" + encodeUrlPathSegment(srgroup.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String SrgroupController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Srgroup());
        return "srgroups/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String SrgroupController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("srgroup", Srgroup.findSrgroup(id));
        uiModel.addAttribute("itemId", id);
        return "srgroups/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String SrgroupController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("srgroups", Srgroup.findSrgroupEntries(firstResult, sizeNo));
            float nrOfPages = (float) Srgroup.countSrgroups() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("srgroups", Srgroup.findAllSrgroups());
        }
        addDateTimeFormatPatterns(uiModel);
        return "srgroups/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String SrgroupController.update(@Valid Srgroup srgroup, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, srgroup);
            return "srgroups/update";
        }
        uiModel.asMap().clear();
        srgroup.merge();
        return "redirect:/srgroups/" + encodeUrlPathSegment(srgroup.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String SrgroupController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Srgroup.findSrgroup(id));
        return "srgroups/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String SrgroupController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Srgroup srgroup = Srgroup.findSrgroup(id);
        srgroup.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/srgroups";
    }
    
    void SrgroupController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("srgroup_created_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("srgroup_updated_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void SrgroupController.populateEditForm(Model uiModel, Srgroup srgroup) {
        uiModel.addAttribute("srgroup", srgroup);
        addDateTimeFormatPatterns(uiModel);
    }
    
    String SrgroupController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
