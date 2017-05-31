package com.vn.vietatech.combo;

import java.util.ArrayList;

import com.vn.vietatech.model.Cashier;
import com.vn.vietatech.model.PosMenu;
import com.vn.vietatech.model.SalesCode;
import com.vn.vietatech.model.Section;
import com.vn.vietatech.model.Table;

import android.app.Application;

public class MyApplication extends Application {
	private ArrayList<Section> _listSections = null;
	private Cashier _cashier = null;
	private ArrayList<Table> _tables = null;
	private ArrayList<PosMenu> _listPosMenu = null;
	private ArrayList<SalesCode> _salesCode = null;
	
	public ArrayList<PosMenu> getListPosMenu() {
		return _listPosMenu;
	} 

	public void setListPosMenu(ArrayList<PosMenu> listPosMenu) {
		this._listPosMenu = listPosMenu;
	}
	
	public ArrayList<Section> getSections() {
		return _listSections;
	} 

	public void setSections(ArrayList<Section> listSections) {
		this._listSections = listSections;
	}
	
	public Cashier getCashier() {
		return _cashier;
	}

	public void setCashier(Cashier cashier) {
		this._cashier = cashier;
	}
	
	public ArrayList<Table> getTables() {
		return this._tables;
	}

	public void setTables(ArrayList<Table> tables) {
		tables.add(0, new Table());
		this._tables = tables;
	}

	public ArrayList<SalesCode> getSalesCode() {
		return _salesCode;
	}

	public void setSalesCode(ArrayList<SalesCode> _salesCode) {
		_salesCode.add(0, new SalesCode());
		this._salesCode = _salesCode;
	}

}
