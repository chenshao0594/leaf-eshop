package org.broadleafcommerce.menu.processor;

import java.util.Map;
import org.broadleafcommerce.common.extension.ExtensionHandler;
import org.broadleafcommerce.common.extension.ExtensionResultStatusType;
import org.broadleafcommerce.presentation.model.BroadleafTemplateContext;

public interface MenuProcessorExtensionHandler extends ExtensionHandler {

   ExtensionResultStatusType addAdditionalFieldsToModel(String var1, Map var2, Map var3, BroadleafTemplateContext var4);
}
