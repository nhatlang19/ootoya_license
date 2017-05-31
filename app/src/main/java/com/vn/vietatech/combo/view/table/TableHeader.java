package com.vn.vietatech.combo.view.table;

import java.util.ArrayList;

import com.vn.vietatech.combo.R;

import android.content.Context;
import android.graphics.Color;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableHeader extends TableLayout {
	
	private Context mContext;
	protected ArrayList<DataTable> dataTable;
	private ArrayList<String> arrHeader = new ArrayList<String>();

	public TableHeader(Context context, ArrayList<DataTable> dataTable) {
		super(context);
		
		this.mContext = context;
		this.dataTable = dataTable;
		
		addData();
	}
	
	public void addData() {
		
		TableRow tblHeader = new TableRow(mContext);
		tblHeader.setLayoutParams(new TableLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		for (DataTable  header : this.dataTable) {
			TextView textView = new TextView(mContext);
			textView.setWidth(header.getColWidth());
			textView.setBackgroundResource(R.drawable.order_table_header);
			textView.setText(header.getColName());
			textView.setGravity(header.getColGravity());
			textView.setTextColor(Color.BLACK);
			tblHeader.addView(textView);
			
			arrHeader.add(header.getColName());
		}
		this.addView(tblHeader);
	}

	public ArrayList<DataTable> getDataTable() {
		return this.dataTable;
	}
	
	public ArrayList<String> getListHeader() {
		return arrHeader;
	}
	
	public int getColumnIndex(String name) {
		return arrHeader.indexOf(name);
	}
	
	public DataTable getColHeader(String name) {
		int indexCol = arrHeader.indexOf(name);
		if (indexCol != -1) {
			return this.dataTable.get(indexCol);
		}
		return null;
	}
}
