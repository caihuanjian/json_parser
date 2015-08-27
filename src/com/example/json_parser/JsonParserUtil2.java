package com.example.json_parser;

import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**ʹ��JSONObject,JSONArray��������json����Ҫ��android sdk �汾��2.3����*/
public class JsonParserUtil2 {

	/**��json��ʽ���ַ�������*/
	public static ArrayList<CDEntity> readJson(String in) throws JSONException{
		ArrayList<CDEntity> cds = new ArrayList<CDEntity>();
		//��ȡjson����������
		JSONTokener tokener = new JSONTokener(in);
		//ͨ��nextValue���Զ�ȡ�����������һ���ֶ��󣨴ӵ�ǰλ�ÿ�ʼ�Ժ�ĵ�һ����������������ı���
		JSONArray jArray = (JSONArray) tokener.nextValue();
		CDEntity entity = null;
		//ѭ������������󣬻�ȡÿһ��json������н���
		for(int i = 0;i<jArray.length();i++){
			JSONObject jo = (JSONObject) jArray.get(i);
			entity = new CDEntity();
			//��json��Ӧ��key��ֵȡ����������Ϊcdentity������
			entity.setTitle(jo.getString("title"));
			entity.setArtist(jo.getString("artist"));
			entity.setCompany(jo.getString("company"));
			entity.setCountry(jo.getString("country"));
			entity.setPrice(Float.parseFloat(jo.getString("price")));
			entity.setYear(jo.getInt("year"));
			//������ɣ���entity��ӵ�list��
			cds.add(entity);
		}
		return cds;
	}
	
	/**��ArrayList�е�����д��json������
	 * @throws JSONException */
	public static String writeJson(ArrayList<CDEntity> cds) throws JSONException{
		String str = null;
		//����jsonArray���������洢����
		JSONArray jArray = new JSONArray();
		//������Ž���������entity����
		JSONObject jo = null;
		//����ArrayList����ÿһ��entity��������jsonObject����
		for(CDEntity entity:cds){
			jo = new JSONObject();
			jo.put("title", entity.getTitle());
			jo.put("artist", entity.getArtist());
			jo.put("company", entity.getCompany());
			jo.put("country", entity.getCountry());
			jo.put("price", entity.getPrice());
			jo.put("year", entity.getYear());
			//������������jsonObject������ӵ�jsonArray������
			jArray.put(jo);
		}
		str = jArray.toString();  //����json��ʽ�ַ���
		return str;
	}
	
}
