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

/**
 * <pre>
 *  채팅 서비스 클래스 정의
 * </pre>
 */

@Service
@Transactional(propagation = Propagation.REQUIRED) // 서비스 클래스의 모든 메서드에 트랜잭션을 적용
public class UserServiceImpl extends SuperService implements UserService {

	@Override
	public ABox addUser(ABox paramBox) {
		ABox resultBox = new ABox();
		try {
			ABox abox = paramBox;

			if (paramBox.getString("userName").length() > 5 && paramBox.getString("userPassword").length() > 5
					&& paramBox.getString("userNickName").length() > 5) {

				if (commonDao.insert("mybatis.common.common_mapper.insertUserSQL", paramBox) != 0) {

					resultBox.set("result", "ok");
				} else {
					resultBox.set("result", "fail");

				}
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

			// 검증이 성공했는가를 표현하고 싶은데,, 어떻게 하면 좋을까요,,,,
			/*
			if (paramBox.getString("userName") && paramBox.getString("userPassword")) {

				resultBox.set("result", "ok");
			} else {
				resultBox.set("result", "fail");
			}
			*/
		} catch (Exception ex) {
			ex.printStackTrace();
			resultBox.set("result", "fail");
		}
		return resultBox;
	}

	@Override
	public ABox modifyprofile(ABox paramBox) {
		ABox resultBox = new ABox();
		try {
			// 프로필 수정 정보 입력

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
	public ABox selectprofile(ABox paramBox) {
		ABox resultBox = new ABox();
		try {
			// 프로필 정보
			// 프로필 정보 조회가 잘 되는가
			commonDao.select("mybatis.common.common_mapper.selectUserSQL", paramBox);

			resultBox.set("check", "ok");

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
			ABoxList<ABox> userList = commonDao.selectList("mybatis.user.user_mapper.selectuserListSQL", paramBox);
			ABoxList<ABox> goodsList = commonDao.selectList("mybatis.goods.goods_mapper.selectGoodsListSQL", paramBox);
			// tb_user_wish 조회
			// 데이터가 존재하는가?
			// if()
			// tb_user_wish 생성
			// user_wish 상태 코드 및 성공 메시지 전송
			// tb_user_wish 삭제

			// user_wish 상태 코드 및 성공 메시지 전송
			resultBox.set("check", "ok");

		} catch (Exception ex) {
			ex.printStackTrace();
			resultBox.set("check", "fail");
		}
		return resultBox;
	}
}
