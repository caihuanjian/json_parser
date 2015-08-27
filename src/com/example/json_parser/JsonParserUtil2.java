package com.example.json_parser;

import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**使用JSONObject,JSONArray等来构建json对象。要求android sdk 版本在2.3以上*/
public class JsonParserUtil2 {

	/**将json格式的字符串解析*/
	public static ArrayList<CDEntity> readJson(String in) throws JSONException{
		ArrayList<CDEntity> cds = new ArrayList<CDEntity>();
		//获取json解析器对象
		JSONTokener tokener = new JSONTokener(in);
		//通过nextValue可以读取整个对象或者一部分对象（从当前位置开始以后的第一个完整对象或完整文本）
		JSONArray jArray = (JSONArray) tokener.nextValue();
		CDEntity entity = null;
		//循环遍历数组对象，获取每一个json对象进行解析
		for(int i = 0;i<jArray.length();i++){
			JSONObject jo = (JSONObject) jArray.get(i);
			entity = new CDEntity();
			//将json对应的key的值取出来，设置为cdentity的属性
			entity.setTitle(jo.getString("title"));
			entity.setArtist(jo.getString("artist"));
			entity.setCompany(jo.getString("company"));
			entity.setCountry(jo.getString("country"));
			entity.setPrice(Float.parseFloat(jo.getString("price")));
			entity.setYear(jo.getInt("year"));
			//解析完成，将entity添加到list中
			cds.add(entity);
		}
		return cds;
	}
	
	/**将ArrayList中的内容写入json对象中
	 * @throws JSONException */
	public static String writeJson(ArrayList<CDEntity> cds) throws JSONException{
		String str = null;
		//构建jsonArray对象，用来存储数组
		JSONArray jArray = new JSONArray();
		//用来存放解析出来的entity对象
		JSONObject jo = null;
		//遍历ArrayList，将每一个entity对象生成jsonObject对象
		for(CDEntity entity:cds){
			jo = new JSONObject();
			jo.put("title", entity.getTitle());
			jo.put("artist", entity.getArtist());
			jo.put("company", entity.getCompany());
			jo.put("country", entity.getCountry());
			jo.put("price", entity.getPrice());
			jo.put("year", entity.getYear());
			//将解析出来的jsonObject对象添加到jsonArray数组中
			jArray.put(jo);
		}
		str = jArray.toString();  //生成json格式字符串
		return str;
	}
	
}
