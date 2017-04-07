package org.broadleafcommerce.menu.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDTO implements Serializable {

   private static final long serialVersionUID = 1L;
   protected String label;
   protected String url;
   protected String imageUrl;
   protected String altText;
   protected Long categoryId;
   protected List submenu = new ArrayList();


   public String getLabel() {
      return this.label;
   }

   public void setLabel(String label) {
      this.label = label;
   }

   public String getUrl() {
      return this.url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public String getImageUrl() {
      return this.imageUrl;
   }

   public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
   }

   public String getAltText() {
      return this.altText;
   }

   public void setAltText(String altText) {
      this.altText = altText;
   }

   public List getSubmenu() {
      return this.submenu;
   }

   public void setSubmenu(List submenu) {
      this.submenu = submenu;
   }

   public Long getCategoryId() {
      return this.categoryId;
   }

   public void setCategoryId(Long categoryId) {
      this.categoryId = categoryId;
   }

   public static long getSerialversionuid() {
      return 1L;
   }
}
