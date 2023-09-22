package shadow.nft.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import common.utils.ConnectMo;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import common.collection.ABox;
import common.collection.ABoxList;
import common.collection.AToken;
import common.service.SuperService;

/**
 * <pre>
 *  채팅 서비스 클래스 정의
 * </pre>
 */

@Service
@Transactional(propagation = Propagation.REQUIRED) // 서비스 클래스의 모든 메서드에 트랜잭션을 적용
public class NftServiceImpl extends SuperService implements NftService {
	
	private AToken aToken = new AToken();

	ConnectMo connectMo = new ConnectMo();

	@Value("#{nft_account['ACCESSKEY'].trim()}")
	private String accessKey;

	@Value("#{nft_account['SECRETKEY'].trim()}")
	private String secretKey;

	@Value("#{nft_account['WALLET'].trim()}")
	private String wallet;

	@Override
	@SuppressWarnings("unchecked")
	public ABox getAuthToken(ABox paramBox) {
		ABox resultBox = new ABox();		
		try {
			ABox connectBox = new ABox();
			connectBox.set("url", "https://api.luniverse.io/svc/v2/auth-tokens/");
			connectBox.set("type", "POST");
			connectBox.set("auth", true);
			connectBox.set("accessKey", accessKey);
			connectBox.set("secretKey", secretKey);
			if(paramBox.containsKey("expiresIn")) {
				connectBox.set("expiresIn", paramBox.getString("expiresIn"));
			} else {
				connectBox.set("expiresIn", "604800");
			}

			resultBox = connectMo.connectAPI(connectBox);
			aLog.i(resultBox.toString());

		} catch (Exception e) {
			resultBox.set("result", "fail");
			e.printStackTrace();
		}
		return resultBox;
	}

	@Override
	public ABox uploadMediaFile(MultipartHttpServletRequest multipartRequest) {
		ABox resultBox = new ABox();		
		try {
			
			URL url = new URL("https://api.luniverse.io/svc/v2/nft/media/");
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

			conn.setUseCaches(false);
	        conn.setDoInput(true);
	        conn.setDoOutput(true); 
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-type", "application/json");

			JSONObject message = new JSONObject();
						
			Iterator<String> fileNames = multipartRequest.getFileNames();
			while (fileNames.hasNext()) {
				String fileName = fileNames.next();
				MultipartFile mFile = multipartRequest.getFile(fileName);
				aLog.i(mFile.getName());
				message.put("file", mFile.getName());
			}
			//			message.put("file", multipartRequest.getFile(accessKey));

			OutputStream os = conn.getOutputStream();
			os.write(message.toJSONString().getBytes("UTF-8"));
			os.flush();
			os.close();

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        StringBuilder results = new StringBuilder();
	        String line;
	        while ((line = br.readLine()) != null) {
	            results.append(line);
	        }
			
			if (conn.getResponseCode() == 201) {		
				ABox parsingBox = new ABox();
				parsingBox = parsingBox.jsonToABox(results.toString());
				resultBox = parsingBox;

			} else {
				resultBox.set("check", "fail");
				resultBox.set("check_code", conn.getResponseCode());
			}
			
		} catch (Exception e) {
			resultBox.set("check", "fail");
			e.printStackTrace();
		}
		return resultBox;
	}

}
