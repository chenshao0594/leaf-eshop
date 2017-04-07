package org.broadleafcommerce.menu.domain;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Nonnull;
import org.broadleafcommerce.common.copy.MultiTenantCloneable;

public interface Menu extends Serializable, MultiTenantCloneable {

   Long getId();

   void setId(Long var1);

   String getName();

   void setName(String var1);

   List getMenuItems();

   void setMenuItems(@Nonnull List var1);
}
