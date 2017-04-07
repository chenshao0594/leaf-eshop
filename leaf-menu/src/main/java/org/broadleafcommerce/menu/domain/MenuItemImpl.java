package org.broadleafcommerce.menu.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.broadleafcommerce.cms.page.domain.Page;
import org.broadleafcommerce.cms.page.domain.PageImpl;
import org.broadleafcommerce.common.copy.CreateResponse;
import org.broadleafcommerce.common.copy.MultiTenantCopyContext;
import org.broadleafcommerce.common.extensibility.jpa.clone.ClonePolicy;
import org.broadleafcommerce.common.extensibility.jpa.copy.DirectCopyTransform;
import org.broadleafcommerce.common.extensibility.jpa.copy.DirectCopyTransformMember;
import org.broadleafcommerce.common.extensibility.jpa.copy.ProfileEntity;
import org.broadleafcommerce.common.i18n.service.DynamicTranslationProvider;
import org.broadleafcommerce.common.media.domain.Media;
import org.broadleafcommerce.common.media.domain.MediaImpl;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.AdminPresentationToOneLookup;
import org.broadleafcommerce.common.presentation.PopulateToOneFieldsEnum;
import org.broadleafcommerce.common.presentation.client.SupportedFieldType;
import org.broadleafcommerce.common.presentation.client.VisibilityEnum;
import org.broadleafcommerce.common.presentation.override.AdminPresentationMergeEntry;
import org.broadleafcommerce.common.presentation.override.AdminPresentationMergeOverride;
import org.broadleafcommerce.common.presentation.override.AdminPresentationMergeOverrides;
import org.broadleafcommerce.menu.type.MenuItemType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Inheritance(
   strategy = InheritanceType.JOINED
)
@Table(
   name = "BLC_CMS_MENU_ITEM"
)
@Cache(
   usage = CacheConcurrencyStrategy.READ_WRITE,
   region = "blCMSElements"
)
@AdminPresentationClass(
   friendlyName = "MenuItemImpl",
   populateToOneFields = PopulateToOneFieldsEnum.TRUE
)
@AdminPresentationMergeOverrides({   @AdminPresentationMergeOverride(
      name = "image.",
      mergeEntries = {         @AdminPresentationMergeEntry(
            propertyType = "excluded",
            booleanOverrideValue = true
         )}
   ),    @AdminPresentationMergeOverride(
      name = "image.url",
      mergeEntries = {         @AdminPresentationMergeEntry(
            propertyType = "excluded",
            booleanOverrideValue = false
         ),          @AdminPresentationMergeEntry(
            propertyType = "order",
            intOverrideValue = 4000
         ),          @AdminPresentationMergeEntry(
            propertyType = "friendlyName",
            overrideValue = "MenuItemImpl_Image"
         ),          @AdminPresentationMergeEntry(
            propertyType = "requiredOverride",
            overrideValue = "NOT_REQUIRED"
         )}
   )})
@DirectCopyTransform({   @DirectCopyTransformMember(
      templateTokens = {"sandbox"},
      skipOverlaps = true
   ),    @DirectCopyTransformMember(
      templateTokens = {"multiTenantSite"}
   )})
public class MenuItemImpl implements MenuItem, ProfileEntity {

   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(
      generator = "MenuItemId"
   )
   @GenericGenerator(
      name = "MenuItemId",
      strategy = "org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
      parameters = {         @Parameter(
            name = "segment_value",
            value = "MenuItemImpl"
         ),          @Parameter(
            name = "entity_name",
            value = "org.broadleafcommerce.menu.domain.MenuItemImpl"
         )}
   )
   @Column(
      name = "MENU_ITEM_ID"
   )
   protected Long id;
   @Column(
      name = "LABEL"
   )
   @AdminPresentation(
      friendlyName = "MenuItemImpl_Label",
      order = 1000,
      gridOrder = 1000,
      prominent = true,
      translatable = true
   )
   protected String label;
   @Column(
      name = "MENU_ITEM_TYPE"
   )
   @AdminPresentation(
      friendlyName = "MenuItemImpl_Type",
      order = 2000,
      prominent = true,
      gridOrder = 2000,
      fieldType = SupportedFieldType.BROADLEAF_ENUMERATION,
      broadleafEnumeration = "org.broadleafcommerce.menu.type.MenuItemType"
   )
   protected String type;
   @Column(
      name = "SEQUENCE",
      precision = 10,
      scale = 6
   )
   @AdminPresentation(
      visibility = VisibilityEnum.HIDDEN_ALL
   )
   protected BigDecimal sequence;
   @ManyToOne(
      optional = true,
      targetEntity = MenuImpl.class,
      cascade = {CascadeType.REFRESH}
   )
   @JoinColumn(
      name = "PARENT_MENU_ID"
   )
   @AdminPresentation(
      excluded = true
   )
   protected Menu parentMenu;
   @Column(
      name = "ACTION_URL"
   )
   @AdminPresentation(
      friendlyName = "MenuItemImpl_ActionUrl",
      order = 3000
   )
   protected String actionUrl;
   @ManyToOne(
      targetEntity = MediaImpl.class,
      cascade = {CascadeType.MERGE, CascadeType.PERSIST}
   )
   @JoinColumn(
      name = "MEDIA_ID"
   )
   @ClonePolicy
   protected Media image;
   @Column(
      name = "ALT_TEXT"
   )
   @AdminPresentation(
      friendlyName = "MenuItemImpl_AltText",
      order = 5000
   )
   protected String altText;
   @ManyToOne(
      targetEntity = MenuImpl.class
   )
   @JoinColumn(
      name = "LINKED_MENU_ID"
   )
   @AdminPresentation(
      friendlyName = "MenuItemImpl_LinkedMenu",
      order = 6000
   )
   @AdminPresentationToOneLookup
   protected Menu linkedMenu;
   @ManyToOne(
      targetEntity = PageImpl.class
   )
   @JoinColumn(
      name = "LINKED_PAGE_ID"
   )
   @AdminPresentation(
      friendlyName = "MenuItemImpl_LinkedPage",
      order = 7000
   )
   @AdminPresentationToOneLookup
   protected Page linkedPage;
   @Lob
   @Type(
      type = "org.hibernate.type.StringClobType"
   )
   @Column(
      name = "CUSTOM_HTML",
      length = 2147483646
   )
   @AdminPresentation(
      friendlyName = "MenuItemImpl_CustomHtml",
      order = 10000,
      largeEntry = true,
      fieldType = SupportedFieldType.HTML_BASIC,
      translatable = true
   )
   protected String customHtml;


   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public MenuItemType getMenuItemType() {
      return MenuItemType.getInstance(this.type);
   }

