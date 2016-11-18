package com.zzti.fengyongge.timepicker.timeview;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StringUtils {
	public static final String EMPTY = "";
	public static final int INDEX_NOT_FOUND = -1;
	private static final int PAD_LIMIT = 8192;
	public static Toast toast;

	public static boolean isEmpty(CharSequence cs) {
		return (cs == null) || (cs.length() == 0);
	}

	public static boolean isNotEmpty(CharSequence cs) {
		return !isEmpty(cs);
	}
	public static boolean isNotEmpty(List<String> cs) {
		if (cs == null) {
			
			return false;
		}
		
		if (cs.size() == 0) {
					
			return false;
		}
		
		return true;
	}
	
	
	public static String getString(String cs, String name) {
		if (isEmpty(cs)) {
			return name+" ";
		}else {
			
			return "";
		}
	}
	public static String getListString(List<String> cs, String name) {
		if (cs == null) {
			return name+" ";
		}
		if (cs.size() == 0) {
			return name+" ";
		}
		else {
			
			return "";
		}
	}

	/**
	* function 判断过滤单个string为null
	 * author fengyongge
	 * @param s
	 * @return
	 */
	public static String filtNull(String s) {
		if (s!=null) {
			return  s;
		} else {
			s="null";
		}
		return  s;
	}

	/**
	 * function 给控件设置一个可以为null的值
	 * author fengyongge
	 * @param textView
	 * @param s
	 */
	public static void filtNull(TextView textView, String s) {
		if (s != null) {
			textView.setText(s);
		} else {
			textView.setText(filtNull(s));
		}
	}

	/**
	 * function 防止多次弹toast
	 * author fengyongge
	 * @param context
	 * @param content
	 */
	public static void showToast(Context context, String content){
		if(toast==null){
			toast = Toast.makeText(context,content, Toast.LENGTH_SHORT);
		}else{
			toast.setText(content);
		}
		toast.show();
	}


}