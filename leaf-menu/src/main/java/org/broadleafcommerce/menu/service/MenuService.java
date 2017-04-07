package org.broadleafcommerce.menu.service;

import java.util.List;
import org.broadleafcommerce.menu.domain.Menu;
import org.broadleafcommerce.menu.domain.MenuItem;

public interface MenuService {

   Menu findMenuById(Long var1);

   Menu findMenuByName(String var1);

   MenuItem findMenuItemById(Long var1);

   List constructMenuItemDTOsForMenu(Menu var1);
}
