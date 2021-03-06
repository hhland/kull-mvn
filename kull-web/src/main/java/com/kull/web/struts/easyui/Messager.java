package com.kull.web.struts.easyui;


import java.text.MessageFormat;




import com.kull.Clazz;
import com.kull.Stringz;
import com.kull.able.Scriptable;
import com.kull.script.*;




public class Messager  implements Scriptable {

	public static String SHOW_TYPE_SLIDE="slide";
	public static String SHOW_TYPE_FADE="fade";
	public static String SHOW_TYPE_SHOW="show";
	
	public static String ICON_INFO="info";
	public static String ICON_ERROR="error";
	public static String ICON_QUESTION="question";
	public static String ICON_WARNING="warning";
	
	private String showType;
	private String showSpeed;
	private String width;
	private String height;
	private String msg;
	private String title;
	private String timeout;
	private String icon;
	private String fn;
	
	public Messager(){
		
	}
	
	public String alert()
	{
		String lStrReturn="";
		String lStrRege="$.messager.alert({0},{1},{2},{3});";
		lStrReturn=MessageFormat.format(lStrRege,
	            Clazz.<String>valueOf(this.title,"\"\""),	 //0
	            Clazz.<String>valueOf(this.msg, "\"\""),   //1
	            Clazz.<String>valueOf(this.icon,"\"\""),    //2
	            Clazz.<String>valueOf(this.fn, new JsFunction(new String[]{"ok"}).toScirpt()) //3
		);
		return lStrReturn;
	}
	
	public String confirm()
	{
		String lStrReturn="";
		String lStrRege="$.messager.confirm({0},{1},{2});";
		lStrReturn=MessageFormat.format(lStrRege,
	    Clazz.<String>valueOf(this.title,"\"\""),	 //0
	    Clazz.<String>valueOf(this.msg, "\"\""),   //1
	    Clazz.<String>valueOf(this.fn, new JsFunction(new String[]{"ok"}).toScirpt())  //2
		);
		return lStrReturn;
	}
	
	public String prompt()
	{
		String lStrReturn="";
		String lStrRege="$.messager.prompt({0},{1},{2})";
		lStrReturn=MessageFormat.format(lStrRege,
			    Clazz.<String>valueOf(this.title,"\"\""),	 //0
			    Clazz.<String>valueOf(this.msg, "\"\""),   //1
			    Clazz.<String>valueOf(this.fn, new JsFunction(new String[]{"ok"}).toScirpt())  //2
				);
		return lStrReturn;
	}
	
	public String show()
	{
		String lStrReturn="";
		String lStrRege="$.messager.{0}.({1})";
		return lStrReturn;
	}
	
	
	
	
	public String getShowType() {
		return showType;
	}

	public Messager setShowType(String showType) {
		this.showType = showType;
		return this;
	}
	
	public Messager setShowType(String showType,boolean isStr) {
		this.showType =isStr?Stringz.quota(showType):showType;
		return this;
	}

	public String getShowSpeed() {
		return showSpeed;
	}

	public Messager setShowSpeed(String showSpeed) {
		this.showSpeed = showSpeed;
		return this;
	}
	
	public Messager setShowSpeed(String showSpeed,boolean isStr) {
		this.showSpeed = isStr?Stringz.quota(showSpeed):showSpeed;
		return this;
	}

	public String getWidth() {
		return width;
	}

	public Messager setWidth(String width) {
		this.width = width;
		return this;
	}
	
	public Messager setWidth(String showSpeed,boolean isStr) {
		this.width = isStr?Stringz.quota(width):width;
		return this;
	}

	public String getHeight() {
		return height;
	}

	public Messager setHeight(String height) {
		this.height = height;
		return this;
	}
	
	public Messager setHeight(String height,boolean isStr) {
		this.height = isStr?Stringz.quota(height):height;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public Messager setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	
	public Messager setMsg(String msg,boolean isStr) {
		this.msg = isStr?Stringz.quota(msg):msg;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Messager setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public Messager setTitle(String title,boolean isStr) {
		this.title = isStr?Stringz.quota(title):title;
		return this;
	}

	public String getTimeout() {
		return timeout;
	}

	public Messager setTimeout(String timeout) {
		this.timeout = timeout;
		return this;
	}
	
	public Messager setTimeout(String timeout,boolean isStr) {
		this.timeout =isStr?Stringz.quota(timeout):timeout;
	    return this;
	}

	public String getIcon() {
		return icon;
	}

	public Messager setIcon(String icon) {
		this.icon = icon;
		return this;
	}
	
	public Messager setIcon(String icon,boolean isStr) {
		this.icon =isStr?Stringz.quota(icon):icon;
		return this;
	}

	public String getFn() {
		return fn;
	}

	public Messager setFn(String fn) {
		this.fn = fn;
		return this;
	}

	public String toScirpt() {
		// TODO Auto-generated method stub
		String lStrRege="$.messager.{0}.({1})";
		return null;
	}

}
