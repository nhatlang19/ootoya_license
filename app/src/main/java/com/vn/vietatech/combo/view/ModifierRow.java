package com.vn.vietatech.combo.view;

import java.io.IOException;

import com.vn.vietatech.model.ItemCombo;
import com.vn.vietatech.model.ItemModifier;
import com.vn.vietatech.model.Setting;
import com.vn.vietatech.utils.SettingUtil;
import com.vn.vietatech.combo.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TableRow;
import android.widget.TextView;

public class ModifierRow extends TableRow {

	private Context mContext;
	private ItemModifier itemModifier;

	private TextView textView;
	private EditText editText;
	private ItemCombo itemCombo;
	private boolean setDefaultValue;
	
	public ItemModifier getItemModifier() {
		return itemModifier;
	}

	public ModifierRow(Context context, ItemModifier itemModifier,
			ItemCombo itemCombo, boolean setDefaultValue) {
		super(context);

		this.mContext = context;
		this.itemModifier = itemModifier;
		this.itemCombo = itemCombo;
		this.setDefaultValue = setDefaultValue;

		setPadding(0, 5, 0, 5);

		initRow();
	}

	public void initRow() {
		textView = new TextView(mContext);
		textView.setPadding(20, 10, 20, 10);
		textView.setGravity(Gravity.LEFT);
		textView.setText(itemModifier.getModDesc());
		textView.setTextColor(Color.BLACK);
		textView.setTextSize(15);
		this.addView(textView);

		editText = new EditText(mContext);
		editText.setPadding(20, 10, 20, 10);
		editText.setWidth(100);
		editText.setGravity(Gravity.CENTER);
		editText.setRight(0);
		editText.setFocusable(false);
		editText.setBackgroundResource(R.drawable.textview_money);
		editText.setText("0");
		if(setDefaultValue) {
			editText.setText(itemCombo.getMaxQuantity() + "");
		}
		editText.setClickable(true);
		editText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Setting setting;
				try {
					setting = SettingUtil.read(mContext);
					if(!setting.getType().equals("1")) {
						int val = Integer.parseInt(editText.getText().toString());
						if(val == 0) {
							val = 1;
						} else {
							val = 0;
						}
						editText.setText(val + "");
					} else {
						loadPickerDialog();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		this.addView(editText);
	}

	// ///////////////////////
	/**
	 * load number picker when set people
	 */
	private void loadPickerDialog() {
		LayoutInflater layoutInflater = LayoutInflater.from(mContext);
		View promptView = layoutInflater.inflate(R.layout.number_picker_dialog,
				null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				mContext);

		final NumberPicker np = (NumberPicker) promptView
				.findViewById(R.id.npPeople);
		np.setMaxValue(100);
		np.setMinValue(0);
		if (editText.getText().toString().length() != 0) {
			np.setValue(Integer.parseInt(editText.getText().toString()));
		}

		alertDialogBuilder.setView(promptView);
		alertDialogBuilder.setCancelable(false);
		alertDialogBuilder.setTitle("Set quantity");
		alertDialogBuilder.setPositiveButton("Set",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						editText.setText(String.valueOf(np.getValue()));
						dialog.cancel();
					}
				}).setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.cancel();
					}
				});
		AlertDialog alertD = alertDialogBuilder.create();
		alertD.show();
	}

	public int getValue() {
		String value = editText.getText().toString();
		if(value.isEmpty()) {
			if(setDefaultValue) {
				return itemCombo.getMaxQuantity();
			}
			return 0;
		}
		return Integer.parseInt(value);
	}
}
