package common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import common.collection.ABox;
import common.collection.ABoxList;

/**
 * <pre>
 * 	CRUD Dao 정의
 * </pre>
 */

@Repository
public class CommonDaoImpl extends SuperDao implements CommonDaoIF {

	/**
	 * <pre>
	 * List 형태의 데이터 조회 구현 메소드
	 * </pre>
	 * 
	 * @param key  mybatis의 xml 문서 id값
	 * @param aBox SQL 파라미터
	 * @return
	 */
	public ABoxList<ABox> selectList(String key, ABox aBox) {
		List<Object> list = sqlSessionTemplate.selectList(key, aBox);
		return new ABoxList<ABox>(list);
	}
	
	/**
	 * <pre>
	 * 데이터 등록 구현메소드
	 * </pre>
	 * 
	 * @param key  mabatis 의 xml id값
	 * @param aBox SQL파라미터
	 * @return
	 */
	public int insert(String key, ABox aBox) {
		return sqlSessionTemplate.insert(key, aBox);
	}

	/**
	 * <pre>
	 * 데이터 등록 구현메소드
	 * </pre>
	 * 
	 * @param key      mabatis 의 xml id값
	 * @param aBoxList SQL파라미터
	 * @return
	 */
	public int insert(String key, ABoxList<ABox> aBoxList) {
		return sqlSessionTemplate.insert(key, aBoxList);
	}

	/**
	 * <pre>
	 * 데이터 단건 조회 구현메소드
	 * </pre>
	 * 
	 * @param key  mabatis 의 xml id값
	 * @param aBox SQL파라미터
	 * @return
	 */
	public ABox select(String key, ABox aBox) {
		return sqlSessionTemplate.selectOne(key, aBox);
	}

	/**
	 * <pre>
	 * 데이터 수정 구현메소드
	 * </pre>
	 * 
	 * @param key  mabatis 의 xml id값
	 * @param aBox SQL파라미터
	 * @return
	 */
	public int update(String key, ABox aBox) {
		return sqlSessionTemplate.update(key, aBox);
	}

	/**
	 * <pre>
	 * 데이터 삭제 구현메소드
	 * </pre>
	 * 
	 * @param key  mabatis 의 xml id값
	 * @param aBox SQL파라미터
	 * @return
	 */
	public int delete(String key, ABox aBox) {
		return sqlSessionTemplate.delete(key, aBox);
	}

}
