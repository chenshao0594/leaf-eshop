package org.broadleafcommerce.menu.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.broadleafcommerce.common.admin.domain.AdminMainEntity;
import org.broadleafcommerce.common.copy.CreateResponse;
import org.broadleafcommerce.common.copy.MultiTenantCopyContext;
import org.broadleafcommerce.common.extensibility.jpa.copy.DirectCopyTransform;
import org.broadleafcommerce.common.extensibility.jpa.copy.DirectCopyTransformMember;
import org.broadleafcommerce.common.extensibility.jpa.copy.ProfileEntity;
import org.broadleafcommerce.common.i18n.domain.TranslatedEntity;
import org.broadleafcommerce.common.i18n.service.DynamicTranslationProvider;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.AdminPresentationCollection;
import org.broadleafcommerce.common.presentation.client.AddMethodType;
import org.broadleafcommerce.menu.domain.Menu;
import org.broadleafcommerce.menu.domain.MenuItem;
import org.broadleafcommerce.menu.domain.MenuItemImpl;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Inheritance(
   strategy = InheritanceType.JOINED
)
@Table(
   name = "BLC_CMS_MENU"
)
@Cache(
   usage = CacheConcurrencyStrategy.READ_WRITE,
   region = "blCMSElements"
)
@AdminPresentationClass(
   friendlyName = "MenuImpl"
)
@DirectCopyTransform({   @DirectCopyTransformMember(
      templateTokens = {"sandbox"},
      skipOverlaps = true
   ),    @DirectCopyTransformMember(
      templateTokens = {"multiTenantSite"}
   )})
public class MenuImpl implements Menu, AdminMainEntity, ProfileEntity {

   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(
      generator = "MenuId"
   )
   @GenericGenerator(
      name = "MenuId",
      strategy = "org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
      parameters = {         @Parameter(
            name = "segment_value",
            value = "MenuImpl"
         ),          @Parameter(
            name = "entity_name",
            value = "org.broadleafcommerce.menu.domain.MenuImpl"
         )}
   )
   @Column(
      name = "MENU_ID"
   )
   protected Long id;
   @Column(
      name = "NAME",
      nullable = false
   )
   @AdminPresentation(
      friendlyName = "MenuImpl_Name",
      order = 1000,
      gridOrder = 1000,
      prominent = true,
      translatable = true
   )
   protected String name;
   @OneToMany(
      mappedBy = "parentMenu",
      targetEntity = MenuItemImpl.class,
      cascade = {CascadeType.ALL},
      orphanRemoval = true
   )
   @AdminPresentationCollection(
      friendlyName = "MenuItemImpl_MenuItems",
      sortProperty = "sequence",
      addType = AddMethodType.PERSIST
   )
   @Cache(
      usage = CacheConcurrencyStrategy.READ_WRITE,
      region = "blCMSElements"
   )
   @BatchSize(
      size = 50
   )
   @OrderBy("sequence")
   protected List menuItems = new ArrayList(20);


   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return DynamicTranslationProvider.getValue(this, "name", this.name);
   }

   public void setName(String name) {
      this.name = name;
   }

   public List getMenuItems() {
      return this.menuItems;
   }

   public void setMenuItems(List menuItems) {
      this.menuItems = menuItems;
   }

   public String getMainEntityName() {
      return this.getName();
   }

   public CreateResponse createOrRetrieveCopyInstance(MultiTenantCopyContext context) throws CloneNotSupportedException {
      CreateResponse createResponse = context.createOrRetrieveCopyInstance(this);
      if(createResponse.isAlreadyPopulated()) {
         return createResponse;
      } else {
         Menu cloned = (Menu)createResponse.getClone();
         cloned.setName(this.name);
         Iterator i$ = this.menuItems.iterator();

         while(i$.hasNext()) {
            MenuItem item = (MenuItem)i$.next();
            cloned.getMenuItems().add(item.createOrRetrieveCopyInstance(context).getClone());
         }

         return createResponse;
      }
   }

   static {
      new TranslatedEntity("org.broadleafcommerce.menu.domain.Menu", "Menu");
   }

   public static class Presentation {


      public static class FieldOrder {

         public static final int NAME = 1000;


      }
   }
}
