package common.collection;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * <pre>
 * HashMap을 상속받아 구현한 ABox 클래스
 * </pre>
 */
@Component
@Qualifier("aBox")
public class ABox extends HashMap<String, Object> {

	/**
	 * SBox seraiID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <pre>
	 * ABox 생성자
	 * </pre>
	 */
	public ABox() {

	}

	/**
	 * <pre>
	 * Map 자료형을 ABox에 Setting 하는 메소드
	 * </pre>
	 * 
	 * @param <E>
	 * @param map
	 */
	public <E> ABox(Map<String, Object> map) {
		if (map != null) {
			Iterator<String> mapIt = map.keySet().iterator();

			while (mapIt.hasNext()) {
				String key = (String) mapIt.next();

				this.set(key, map.get(key));
			}
		}
	}

	/**
	 * <pre>
	 * 입력받은 key, Value 값을 ABox에 셋팅하는 메소드
	 * </pre>
	 * 
	 * @param <E>
	 * @param key
	 *            입력받은 key
	 * @param obj
	 *            입력받은 Object
	 */
	public <E> ABox set(String key, E obj) {
		super.put(key, obj);
		return this;
	}

	/**
	 * <pre>
	 * 입력받은 key, Value 값을 ABox에 셋팅하는 메소드
	 * 
	 * </pre>
	 * 
	 * @param strArray
	 *            : 입력형태 : key:value,key:value...
	 */
	public <E> ABox set(String strArray) {
		String[] splData = strArray.trim().split("\\,");
		for (String data : splData) {
			String[] keyValue = data.trim().split("\\:");
			super.put(keyValue[0].trim(), keyValue[1].trim());
		}
		return this;
	}

