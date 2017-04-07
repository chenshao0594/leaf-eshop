package org.broadleafcommerce.menu.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import org.broadleafcommerce.cms.page.domain.Page;
import org.broadleafcommerce.common.copy.MultiTenantCloneable;
import org.broadleafcommerce.common.media.domain.Media;
import org.broadleafcommerce.menu.type.MenuItemType;

public interface MenuItem extends Serializable, MultiTenantCloneable {

   Long getId();

   void setId(Long var1);

   String getLabel();

   void setLabel(String var1);

   MenuItemType getMenuItemType();

   void setMenuItemType(MenuItemType var1);

   String getActionUrl();

   void setActionUrl(String var1);

   Media getImage();

   void setImage(Media var1);

   Menu getParentMenu();

   void setParentMenu(Menu var1);

   Menu getLinkedMenu();

   void setLinkedMenu(Menu var1);

   void setSequence(BigDecimal var1);

   BigDecimal getSequence();

   String getAltText();

   void setAltText(String var1);

   Page getLinkedPage();

   void setLinkedPage(Page var1);

   String getCustomHtml();

   void setCustomHtml(String var1);

   String getDerivedUrl();

   String getDerivedLabel();
}
