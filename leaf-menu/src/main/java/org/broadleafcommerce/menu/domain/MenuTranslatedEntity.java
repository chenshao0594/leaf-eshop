package org.broadleafcommerce.menu.domain;

import org.broadleafcommerce.common.i18n.domain.TranslatedEntity;
import org.springframework.stereotype.Component;

@Component("blMenuTranslatedEntity")
public class MenuTranslatedEntity extends TranslatedEntity {

   private static final long serialVersionUID = -1L;
   public static final TranslatedEntity MENU = new TranslatedEntity("org.broadleafcommerce.menu.domain.Menu", "Menu");
   public static final TranslatedEntity MENU_ITEM = new TranslatedEntity("org.broadleafcommerce.menu.domain.MenuItem", "MenuItem");


}
