package org.broadleafcommerce.menu.admin.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.broadleafcommerce.openadmin.web.controller.entity.AdminBasicEntityController;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping({"/menu"})
public class AdminMenuController extends AdminBasicEntityController {

   protected static final String SECTION_KEY = "menu";


   protected String getSectionKey(Map pathVars) {
      return super.getSectionKey(pathVars) != null?super.getSectionKey(pathVars):"menu";
   }

   @GetMapping(value = {"/{id}/{collectionField:.*}/add"}   )
   public String showAddCollectionItem(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable Map pathVars, @PathVariable("id") String id, @PathVariable("collectionField") String collectionField, @RequestParam MultiValueMap requestParams) throws Exception {
      String view = super.showAddCollectionItem(request, response, model, pathVars, id, collectionField, requestParams);
      model.addAttribute("additionalControllerClasses", "menu-item-form");
      return view;
   }

   @GetMapping(value = {"/{id}/{collectionField:.*}/{collectionItemId}"})
   public String showUpdateCollectionItem(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable Map pathVars, @PathVariable("id") String id, @PathVariable("collectionField") String collectionField, @PathVariable("collectionItemId") String collectionItemId) throws Exception {
      String view = super.showUpdateCollectionItem(request, response, model, pathVars, id, collectionField, collectionItemId);
      model.addAttribute("additionalControllerClasses", "menu-item-form");
      return view;
   }
}
