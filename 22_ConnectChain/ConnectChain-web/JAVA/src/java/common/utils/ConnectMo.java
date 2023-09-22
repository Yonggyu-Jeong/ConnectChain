package common.utils;

import common.collection.ABox;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;

@Component
public class ConnectMo {

	// service 클래스 내부에서 쓸 함수.

	// input 필수 : url, type, auth
	//	accessKey, secretKey, expiresIn
	public ABox connectAPI(ABox paramBox) {
		ABox resultBox = new ABox();
		try {
			URL url = new URL(paramBox.getString("url"));
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestMethod(paramBox.getString("type"));

			JSONObject message = new JSONObject();
			if(!(paramBox.getString("auth").isEmpty() || paramBox.getString("auth") == null)) {
				message.put("accessKey", paramBox.getString("accessKey"));
				message.put("secretKey", paramBox.getString("secretKey"));
				if(paramBox.containsKey("expiresIn")) {
					message.put("expiresIn", paramBox.getString("expiresIn"));
				} else {
					message.put("expiresIn", "604800");
				}
			}
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

			if (conn.getResponseCode() == 200) {
				resultBox = resultBox.jsonToABox(results.toString());
				resultBox.set("check_code", conn.getResponseCode());

			} else {
				resultBox.set("check", "fail");
				resultBox.set("check_code", conn.getResponseCode());
			}

		} catch (Exception e) {
			resultBox.set("result", "fail");
			e.printStackTrace();
		}
		return resultBox;
	}
}