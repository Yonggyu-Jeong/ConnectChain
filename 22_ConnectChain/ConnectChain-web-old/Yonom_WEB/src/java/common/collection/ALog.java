package common.collection;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("aLog")
public class ALog {

	/**
	 * <pre>
	 * Logger 선언
	 * </pre>
	 */
	private Logger log = null;

	public String randomCharacter = "";

	/**
	 * <pre>
	 * RLog Overloading Constructorz
	 * </pre>
	 */
	public ALog() {
		log = LogManager.getLogger(ALog.class.getName());
	}

	/**
	 * <pre>
	 * Debug 모드 Message 출력 메소드
	 * </pre>
	 * 
	 * @param msg
	 *            출력할 메시지
	 */
	public void d(String msg) {
		log.debug(randomCharacter + msg);
	}

	/**
	 * <pre>
	 * Info 모드 Message 출력 메소드
	 * </pre>
	 * 
	 * @param msg
	 *            출력할 메시지
	 */
	public void i(String msg) {
		log.info(randomCharacter + msg);
	}

	/**
	 * <pre>
	 * Fatal 모드 Message 출력 메소드
	 * </pre>
	 * 
	 * @param msg
	 *            출력할 메시지
	 */
	public void f(String msg) {
		log.fatal(randomCharacter + msg);
	}

	/**
	 * <pre>
	 * Warning 모드 Message 출력 메소드
	 * </pre>
	 * 
	 * @param msg
	 *            출력할 메시지
	 */
	public void w(String msg) {
		log.warn(randomCharacter + msg);
	}

	/**
	 * <pre>
	 * Error 모드 Message 출력 메소드
	 * </pre>
	 * 
	 * @param msg
	 *            출력할 메소드
	 */
	public void e(String msg) {
		log.error(randomCharacter + msg);
	}
}
