// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrLocations;
import com.osrce.sitrep.web.SrLocationsController;
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

privileged aspect SrLocationsController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String SrLocationsController.create(@Valid SrLocations srLocations, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, srLocations);
            return "srlocationses/create";
        }
        uiModel.asMap().clear();
        srLocations.persist();
        return "redirect:/srlocationses/" + encodeUrlPathSegment(srLocations.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String SrLocationsController.createForm(Model uiModel) {
        populateEditForm(uiModel, new SrLocations());
        return "srlocationses/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String SrLocationsController.show(@PathVariable("id") Integer id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("srlocations", SrLocations.findSrLocations(id));
        uiModel.addAttribute("itemId", id);
        return "srlocationses/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String SrLocationsController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("srlocationses", SrLocations.findSrLocationsEntries(firstResult, sizeNo));
            float nrOfPages = (float) SrLocations.countSrLocationses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("srlocationses", SrLocations.findAllSrLocationses());
        }
        addDateTimeFormatPatterns(uiModel);
        return "srlocationses/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String SrLocationsController.update(@Valid SrLocations srLocations, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, srLocations);
            return "srlocationses/update";
        }
        uiModel.asMap().clear();
        srLocations.merge();
        return "redirect:/srlocationses/" + encodeUrlPathSegment(srLocations.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String SrLocationsController.updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel, SrLocations.findSrLocations(id));
        return "srlocationses/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String SrLocationsController.delete(@PathVariable("id") Integer id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        SrLocations srLocations = SrLocations.findSrLocations(id);
        srLocations.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/srlocationses";
    }
    
    void SrLocationsController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("srLocations_createdon_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("srLocations_lastused_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void SrLocationsController.populateEditForm(Model uiModel, SrLocations srLocations) {
        uiModel.addAttribute("srLocations", srLocations);
        addDateTimeFormatPatterns(uiModel);
    }
    
    String SrLocationsController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
