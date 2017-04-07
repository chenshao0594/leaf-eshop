package org.broadleafcommerce.menu.processor;

import java.util.Map;
import org.broadleafcommerce.common.extension.AbstractExtensionHandler;
import org.broadleafcommerce.common.extension.ExtensionResultStatusType;
import org.broadleafcommerce.menu.processor.MenuProcessorExtensionHandler;
import org.broadleafcommerce.presentation.model.BroadleafTemplateContext;

public abstract class AbstractMenuProcessorExtensionHandler extends AbstractExtensionHandler implements MenuProcessorExtensionHandler {

   public ExtensionResultStatusType addAdditionalFieldsToModel(String tagName, Map tagAttributes, Map newModelVars, BroadleafTemplateContext context) {
      return ExtensionResultStatusType.NOT_HANDLED;
   }
}
