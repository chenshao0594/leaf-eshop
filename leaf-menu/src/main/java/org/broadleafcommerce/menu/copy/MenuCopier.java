package org.broadleafcommerce.menu.copy;

import org.broadleafcommerce.common.copy.MultiTenantCopier;
import org.broadleafcommerce.common.copy.MultiTenantCopyContext;
import org.broadleafcommerce.common.site.domain.Catalog;
import org.broadleafcommerce.common.site.domain.Site;
import org.broadleafcommerce.menu.domain.MenuImpl;

public class MenuCopier extends MultiTenantCopier {

   public void copyEntities(MultiTenantCopyContext context) throws Exception {
      Site fromSite = context.getFromSite();
      Catalog fromCatalog = context.getFromCatalog();
      this.copyEntitiesOfType(MenuImpl.class, fromSite, fromCatalog, context);
   }
}
