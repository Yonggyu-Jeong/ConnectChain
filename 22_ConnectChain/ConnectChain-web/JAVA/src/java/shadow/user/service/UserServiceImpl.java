package shadow.user.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import common.collection.ABox;
import common.collection.ABoxList;
import common.service.SuperService;
import common.utils.UploadMo;

/**
 * <pre>
 *  채팅 서비스 클래스 정의
 * </pre>
 */

@Service
@Transactional(propagation = Propagation.REQUIRED) // 서비스 클래스의 모든 메서드에 트랜잭션을 적용
public class UserServiceImpl extends SuperService implements UserService {

	@Autowired
	private UploadMo uploadMo;
	
	@Override
	public ABox addUser(MultipartHttpServletRequest multipartRequest){
		ABox resultBox = new ABox();
		try {
			ABox userBox = new ABox();
			ABoxList<ABox> imageList = new ABoxList<ABox>();

			userBox.set("userName", multipartRequest.getParameter("userName"));
			userBox.set("userNickName", multipartRequest.getParameter("userNickName"));
			userBox.set("userPassword", multipartRequest.getParameter("userPassword"));
			userBox.set("userProfile", multipartRequest.getParameter("userProfile"));

			imageList = uploadMo.upload(multipartRequest);
			for (int i = 0; i < imageList.size(); i++) {
				userBox.set("userImagePath", imageList.get(i).getString("originFileName" + (i + 1)));
			}
			
			if(commonDao.insert("mybatis.user.user_mapper.insertUserSQL", userBox) != 0) {
				resultBox.set("check", "ok");
			} else {
				resultBox.set("check", "fail");
			}
			
		} catch (Exception ex) {
			resultBox.set("result", "fail");
			ex.printStackTrace();
		}
		return resultBox;
	}
	
	@Override
	public ABox modifyProfileImage(MultipartHttpServletRequest multipartRequest){
		ABox resultBox = new ABox();
		try {
			ABox userBox = new ABox();
			ABoxList<ABox> imageList = new ABoxList<ABox>();

			userBox.set("userSeq", multipartRequest.getParameter("userSeq"));
			imageList = uploadMo.upload(multipartRequest);
			for (int i = 0; i < imageList.size(); i++) {
				userBox.set("userImagePath", imageList.get(i).getString("originFileName" + (i + 1)));
			}
			
			if (commonDao.update("mybatis.common.common_mapper.updateUserSQL", userBox) != 0) {
				resultBox.set("result", "ok");
			} else {
				resultBox.set("result", "fail");
			}
			
		} catch (Exception ex) {
			resultBox.set("result", "fail");
			ex.printStackTrace();
		}
		return resultBox;
	}

	@Override
	public ABox loginUser(ABox paramBox) {
		ABox resultBox = new ABox();
		try {
			ABox userBox = commonDao.select("mybatis.common.common_mapper.selectUserLoginSQL", paramBox);
			if (!userBox.isEmpty()) {
				resultBox.set("user", userBox);
				resultBox.set("result", "ok");
			} else {
				resultBox.set("result", "fail");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			resultBox.set("result", "fail");
		}
		return resultBox;
	}

	@Override
	public ABox modifyProfile(ABox paramBox) {
		ABox resultBox = new ABox();
		try {
			if (commonDao.update("mybatis.common.common_mapper.updateUserSQL", paramBox) != 0) {
				resultBox.set("result", "ok");
			} else {
				resultBox.set("result", "fail");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			resultBox.set("result", "fail");
		}
		return resultBox;
	}

	@Override
	public ABox selectProfile(ABox paramBox) {
		ABox resultBox = new ABox();
		try {
			ABox userBox = commonDao.select("mybatis.common.common_mapper.selectUserSQL", paramBox);
			if (!userBox.isEmpty()) {
				resultBox.set("user", userBox);
				resultBox.set("result", "ok");
			} else {
				resultBox.set("result", "fail");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			resultBox.set("check", "fail");
		}
		return resultBox;
	}

	@Override
	public ABox wishgoods(ABox paramBox) {
		ABox resultBox = new ABox();
		try {
			// 유저, 상품 정보
			ABoxList<ABox> goodsList = commonDao.selectList("mybatis.user.user_mapper.selectUserWishListSQL", paramBox);
			if(goodsList.size() > 0) {
				resultBox.set("goods-list", goodsList);
				resultBox.set("size", goodsList.size());
				resultBox.set("check", "ok");

			} else {
				resultBox.set("check", "fail");

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			resultBox.set("check", "fail");
		}
		return resultBox;
	}
}
