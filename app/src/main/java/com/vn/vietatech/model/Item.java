package com.vn.vietatech.model;

import java.util.ArrayList;

public class Item {
	private String id;
	private String splited;
	private String qty;
	private String printStatus;
	private String itemName;
	private String orgPrice;
	private String total;
	private String itemType;
	private String itemCode;
	private String modifierInt;
	private String masterCode;
	private String comboPack;
	private String hidden;
	private String instruction;
	private String segNo;
	private String onPromotion;
	private ArrayList<Remark> remarks;
	private String promoCode;
	private String promoPrice;
	private String promoDesc;
	private String promoClass;
	private String startDate;
	private String endDate;
	private String minQty;
	private String maxQty;
	private String pkgPrice;
	private String pkgQty;
	private String pkgItems;
	private String blanket;
	private ArrayList<ItemCombo> itemCombo;
	private ArrayList<ItemModifier> itemModifiers;
	private int numberClick;

	private static final String SEPARATE = "|";
	public static final String STATUS_OLD = "#";
	public static final String STATUS_CANCEL = "C";

	public Item() {
		id = " ";
		qty = "1";
		splited = "0";
		printStatus = " ";
		itemName = " ";
		orgPrice = "0";
		setPromoPrice("0");
		total = "0";
		itemType = " ";
		itemCode = " ";
		modifierInt = "0";
		masterCode = " ";
		comboPack = " ";
		hidden = " ";
		instruction = " ";
		onPromotion = "N";
		setRemarks(new ArrayList<Remark>());
		setItemCombo(new ArrayList<ItemCombo>());
		setItemModifiers(new ArrayList<ItemModifier>());
		promoCode = "";
		promoPrice = "0";
		promoDesc = "0";
		promoClass = "0";
		startDate = "";
		endDate = "";
		minQty = "";
		maxQty = "";
		pkgPrice = "0";
		pkgQty = "0";
		pkgItems = "0";
		blanket = "";
		setNumberClick(0);
		segNo = "0";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		if (instruction.contains("anyType{}")) {
			instruction = " ";
		}
		this.instruction = instruction;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getComboPack() {
		return comboPack.trim();
	}

	public void setComboPack(String comboPack) {
		this.comboPack = comboPack;
	}

	public String getMasterCode() {
		return masterCode;
	}

	public void setMasterCode(String masterCode) {
		this.masterCode = masterCode;
	}

	public String getModifier() {
		return modifierInt;
	}

	public void setModifier(String modifierInt) {
		this.modifierInt = modifierInt;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getOrgPrice() {
		return orgPrice;
	}

	public void setOrgPrice(String orgPrice) {
		float iorgPrice = Float.parseFloat(orgPrice);
		this.orgPrice = String.valueOf((int) iorgPrice);
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPrintStatus() {
		if (printStatus.trim().isEmpty()) {
			return printStatus;
		} else if (printStatus.equals("9")) {
			printStatus = STATUS_CANCEL;
		} else {
			printStatus = STATUS_OLD;
		}
		return printStatus;
	}
	
	public boolean isNewItem() {
		return ! (getPrintStatus().equals(STATUS_CANCEL) || getPrintStatus().equals(STATUS_OLD) ); 
	}

	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public ArrayList<Remark> getRemarks() {
		return remarks;
	}

	public void setRemarks(ArrayList<Remark> remarks) {
		this.remarks = remarks;
	}

	public String getSplited() {
		return splited;
	}

	public void setSplited(String splited) {
		this.splited = splited;
	}

	public String getPromoPrice() {
		return promoPrice;
	}

	public void setPromoPrice(String promoPrice) {
		float ipromoPrice = Float.parseFloat(promoPrice);
		this.promoPrice = String.valueOf((int) ipromoPrice);
	}

	public String getOnPromotion() {
		return onPromotion.trim();
	}

	public void setOnPromotion(String onPromotion) {
		this.onPromotion = onPromotion;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public String getSegNo() {
		return segNo;
	}

	public void setSegNo(String segNo) {
		this.segNo = segNo;
	}

	public String getPromoDesc() {
		return promoDesc;
	}

	public void setPromoDesc(String promoDesc) {
		this.promoDesc = promoDesc;
	}

	public String getPromoClass() {
		return promoClass;
	}

	public void setPromoClass(String promoClass) {
		this.promoClass = promoClass;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMinQty() {
		return minQty;
	}

	public void setMinQty(String minQty) {
		this.minQty = minQty;
	}

	public String getMaxQty() {
		return maxQty;
	}

	public void setMaxQty(String maxQty) {
		this.maxQty = maxQty;
	}

	public String getPkgQty() {
		return pkgQty;
	}

	public void setPkgQty(String pkgQty) {
		this.pkgQty = pkgQty;
	}

	public String getPkgItems() {
		return pkgItems;
	}

	public void setPkgItems(String pkgItems) {
		this.pkgItems = pkgItems;
	}

	public String getBlanket() {
		return blanket;
	}

	public void setBlanket(String blanket) {
		this.blanket = blanket;
	}

	public ArrayList<ItemCombo> getItemCombo() {
		return itemCombo;
	}

	public void setItemCombo(ArrayList<ItemCombo> itemCombo) {
		this.itemCombo = itemCombo;
	}

	public ArrayList<ItemModifier> getItemModifiers() {
		return itemModifiers;
	}

	public void setItemModifiers(ArrayList<ItemModifier> itemModifiers) {
		this.itemModifiers = itemModifiers;
	}

	public int getNumberClick() {
		return numberClick;
	}

	public void setNumberClick(int numberClick) {
		this.numberClick = numberClick;
	}

	public String getPkgPrice() {
		return pkgPrice;
	}

	public void setPkgPrice(String pkgPrice) {
		float ipkgPrice = Float.parseFloat(pkgPrice);
		this.pkgPrice = String.valueOf((int) ipkgPrice);
	}

	@Override
	public String toString() {
		String result = "";
		result += qty + SEPARATE;
		result += printStatus + SEPARATE;
		result += itemName + SEPARATE;
		result += orgPrice + SEPARATE;
		result += promoPrice + SEPARATE;
		result += total + SEPARATE;
		result += itemType + SEPARATE;
		result += itemCode + SEPARATE;
		result += modifierInt + SEPARATE;
		result += masterCode + SEPARATE;
		result += comboPack + SEPARATE;
		result += hidden + SEPARATE;
		result += instruction + SEPARATE;
		result += segNo + SEPARATE;
		result += promoCode + SEPARATE;
		result += promoClass + SEPARATE;
		result += pkgPrice + SEPARATE;
		result += pkgQty + SEPARATE;
		result += pkgItems + SEPARATE;
		result += blanket;
		return result;
	}
}
