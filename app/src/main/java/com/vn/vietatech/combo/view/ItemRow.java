package com.vn.vietatech.combo.view;

import com.vn.vietatech.combo.view.table.DataTable;
import com.vn.vietatech.combo.view.table.TableHeader;
import com.vn.vietatech.model.Item;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TableRow;
import android.widget.TextView;

public class ItemRow extends TableRow {

	private Context mContext;
	private Item currentItem;

	public ItemRow(Context context) {
		super(context);

		mContext = context;
	}
	
	public Item getCurrentItem() {
		return this.currentItem;
	}

	public void addAllColumns(Item item, TableHeader tblHeader, int segNo) {
		currentItem = item;
		
		// quality
		TextView txtQuality = createColumn(currentItem.getQty(), tblHeader, "Q");
		this.addView(txtQuality);
		// status
		TextView txtStatus = createColumn(currentItem.getPrintStatus(), tblHeader, "P");
		this.addView(txtStatus);
		// name
		TextView txtName = createColumn(currentItem.getItemName(), tblHeader, "Name");
		this.addView(txtName);
		// O.price
		TextView txtOPrice = createColumn(currentItem.getOrgPrice(), tblHeader, "O.price");
		this.addView(txtOPrice);
		// P.price
		TextView txtPPrice = createColumn(currentItem.getPromoPrice(), tblHeader, "P.price");
		this.addView(txtPPrice);
		// total
		int total;
		if(!currentItem.getOnPromotion().equals("Y")) {
			total = Integer.parseInt(currentItem.getQty())
					* Integer.parseInt(currentItem.getOrgPrice());
		} else {
			total = Integer.parseInt(currentItem.getQty())
					* Integer.parseInt(currentItem.getPromoPrice());
		}
		currentItem.setTotal(String.valueOf(total));
		TextView txtTotal = createColumn(currentItem.getTotal(), tblHeader, "Total");
		this.addView(txtTotal);
		// currentItemType
		TextView txtItemType = createColumn(currentItem.getItemType(), tblHeader, "Type");
		this.addView(txtItemType);
		// currentItemCode
		TextView txtItemCode = createColumn(currentItem.getItemCode(), tblHeader, "ItemCode");
		this.addView(txtItemCode);
		// Instruction
		TextView txtInstruction = createColumn(currentItem.getInstruction(), tblHeader, "Instruction");
		this.addView(txtInstruction);
		// ModifierInt
		TextView txtModifierInt = createColumn(currentItem.getModifier(), tblHeader, "Modifier");
		this.addView(txtModifierInt);
		// MasterCode
		TextView txtMasterCode = createColumn(currentItem.getMasterCode(), tblHeader, "MasterCode");
		this.addView(txtMasterCode);
		// ComboClass
		TextView txtComboClass = createColumn(currentItem.getComboPack(), tblHeader, "Combo");
		this.addView(txtComboClass);
		// Hidden
		TextView txtHidden = createColumn(currentItem.getHidden(), tblHeader, "Hidden");
		this.addView(txtHidden);
		// SegNo
		
		int segNoItem = Integer.parseInt(currentItem.getSegNo());
		TextView txtSegNo = null;
		if(segNoItem == 0) {
			txtSegNo = createColumn(String.valueOf(segNo), tblHeader, "SegNo");
			currentItem.setSegNo(String.valueOf(segNo));
		} else {
			txtSegNo = createColumn(currentItem.getSegNo(), tblHeader, "SegNo");
		}
		this.addView(txtSegNo);
		// p.Code
		TextView txtCode = createColumn(currentItem.getPromoCode(), tblHeader, "p.Code");
		this.addView(txtCode);
		// Class
		TextView txtClass = createColumn(currentItem.getPromoClass(), tblHeader, "p.Class");
		this.addView(txtClass);
		// PkgPrice
		TextView txtPkgPrice = createColumn(currentItem.getPkgPrice(), tblHeader, "p.PkgPrice");
		this.addView(txtPkgPrice);
		// PkgQty
		TextView txtPkgQty = createColumn(currentItem.getPkgQty(), tblHeader, "p.PkgQty");
		this.addView(txtPkgQty);
		// PkgItems
		TextView txtPkgItems = createColumn(currentItem.getPkgItems(), tblHeader, "p.PkgItems");
		this.addView(txtPkgItems);
		// Blanket
		TextView txtBlanket = createColumn(currentItem.getBlanket(), tblHeader, "p.Blanket");
		this.addView(txtBlanket);
	}

	private TextView createColumn(String item, TableHeader tblHeader, String columnName) {
		DataTable data = tblHeader.getColHeader(columnName);
		
		TextView textView = new TextView(mContext);
		textView.setLayoutParams(new LayoutParams(data.getColWidth(), LayoutParams.WRAP_CONTENT));
		textView.setPadding(20, 5, 20, 5);
		textView.setGravity(data.getColGravity());
		textView.setText(item.trim());
		textView.setTextColor(Color.BLACK);

		return textView;
	}
	
	@Override
	public String toString() {
		return currentItem.toString();
	}
}
