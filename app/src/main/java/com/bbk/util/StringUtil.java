package com.bbk.util;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bbk.activity.R;

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {
	private static RelativeLayout inflater;
	static Toast toast;
	static TextView title;
	public static String getResultNum(String str) {
		char[] a = str.toCharArray();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < a.length; i++) {
			char tmp = a[i];
			String s1 = "[0-9 -]";
			Pattern p = Pattern.compile(s1);
			Matcher m = p.matcher(tmp + "");
			if (m.find()) {
				sb.append(tmp);
			} 
		}

		return sb.toString();
	}

	public static String getCode() {
		StringBuffer codeBuffer = new StringBuffer();
		for(int i = 0; i < 6; i ++) {
			int code = (int) (Math.random() * 10);
			codeBuffer.append(code);
		}
		return codeBuffer.toString();
	}

	public static boolean isNum(String str) {

		char[] a = str.toCharArray();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < a.length; i++) {
			char tmp = a[i];
			String s1 = "[0-9]";
			Pattern p = Pattern.compile(s1);
			Matcher m = p.matcher(tmp + "");
			if (!m.find()) {
				return false;
			} 
		}
		return true;
	}
	public static boolean isPhoneNum(String str){
		return str.matches("^((1[3,5,8][0-9])|(14[5,7])|(17[0,1,6,7,8]))\\d{8}$");
	}

	public static String getRsult(String str) {
		char[] a = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		for(int i = 2; i < a.length; i ++) {
			sb.append(a[i]);
		}
		return sb.toString();
	}

	public static boolean isJsonArray(String str){
		final char[] strChar = str.substring(0, 1).toCharArray(); 
		final char firstChar = strChar[0];
		if(firstChar == '['){ 
			return true;
		}else{ 
			return false;
		} 
	}
	public static void main(String[] args) {
		System.out.println(isPhoneNum("174813925349"));
	}

	//提示消息自定义弹窗
	public static void showToast(Context context,String tishi) {
// TODO Auto-generated method stub
		if (toast == null){
		toast = new Toast(context);
		View layout = inflater.inflate(context, R.layout.toast_layout, null);
		title = layout.findViewById(R.id.toast_title);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setView(layout);
		}
		title.setText(tishi);
		toast.show();
	}
	public static void showToast(Context ctx, int stringId) {
		showToast(ctx, ctx.getString(stringId));
	}
	public static void cancelToast() {
		if (toast != null) {
			toast.cancel();
			toast = null;
			title = null;
		}
	}
	public  static List removeDuplicate(List list)  {
		for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )  {
			for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )  {
				if  (list.get(j).equals(list.get(i)))  {
					list.remove(j);
				}
			}
		}
		return list;
	}

	public static boolean isNullOrEmpty(String content){
		boolean result = false;
		if(null == content || "".equals(content.trim())){
			return true;
		}
		return false;
	}

	/**
	 * 验证手机号码的格式是否正确
	 */
	public static boolean isMobilePhoneVerify(String mobileString){
		if(mobileString ==null || "".equals(mobileString.trim())){
			return true;
		}else{
			Pattern p = Pattern.compile("^1[0-9][0-9]{9}$");
			Matcher m = p.matcher(mobileString.trim());
			if(!m.matches()){
				return true;
			}else{
				return false;
			}
		}

	}

	public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
		Class<?> tabLayout = tabs.getClass();
		Field tabStrip = null;
		try {
			tabStrip = tabLayout.getDeclaredField("mTabStrip");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

		tabStrip.setAccessible(true);
		LinearLayout llTab = null;
		try {
			llTab = (LinearLayout) tabStrip.get(tabs);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
		int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

		for (int i = 0; i < llTab.getChildCount(); i++) {
			View child = llTab.getChildAt(i);
			child.setPadding(0, 0, 0, 0);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
			params.leftMargin = left;
			params.rightMargin = right;
			child.setLayoutParams(params);
			child.invalidate();
		}


	}
}
