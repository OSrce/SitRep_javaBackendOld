// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.SrAssetsStatus;
import com.osrce.sitrep.web.SrAssetsStatusController;
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

privileged aspect SrAssetsStatusController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String SrAssetsStatusController.create(@Valid SrAssetsStatus srAssetsStatus, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, srAssetsStatus);
            return "srassetsstatuses/create";
        }
        uiModel.asMap().clear();
        srAssetsStatus.persist();
        return "redirect:/srassetsstatuses/" + encodeUrlPathSegment(srAssetsStatus.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String SrAssetsStatusController.createForm(Model uiModel) {
        populateEditForm(uiModel, new SrAssetsStatus());
        return "srassetsstatuses/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String SrAssetsStatusController.show(@PathVariable("id") Integer id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("srassetsstatus", SrAssetsStatus.findSrAssetsStatus(id));
        uiModel.addAttribute("itemId", id);
        return "srassetsstatuses/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String SrAssetsStatusController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("srassetsstatuses", SrAssetsStatus.findSrAssetsStatusEntries(firstResult, sizeNo));
            float nrOfPages = (float) SrAssetsStatus.countSrAssetsStatuses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("srassetsstatuses", SrAssetsStatus.findAllSrAssetsStatuses());
        }
        addDateTimeFormatPatterns(uiModel);
        return "srassetsstatuses/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String SrAssetsStatusController.update(@Valid SrAssetsStatus srAssetsStatus, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, srAssetsStatus);
            return "srassetsstatuses/update";
        }
        uiModel.asMap().clear();
        srAssetsStatus.merge();
        return "redirect:/srassetsstatuses/" + encodeUrlPathSegment(srAssetsStatus.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String SrAssetsStatusController.updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel, SrAssetsStatus.findSrAssetsStatus(id));
        return "srassetsstatuses/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String SrAssetsStatusController.delete(@PathVariable("id") Integer id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        SrAssetsStatus srAssetsStatus = SrAssetsStatus.findSrAssetsStatus(id);
        srAssetsStatus.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/srassetsstatuses";
    }
    
    void SrAssetsStatusController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("srAssetsStatus_srstart_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("srAssetsStatus_srend_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("srAssetsStatus_updatedon_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void SrAssetsStatusController.populateEditForm(Model uiModel, SrAssetsStatus srAssetsStatus) {
        uiModel.addAttribute("srAssetsStatus", srAssetsStatus);
        addDateTimeFormatPatterns(uiModel);
    }
    
    String SrAssetsStatusController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
