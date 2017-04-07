package org.broadleafcommerce.menu.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.broadleafcommerce.core.catalog.domain.Category;
import org.broadleafcommerce.core.catalog.domain.CategoryXref;
import org.broadleafcommerce.core.catalog.service.CatalogService;
import org.broadleafcommerce.menu.dao.MenuDao;
import org.broadleafcommerce.menu.domain.Menu;
import org.broadleafcommerce.menu.domain.MenuItem;
import org.broadleafcommerce.menu.dto.MenuItemDTO;
import org.broadleafcommerce.menu.service.MenuService;
import org.broadleafcommerce.menu.type.MenuItemType;
import org.springframework.stereotype.Service;

@Service("blMenuService")
public class MenuServiceImpl implements MenuService {

   @Resource(
      name = "blMenuDao"
   )
   protected MenuDao menuDao;
   @Resource(
      name = "blCatalogService"
   )
   protected CatalogService catalogService;


   public Menu findMenuById(Long id) {
      return this.menuDao.readMenuById(id);
   }

   public Menu findMenuByName(String menuName) {
      return this.menuDao.readMenuByName(menuName);
   }

   public MenuItem findMenuItemById(Long menuItemId) {
      return this.menuDao.readMenuItemById(menuItemId);
   }

   public List constructMenuItemDTOsForMenu(Menu menu) {
      ArrayList dtos = new ArrayList();
      if(CollectionUtils.isNotEmpty(menu.getMenuItems())) {
         Iterator i$ = menu.getMenuItems().iterator();

         while(i$.hasNext()) {
            MenuItem menuItem = (MenuItem)i$.next();
            MenuItemDTO menuItemDTO = this.convertMenuItemToDTO(menuItem);
            if(menuItemDTO != null) {
               dtos.add(menuItemDTO);
            }
         }
      }

      return dtos;
   }

   protected MenuItemDTO convertMenuItemToDTO(MenuItem menuItem) {
      MenuItemDTO dto;
      if(MenuItemType.SUBMENU.equals(menuItem.getMenuItemType()) && menuItem.getLinkedMenu() != null) {
         dto = new MenuItemDTO();
         dto.setUrl(menuItem.getDerivedUrl());
         dto.setLabel(menuItem.getDerivedLabel());
         ArrayList submenu = new ArrayList();
         List items = menuItem.getLinkedMenu().getMenuItems();
         if(CollectionUtils.isNotEmpty(items)) {
            Iterator i$ = items.iterator();

            while(i$.hasNext()) {
               MenuItem item = (MenuItem)i$.next();
               submenu.add(this.convertMenuItemToDTO(item));
            }
         }

         dto.setSubmenu(submenu);
         return dto;
      } else if(MenuItemType.CATEGORY.equals(menuItem.getMenuItemType())) {
         Category dto1 = this.catalogService.findCategoryByURI(menuItem.getActionUrl());
         return dto1 != null?this.convertCategoryToMenuItemDTO(dto1):null;
      } else {
         dto = new MenuItemDTO();
         dto.setUrl(menuItem.getDerivedUrl());
         dto.setLabel(menuItem.getDerivedLabel());
         if(menuItem.getImage() != null) {
            dto.setImageUrl(menuItem.getImage().getUrl());
            dto.setAltText(menuItem.getAltText());
         }

         return dto;
      }
   }

   protected MenuItemDTO convertCategoryToMenuItemDTO(Category category) {
      MenuItemDTO dto = new MenuItemDTO();
      dto.setLabel(category.getName());
      dto.setUrl(category.getUrl());
      dto.setCategoryId(category.getId());
      List categoryXrefs = category.getChildCategoryXrefs();
      if(CollectionUtils.isNotEmpty(categoryXrefs)) {
         ArrayList submenu = new ArrayList();
         Iterator i$ = categoryXrefs.iterator();

         while(i$.hasNext()) {
            CategoryXref xref = (CategoryXref)i$.next();
            submenu.add(this.convertCategoryToMenuItemDTO(xref.getSubCategory()));
         }

         dto.setSubmenu(submenu);
      }

      return dto;
   }
}
