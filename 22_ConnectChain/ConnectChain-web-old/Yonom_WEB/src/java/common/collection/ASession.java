package common.collection;

import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * ABox을 상속받아 구현한 ASession 클래스
 * </pre>
 */
@Component
@Qualifier("aSession")
public class ASession extends ABox {

	private HttpSession session; // HttpSession 객체

	/**
	 * serialVersionUID Setting
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <pre>
	 * HttpSession 객체의 데이터를 ABox 데이터로 Sync 한다.
	 * </pre>
	 * 
	 * @param
	 * @return
	 */
//	@SuppressWarnings("unchecked")
	private void syncSesstionToABox() {
		Enumeration<String> names = this.session.getAttributeNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			this.set(name, this.session.getAttribute(name));
		}
		this.println();
	}

	/**
	 * <pre>
	 * ABox 객체의 데이터를 HttpSession 데이터로 Sync 한다.
	 * </pre>
	 * 
	 * @param
	 * @return
	 */
	private void syncABoxToSession() {
		Iterator<String> it = this.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			this.session.setAttribute(key, this.get(key));
		}
	}

	/**
	 * <pre>
	 * HttpSession 객체를 내부 객체로 설정한다.
	 * </pre>
	 * 
	 * @param
	 * @return
	 */
	public void setSession(HttpSession session) {
		this.session = session;
		this.syncSesstionToABox();
	}

	/**
	 * <pre>
	 * 내부 HttpSession 객체를 반환한다.
	 * </pre>
	 * 
	 * @param
	 * @return
	 */
	public HttpSession getSession() {
		this.syncABoxToSession();
		return this.session;
	}

	/**
	 * <pre>
	 * 해당 세션의 아이디를 반환하는 메서드
	 * </pre>
	 * 
	 * @param
	 * @return 해당 세션의 아이디
	 */
	public String getSessionId() {
		return this.session.getId();
	}
	
	/**
	 * <pre>
	 * ABox 의 값을 오버라이딩 하여 입력받은 key, Value 값을 ABox에 셋팅하는 메소드
	 * </pre>
	 * 
	 * @param <E>
	 * @param key
	 *            입력받은 key
	 * @param obj
	 *            입력받은 Object
	 */
	@Override
	public <E> ABox set(String key, E obj) {
		super.put(key, obj);
		this.session.setAttribute(key, obj);
		return this;
	}

	/**
	 * <pre>
	 * ABox 의 값을 오버라이딩 하여 입력받은 key, Value 값을 ABox에 셋팅하는 메소드
	 * 
	 * </pre>
	 * 
	 * @param strArray
	 *            : 입력형태 : key:value,key:value...
	 */
	@Override
	public <E> ABox set(String strArray) {
		String[] splData = strArray.trim().split("\\,");
		for (String data : splData) {
			String[] keyValue = data.trim().split("\\:");
			super.put(keyValue[0].trim(), keyValue[1].trim());
			this.session.setAttribute(keyValue[0].trim(), keyValue[1].trim());
		}
		return this;
	}

	/**
	 * <pre>
	 * ABox 의 값을 오버라이딩 하여 JSON 형식의 Data를 SBox 형태로 출력함.
	 * </pre>
	 * 
	 * @param jsonData
	 *            : json Object 형태의 String Data
	 */
	@Override
	public <E> void setJson(String jsonData) {
		System.out.println("이 기능은 지원하지 않습니다.");
	}

	/**
	 * <pre>
	 * ASession 의 특정 key 값에 해당하는 객체를 삭제한다.
	 * </pre>
	 * 
	 * @param key
	 *            : 삭제할 key 값
	 */
	@Override
	public Object remove(Object key) {
		super.remove(key);
		this.session.removeAttribute((String) key);
		return key;
	}

	/**
	 * <pre>
	 * ASession 의 모든 객체를 삭제한다.
	 * </pre>
	 */
	public synchronized void invalidate() {

		this.session.invalidate();
	}
}
