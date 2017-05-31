package com.vn.vietatech.api;

import java.util.ArrayList;
import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

import com.vn.vietatech.model.Item;
import com.vn.vietatech.model.Remark;

import android.content.Context;

public class ItemAPI extends AbstractAPI {

	public ItemAPI(Context context) throws Exception {
		super(context);
	}

	public ArrayList<Remark> getRemarkByItem(String itemCode) throws Exception {
		setMethod(METHOD_GET_REMARK_BY_ITEM);
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("itemcode", itemCode);

		SoapObject response = (SoapObject) this.callService(params);
		SoapObject soapObject = (SoapObject) response.getProperty("diffgram");
		
		ArrayList<Remark> remarks = new ArrayList<Remark>();
		if (soapObject.getPropertyCount() != 0) {
			
			SoapObject webServiceResponse = (SoapObject) soapObject
					.getProperty("NewDataSet");
			
			for (int i = 0; i < webServiceResponse.getPropertyCount(); i++) {
				SoapObject tableObject = (SoapObject) webServiceResponse
						.getProperty(i);
			
				Remark remark = new Remark();
				remark.setName(tableObject.getProperty("Description").toString());
				
				remarks .add(remark);
			}
		}
		return remarks;
	}

	public Item getItemBySubMenuSelected(String currSubItem, String priceLevel, String qty) throws Exception {
		setMethod(METHOD_GET_ITEM);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("currSubItem", currSubItem);
		params.put("priceLevel", priceLevel);
		params.put("qty", qty);

		SoapObject response = (SoapObject) this.callService(params);
		SoapObject soapObject = (SoapObject) response.getProperty("diffgram");
		System.out.println(soapObject);
		Item item = new Item();
		if (soapObject.getPropertyCount() != 0) {

			SoapObject webServiceResponse = (SoapObject) soapObject
					.getProperty("NewDataSet");
			SoapObject tableObject = (SoapObject) webServiceResponse
					.getProperty("Table1");

			item.setItemCode(tableObject.getProperty("ItemCode").toString());
			item.setItemName(tableObject.getProperty("RecptDesc").toString());
			item.setOrgPrice(tableObject.getProperty("UnitSellPrice").toString());
			item.setComboPack(tableObject.getProperty("ComboPack").toString());
			item.setOnPromotion(tableObject.getProperty("OnPromotion").toString());
			if(item.getOnPromotion().equals("Y")) {
				item.setPromoPrice(tableObject.getProperty("PromoPrice").toString());
				item.setPromoCode(tableObject.getProperty("PromoCode").toString());
				item.setPromoDesc(tableObject.getProperty("PromoDesc").toString());
				item.setPromoClass(tableObject.getProperty("PromoClass").toString());
				item.setStartDate(tableObject.getProperty("StartDate").toString());
				item.setEndDate(tableObject.getProperty("EndDate").toString());
				item.setMinQty(tableObject.getProperty("MinQty").toString());
				item.setMaxQty(tableObject.getProperty("MaxQty").toString());
				item.setPkgPrice(tableObject.getProperty("PkgPrice").toString());
				item.setPkgQty(tableObject.getProperty("PkgQty").toString());
				item.setPkgItems(tableObject.getProperty("PkgItems").toString());
				item.setBlanket(tableObject.getProperty("Blanket").toString());
			}
		}
		return item;
	}
}
