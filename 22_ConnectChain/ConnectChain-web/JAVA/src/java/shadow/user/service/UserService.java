package shadow.user.service;

import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import common.collection.ABox;
import common.collection.ABoxList;

/**
 * <pre>
 * 	라운지 서비스 인터페이스 정의
 * </pre>
 */
public interface UserService {

	public ABox addUser(MultipartHttpServletRequest multipartRequest);

	public ABox loginUser(ABox paramBox);

	public ABox modifyProfile(ABox paramBox);

	public ABox modifyProfileImage(MultipartHttpServletRequest multipartRequest);
	
	public ABox selectProfile(ABox paramBox);

	public ABox wishgoods(ABox paramBox);
	
}