   public void setMenuItemType(MenuItemType menuItemType) {
      this.type = menuItemType.getType();
   }

   public String getLabel() {
      return DynamicTranslationProvider.getValue(this, "label", this.label);
   }

   public void setLabel(String label) {
      this.label = label;
   }

   public String getActionUrl() {
      return this.actionUrl;
   }

   public void setActionUrl(String actionUrl) {
      this.actionUrl = actionUrl;
   }

   public Media getImage() {
      return this.image;
   }

   public void setImage(Media image) {
      this.image = image;
   }

   public BigDecimal getSequence() {
      return this.sequence;
   }

   public void setSequence(BigDecimal sequence) {
      this.sequence = sequence;
   }

   public Menu getParentMenu() {
      return this.parentMenu;
   }

   public void setParentMenu(Menu parentMenu) {
      this.parentMenu = parentMenu;
   }

   public Menu getLinkedMenu() {
      return this.linkedMenu;
   }

   public void setLinkedMenu(Menu linkedMenu) {
      this.linkedMenu = linkedMenu;
   }

   public String getAltText() {
      return this.altText;
   }

   public void setAltText(String altText) {
      this.altText = altText;
   }

   public Page getLinkedPage() {
      return this.linkedPage;
   }

   public void setLinkedPage(Page linkedPage) {
      this.linkedPage = linkedPage;
   }

   public String getCustomHtml() {
      return DynamicTranslationProvider.getValue(this, "customHtml", this.customHtml);
   }

   public void setCustomHtml(String customHtml) {
      this.customHtml = customHtml;
   }

   public String getDerivedUrl() {
      String url = this.getActionUrl();
      if(MenuItemType.PAGE.equals(this.getMenuItemType()) && this.getLinkedPage() != null) {
         url = this.getLinkedPage().getFullUrl();
      }

      return url;
   }

   public String getDerivedLabel() {
      String l = this.getLabel();
      if(l == null && MenuItemType.SUBMENU.equals(this.getMenuItemType()) && this.getLinkedMenu() != null) {
         l = this.getLinkedMenu().getName();
      }

      return l;
   }

   public CreateResponse createOrRetrieveCopyInstance(MultiTenantCopyContext context) throws CloneNotSupportedException {
      CreateResponse createResponse = context.createOrRetrieveCopyInstance(this);
      if(createResponse.isAlreadyPopulated()) {
         return createResponse;
      } else {
         MenuItem cloned = (MenuItem)createResponse.getClone();
         cloned.setLabel(this.label);
         cloned.setMenuItemType(this.getMenuItemType());
         cloned.setSequence(this.sequence);
         if(this.parentMenu != null) {
            cloned.setParentMenu((Menu)this.parentMenu.createOrRetrieveCopyInstance(context).getClone());
         }

         cloned.setActionUrl(this.actionUrl);
         if(this.image != null) {
            cloned.setImage((Media)((MediaImpl)this.image).createOrRetrieveCopyInstance(context).getClone());
         }

         cloned.setAltText(this.altText);
         if(this.linkedMenu != null) {
            cloned.setLinkedMenu((Menu)this.linkedMenu.createOrRetrieveCopyInstance(context).getClone());
         }

         if(this.linkedPage != null) {
            cloned.setLinkedPage((Page)this.linkedPage.createOrRetrieveCopyInstance(context).getClone());
         }

         cloned.setCustomHtml(this.customHtml);
         return createResponse;
      }
   }

   public static class Presentation {


      public static class FieldOrder {

         public static final int LABEL = 1000;
         public static final int MENU_ITEM_TYPE = 2000;
         public static final int ACTION_URL = 3000;
         public static final int IMAGE_URL = 4000;
         public static final int ALT_TEXT = 5000;
         public static final int LINKED_MENU = 6000;
         public static final int LINKED_PAGE = 7000;
         public static final int CATEGORY = 8000;
         public static final int PRODUCT = 9000;
         public static final int CUSTOM_HTML = 10000;


      }
   }

}
