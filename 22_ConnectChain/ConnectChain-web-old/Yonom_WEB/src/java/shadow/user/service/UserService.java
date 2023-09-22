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

	ABox addUser(ABox paramBox);

	ABox loginUser(ABox paramBox);

	ABox modifyprofile(ABox paramBox);

	ABox selectprofile(ABox paramBox);

	ABox wishgoods(ABox paramBox);
	
}


