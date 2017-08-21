package Jmeter;

//中城视通---在AES.java基础上修改
import java.security.SecureRandom;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.json.JSONException;
import org.json.JSONObject;

import sun.misc.BASE64Encoder;


// gdszzcst.#@&!
public class doMarket {
	private final static String DES = "DES";
	//密码加密秘钥
	private final static String KEY = "gdszzcst.#@&!";
	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {

        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }
	
	public static String encryptPWD(String data) throws Exception {
		String key = KEY;
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
//        System.out.println(new BASE64Encoder().encode(bt));
        return new BASE64Encoder().encode(bt);
    }
	
	//解析服务器返回的JSon数据
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
	
	public static void main(String[] args) throws Exception {
		encryptPWD("123456");
		
	}
	
}
