package org.broadleafcommerce.menu.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.broadleafcommerce.common.util.dao.TypedQueryBuilder;
import org.broadleafcommerce.menu.dao.MenuDao;
import org.broadleafcommerce.menu.domain.Menu;
import org.broadleafcommerce.menu.domain.MenuImpl;
import org.broadleafcommerce.menu.domain.MenuItem;
import org.broadleafcommerce.menu.domain.MenuItemImpl;
import org.springframework.stereotype.Repository;

@Repository("blMenuDao")
public class MenuDaoImpl implements MenuDao {

   @PersistenceContext(
      unitName = "blPU"
   )
   protected EntityManager em;


   public List readAllMenus() {
      TypedQuery q = (new TypedQueryBuilder(Menu.class, "m")).toQuery(this.em);
      return q.getResultList();
   }

   public List readAllMenuItems() {
      TypedQuery q = (new TypedQueryBuilder(MenuItem.class, "mi")).toQuery(this.em);
      return q.getResultList();
   }

   public Menu readMenuById(Long menuId) {
      return (Menu)this.em.find(MenuImpl.class, menuId);
   }

   public MenuItem readMenuItemById(Long menuItemId) {
      return (MenuItem)this.em.find(MenuItemImpl.class, menuItemId);
   }

   public Menu readMenuByName(String menuName) {
      TypedQuery query = this.em.createNamedQuery("BC_READ_MENU_BY_NAME", Menu.class);
      query.setParameter("menuName", menuName);
      query.setHint("org.hibernate.cacheable", Boolean.valueOf(true));
      query.setHint("org.hibernate.cacheRegion", "query.Cms");
      List results = query.getResultList();
      return !results.isEmpty()?(Menu)results.get(0):null;
   }

   public Menu saveMenu(Menu menu) {
      return (Menu)this.em.merge(menu);
   }

   public MenuItem saveMenuItem(MenuItem menuItem) {
      return (MenuItem)this.em.merge(menuItem);
   }
}
