package org.broadleafcommerce.menu.type;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MenuItemType implements Serializable {

   private static final long serialVersionUID = 1L;
   private static final Map TYPES = new HashMap();
   public static final MenuItemType LINK = new MenuItemType("LINK", "Link");
   public static final MenuItemType CATEGORY = new MenuItemType("CATEGORY", "Category");
   public static final MenuItemType PAGE = new MenuItemType("PAGE", "Page");
   public static final MenuItemType SUBMENU = new MenuItemType("SUBMENU", "Sub Menu");
   public static final MenuItemType PRODUCT = new MenuItemType("PRODUCT", "Product");
   public static final MenuItemType CUSTOM = new MenuItemType("CUSTOM", "Custom");
   private String type;
   private String friendlyType;


   public static MenuItemType getInstance(String type) {
      return (MenuItemType)TYPES.get(type);
   }

   public MenuItemType() {
   }

   public MenuItemType(String type, String friendlyType) {
      this.friendlyType = friendlyType;
      this.setType(type);
   }

   public String getType() {
      return this.type;
   }

   public String getFriendlyType() {
      return this.friendlyType;
   }

   private void setType(String type) {
      this.type = type;
      if(!TYPES.containsKey(type)) {
         TYPES.put(type, this);
      }

   }

   public int hashCode() {
      boolean prime = true;
      byte result = 1;
      int result1 = 31 * result + (this.type == null?0:this.type.hashCode());
      return result1;
   }

   public boolean equals(Object obj) {
      if(this == obj) {
         return true;
      } else if(obj == null) {
         return false;
      } else if(this.getClass() != obj.getClass()) {
         return false;
      } else {
         MenuItemType other = (MenuItemType)obj;
         return this.type.equals(other.type);
      }
   }

}
