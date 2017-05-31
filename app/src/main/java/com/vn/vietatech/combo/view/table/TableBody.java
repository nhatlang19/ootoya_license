package com.vn.vietatech.combo.view.table;

import java.util.ArrayList;

import com.vn.vietatech.combo.POSMenuActivity;
import com.vn.vietatech.combo.view.ItemRow;
import com.vn.vietatech.model.Item;
import com.vn.vietatech.utils.Utils;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

public class TableBody extends TableLayout {
	private TableHeader tblHeader;
	private Context mContext;
	private ArrayList<ItemRow> listRow = new ArrayList<ItemRow>();
	private int currentIndex = -1;
	private ArrayList<Integer> listKey = new ArrayList<Integer>();
	private int segNo = 1;

	public TableBody(Context context, TableHeader header) {
		super(context);

		tblHeader = header;
		mContext = context;
	}

	public ArrayList<ItemRow> getAllRows() {
		return listRow;
	}

	public void removeRow(ItemRow deletedRow) {
		for (int i = listRow.size() - 1; i >= 0; i--) {
			ItemRow row = listRow.get(i);
			if (row.getId() == deletedRow.getId()) {
				listRow.remove(row);
				break;
			}
		}

		setCurrentIndex(-1);
	}

	public int getSegNo() {
		return segNo;
	}

	public void setSegNo(int segNo) {
		this.segNo = segNo;
	}

	public ItemRow getCurrentRow() {
		if (currentIndex != -1) {
			return listRow.get(currentIndex);
		}
		return null;
	}

	public ItemRow getRowIndex(int index) {
		if (index >= 0 && index < listRow.size()) {
			return listRow.get(index);
		}
		return null;
	}

	public Object getColumnCurrentRow(String name) {
		int index = tblHeader.getColumnIndex(name);
		if (index != -1 && currentIndex != -1) {
			return listRow.get(currentIndex).getChildAt(index);
		}
		return null;
	}

	public Object getColumnByRow(int index, String name) {
		int indexCol = tblHeader.getColumnIndex(name);
		if (indexCol != -1 && index >= 0) {
			return listRow.get(index).getChildAt(indexCol);
		}
		return null;
	}

	public void clearBgRow() {
		for (ItemRow row : listRow) {
			row.setBackgroundColor(Color.parseColor("#ffffff"));
		}
	}

	@Override
	public void removeView(View view) {
		super.removeView(view);

		ItemRow deletedRow = (ItemRow) view;
		this.removeRow(deletedRow);
		reupdateSegNo();
	}

	public void reupdateSegNo() {
		int prev = 0;
		int tmpSegNo = 0;
		ArrayList<ItemRow> listRow = getAllRows();
		int size = listRow.size();
		for (int i = 0; i < size; i++) {
			TextView txtSegNo = (TextView) getColumnByRow(i, "SegNo");
			int segNo = Integer.parseInt(txtSegNo.getText().toString());
			if (prev != segNo) {
				tmpSegNo++;
			}
			txtSegNo.setText(tmpSegNo + "");
			getRowIndex(i).getCurrentItem().setSegNo(tmpSegNo + "");

			prev = segNo;
		}

		// update current segNo
		setSegNo(tmpSegNo + 1);
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public void addRow(final Item item) {
		if (item != null) {
			// add new row
			final ItemRow newRow = new ItemRow(mContext);
			int segNoItem = Integer.parseInt(item.getSegNo());
			boolean isZero = segNoItem == 0;
			newRow.addAllColumns(item, tblHeader, segNo);

			if (item.isNewItem()) {
				if (isZero) {
					segNo++;
				} else {
					if (segNoItem != segNo) {
						segNo = segNoItem + 1;
					} else {
						segNo = segNoItem;
					}
				}
			} else {
				segNo = segNoItem + 1;
			}

			newRow.setId(listKey.size());
			newRow.setLayoutParams(new TableLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			newRow.setPadding(0, 5, 0, 5);

			final POSMenuActivity act = (POSMenuActivity) mContext;
			act.btnIPlus.setEnabled(false);
			act.btnISub.setEnabled(false);
			act.btnIx.setEnabled(false);
			newRow.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// set other row : #ffffff
					clearBgRow();

					newRow.setBackgroundColor(Color.parseColor("#edf0fe"));
					Item item = newRow.getCurrentItem();
					if (!item.getPrintStatus().equals(Item.STATUS_OLD)
							&& !item.getPrintStatus()
									.equals(Item.STATUS_CANCEL)) {
						act.btnIPlus.setEnabled(true);
						act.btnISub.setEnabled(true);
						act.btnIx.setEnabled(true);

						if (item.getItemType().equals("C")
								|| item.getItemType().equals("M")) {
							act.btnIPlus.setEnabled(false);
							act.btnISub.setEnabled(false);
						}
					}
					for (int i = listRow.size() - 1; i >= 0; i--) {
						ItemRow row = listRow.get(i);
						if (row.getId() == newRow.getId()) {
							setCurrentIndex(i);

							// load remarks
							act.loadRemarks(row.getCurrentItem());
							break;
						}
					}
				}
			});

			this.addView(newRow);
			// add into array list
			listRow.add(newRow);
			listKey.add(listKey.size());
		}
	}

}
