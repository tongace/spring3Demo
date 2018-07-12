package com.dxc.application.model;

import java.math.BigDecimal;
import java.util.Date;

public abstract class BaseModel {
	private BigDecimal createdBy;
	private Date createdDt;
	private BigDecimal modifiedBy;
	private Date modifiedDt;
	
	public BigDecimal getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}
	public BigDecimal getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(BigDecimal modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDt() {
		return modifiedDt;
	}
	public void setModifiedDt(Date modifiedDt) {
		this.modifiedDt = modifiedDt;
	}
	
}
