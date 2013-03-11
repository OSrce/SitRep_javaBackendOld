// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.osrce.sitrep.web;

import com.osrce.sitrep.domain.Entity;
import com.osrce.sitrep.web.EntityController;
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

privileged aspect EntityController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String EntityController.create(@Valid Entity entity, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, entity);
            return "entitys/create";
        }
        uiModel.asMap().clear();
        entity.persist();
        return "redirect:/entitys/" + encodeUrlPathSegment(entity.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String EntityController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Entity());
        return "entitys/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String EntityController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("entity", Entity.findEntity(id));
        uiModel.addAttribute("itemId", id);
        return "entitys/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String EntityController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("entitys", Entity.findEntityEntries(firstResult, sizeNo));
            float nrOfPages = (float) Entity.countEntitys() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("entitys", Entity.findAllEntitys());
        }
        addDateTimeFormatPatterns(uiModel);
        return "entitys/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String EntityController.update(@Valid Entity entity, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, entity);
            return "entitys/update";
        }
        uiModel.asMap().clear();
        entity.merge();
        return "redirect:/entitys/" + encodeUrlPathSegment(entity.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String EntityController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Entity.findEntity(id));
        return "entitys/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String EntityController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Entity entity = Entity.findEntity(id);
        entity.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/entitys";
    }
    
    void EntityController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("entity_created_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("entity_updated_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    String EntityController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
