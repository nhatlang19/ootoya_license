package com.vn.vietatech.combo.view.table;

import java.util.ArrayList;

import com.vn.vietatech.combo.view.ItemRow;
import com.vn.vietatech.utils.Utils;

import android.content.Context;

public class MyTable  {
	private TableHeader header;
	private TableBody body;
	
	public MyTable(Context context, ArrayList<DataTable> dataTable) {
		header = new TableHeader(context, dataTable);
		body = new TableBody(context, header);
	}
	
	public TableBody getBody() {
		return body;
	}
	
	public TableHeader getHeader() {
		return header;
	}
	
	@Override
	public String toString() {
		ArrayList<ItemRow> listRow = body.getAllRows();
		int size = listRow.size();
		String result = "";
		if(size != 0) {
			String []items = new String[size];
			
			int i = 0;
			for (ItemRow itemRow : listRow) {
				items[i++] = itemRow.toString();
			}
			result = Utils.implode("\n", items);
		}
		return result;
	}
}