	/**
	 * <pre>
	 * JSON 형식의 Data를 SBox 형태로 출력함.
	 * </pre>
	 * 
	 * @param jsonData
	 *            : json Object 형태의 String Data
	 */
	@SuppressWarnings("unchecked")
	public <E> void setJson(String jsonData) {

		try {
			JSONParser parser = new JSONParser();

			this.putAll(((JSONObject) parser.parse(jsonData)));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <pre>
	 * 입력받은 key 통해 Object 반환한다
	 * </pre>
	 * 
	 * @param <E>
	 * @param key
	 *            입력받은 Key 값
	 * @return 반환할 Object
	 */
	@SuppressWarnings("unchecked")
	public <E> E get(String key) {
		return (E) super.get(key);
	}

	/**
	 * <pre>
	 * 해당 Key 값의 Null 또는 공백시 true를 반환한다.
	 * </pre>
	 * 
	 * @param key
	 *            Null체크할 키값
	 * @return true(NULL 또는 공백) / false(데이터존재함)
	 */
	public boolean isEmpty(String key) {
		boolean bTF = false;
		if (this.get(key) == null || this.getString(key).equals("")) {
			bTF = true;
		}
		return bTF;
	}

	/**
	 * <pre>
	 * 입력 받은 Key 통해 String 형 변환 메소드
	 * </pre>
	 * 
	 * @param key
	 *            입력값
	 * @return 메소드 내에 정의된 여러 자료형을 String으로 변환한 값
	 */
	public String getString(String key) {
		Object obj = this.get(key);
		String result = "";
		if (obj != null) {
			if (obj instanceof String) {
				result = (String) obj;
			} else if (obj instanceof Integer) {
				result = obj.toString();
			} else if (obj instanceof Long) {
				result = obj.toString();
			} else if (obj instanceof Float) {
				result = obj.toString();
			} else if (obj instanceof Double) {
				result = obj.toString();
			} else if (obj instanceof Boolean) {
				result = obj.toString();
			} else if (obj instanceof Short) {
				result = obj.toString();
			} else if (obj instanceof Date) {
				result = obj.toString();
			} else if (obj instanceof BigInteger) {
				result = ((BigInteger) obj).toString();
			} else if (obj instanceof BigDecimal) {
				result = ((BigDecimal) obj).toString();
			}
		}
		return result;
	}

	/**
	 * <pre>
	 * 입력받은 Key 통해 integer 형 변환 메소드
	 * </pre>
	 * 
	 * @param key
	 *            입력값
	 * @return integer 변환값
	 */
	public int getInt(String key) {
		Object obj = this.get(key);
		int result = 0;
		if (obj != null) {
			if (obj instanceof String) {
				result = Integer.parseInt((String) obj);
			} else if (obj instanceof Integer) {
				result = (Integer) obj;
			} else if (obj instanceof Long) {
				result = ((Long) obj).intValue();
			} else if (obj instanceof Double) {
				result = ((Double) obj).intValue();
			} else if (obj instanceof Float) {
				result = ((Float) obj).intValue();
			} else if (obj instanceof Short) {
				result = ((Short) obj).intValue();
			} else if (obj instanceof BigInteger) {
				result = ((BigInteger) obj).intValue();
			} else if (obj instanceof BigDecimal) {
				result = ((BigDecimal) obj).intValue();
			}
		}
		return result;
	}

	/**
	 * <pre>
	 * 입력받은 Key 통해 Long 형 변환 메소드
	 * </pre>
	 * 
	 * @param key
	 *            입력값
	 * @return long 변환값
	 */
	public long getLong(String key) {
		Object obj = this.get(key);
		long result = 0L;
		if (obj != null) {
			if (obj instanceof String) {
				result = Long.parseLong((String) obj);
			} else if (obj instanceof Long) {
				result = (Long) obj;
			} else if (obj instanceof Integer) {
				result = ((Integer) obj).longValue();
			} else if (obj instanceof BigInteger) {
				result = ((BigInteger) obj).longValue();
			} else if (obj instanceof BigDecimal) {
				result = ((BigDecimal) obj).longValue();
			}
		}
		return result;
	}

	/**
	 * <pre>
	 * 입력받은 Key 통해 Float 형 변환 메소드
	 * </pre>
	 * 
	 * @param key
	 *            입력값
	 * @return Float 변환값
	 */
	public float getFloat(String key) {
		Object obj = this.get(key);
		float result = 0f;
		if (obj != null) {
			if (obj instanceof String) {
				result = Float.parseFloat((String) obj);
			} else if (obj instanceof Float) {
				result = (Float) obj;
			} else if (obj instanceof BigDecimal) {
				result = ((BigDecimal) obj).floatValue();
			} else {
				result = (Float) obj;
			}
		}
		return result;
	}

	/**
	 * <pre>
	 * 입력받은 Key 통해 Double 형 변환 메소드
	 * </pre>
	 * 
	 * @param key
	 *            입력값
	 * @return Double 변환값
	 */
	public double getDouble(String key) {
		Object obj = this.get(key);
		double result = 0D;
		if (obj != null) {
			if (obj instanceof String) {
				result = Double.parseDouble((String) obj);
			} else if (obj instanceof Double) {
				result = (Double) obj;
			} else if (obj instanceof BigDecimal) {
				result = ((BigDecimal) obj).doubleValue();
			} else {
				result = (Double) obj;
			}
		}
		return result;
	}

	/**
	 * <pre>
	 * ABox 내부의 데이터를 key=value (\n) 값 형식으로 변환하여 출력한다.
	 * </pre>
	 * 
	 * @return key=value (\n) 형식의 String Data
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (this != null) {
			Iterator<String> itMap = this.keySet().iterator();
			while (itMap.hasNext()) {
				String key = (String) itMap.next();
				Object obj = this.get(key);
				if (obj != null) {
					sb.append(key);
					sb.append("[");
					if (obj instanceof ABoxList) {
						sb.append(obj.toString());
					} else if (obj instanceof String[]) {
						String temp = "";
						for (int i = 0; i < ((String[]) obj).length; i++) {
							temp += ((String[]) obj)[i];
							temp += ",";
						}
						if (((String[]) obj).length > 0) {
							sb.append(temp.substring(0, temp.length() - 1));
						}
					} else {
						sb.append(obj);
					}
					sb.append("],");
				}
			}
		}

		return sb.length() > 0 ? sb.toString().substring(0, sb.length() - 1) : "";
	}

	/**
	 * <pre>
	 * ABox 내부의 데이터를 key=value (\n) 값 형식으로 줄바꿈을 하며 출력한다.
	 * </pre>
	 * 
	 * @return key=value (\n) 형식의 String Data
	 */
	public String println() {
		StringBuffer sb = new StringBuffer();
		if (this != null) {
			Iterator<String> itMap = this.keySet().iterator();
			while (itMap.hasNext()) {
				String key = (String) itMap.next();
				Object obj = this.get(key);
				if (obj != null) {
					sb.append(key);
					sb.append("[");
					if (obj instanceof ABoxList) {
						sb.append(obj.toString());
					} else if (obj instanceof String[]) {
						String temp = "";
						for (int i = 0; i < ((String[]) obj).length; i++) {
							temp += ((String[]) obj)[i];
							temp += ",";
						}
						if (((String[]) obj).length > 0) {
							sb.append(temp.substring(0, temp.length() - 1));
						}
					} else {
						sb.append(obj);
					}
					sb.append("],");
					sb.append("\n");
				}
			}
		}

		return sb.length() > 0 ? sb.toString().substring(0, sb.length() - 1) : "";
	}

	/**
	 * <pre>
	 * ABox 내부의 데이터를 key=value (\n) 값 형식으로 변환하여 출력한다.
	 * 출력시에는 keySet의 내용을 파싱 하여 부분적으로 출력한다.
	 * </pre>
	 * 
	 * @param keySet
	 *            key1,key2,key3...
	 * @return key=value (\n) 형식의 String Data
	 */
	public String toString(String keySet) {
		StringBuffer sb = new StringBuffer();
		if (this != null) {
			Iterator<String> itMap = this.keySet().iterator();
			while (itMap.hasNext()) {
				String key = (String) itMap.next();

				String[] keySetArray = keySet.split("\\,");
				for (int k = 0; k < keySetArray.length; k++) {

					if (key.equals(keySetArray[k])) {
						key = keySetArray[k];
						Object obj = this.get(key);
						if (obj != null) {
							sb.append(key);
							sb.append("[");
							if (obj instanceof ABoxList) {
								sb.append(obj.toString());
							} else if (obj instanceof String[]) {
								String temp = "";
								for (int i = 0; i < ((String[]) obj).length; i++) {
									temp += ((String[]) obj)[i];
									temp += ",";
								}
								if (((String[]) obj).length > 0) {
									sb.append(temp.substring(0, temp.length() - 1));
								}
							} else {
								sb.append(obj);
							}
							sb.append("],");
						}

						break;
					}
				}
			}
		}

		return sb.length() > 0 ? sb.toString().substring(0, sb.length() - 1) : "";
	}

    public ABox jsonToABox(JsonObject jsonObject){
        Gson gson = new Gson();
        ABox aBox = new ABox();
        aBox = gson.fromJson(jsonObject, aBox.getClass());
        
        return aBox;
    }

    public ABox jsonToABox(String jsonObject){
        Gson gson = new Gson();
        ABox aBox = new ABox();
        aBox = gson.fromJson(jsonObject, aBox.getClass());
        
        return aBox;
    }
    
    public JsonObject aBoxToJsonObject(){
        Gson gson = new Gson();
        JsonObject json = gson.toJsonTree(this).getAsJsonObject();
        
        return json;
    }
}