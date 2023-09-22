package common.collection;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

/**
 * ArrayList를 상속받아 구현한 SBoxList
 * 
 * @param <E>
 */
@Component
@Qualifier("aBoxList")
public class ABoxList<E> extends ArrayList<E> {

	/**
	 * serialVersionID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <pre>
	 * ABoxList 생성자
	 * </pre>
	 * 
	 */
	public ABoxList() {
		super();
	}

	/**
	 * <pre>
	 * List 를 가져와 ABoxList로 변환하는 오버로딩 된 생성자
	 * </pre>
	 * 
	 * @param list
	 *            변환할 대상의 List 객체
	 */
	@SuppressWarnings("unchecked")
	public ABoxList(List<?> list) {
		if (list != null) {
			Iterator<?> it = list.iterator();
			while (it.hasNext()) {
				this.add((E) it.next());
			}
		}
	}

	/**
	 * <pre>
	 * ABoxList에 Object 셋팅하는 메소드
	 * </pre>
	 * 
	 * @param obj
	 *            SBoxList에 셋팅할 값
	 */
	public void set(E obj) {
		super.add(obj);
	}

	/**
	 * <pre>
	 * ABoxList 에 Object Array 셋팅하는 메소드
	 * </pre>
	 * 
	 * @param objs
	 *            : 추가할 Object Array
	 */
	public void set(E[] objs) {
		for (E obj : objs) {
			super.add(obj);
		}
	}

	/**
	 * <pre>
	 * JSON 형식의 Data 를 SBox를 포함한 ABoxList Data로 Settting 함
	 * </pre>
	 * 
	 * @param jsonData
	 *            : json 형식의 Data
	 */
	@SuppressWarnings("unchecked")
	public void setJson(String jsonData) {

		Object obj = JSONValue.parse(jsonData);
		JSONArray array = (JSONArray) obj;

		for (int i = 0; i < array.size(); i++) {
			if (array.get(i) instanceof JSONObject) {
				ABox sBox = new ABox();
				sBox.putAll((JSONObject) array.get(i));
				this.set((E) sBox);
			}

		}

	}

	/**
	 * <pre>
	 * ABoxList 를 String 으로 출력하는 메소드
	 * </pre>
	 * 
	 * @return SBoxList 출력 String
	 */
	public String toString() {

		StringBuffer sb = new StringBuffer();

		if (this != null) {
			Iterator<E> itList = this.iterator();
			while (itList.hasNext()) {
				E obj = itList.next();
				if (obj instanceof ABox) {
					sb.append(obj.toString());
				} else if (obj instanceof ABoxList) {
					sb.append("SBoxList Object");
				} else if (obj instanceof String[]) {
					String temp = "";
					for (int i = 0; i < ((String[]) obj).length; i++) {
						temp += ((String[]) obj)[i];
						temp += ",";
					}
					if (((String[]) obj).length > 0) {
						sb.append(temp.substring(0, temp.length() - 1));
					}
				} else if (obj instanceof String) {
					sb.append((String) obj);
				} else {
					sb.append(obj);
				}
				sb.append(",");
			}
		} 
		return sb.length() > 0 ? sb.toString().substring(0, sb.length() - 1) : "";
	}

	/**
	 * <pre>
	 * ABoxList 를 줄바꿈 하여 String 으로 출력하는 메소드
	 * </pre>
	 * 
	 * @return ABoxList 출력 String
	 */
	public String println() {

		StringBuffer sb = new StringBuffer();

		if (this != null) {
			Iterator<E> itList = this.iterator();
			while (itList.hasNext()) {
				E obj = itList.next();
				if (obj instanceof ABox) {
					sb.append(obj.toString());
				} else if (obj instanceof ABoxList) {
					sb.append("SBoxList Object");
				} else if (obj instanceof String[]) {
					String temp = "";
					for (int i = 0; i < ((String[]) obj).length; i++) {
						temp += ((String[]) obj)[i];
						temp += ",";
					}
					if (((String[]) obj).length > 0) {
						sb.append(temp.substring(0, temp.length() - 1));
					}
				} else if (obj instanceof String) {
					sb.append((String) obj);
				} else {
					sb.append(obj);
				}
				sb.append(",");
				sb.append("\n");
			}
		}
		return sb.length() > 0 ? sb.toString().substring(0, sb.length() - 2) : "";
	}

    public ABoxList<ABox> jsonToABoxList(JsonArray jsonArray){
        Gson gson = new Gson();
        ABoxList<ABox> aBoxList = new ABoxList<ABox>();
        aBoxList = gson.fromJson(jsonArray.toString(), new TypeToken<ABoxList<ABox>>(){}.getType());
        
        return aBoxList;
    }

    public ABoxList<ABox> jsonToABoxList(String jsonArray){
        Gson gson = new Gson();
        ABoxList<ABox> aBoxList = new ABoxList<ABox>();
        aBoxList = gson.fromJson(jsonArray, new TypeToken<ABoxList<ABox>>(){}.getType());
        
        return aBoxList;
    }
}