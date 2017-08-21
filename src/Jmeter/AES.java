package Jmeter;

//游视秀
import java.lang.reflect.Method;
import java.security.Key;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class AES {
	
    /*** 
     * encode by Base64 
     */  
    public static String encodeBase64(byte[]input) throws Exception{  
        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
        Method mainMethod= clazz.getMethod("encode", byte[].class);  
        mainMethod.setAccessible(true);  
         Object retObj=mainMethod.invoke(null, new Object[]{input});  
         return (String)retObj;  
    }  
    /*** 
     * decode by Base64 
     */  
    public static byte[] decodeBase64(String input) throws Exception{  
        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
        Method mainMethod= clazz.getMethod("decode", String.class);  
        mainMethod.setAccessible(true);  
         Object retObj=mainMethod.invoke(null, input);  
         return (byte[])retObj;  
    }  

	private static final String AESTYPE = "AES/ECB/PKCS5Padding";
	
	/**
	 * 对字符串进行Base64编码加密
	 * @param keyStr
	 * @param plainText
	 * @return
	 */
	
	public static String tojson(String str){
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		String json = gson.toJson(str);
		return json;
	}
	
	
	
	public static String encrypt(String plainText) {
		byte[] encrypt = null;
		try {
			String keyStr = "AKxNB89D3Fcgenkc";
			Key key = generateKey(keyStr);
//			System.out.println("sdf");
			Cipher cipher = Cipher.getInstance(AESTYPE);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			encrypt = cipher.doFinal(plainText.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
//		return new String(Base64.encode(encrypt, Base64.DEFAULT));
		try {
			return new String(AES.encodeBase64(encrypt));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 对字符串进行Base64解密
	 * @param keyStr
	 * @param encryptData
	 * @return
	 */
	public static String decrypt(String encryptData) {
		byte[] decrypt = null;
		try {
			String keyStr = "AKxNB89D3Fcgenkc";
			Key key = generateKey(keyStr);
			Cipher cipher = Cipher.getInstance(AESTYPE);
			cipher.init(Cipher.DECRYPT_MODE, key);
//			decrypt = cipher.doFinal(Base64.decode(encryptData, Base64.DEFAULT));
			decrypt = cipher.doFinal(AES.decodeBase64(encryptData));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(decrypt).trim();
	}

	private static Key generateKey(String key) throws Exception {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
			return keySpec;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	
	public static String print(String str){
		System.out.println(str);
		return null;		
	}
	
	public static String getvalue(String key,String josn){
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(josn);
			String value = jsonObject.optString(key);
			return value;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static String Requestbody(String key1,String value1,String key2,String value2,String key3,String value3){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("ysx_ua","iPad4,4");
		map.put("ysx_os", "2");// 平台:1：android 2：IOS（上传视频记录
		map.put("ysx_appid", "yxs14615737845553");
		map.put("ysx_appkey", "Y3AxNDMxNDg5ODQyNTUzMg==");
		map.put("ysx_version", "2.1.26");
		map.put("channel_id", "20141208");
		map.put("channel_key", "def5a36fe65e6933ec9e285ee161b9fe");
		map.put("ysx_udid", "BA7088FE-BC06-4851-82F6-FBECA37964AA");
		
		map.put(key1, value1);
		map.put(key2, value2);
		map.put(key3, value3);


		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		String json = gson.toJson(map);
		
		String encrypt = AES.encrypt(json);
		
		return encrypt;		
	}
	
	public static String Requestbodyfortwo(String key1,String value1,String key2,String value2){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("ysx_ua","iPad4,4");
		map.put("ysx_os", "2");// 平台:1：android 2：IOS（上传视频记录
		map.put("ysx_appid", "yxs14615737845553");
		map.put("ysx_appkey", "Y3AxNDMxNDg5ODQyNTUzMg==");
		map.put("ysx_version", "2.1.26");
		map.put("channel_id", "20141208");
		map.put("channel_key", "def5a36fe65e6933ec9e285ee161b9fe");
		map.put("ysx_udid", "BA7088FE-BC06-4851-82F6-FBECA37964AA");
		
		map.put(key1, value1);
		map.put(key2, value2);

		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		String json = gson.toJson(map);
		
		String encrypt = AES.encrypt(json);
		
		return encrypt;		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AES.print("hello");
		
		
		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("ysx_ua", AndroidUtils.getPhoneInfo());
		map.put("ysx_os", "1");// 平台:1：android 2：IOS（上传视频记录
		map.put("ysx_appid", "yxs14615737845553");
		map.put("ysx_appkey", "Y3AxNDMxNDg5ODQyNTUzMg==");


		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		String json = gson.toJson(map);
		
//		String encrypt = AES.encrypt(json);
//		
		AES.print(json);
//		
//		String decode = AES.decrypt(encrypt);
//		
//		System.out.println(map);
//		
//		String decode2 = AES.Requestbody();
//		AES.print(decode2);
		
		String test = AES.Requestbody("id","54139","from_uid","436782","type","2");
//		AES.print(test);
		
		String test2 = AES.decrypt(test);
//		AES.print(test2);
		

//		\"ysx_appid\":\"(.+?)\"
			
//			String json1 = "122345678902345678912345678";
		
//			String a = "ysx_appid";
	        Pattern pattern1 = Pattern.compile("\"ysx_appid\":\"(.+?)\"");	
	        Matcher matcher1 = pattern1.matcher(json);
	        
	        System.out.println(matcher1.find());
	        
	        System.out.println(matcher1.group(1));
	        
//	        String appid1 = matcher1.group(1);
//
//	        System.out.println(appid1);
	        
	        String appid = matcher1.replaceAll(""); 
	        
//	        String appid2 = matcher1.group(0);
//	        AES.print(appid2);
//	        
	        AES.print(appid);
	        
	        
//	        String str = "fhajsfjidsoajfkdlsa;jfeiwojf;dsa";
//	        Pattern pattern2 = Pattern.compile("(fj)(id)");	
//	        Matcher matcher3 = pattern2.matcher(str);
	        
//	        
//	        String appid1 = matcher3.group(1);
//	        AES.print(appid1);
	        
	        // TODO Auto-generated method stub  
	        String str = "Hello,World! in Java.";  
	        Pattern pattern = Pattern.compile("W(or)(ld!)");  
	        Matcher matcher = pattern.matcher(str);  
	        while(matcher.find()){  
	         System.out.println("Group 0:"+matcher.group(0));//得到第0组——整个匹配  
	         System.out.println("Group 1:"+matcher.group(1));//得到第一组匹配——与(or)匹配的  
	         System.out.println("Group 2:"+matcher.group(2));//得到第二组匹配——与(ld!)匹配的，组也就是子表达式  
	         System.out.println("Start 0:"+matcher.start(0)+" End 0:"+matcher.end(0));//总匹配的索引  
	         System.out.println("Start 1:"+matcher.start(1)+" End 1:"+matcher.end(1));//第一组匹配的索引  
	         System.out.println("Start 2:"+matcher.start(2)+" End 2:"+matcher.end(2));//第二组匹配的索引  
	         System.out.println(str.substring(matcher.start(0),matcher.end(1)));//从总匹配开始索引到第1组匹配的结束索引之间子串——Wor  
	        }  

	       
	        
	        JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(json);
				String ysx_appid = jsonObject.optString("ysx_appid");
				System.out.println(ysx_appid);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			String abc = AES.getvalue("ysx_os", json);
			
			AES.print(abc);
			
			
			
			
	}
	
}


