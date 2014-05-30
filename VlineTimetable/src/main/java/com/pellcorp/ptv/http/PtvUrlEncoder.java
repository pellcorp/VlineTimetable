package com.pellcorp.ptv.http;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class PtvUrlEncoder {
	private String encoding = "UTF-8";
	private String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	
	private final String privateKey;
	private final int developerId;
	
	public PtvUrlEncoder(final String privateKey, final int developerId) {
		this.privateKey = privateKey;
		this.developerId = developerId;
	}
	
	public String encodeUri(String uri) {
		return generateCompleteURLWithSignature(uri);
	}
	
	private String generateSignature(String uri) {
		try {
			StringBuffer uriWithDeveloperID = new StringBuffer();
			uriWithDeveloperID.append(uri).append(uri.contains("?") ? "&" : "?").append("devid=" + developerId);
			
			SecretKeySpec keySpec = new SecretKeySpec(privateKey.getBytes(encoding), HMAC_SHA1_ALGORITHM);
	        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
	        mac.init(keySpec);
	        
	        byte[] rawHmac = mac.doFinal(uriWithDeveloperID.toString().getBytes(encoding));
	
	        return new String(Hex.encodeHex(rawHmac)).toUpperCase();
		} catch (UnsupportedEncodingException e) {
			throw new PtvUrlEncoderException(e);
		} catch (InvalidKeyException e) {
			throw new PtvUrlEncoderException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new PtvUrlEncoderException(e);
		}
	}
	
	private String generateCompleteURLWithSignature(String uri) {
		StringBuffer url = new StringBuffer(uri)
				.append(uri.contains("?") ? "&" : "?")
				.append("devid=" + developerId)
				.append("&signature=" + generateSignature(uri));
		return url.toString();
	}
}
