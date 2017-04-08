package org.broadleafcommerce.common.admin.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public class AbstractBasicEntity {

	@Id
	@Column(nullable=false, updatable=false)
	private Long id;


	@Basic
	@Column(name = "creator_Id", nullable = false,  updatable = false)
	private Long creatorId = (long) 0;

	@CreatedDate
	@Column(nullable = false,  updatable = false)
	private DateTime creationTime = new DateTime();


	@Basic
	@Column(name = "updator_Id")
	private Long updatorId =  (long) 0;

	@LastModifiedDate
	@Column(nullable = false)
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
