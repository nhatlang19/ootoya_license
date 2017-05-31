package com.vn.vietatech.model;

public class SalesCode {
	private String code;
	private String description;
	private String priceLevel;
	private String taxClass;
	private String memberType;
	
	public SalesCode() {
		code = "";
		description = "";
		priceLevel = "";
		taxClass = "";
		memberType = "";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description.trim();
	}

	public String getPriceLevel() {
		return priceLevel;
	}

	public void setPriceLevel(String priceLevel) {
		this.priceLevel = priceLevel.trim();
	}

	public String getTaxClass() {
		return taxClass;
	}

	public void setTaxClass(String taxClass) {
		this.taxClass = taxClass.trim();
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType.trim();
	}

}
