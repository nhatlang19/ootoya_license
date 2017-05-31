package com.vn.vietatech.api;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.vn.vietatech.combo.MyApplication;
import com.vn.vietatech.model.SalesCode;

import android.content.Context;

public class SaleCodeAPI extends AbstractAPI {
	public SaleCodeAPI(Context context) throws Exception {
		super(context);
	}
	
	@Override
	protected String doInBackground(String... params) {

		final MyApplication globalVariable = (MyApplication) mContext;
		ArrayList<SalesCode> data = globalVariable.getSalesCode();
		if (data == null) {
			try {
				data = getSalesCode();
				globalVariable.setSalesCode(data);
			} catch (Exception e) {
			}
		}

		return super.doInBackground(params);
	}

	public ArrayList<SalesCode> getSalesCode() throws Exception {
		setMethod(METHOD_GET_SALES_CODE);

		ArrayList<SalesCode> salesCodes = new ArrayList<SalesCode>();

		SoapObject response = (SoapObject) this.callService();
		SoapObject soapObject = (SoapObject) response.getProperty("diffgram");

		if (soapObject.getPropertyCount() != 0) {
			SoapObject webServiceResponse = (SoapObject) soapObject
					.getProperty("NewDataSet");

			for (int i = 0; i < webServiceResponse.getPropertyCount(); i++) {
				SoapObject tableObject = (SoapObject) webServiceResponse
						.getProperty(i);

				SalesCode salesCode = new SalesCode();
				salesCode.setCode(tableObject.getProperty("Code").toString());
				salesCode.setDescription(tableObject.getProperty("Description").toString());
				salesCode.setPriceLevel(tableObject.getProperty("PriceLevel").toString());
				salesCode.setTaxClass(tableObject.getProperty("TaxClass").toString());
				salesCode.setMemberType(tableObject.getProperty("MemberType").toString());
				
				salesCodes.add(salesCode);
			}
		}
		return salesCodes;
	}

}
