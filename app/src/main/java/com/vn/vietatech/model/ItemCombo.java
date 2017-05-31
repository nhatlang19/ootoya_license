package com.vn.vietatech.model;

public class ItemCombo {
	private String ComboItem;
	private String ItemDesc;
	private String Quantity;
	private String ModClass;
	private String Hidden;
	private String QtyEditable;
	private int MaxQuantity;

	public String getItemDesc() {
		return ItemDesc;
	}

	public void setItemDesc(String itemDesc) {
		ItemDesc = itemDesc;
	}

	public String getComboItem() {
		return ComboItem;
	}

	public void setComboItem(String comboItem) {
		ComboItem = comboItem;
	}

	public String getQuantity() {
		return Quantity;
	}

	public void setQuantity(String quantity) {
		Quantity = quantity;
	}

	public String getHidden() {
		return Hidden;
	}

	public void setHidden(String hidden) {
		Hidden = hidden;
	}

	public String getModClass() {
		return ModClass;
	}

	public void setModClass(String modClass) {
		ModClass = modClass;
	}

	public String getQtyEditable() {
		return QtyEditable;
	}

	public void setQtyEditable(String qtyEditable) {
		QtyEditable = qtyEditable;
	}

	public int getMaxQuantity() {
		return MaxQuantity;
	}

	public void setMaxQuantity(int maxQuantity) {
		float qty = Float.parseFloat(getQuantity().trim());
		MaxQuantity = (int) (qty * maxQuantity);
	}
}
