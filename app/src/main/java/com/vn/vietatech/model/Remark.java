package com.vn.vietatech.model;

public class Remark {
	private String _id;
	private String _name;
	
	public Remark() {
		this._id = "";
		this._name = "";
	}
	
	public Remark(String id, String name) {
		this._id = id;
		this._name = name;
	}
	
	public void setId(String id) {
		this._id = id;
	}
	
	public String getId() {
		return this._id;
	}

	public void setName(String name) {
		this._name = name;
	}
	
	public String getName() {
		return this._name;
	}
}
