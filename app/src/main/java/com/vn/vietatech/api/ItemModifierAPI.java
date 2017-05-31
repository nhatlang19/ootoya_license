package com.vn.vietatech.api;

import java.util.ArrayList;
import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

import com.vn.vietatech.model.ItemModifier;

import android.content.Context;

public class ItemModifierAPI extends AbstractAPI {

	public ItemModifierAPI(Context context) throws Exception {
		super(context);
	}
	
	public ArrayList<ItemModifier> getModifierByModifierItem(String modifierItem) throws Exception {
		setMethod(METHOD_GET_ITEM_MODIFIER);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("modifierItem", modifierItem);
		SoapObject response = (SoapObject) this.callService(params);
		SoapObject soapObject = (SoapObject) response.getProperty("diffgram");
//		System.out.println(soapObject);
		
		ArrayList<ItemModifier> list = new ArrayList<ItemModifier>();
		if (soapObject.getPropertyCount() != 0) {
			
			SoapObject webServiceResponse = (SoapObject) soapObject
					.getProperty("NewDataSet");
			
			for (int i = 0; i < webServiceResponse.getPropertyCount(); i++) {
				SoapObject tableObject = (SoapObject) webServiceResponse
						.getProperty(i);
			
				ItemModifier item = new ItemModifier();
				item.setItemCode(tableObject.getProperty("ItemCode").toString());
				item.setQuantity(tableObject.getProperty("Quantity").toString());
				item.setModCode(tableObject.getProperty("ModCode").toString());
				item.setModDesc(tableObject.getProperty("ModDesc").toString());
				item.setUnitPrice(tableObject.getProperty("UnitPrice").toString());
				
				list .add(item);
			}
		}
		return list;
	}
}
