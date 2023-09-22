package shadow.goods.service;

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
import common.utils.ConnectMo;
import common.utils.UploadMo;

/**
 * <pre>
 *  채팅 서비스 클래스 정의
 * </pre>
 */

@Service
@Transactional(propagation = Propagation.REQUIRED) // 서비스 클래스의 모든 메서드에 트랜잭션을 적용
public class GoodsServiceImpl extends SuperService implements GoodsService {

	@Autowired
	private UploadMo uploadMo;
	
	@Autowired
	private ConnectMo connectMo;
	
	@Override
	public ABox selectGoods(ABox paramBox) {
		ABox resultBox = new ABox();
		try {
			aLog.i(paramBox.toString());
			ABoxList<ABox> goodsList = new ABoxList<ABox>();
			paramBox.set("goodsState", "GS01");
			if(paramBox.getInt("num") != 0) {
				paramBox.set("num", paramBox.getInt("num"));
				paramBox.set("num2", paramBox.getInt("num")+10);
			}
			if(!(paramBox.getString("search") == null)) {
				paramBox.set("search", "%"+paramBox.getString("search")+"%");
			}
			goodsList = commonDao.selectList("mybatis.goods.goods_mapper.selectGoodsListSQL", paramBox);
			if(goodsList.size() > 0) {
				for(int i=0; i<goodsList.size(); i++) {
					ABox goodBox = goodsList.get(i);
					if(!(goodBox.getString("wish") == null || goodBox.getString("wish").isEmpty())) {
						if(goodBox.getString("wish").equals("WS01")) {
							goodsList.get(i).set("wish", "on");	
						} else {
							goodsList.get(i).set("wish", "off");
						}
					} else {
						goodsList.get(i).set("wish", "off");
					}
				}
				resultBox.set("goods-list", goodsList);
				resultBox.set("count", goodsList.size());
				resultBox.set("check", "ok");
				
			} else {
				resultBox.set("count", 0);
				resultBox.set("check", "empty");
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			resultBox.set("check", "fail");
		}
		return resultBox;
	}
	
	@Override
	public ABox findGoods(ABox paramBox) {
		ABox resultBox = new ABox();
		try {
			ABox goodsBox = new ABox();
			ABox wishBox = new ABox(); 
			aLog.i("============"+paramBox.toString());
			
			goodsBox = commonDao.select("mybatis.goods.goods_mapper.selectGoodsSQL", paramBox);
			aLog.i("==============================="+goodsBox.toString());
			if(goodsBox.isEmpty()) {
				resultBox.set("check", "empty");	
			} else {
				if(!goodsBox.getString("GOODS_STATE").equals("GS01")) {
					resultBox.set("check", "forbidden");	
				} else {	
					wishBox = commonDao.select("mybatis.goods.goods_mapper.selectWishStateSQL", paramBox);
					if(!(wishBox == null || wishBox.isEmpty())) {
						if(wishBox.getInt("count") > 0) {
							goodsBox.set("wish", "on");	
						} else {
							goodsBox.set("wish", "off");
						}
					} else {
						goodsBox.set("wish", "off");
					}
					resultBox.set("goods", goodsBox);
					resultBox.set("check", "ok"); 	
				}			
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			resultBox.set("check", "fail");
		}
		return resultBox;
	}

	@Override
	public ABox selectSalesRecord(ABox paramBox) {
		ABox resultBox = new ABox();
		try {
			ABoxList<ABox> goodsList = new ABoxList<ABox>();
			if(paramBox.getInt("num") != 0) {
				paramBox.set("num", paramBox.getInt("num"));
				paramBox.set("num2", paramBox.getInt("num")+10);
			}
			goodsList = commonDao.selectList("mybatis.goods.goods_mapper.selectSaleRecordListSQL", paramBox);
			if(goodsList.size() > 0) {
				resultBox.set("traffic-list", goodsList);
				resultBox.set("count", goodsList.size());
				resultBox.set("check", "ok");
				
			} else {
				resultBox.set("count", 0);
				resultBox.set("check", "empty");
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			resultBox.set("check", "fail");
		}
		return resultBox;
	}

	@Override
	public ABox registerGoods(MultipartHttpServletRequest multipartRequest) throws DataAccessException {
		ABox resultBox = new ABox();
		try {
			ABox goodsBox = new ABox();
			ABox tokenBox = new ABox();
			ABox trafficBox = new ABox();

			ABoxList<ABox> imageList = new ABoxList<ABox>();
			// [2] multipartRequest로 가져온 패러미터를 aBox으로 초기화

			goodsBox.set("userSeq", multipartRequest.getParameter("userSeq"));
			goodsBox.set("goodsCategory", multipartRequest.getParameter("goodsCategory"));
			goodsBox.set("goodsPrice", multipartRequest.getParameter("goodsPrice"));
			goodsBox.set("goodsTitle", multipartRequest.getParameter("goodsTitle"));
			goodsBox.set("goodsContents", multipartRequest.getParameter("goodsContents"));
			goodsBox.set("goodsState", multipartRequest.getParameter("goodsState"));
			//goodsBox.set("goodsImagePathMain", multipartRequest.getParameter("goodsImagePathMain"));

			imageList = uploadMo.upload(multipartRequest);
			//imageList.size() 사용시 main 포함 4개의 리스트를 반환함 but insert Query는 최대 3개이므로 3로 고정 
			for (int i = 0; i < 3; i++) {
				goodsBox.set("goodsImagePath" + (i + 1), imageList.get(i).getString("originFileName" + (i + 1)));
				trafficBox.set("trafficImagePath" + (i + 1), imageList.get(i).getString("originFileName" + (i + 1)));
			}
			
			
			
			//TODO 이미지 수정 모듈, 이미지 수정 후 업로드 
			tokenBox.set("file", multipartRequest.getParameter("goodsImagePathMain"));
			
			//connectMo.connectAPI();
			// trafficBox = connectMo.
			
			
			
			trafficBox.set("userSeq", multipartRequest.getParameter("userSeq"));
			trafficBox.set("goodsSeq", multipartRequest.getParameter("goodsSeq"));
			trafficBox.set("trafficPrice", multipartRequest.getParameter("goodsPrice"));
			for (int i = 0; i < imageList.size(); i++) {
				goodsBox.set("trafficImagePath" + (i + 1), imageList.get(i).getString("originFileName" + (i + 1)));
			}
			
			trafficBox.set("goodsImagePathMain", multipartRequest.getParameter("goodsImagePathMain"));
			if(!(multipartRequest.getParameter("trafficImagePath1").isEmpty())) {
				goodsBox.set("goodsImagePath1", multipartRequest.getParameter("trafficImagePath1"));
			}
			if(!(multipartRequest.getParameter("goodsImagePath2").isEmpty())) {
				goodsBox.set("goodsImagePath1", multipartRequest.getParameter("trafficImagePath2"));
			}
			if(!(multipartRequest.getParameter("goodsImagePath3").isEmpty())) {
				goodsBox.set("goodsImagePath1", multipartRequest.getParameter("goodsImagePath3"));
			}
			/*
			 *  "id": "1234567890123456789",
    			"originalFilename": "abc.jpg",
    			"mimetype": "image/jpeg",
    			"size": "2048000",
    			"mediaUrl": "https://nft-cdn.luniverse.io/public/sample.jpg",
    			"createdAt": "2021-05-18T07:32:25.304Z"
			 */
			
			if(!(multipartRequest.getParameter("goodsImagePath1").isEmpty())) {
				goodsBox.set("goodsImagePath1", multipartRequest.getParameter("goodsImagePath1"));
			}
			if(!(multipartRequest.getParameter("goodsImagePath2").isEmpty())) {
				goodsBox.set("goodsImagePath1", multipartRequest.getParameter("goodsImagePath2"));
			}
			if(!(multipartRequest.getParameter("goodsImagePath3").isEmpty())) {
				goodsBox.set("goodsImagePath1", multipartRequest.getParameter("goodsImagePath3"));
			}
			
			if(commonDao.insert("mybatis.goods.goods_mapper.insertGoodsSQL", goodsBox) != 0) {
				
			}
			if(commonDao.insert("mybatis.goods.goods_mapper.insertTokenSQL", tokenBox) != 0) {
				
			}
			if(commonDao.insert("mybatis.goods.goods_mapper.insertTrafficSQL", trafficBox) != 0) {
				
			}

			
		} catch (Exception ex) {
			ex.printStackTrace();
			resultBox.set("check", "fail");
			
		}
		return resultBox;
	}

	@Override
	public ABox requestWish(ABox paramBox) throws DataAccessException {
		ABox resultBox = new ABox();
		try {
			ABox wishBox = new ABox();
			wishBox = commonDao.select("mybatis.goods.goods_mapper.selectWishUserSQL", paramBox);
			if(!wishBox.containsKey("USER_WISH_STATE")) {
				paramBox.set("userWishState", "WS01");
				if(commonDao.insert("mybatis.goods.goods_mapper.insertRequestSQL", paramBox) != 0) {
					resultBox.set("check", "ok");
				} else {
					resultBox.set("check", "fail");
				}
			} else if (wishBox.get("USER_WISH_STATE").equals("WS02")) {
				paramBox.set("userWishState", "WS01");
				if(commonDao.update("mybatis.goods.goods_mapper.updateUserWishSQL", paramBox) != 0) {
					resultBox.set("check", "ok");
				} else {
					resultBox.set("check", "fail");
				}
			} else {
				paramBox.set("userWishState", "WS02"); // 취소 
				if(commonDao.update("mybatis.goods.goods_mapper.updateUserWishSQL", paramBox) != 0) {
					resultBox.set("check", "ok");
				} else {
					resultBox.set("check", "fail");
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			resultBox.set("check", "fail");
		}
		return resultBox;
	}
	
	@Override
	public ABox requestPurchase(ABox paramBox) throws DataAccessException {
		ABox resultBox = new ABox();
		try {
			if(commonDao.insert("mybatis.goods.goods_mapper.insertRequestSQL", paramBox) != 0) {
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


	@Override
	public ABox chooseBuyer(ABox paramBox) throws DataAccessException {
		ABox resultBox = new ABox();
		try {
			ABox requestBox = commonDao.select("mybatis.goods.goods_mapper.selectRequestUserSQL", paramBox);
			if(requestBox.getString("REQUEST_STATE").equals("RS01")) {
				paramBox.set("requestState", "RS02"); // 예약 	
				if(commonDao.update("mybatis.goods.goods_mapper.updateRequestSQL", paramBox) != 0) {
					resultBox.set("check", "ok");
				} else {
					resultBox.set("check", "fail");
				}
				
			} else if(requestBox.getString("REQUEST_STATE").equals("RS02")) {
				paramBox.set("requestState", "RS03");	// 판매
				if(commonDao.update("mybatis.goods.goods_mapper.updateRequestSQL", paramBox) != 0) {
					resultBox.set("check", "ok");
					if(paramBox.get("requestState").equals("RS03")) {	// 판매 완료로 변경 
						commonDao.update("mybatis.goods.goods_mapper.updateRequestAllSQL", paramBox);
						
					}
				} else {
					resultBox.set("check", "fail");
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			resultBox.set("check", "fail");
		}
		return resultBox;
	}

}
