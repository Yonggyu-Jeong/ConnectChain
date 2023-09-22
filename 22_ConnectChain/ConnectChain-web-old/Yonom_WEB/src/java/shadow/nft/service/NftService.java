package shadow.nft.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import common.collection.ABox;

/**
 * <pre>
 * 	라운지 서비스 인터페이스 정의
 * </pre>
 */
public interface NftService {

	public ABox getAuthToken(ABox paramBox);

	public ABox uploadMediaFile(MultipartHttpServletRequest multipartRequest); 

}
