package Jmeter;

//�г���ͨ---��AES.java�������޸�
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
	//���������Կ
	private final static String KEY = "gdszzcst.#@&!";
	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {

        // ����һ�������ε������Դ
        SecureRandom sr = new SecureRandom();

        // ��ԭʼ��Կ���ݴ���DESKeySpec����
        DESKeySpec dks = new DESKeySpec(key);

        // ����һ����Կ������Ȼ��������DESKeySpecת����SecretKey����
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher����ʵ����ɼ��ܲ���
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(DES);

        // ����Կ��ʼ��Cipher����
        cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }
	
	public static String encryptPWD(String data) throws Exception {
		String key = KEY;
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
//        System.out.println(new BASE64Encoder().encode(bt));
        return new BASE64Encoder().encode(bt);
    }
	
	//�������������ص�JSon����
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
