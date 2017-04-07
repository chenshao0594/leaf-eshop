package org.broadleafcommerce.menu.processor;

import org.broadleafcommerce.common.extension.ExtensionManager;
import org.broadleafcommerce.menu.processor.MenuProcessorExtensionHandler;
import org.broadleafcommerce.presentation.condition.ConditionalOnTemplating;
import org.springframework.stereotype.Service;

@Service("blMenuProcessorExtensionManager")
@ConditionalOnTemplating
public class MenuProcessorExtensionManager extends ExtensionManager {

   public MenuProcessorExtensionManager() {
      super(MenuProcessorExtensionHandler.class);
   }

   public boolean continueOnHandled() {
      return true;
   }
}
