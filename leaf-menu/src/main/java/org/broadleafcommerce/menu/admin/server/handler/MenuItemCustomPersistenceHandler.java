package org.broadleafcommerce.menu.admin.server.handler;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.exception.ServiceException;
import org.broadleafcommerce.common.presentation.client.OperationType;
import org.broadleafcommerce.common.presentation.client.SupportedFieldType;
import org.broadleafcommerce.common.presentation.client.VisibilityEnum;
import org.broadleafcommerce.menu.domain.MenuItem;
import org.broadleafcommerce.menu.domain.MenuItemImpl;
import org.broadleafcommerce.menu.service.MenuService;
import org.broadleafcommerce.openadmin.dto.BasicFieldMetadata;
import org.broadleafcommerce.openadmin.dto.ClassMetadata;
import org.broadleafcommerce.openadmin.dto.CriteriaTransferObject;
import org.broadleafcommerce.openadmin.dto.DynamicResultSet;
import org.broadleafcommerce.openadmin.dto.Entity;
import org.broadleafcommerce.openadmin.dto.MergedPropertyType;
import org.broadleafcommerce.openadmin.dto.PersistencePackage;
import org.broadleafcommerce.openadmin.dto.PersistencePerspective;
import org.broadleafcommerce.openadmin.dto.Property;
import org.broadleafcommerce.openadmin.server.dao.DynamicEntityDao;
import org.broadleafcommerce.openadmin.server.service.handler.CustomPersistenceHandlerAdapter;
import org.broadleafcommerce.openadmin.server.service.persistence.module.InspectHelper;
import org.broadleafcommerce.openadmin.server.service.persistence.module.PersistenceModule;
import org.broadleafcommerce.openadmin.server.service.persistence.module.RecordHelper;
import org.springframework.stereotype.Component;

@Component("blMenuItemCustomPersistenceHandler")
public class MenuItemCustomPersistenceHandler extends CustomPersistenceHandlerAdapter {

   private static final Log LOG = LogFactory.getLog(MenuItemCustomPersistenceHandler.class);
   public static final String DERIVED_LABEL_FIELD_NAME = "derivedLabel";
   @Resource(
      name = "blMenuService"
   )
   protected MenuService menuService;


   public Boolean canHandleInspect(PersistencePackage persistencePackage) {
      String ceilingEntityFullyQualifiedClassname = persistencePackage.getCeilingEntityFullyQualifiedClassname();

      try {
         Class e = Class.forName(ceilingEntityFullyQualifiedClassname);
         return Boolean.valueOf(MenuItem.class.isAssignableFrom(e));
      } catch (ClassNotFoundException var4) {
         return Boolean.valueOf(false);
      }
   }

   public Boolean canHandleFetch(PersistencePackage persistencePackage) {
      return this.canHandleInspect(persistencePackage);
   }

   public Boolean canHandleAdd(PersistencePackage persistencePackage) {
      return this.canHandleInspect(persistencePackage);
   }

   public Boolean canHandleUpdate(PersistencePackage persistencePackage) {
      return this.canHandleInspect(persistencePackage);
   }

   public DynamicResultSet inspect(PersistencePackage persistencePackage, DynamicEntityDao dynamicEntityDao, InspectHelper helper) throws ServiceException {
      try {
         PersistencePerspective e = persistencePackage.getPersistencePerspective();
         HashMap className1 = new HashMap();
         Map ex1 = helper.getSimpleMergedProperties(MenuItem.class.getName(), e);
         BasicFieldMetadata derivedLabelMetadata = new BasicFieldMetadata();
         derivedLabelMetadata.setFieldType(SupportedFieldType.STRING);
         derivedLabelMetadata.setMutable(Boolean.valueOf(false));
         derivedLabelMetadata.setInheritedFromType(MenuItemImpl.class.getName());
         derivedLabelMetadata.setAvailableToTypes(new String[]{MenuItemImpl.class.getName()});
         derivedLabelMetadata.setForeignKeyCollection(Boolean.valueOf(false));
         derivedLabelMetadata.setMergedPropertyType(MergedPropertyType.PRIMARY);
         derivedLabelMetadata.setName("derivedLabel");
         derivedLabelMetadata.setFriendlyName("MenuItemImpl_Derived_Label");
         derivedLabelMetadata.setExplicitFieldType(SupportedFieldType.STRING);
         derivedLabelMetadata.setProminent(Boolean.valueOf(true));
         derivedLabelMetadata.setReadOnly(Boolean.valueOf(true));
         derivedLabelMetadata.setOrder(Integer.valueOf(500));
         derivedLabelMetadata.setGridOrder(Integer.valueOf(500));
         derivedLabelMetadata.setVisibility(VisibilityEnum.FORM_HIDDEN);
         derivedLabelMetadata.setExcluded(Boolean.valueOf(false));
         ex1.put("derivedLabel", derivedLabelMetadata);
         className1.put(MergedPropertyType.PRIMARY, ex1);
         Class[] entityClasses = dynamicEntityDao.getAllPolymorphicEntitiesFromCeiling(MenuItem.class);
         ClassMetadata mergedMetadata = helper.buildClassMetadata(entityClasses, persistencePackage, className1);
         return new DynamicResultSet(mergedMetadata, (Entity[])null, (Integer)null);
      } catch (Exception var10) {
         String className = persistencePackage.getCeilingEntityFullyQualifiedClassname();
         ServiceException ex = new ServiceException("Unable to retrieve inspection results for " + className, var10);
         LOG.error("Unable to retrieve inspection results for " + className, ex);
         throw ex;
      }
   }

   public DynamicResultSet fetch(PersistencePackage persistencePackage, CriteriaTransferObject cto, DynamicEntityDao dynamicEntityDao, RecordHelper helper) throws ServiceException {
      OperationType fetchType = persistencePackage.getPersistencePerspective().getOperationTypes().getFetchType();
      PersistenceModule persistenceModule = helper.getCompatibleModule(fetchType);
      DynamicResultSet drs = persistenceModule.fetch(persistencePackage, cto);
      Entity[] arr$ = drs.getRecords();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         Entity entity = arr$[i$];
         Property menuItemId = entity.findProperty("id");
         if(menuItemId != null) {
            MenuItem menuItem = this.menuService.findMenuItemById(Long.valueOf(Long.parseLong(menuItemId.getValue())));
            if(menuItem != null) {
               Property derivedLabel = new Property();
               derivedLabel.setName("derivedLabel");
               derivedLabel.setValue(menuItem.getDerivedLabel());
               entity.addProperty(derivedLabel);
            }
         }
      }

      return drs;
   }

   public Entity add(PersistencePackage persistencePackage, DynamicEntityDao dynamicEntityDao, RecordHelper helper) throws ServiceException {
      Property url = persistencePackage.getEntity().findProperty("image.url");
      if(url != null && StringUtils.isEmpty(url.getValue())) {
         url.setValue(" ");
      }

      return helper.getCompatibleModule(OperationType.BASIC).add(persistencePackage);
   }

   public Entity update(PersistencePackage persistencePackage, DynamicEntityDao dynamicEntityDao, RecordHelper helper) throws ServiceException {
      Property url = persistencePackage.getEntity().findProperty("image.url");
      if(url != null && StringUtils.isEmpty(url.getValue())) {
         url.setValue(" ");
      }

      return helper.getCompatibleModule(OperationType.BASIC).update(persistencePackage);
   }

}
