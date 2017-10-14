package com.tomoya.jmeter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * ʹ��˽Կ����Կ����RSA�ӽ���
 * @author huyidong
 *
 */
public class RSA {
	/**
	 * ʹ�ù�Կ���м���
	 * @param plainText ����
	 * @param filepath ��Կ����ļ���·����Ĭ���ļ���publicKey.keystore
	 * @return RSA��Կ���� 
	 */
	public static String pubKeyEncrypt(String plainText, String filepath) {
		// ��Կ���ܹ���
		byte[] cipherData = null;
		try {
			cipherData = encrypt(
					loadPublicKeyByStr(loadPublicKeyByFile(filepath)),
					plainText.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String cipher = Base64.encode(cipherData);
		return cipher;
	}

	/**
	 * ʹ��˽Կ���м���
	 * @param plainText ����
	 * @param filepath ˽Կ����ļ���·����Ĭ���ļ���privateKey.keystore
	 * @return RSA˽Կ���� 
	 */
	public static String priKeyEncrypt(String plainText, String filepath) {
		// ˽Կ���ܹ���
		byte[] cipherData = null;
		try {
			cipherData = encrypt(
					loadPrivateKeyByStr(loadPrivateKeyByFile(filepath)),
					plainText.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String cipher = Base64.encode(cipherData);
		return cipher;
	}

	/**
	 * ʹ�ù�Կ��˽Կ���Ľ��н���
	 * @param cipher RSA˽Կ����
	 * @param filepath ��Կ����ļ���·����Ĭ���ļ���publicKey.keystore
	 * @return ���� 
	 */
	public static String pubKeyDecrypt(String cipher, String filepath) {
		// ��Կ���ܹ���
		byte[] res = null;
		try {
			res = decrypt(loadPublicKeyByStr(loadPublicKeyByFile(filepath)),
					Base64.decode(cipher));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String restr = new String(res);
		return restr;
	}

	/**
	 * ʹ��˽Կ�Թ�Կ���Ľ��н���
	 * @param cipher RSA��Կ����
	 * @param filepath ˽Կ����ļ���·����Ĭ���ļ���privateKey.keystore
	 * @return ���� 
	 */
	public static String priKeyDecrypt(String cipher, String filepath) {
		// ˽Կ���ܹ���
		byte[] res = null;
		try {
			res = decrypt(loadPrivateKeyByStr(loadPrivateKeyByFile(filepath)),
					Base64.decode(cipher));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String restr = new String(res);
		return restr;
	}

	/**
	 * ���ļ����������м��ع�Կ
	 * 
	 * @param in
	 *            ��Կ������
	 * @throws Exception
	 *             ���ع�Կʱ�������쳣
	 */
	private static String loadPublicKeyByFile(String path) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path
					+ "/publicKey.keystore"));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				sb.append(readLine);
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			throw new Exception("��Կ��������ȡ����");
		} catch (NullPointerException e) {
			throw new Exception("��Կ������Ϊ��");
		}
	}

	/**
	 * ���ַ����м��ع�Կ
	 * 
	 * @param publicKeyStr
	 *            ��Կ�����ַ���
	 * @throws Exception
	 *             ���ع�Կʱ�������쳣
	 */
	private static RSAPublicKey loadPublicKeyByStr(String publicKeyStr)
			throws Exception {
		try {
			byte[] buffer = Base64.decode(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("�޴��㷨");
		} catch (InvalidKeySpecException e) {
			throw new Exception("��Կ�Ƿ�");
		} catch (NullPointerException e) {
			throw new Exception("��Կ����Ϊ��");
		}
	}

	/**
	 * ���ļ����������м���˽Կ
	 * 
	 * @param keyFileName
	 *            ˽Կ�ļ���
	 * @return �Ƿ�ɹ�
	 * @throws Exception
	 */
	private static String loadPrivateKeyByFile(String path) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path
					+ "/privateKey.keystore"));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				sb.append(readLine);
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			throw new Exception("˽Կ���ݶ�ȡ����");
		} catch (NullPointerException e) {
			throw new Exception("˽Կ������Ϊ��");
		}
	}

	/**
	 * ���ַ����м���˽Կ
	 * 
	 * @param privateKeyStr
	 *            ˽Կ�����ַ���
	 * @throws Exception
	 *             ����˽Կʱ�������쳣
	 */
	private static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr)
			throws Exception {
		try {
			byte[] buffer = Base64.decode(privateKeyStr);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("�޴��㷨");
		} catch (InvalidKeySpecException e) {
			throw new Exception("˽Կ�Ƿ�");
		} catch (NullPointerException e) {
			throw new Exception("˽Կ����Ϊ��");
		}
	}

	/**
	 * ��Կ���ܹ���
	 * 
	 * @param publicKey
	 *            ��Կ
	 * @param plainTextData
	 *            ��������
	 * @return
	 * @throws Exception
	 *             ���ܹ����е��쳣��Ϣ
	 */
	private static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData)
			throws Exception {
		if (publicKey == null) {
			throw new Exception("���ܹ�ԿΪ��, ������");
		}
		Cipher cipher = null;
		try {
			// ʹ��Ĭ��RSA
			cipher = Cipher.getInstance("RSA");
			// cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] output = cipher.doFinal(plainTextData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("�޴˼����㷨");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("���ܹ�Կ�Ƿ�,����");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("���ĳ��ȷǷ�");
		} catch (BadPaddingException e) {
			throw new Exception("������������");
		}
	}

	/**
	 * ��Կ���ܹ���
	 * 
	 * @param publicKey
	 *            ��Կ
	 * @param cipherData
	 *            ��������
	 * @return ����
	 * @throws Exception
	 *             ���ܹ����е��쳣��Ϣ
	 */
	private static byte[] decrypt(RSAPublicKey publicKey, byte[] cipherData)
			throws Exception {
		if (publicKey == null) {
			throw new Exception("���ܹ�ԿΪ��, ������");
		}
		Cipher cipher = null;
		try {
			// ʹ��Ĭ��RSA
			cipher = Cipher.getInstance("RSA");
			// cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			byte[] output = cipher.doFinal(cipherData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("�޴˽����㷨");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("���ܹ�Կ�Ƿ�,����");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("���ĳ��ȷǷ�");
		} catch (BadPaddingException e) {
			throw new Exception("������������");
		}
	}

	/**
	 * ˽Կ���ܹ���
	 * 
	 * @param privateKey
	 *            ˽Կ
	 * @param plainTextData
	 *            ��������
	 * @return
	 * @throws Exception
	 *             ���ܹ����е��쳣��Ϣ
	 */
	private static byte[] encrypt(RSAPrivateKey privateKey, byte[] plainTextData)
			throws Exception {
		if (privateKey == null) {
			throw new Exception("����˽ԿΪ��, ������");
		}
		Cipher cipher = null;
		try {
			// ʹ��Ĭ��RSA
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			byte[] output = cipher.doFinal(plainTextData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("�޴˼����㷨");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("����˽Կ�Ƿ�,����");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("���ĳ��ȷǷ�");
		} catch (BadPaddingException e) {
			throw new Exception("������������");
		}
	}

	/**
	 * ˽Կ���ܹ���
	 * 
	 * @param privateKey
	 *            ˽Կ
	 * @param cipherData
	 *            ��������
	 * @return ����
	 * @throws Exception
	 *             ���ܹ����е��쳣��Ϣ
	 */
	private static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData)
			throws Exception {
		if (privateKey == null) {
			throw new Exception("����˽ԿΪ��, ������");
		}
		Cipher cipher = null;
		try {
			// ʹ��Ĭ��RSA
			cipher = Cipher.getInstance("RSA");
			// cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] output = cipher.doFinal(cipherData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("�޴˽����㷨");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("����˽Կ�Ƿ�,����");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("���ĳ��ȷǷ�");
		} catch (BadPaddingException e) {
			throw new Exception("������������");
		}
	}

}