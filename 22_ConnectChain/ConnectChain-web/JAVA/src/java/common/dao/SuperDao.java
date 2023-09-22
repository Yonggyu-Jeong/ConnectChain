package common.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * 	CommonDaoImpl 부모 클래스 정의
 * </pre>
 */

@Repository
public class SuperDao {

	@Autowired
	protected SqlSession sqlSessionTemplate;
}
