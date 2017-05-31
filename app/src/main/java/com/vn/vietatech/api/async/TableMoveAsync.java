package com.vn.vietatech.api.async;

import android.content.Context;
import android.os.AsyncTask;

import com.vn.vietatech.combo.POSMenuActivity;
import com.vn.vietatech.utils.Utils;

public class TableMoveAsync extends AsyncTask<String, String, String> {
	private Context mContext;

	public TableMoveAsync(Context context) {
		this.mContext = context;
	}

	@Override
	protected String doInBackground(String... params) {
		
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		POSMenuActivity act = (POSMenuActivity) mContext;
		act.loadLayoutMoveTable();
		
		super.onPostExecute(result);
	}
}
