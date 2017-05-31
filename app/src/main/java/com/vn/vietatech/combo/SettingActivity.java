package com.vn.vietatech.combo;

import java.io.IOException;

import com.vn.vietatech.api.AbstractAPI;
import com.vn.vietatech.model.Setting;
import com.vn.vietatech.combo.R;
import com.vn.vietatech.utils.SettingUtil;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends ActionBarActivity {

	private EditText txtServerIp;
	private EditText txtStoreNo;
	private EditText txtPosGroup;
	private EditText txtPosId;
	private EditText txtVAT;
	private EditText txtType;
	private Button btnSaveConfig;
	private Button btnTestConnect;
	private Button btnCloseSetting;
	
	private Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		setTitle("Config");

		txtServerIp = (EditText) findViewById(R.id.txtServerIP);
		txtStoreNo = (EditText) findViewById(R.id.txtStoreNo);
		txtPosGroup = (EditText) findViewById(R.id.txtPosGroup);
		txtPosId = (EditText) findViewById(R.id.txtPosId);
		txtVAT = (EditText) findViewById(R.id.txtVAT);
		txtType = (EditText) findViewById(R.id.txtType);

		btnSaveConfig = (Button) findViewById(R.id.btnSaveConfig);
		btnCloseSetting = (Button) findViewById(R.id.btnCloseSetting);
		btnTestConnect = (Button) findViewById(R.id.btnTestConnect);

		// load settings
		loadSettings();
		
		btnCloseSetting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});

		btnSaveConfig.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Setting setting = new Setting();
				setting.setServerIP(txtServerIp.getText().toString());
				setting.setStoreNo(txtStoreNo.getText().toString());
				setting.setPosGroup(txtPosGroup.getText().toString());
				setting.setPosId(txtPosId.getText().toString());
				setting.setVat(txtVAT.getText().toString());
				setting.setType(txtType.getText().toString());
				
				try {
					SettingUtil.write(setting, getApplicationContext());
					Toast.makeText(getApplicationContext(),
							"Save config successful", Toast.LENGTH_LONG).show();
				} catch (IOException e) {
					Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
				}
			}
		});

		btnTestConnect.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				try {
					boolean result = new AbstractAPI(context).IsSQLConnected();
					if(result) {
						Toast.makeText(getApplicationContext(), "Connection successful", Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(getApplicationContext(), "Connection unsuccessful", Toast.LENGTH_LONG).show();
					}
				} catch (Exception e) {
					Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
				}
			}
		});

	}

	private void loadSettings() {
		Setting setting;
		try {
			setting = SettingUtil.read(getApplicationContext());
			if (setting != null) {
				txtServerIp.setText(setting.getServerIP());
				txtStoreNo.setText(setting.getStoreNo());
				txtPosGroup.setText(setting.getPosGroup());
				txtPosId.setText(setting.getPosId());
				txtVAT.setText(setting.getVat());
				txtType.setText(setting.getType());
			}
		} catch (IOException e) {
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		}
		
	}
}
