package common.collection;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * <pre>
 * HashMap을 상속받아 구현한 ABox 클래스
 * </pre>
 */
@Component
@Qualifier("aToken")
public class AToken {
	private String authTokenId;
	private String accountId;
	private String iamUserId;
	private String token;
	private String expiryAt;
	
	public AToken() {
	}

	public AToken(String authTokenId, String accountId, String iamUserId, String token, String expiryAt) {
		super();
		this.authTokenId = authTokenId;
		this.accountId = accountId;
		this.iamUserId = iamUserId;
		this.token = token;
		this.expiryAt = expiryAt;
	}

	public String getAuthTokenId() {
		return authTokenId;
	}

	public void setAuthTokenId(String authTokenId) {
		this.authTokenId = authTokenId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getIamUserId() {
		return iamUserId;
	}

	public void setIamUserId(String iamUserId) {
		this.iamUserId = iamUserId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getExpiryAt() {
		return expiryAt;
	}

	public void setExpiryAt(String expiryAt) {
		this.expiryAt = expiryAt;
	}

	@Override
	public String toString() {
		return "AToken [authTokenId=" + authTokenId + ", accountId=" + accountId + ", iamUserId=" + iamUserId
				+ ", token=" + token + ", expiryAt=" + expiryAt + "]";
	}
	
	
	
	

}