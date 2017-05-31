package com.vn.vietatech.combo.adapter;

import java.util.ArrayList;

import com.vn.vietatech.model.SalesCode;
import com.vn.vietatech.model.Table;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SalesCodeAdapter extends ArrayAdapter<SalesCode> {

	// Your sent context
	private Context context;
	// Your custom values for the spinner (Session)
	private ArrayList<SalesCode> values;

	public SalesCodeAdapter(Context context, int textViewResourceId,
			ArrayList<SalesCode> values) {
		super(context, textViewResourceId, values);
		this.context = context;
		this.values = values;
	}

	public int getCount() {
		return values.size();
	}

	public int findBySaleCode(String salesCode) {
		for (int i = 0; i < getCount(); i++) {
			SalesCode tmpSaleCode = getItem(i);
			if (tmpSaleCode.getCode().equals(salesCode)) {
				return i;
			}
		}
		return 0;
	}

	public SalesCode getItem(int position) {
		return values.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView label = new TextView(context);

		if (convertView != null) {
			label = (TextView) convertView;
		} else {
			label.setPadding(10, 29, 10, 29);
		}

		label.setTextColor(Color.BLACK);
		label.setText(values.get(position).getCode());
		return label;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		TextView label = new TextView(context);
		if (convertView != null) {
			label = (TextView) convertView;
		} else {
			label.setPadding(5, 12, 5, 17);
		}

		label.setText(values.get(position).getCode());
		return label;
	}
}
