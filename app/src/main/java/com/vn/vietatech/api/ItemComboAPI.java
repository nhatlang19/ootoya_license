package com.vn.vietatech.api;

import java.util.ArrayList;
import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

import com.vn.vietatech.model.ItemCombo;

import android.content.Context;

public class ItemComboAPI extends AbstractAPI {

	public ItemComboAPI(Context context) throws Exception {
		super(context);
	}
	
	public ArrayList<ItemCombo> getItemComboComboBySubMenuSelected(String currSubItem) throws Exception {
		setMethod(METHOD_GET_ITEM_COMBO);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("currSubItem", currSubItem);

		SoapObject response = (SoapObject) this.callService(params);
		SoapObject soapObject = (SoapObject) response.getProperty("diffgram");
//		System.out.println(soapObject);
		
		ArrayList<ItemCombo> list = new ArrayList<ItemCombo>();
		if (soapObject.getPropertyCount() != 0) {
			
			SoapObject webServiceResponse = (SoapObject) soapObject
					.getProperty("NewDataSet");
			
			for (int i = 0; i < webServiceResponse.getPropertyCount(); i++) {
				SoapObject tableObject = (SoapObject) webServiceResponse
						.getProperty(i);
			
				ItemCombo itemCombo = new ItemCombo();

				itemCombo.setComboItem(tableObject.getProperty("ComboItem").toString());
				itemCombo.setItemDesc(tableObject.getProperty("ItemDesc").toString());
				itemCombo.setQuantity(tableObject.getProperty("Quantity").toString());
				itemCombo.setHidden(tableObject.getProperty("Hidden").toString());
				itemCombo.setModClass(tableObject.getProperty("ModClass").toString());
				itemCombo.setQtyEditable(tableObject.getProperty("QtyEditable").toString());
				
				list .add(itemCombo);
			}
		}
		return list;
	}
}
