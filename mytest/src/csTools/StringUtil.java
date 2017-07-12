package csTools;
import java.math.BigDecimal;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 /**
 * 
 * 功能描述：字符串判断工具类
 */
public class StringUtil {
	/**
	 * 
	 * isNum:判断一个字符串是否为数字
	 * @param p
	 * @return 
	 * @return boolean
	 */
	public static boolean isNum(String msg) {
		if (java.lang.Character.isDigit(msg.charAt(0))) {
			return true;
		}
		return false;
	}


	/**
	 * 
	 * isBlank:判断一个字符串为空
	 * @param p
	 * @return 
	 * @return boolean
	 */
	public static boolean isBlank(String p){
		if (p!=null && !"".equals(p)) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * 
	 * isEqual:判断两个字符串相等
	 * @param p1
	 * @param p2
	 * @return 相等返回true
	 * @return boolean
	 */
	public static boolean isEqual(String p1,String p2){
		if(isNotBlank(p1)&&isNotBlank(p2)&&p1.equals(p2)){
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * 
	 * isEqual:判断两个字符串相等
	 * @param p1
	 * @param p2
	 * @return 不相等返回true
	 * @return boolean
	 */
	public static boolean isNotEqual(String p1,String p2){
		if(isNotBlank(p1)&&isNotBlank(p2)&&p1.equals(p2)){
			return false;
		}else{
			return true;
		}
		
	}
	/**
	 * 
	 * isNotBlank:判断一个字符串不为空
	 * @param p
	 * @return 
	 * @return boolean
	 */
	public static boolean isNotBlank(String p){
		if (p != null && p.trim() != "" && !p.equals(null) && !p.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String toString(Object obj)
	{
		if(obj==null)
		{
			return "";
		}else{
			return obj.toString().trim();
		}
	}
	
	/**
	 * 仿oracle的nvl函数
	 * 如果第一个参数为空，则返回第二个参数的值
	 * 如果第一个参数不为空，则直接返回第一个参数的值
	 * @param s
	 * @param val
	 * @return
	 */
	public static String nvl(String s, String val) {
		return isBlank(s) ? val : s;
	}
	
	/**
	 * 验证对象是否为空
	 * @param o
	 * @return
	 */
	public static boolean isBlank(Object o) {
		if (o instanceof String) {
			return (o == null || isBlank(o.toString())) ? true : false;
		} else {
			return o == null ? true : false;
		}
	}
	
	/**
	 * 验证Map的指定key是否为空
	 * @param param
	 * @param keys
	 * @return
	 */
	public static boolean verifyMapByNull(Map param, String... keys) {
		// 先验证Map是否为空
		if (param == null || param.size() == 0) {
			return true;
		}
		boolean bool = false;
		// 循环验证key是否为空
		for (String k : keys) {
			if (isBlank(param.get(k))) {
				bool = true;
				break;
			}
		}
		return bool;
	}

	/**
	 * 判断map中字段是否为空
	 * @param name
	 * @param map
	 * @return
	 */
	public static boolean checkObjValueNull(String name,Map map){
		if(map==null) return false;
		Object str =  map.get(name);
		if(str==null){
			return true;
		}
		return false;
	}

	/**
	 * 判断map中字段是否为空
	 * @param name
	 * @param map
	 * @return
	 */
	public static boolean checkValueNull(String name,Map map){
		String str = "";
		if(map==null || map.get(name)==null) return true;
        if (map.get(name)instanceof Integer) {
            str= String.valueOf(map.get(name));
        }else if (map.get(name)instanceof Long) {
            str= String.valueOf(map.get(name));
        }else if (map.get(name)instanceof Double) {
            str= String.valueOf(map.get(name));
        }else if (map.get(name)instanceof BigDecimal) {
            str= String.valueOf(map.get(name));
        }else if (map.get(name)instanceof String) {
            str=(String) map.get(name);
        }else{
        	str=String.valueOf(map.get(name));
        }
        if(str==null||"".equals(str)){
            return true;
        }
		return false;
	}

	/**
	 * 获取以string对象组合的key-value的value值
	 * @param keys   	key集合字符串
	 * @param values	key集合对应的value集合字符串
	 * @param spliter	keys和values分隔符
	 * @param aimedKey	要查找的key
	 * @return	目标key对应的value或null
	 */
	public static String getPareKey(String keys,String values,String spliter,String aimedKey){
		String val=null;
		if((keys!=null && !keys.equals(""))&&(values!=null && !values.equals(""))){
			if(!keys.contains(aimedKey))
				return null;
			String[] keyArray=keys.split(spliter);
			String[] valueArray=values.split(spliter);
			int i=0,
				index=0;
			for(String s:keyArray){
				if(s.equals(aimedKey)){
					index=i;
					break;
				}
				i++;
			}
			if(valueArray.length<(index+1))
				return null;
			val=valueArray[index];
		}
		return val;
	}

	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str){
		if(str==null||"".equals(str)){
			return true;
		}
		return false;
	}
	

    /**
     * 按照字符指定长度将字符串拆分为的数组，用于短信分条
     * @param content
     * @param 截取长度，>5
     * @return
     */
    public static String[] splitSMSContent(String content, int len){
    	if(len <= 5)
    		throw new RuntimeException("截取字符串的长度不能小于5.");
    	
    	int clen = content.length();
    	String[] arr;
    	
    	if(clen > len){
    		
    		len = len - 5; //长度-5用于存放分页内容
	    	int arrCount = clen / len;
	    	
	    	if(clen % len > 0)
	    		arrCount += 1;
	    	
	    	arr = new String[arrCount];
	    	
	    	for(int i = 0; i < arrCount; i++){
	    		int fi = len * i;
	    		int ei = len * i + len;
	    		if(i == arrCount - 1)
	    			ei = clen;
	    		
	    		arr[i] = "[" + (i + 1) + "/" + arrCount + "]" + content.substring(fi, ei);
	    	}
	    	
    	}else{
    		arr = new String[]{content};
    	}
    	
    	
    	return arr;
    }
    /**
	 * 
	 * fSQLString:防止sql注入攻击，
	 * @param str
	 * @return 
	 * @return String
	 */
	public static String SQLString(String str)
	{
		String[] arrs={"and","or",">",":","|","*","=","+","&",")","(","!","<",">=","<=","'","\"","select","update","insert","from","delete","join","left"};//参数出现的特殊字符集合
		for(String a:arrs){
			str=str.toLowerCase().replace(a,"");
		}
		return str;
	}
	/**
	 * 
	 * printMap:拼装map为string串
	 * @param map
	 * @return 
	 * @return String
	 */
	public static String printObjectMap(HashMap<String,Object> map){
		String str="";
		if(map!=null&&map.size()>0){
			for(String key : map.keySet()){
				if(map.get(key)!=null&&!map.get(key).equals("")){
					str+="   "+key+"="+map.get(key);
					//logger.debug(key + "=" + map.get(key));	
				}
			}
		}
		return str;
	}
	
	/**
	 * 功能：double类型四舍五入保留两位有效数字
	 * @return double
	 */
	public static double twoDigct(double digital) {
		BigDecimal bigDecimal = new BigDecimal(digital);  
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
	}
}