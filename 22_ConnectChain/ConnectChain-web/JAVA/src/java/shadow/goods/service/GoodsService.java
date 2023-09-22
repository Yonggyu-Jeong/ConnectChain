package shadow.goods.service;

import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import common.collection.ABox;

/**
 * <pre>
 * 	라운지 서비스 인터페이스 정의
 * </pre>
 */
public interface GoodsService {
	ABox selectGoods(ABox paramBox);	//	상품 목록 조회 
	ABox findGoods(ABox paramBox);	// 상품 조회 
	ABox selectSalesRecord(ABox paramBox);		//	상품 판매 기록 조회 
	ABox registerGoods(MultipartHttpServletRequest multipartRequest) throws DataAccessException;		// 상품 판매 
	ABox requestPurchase(ABox paramBox) throws DataAccessException;	// 상품 구매 신청
	ABox chooseBuyer(ABox paramBox) throws DataAccessException;	// 상품 구매자 선정 
	ABox requestWish(ABox paramBox) throws DataAccessException;
	
}
