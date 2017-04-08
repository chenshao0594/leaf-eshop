package org.broadleafcommerce.common.admin.domain;

import org.joda.time.DateTime;

public interface BasicEntity {
	public Long getCreatorId() ;

	public void setCreatorId(Long creatorId);
	public DateTime getCreationTime() ;

	public void setCreationTime(DateTime creationTime) ;

	public Long getUpdatorId() ;
	public void setUpdatorId(Long updatorId);

	public DateTime getUpdateTime();

	public void setUpdateTime(DateTime updateTime) ;
}
