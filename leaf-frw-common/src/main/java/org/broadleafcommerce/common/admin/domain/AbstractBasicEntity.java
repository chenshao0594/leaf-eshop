package org.broadleafcommerce.common.admin.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public class AbstractBasicEntity  implements Serializable {

	@Basic
	@Column(name = "creator_Id", nullable = false,  updatable = false)
	private Long creatorId = 0l;

	@CreatedDate
	@Column(name="creation_time",  updatable = false)
	private DateTime creationTime = new DateTime();

	@Basic
	@Column(name = "updator_Id")
	private Long updatorId = 0l;

	@LastModifiedDate
	@Column(name="update_time")
	private DateTime updateTime = new DateTime();

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public DateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(DateTime creationTime) {
		this.creationTime = creationTime;
	}

	public Long getUpdatorId() {
		return updatorId;
	}

	public void setUpdatorId(Long updatorId) {
		this.updatorId = updatorId;
	}

	public DateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(DateTime updateTime) {
		this.updateTime = updateTime;
	}

}
