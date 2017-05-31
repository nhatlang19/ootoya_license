package com.vn.vietatech.api.async;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.vn.vietatech.api.PosMenuAPI;
import com.vn.vietatech.api.SaleCodeAPI;
import com.vn.vietatech.api.TableAPI;
import com.vn.vietatech.api.UserApi;
import com.vn.vietatech.combo.MainActivity;
import com.vn.vietatech.combo.MyApplication;
import com.vn.vietatech.combo.TableActivity;
import com.vn.vietatech.combo.dialog.TransparentProgressDialog;
import com.vn.vietatech.model.Cashier;
import com.vn.vietatech.combo.R;
import com.vn.vietatech.utils.UserUtil;

public class LoginAsync extends AsyncTask<String, String, Cashier> {

	private Context mContext;
	private TransparentProgressDialog pd;
	MyApplication globalVariable;
	
	public LoginAsync(Context context, Application app) {
		this.mContext = context;

		globalVariable = (MyApplication) app;
		
		pd = new TransparentProgressDialog(mContext, R.drawable.spinner);
		pd.show();
	}

	@Override
	protected Cashier doInBackground(String... params) {
		String username = params[0];
		String password = params[1];
		Cashier cashier = new Cashier();
		try {
			cashier = new UserApi(mContext).login(username, password);
		} catch (Exception e) {
			Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
		return cashier;
	}

	@Override
	protected void onPostExecute(Cashier cashier) {
		if (cashier.getId().length() != 0) {
			// cache user info
			globalVariable.setCashier(cashier);

			// log recent login
			try {
				UserUtil.write(cashier, mContext);
				new TableAPI(mContext.getApplicationContext()).execute();
				new PosMenuAPI(mContext.getApplicationContext()).execute();
				new SaleCodeAPI(mContext.getApplicationContext()).execute();

				MainActivity act = (MainActivity) mContext;
				Intent myIntent = new Intent(mContext, TableActivity.class);
				act.startActivity(myIntent);
			} catch (Exception e) {
				Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT)
						.show();
			} finally {
				pd.dismiss();
			}

		} else {
			Toast.makeText(mContext, "Invalid Username / password",
					Toast.LENGTH_SHORT).show();
			pd.dismiss();
		}

		super.onPostExecute(cashier);
	}
}
