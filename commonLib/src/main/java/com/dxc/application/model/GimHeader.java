package com.dxc.application.model;

import java.math.BigDecimal;

public class GimHeader extends BaseModel {
	private String gimType;
	private String gimDesc;
	private BigDecimal cdLength;
	private String field1Label;
	private String field2Label;
	private String field3Label;
	private String activeFlag;
	
	public String getGimType() {
		return gimType;
	}
	public void setGimType(String gimType) {
		this.gimType = gimType;
	}
	public String getGimDesc() {
		return gimDesc;
	}
	public void setGimDesc(String gimDesc) {
		this.gimDesc = gimDesc;
	}
	public BigDecimal getCdLength() {
		return cdLength;
	}
	public void setCdLength(BigDecimal cdLength) {
		this.cdLength = cdLength;
	}
	public String getField1Label() {
		return field1Label;
	}
	public void setField1Label(String field1Label) {
		this.field1Label = field1Label;
	}
	public String getField2Label() {
		return field2Label;
	}
	public void setField2Label(String field2Label) {
		this.field2Label = field2Label;
	}
	public String getField3Label() {
		return field3Label;
	}
	public void setField3Label(String field3Label) {
		this.field3Label = field3Label;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
}
