package org.broadleafcommerce.menu.processor;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.broadleafcommerce.menu.domain.Menu;
import org.broadleafcommerce.menu.processor.MenuProcessorExtensionHandler;
import org.broadleafcommerce.menu.processor.MenuProcessorExtensionManager;
import org.broadleafcommerce.menu.service.MenuService;
import org.broadleafcommerce.presentation.condition.ConditionalOnTemplating;
import org.broadleafcommerce.presentation.dialect.AbstractBroadleafVariableModifierProcessor;
import org.broadleafcommerce.presentation.model.BroadleafTemplateContext;
import org.springframework.stereotype.Component;

@Component("blMenuProcessor")
@ConditionalOnTemplating
public class MenuProcessor extends AbstractBroadleafVariableModifierProcessor {

   @Resource(
      name = "blMenuService"
   )
   protected MenuService menuService;
   @Resource(
      name = "blMenuProcessorExtensionManager"
   )
   protected MenuProcessorExtensionManager extensionManager;


   public String getName() {
      return "menu";
   }

   public int getPrecedence() {
      return 1000;
   }

   public Map populateModelVariables(String tagName, Map tagAttributes, BroadleafTemplateContext context) {
      String resultVar = (String)tagAttributes.get("resultVar");
      String menuName = (String)tagAttributes.get("menuName");
      String menuId = (String)tagAttributes.get("menuId");
      Menu menu;
      if(menuId != null) {
         menu = this.menuService.findMenuById(Long.valueOf(Long.parseLong(menuId)));
      } else {
         menu = this.menuService.findMenuByName(menuName);
      }

      HashMap newModelVars = new HashMap();
      if(menu != null) {
         newModelVars.put(resultVar, this.menuService.constructMenuItemDTOsForMenu(menu));
         ((MenuProcessorExtensionHandler)this.extensionManager.getProxy()).addAdditionalFieldsToModel(tagName, tagAttributes, newModelVars, context);
      }

      return newModelVars;
   }
}
