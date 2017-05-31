package com.vn.vietatech.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import android.annotation.SuppressLint;
import android.content.Context;

import com.vn.vietatech.model.Setting;
import com.vn.vietatech.combo.R;

public class SettingUtil {
	private static String FILENAME = "POSinit";

	@SuppressLint("NewApi")
	public static void write(Setting setting, Context context)
			throws IOException {
		File dir = new File(context.getFilesDir().getPath() + "/"
				+ context.getResources().getString(R.string.app_folder));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(dir, FILENAME);
		Properties props = new Properties();
		props.setProperty("ServerIP", setting.getServerIP());
		props.setProperty("StoreNo", setting.getStoreNo());
		props.setProperty("POSGroup", setting.getPosGroup());
		props.setProperty("POSId", setting.getPosId());
		props.setProperty("VAT", setting.getVat());
		props.setProperty("Type", setting.getType());

		FileWriter writer = new FileWriter(file);
		props.store(writer, "config");
		writer.close();
	}

	@SuppressLint("NewApi")
	public static Setting read(Context context) throws IOException {

		File file = new File(context.getFilesDir().getPath() + "/"
				+ context.getResources().getString(R.string.app_folder) + "/"
				+ FILENAME);
		if (file.exists() && file.isFile()) {
			FileReader reader = new FileReader(file);
			Properties props = new Properties();
			props.load(reader);

			Setting setting = new Setting();
			setting.setServerIP(props.getProperty("ServerIP"));
			setting.setStoreNo(props.getProperty("StoreNo"));
			setting.setPosGroup(props.getProperty("POSGroup"));
			setting.setPosId(props.getProperty("POSId"));
			setting.setVat(props.getProperty("VAT"));
			setting.setType(props.getProperty("Type"));
			reader.close();
			return setting;
		}
		return null;
	}
}
