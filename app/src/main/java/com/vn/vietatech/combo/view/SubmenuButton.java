package com.vn.vietatech.combo.view;

import java.util.ArrayList;

import com.vn.vietatech.api.ItemAPI;
import com.vn.vietatech.api.ItemComboAPI;
import com.vn.vietatech.combo.POSMenuActivity;
import com.vn.vietatech.model.Item;
import com.vn.vietatech.model.ItemCombo;
import com.vn.vietatech.model.PosMenu;
import com.vn.vietatech.model.Setting;
import com.vn.vietatech.model.SubMenu;
import com.vn.vietatech.utils.SettingUtil;
import com.vn.vietatech.utils.Utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class SubmenuButton extends Button {

	private Context mContext;
	private Runnable runnable;
	private Handler handler;
	public int numberClick;
	private SubMenu subMenu;
	private ArrayList<Button> listButtonMenu;
	private PosMenu selectedPOSMenu;

	public SubmenuButton(Context context, final SubMenu subMenu
			, ArrayList<Button> listButtonMenu, PosMenu selectedPOSMenu) {
		super(context);
		this.mContext = context;
		this.subMenu = subMenu;
		this.listButtonMenu = listButtonMenu;
		this.selectedPOSMenu = selectedPOSMenu;
		
		this.setLayoutParams(new GridView.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
		GradientDrawable drawable = new GradientDrawable();
	    drawable.setShape(GradientDrawable.RECTANGLE);
	    drawable.setStroke(2, Color.BLACK);
	    drawable.setColor(Utils.parseColor(selectedPOSMenu.getBtnColor()));
	    this.setBackgroundDrawable(drawable);
	    this.setTextColor(Utils.parseColor(selectedPOSMenu.getFontColor()));
		this.setTextSize(12);
		this.setText(subMenu.getDescription());
		this.setLines(2);
		
		handler = new Handler();
		runnable = new Runnable() {
			public void run() {
				
				POSMenuActivity activity = (POSMenuActivity) mContext;
				try {
					Setting setting = SettingUtil.read(mContext);
					if(!setting.getType().equals("1")) {
						numberClick = 1;
					}
					
					Item item = new ItemAPI(mContext).getItemBySubMenuSelected(subMenu.getDefaultValue(), activity.getPriceLevel(), String.valueOf(numberClick));
					String comboPack = item.getComboPack();
					item.setItemType(comboPack);
					item.setNumberClick(numberClick);
					if(comboPack.equals("N") || comboPack.equals("R")) {
						item.setQty(String.valueOf(item.getNumberClick()));
						activity.addItem(item);
					} else if(comboPack.equals("C")) {
						ArrayList<ItemCombo> itemComboList = new ItemComboAPI(mContext).getItemComboComboBySubMenuSelected(item.getItemCode());
						item.setItemCombo(itemComboList);
						activity.onOpenDialogCombo(item);
					}
				} catch (Exception e) {
					Toast.makeText(mContext, e.getMessage(),
							Toast.LENGTH_LONG).show();
				}
				setColorButton();
				numberClick = 0;
			}
		};
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
	
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			if(numberClick != 0) {
				handler.removeCallbacks(runnable);
			}
			numberClick++;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
        	handler.postDelayed(runnable, 450);
        }
		return super.onTouchEvent(event);
	}
	
	private void setColorButton() {
	    for(Button button: listButtonMenu) {
	    	GradientDrawable drawable = (GradientDrawable) button.getBackground();
		    drawable.setStroke(2, Color.BLACK);
		    button.setBackgroundDrawable(drawable);
		}
	    
	    GradientDrawable drawable = (GradientDrawable) this.getBackground();
		drawable.setStroke(10, Color.BLACK);
		this.setBackgroundDrawable(drawable);
	}
}
