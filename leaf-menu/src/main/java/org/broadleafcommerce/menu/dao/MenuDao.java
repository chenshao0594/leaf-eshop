package org.broadleafcommerce.menu.dao;

import java.util.List;
import org.broadleafcommerce.menu.domain.Menu;
import org.broadleafcommerce.menu.domain.MenuItem;

public interface MenuDao {

   List readAllMenus();

   List readAllMenuItems();

   Menu readMenuById(Long var1);

   MenuItem readMenuItemById(Long var1);

   Menu readMenuByName(String var1);

   Menu saveMenu(Menu var1);

   MenuItem saveMenuItem(MenuItem var1);
}
