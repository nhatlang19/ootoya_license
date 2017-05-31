package com.vn.vietatech.combo.adapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import com.vn.vietatech.api.PosMenuAPI;
import com.vn.vietatech.combo.view.SubmenuButton;
import com.vn.vietatech.model.PosMenu;
import com.vn.vietatech.model.SubMenu;
import com.vn.vietatech.utils.SettingUtil;
import com.vn.vietatech.utils.Utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Button;
import android.widget.Toast;

public class SubMenuAdapter extends BaseAdapter {
	private Context mContext;
	ArrayList<SubMenu> listSubMenu = new ArrayList<SubMenu>();
	
	private PosMenu selectedPOSMenu;
	
	ArrayList<Button> listButtonMenu = new ArrayList<Button>();

	public SubMenuAdapter(Context c, PosMenu selectedPOSMenu) {
		this.mContext = c;
		this.selectedPOSMenu = selectedPOSMenu;

		String POSGroup;
		try {
			POSGroup = SettingUtil.read(mContext).getPosGroup();
			if(selectedPOSMenu.getSubMenu().size() == 0) {
				// load form server
				listSubMenu = new PosMenuAPI(mContext).getSubMenu(selectedPOSMenu.getDefaultValue(), POSGroup);
				selectedPOSMenu.setSubMenu(listSubMenu);
			} else {
				// load from local
				listSubMenu = selectedPOSMenu.getSubMenu();
			}
		} catch (IOException e) {
			Toast.makeText(this.mContext, e.getMessage(), Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(this.mContext, e.getMessage(), Toast.LENGTH_LONG).show();
		}
		
	}

	public int getCount() {
		return listSubMenu.size();
	}

	public SubMenu getItem(int position) {
		return listSubMenu.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final SubmenuButton btn;
		final SubMenu subMenu = listSubMenu.get(position);

		btn = new SubmenuButton(mContext, subMenu, listButtonMenu, selectedPOSMenu);
		listButtonMenu.add(btn);

		return btn;
	}
}