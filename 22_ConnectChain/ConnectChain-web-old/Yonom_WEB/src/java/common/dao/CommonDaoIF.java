package common.dao;

import common.collection.ABox;
import common.collection.ABoxList;

/**
 * <pre>
 * 	CRUD 인터페이스 정의
 * </pre>
 */
public interface CommonDaoIF {

	/**
	 * <pre>
	 * List 형태의 데이터 조회 추상 메소드
	 * </pre>
	 * 
	 * @param key
	 *            mybatis의 xml 문서 id값
	 * @param aBox
	 *            SQL 파라미터
	 * @return
	 */
	public abstract ABoxList<ABox> selectList(String key, ABox aBox);

	/**
	 * <pre>
	 * 데이터 등록 추상메소드
	 * </pre>
	 * 
	 * @param key
	 *            mabatis 의 xml id값
	 * @param aBox
	 *            SQL파라미터
	 * @return
	 */
	public abstract int insert(String key, ABox aBox);

	/**
	 * <pre>
	 * 데이터 단건 조회 추상메소드
	 * </pre>
	 * 
	 * @param key
	 *            mabatis 의 xml id값
	 * @param aBox
	 *            SQL파라미터
	 * @return
	 */
	public abstract ABox select(String key, ABox aBox);

	/**
	 * <pre>
	 * 데이터 수정 추상메소드
	 * </pre>
	 * 
	 * @param key
	 *            mabatis 의 xml id값
	 * @param aBox
	 *            SQL파라미터
	 * @return
	 */
	public abstract int update(String key, ABox aBox);

	/**
	 * <pre>
	 * 데이터 삭제 추상메소드
	 * </pre>
	 * 
	 * @param key
	 *            mabatis 의 xml id값
	 * @param aBox
	 *            SQL파라미터
	 * @return
	 */
	public abstract int delete(String key, ABox aBox);
	
}