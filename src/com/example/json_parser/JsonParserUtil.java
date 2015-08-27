package com.example.json_parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.util.JsonReader;
import android.util.JsonWriter;

/**������ʾandroid��json���ݵĶ�д����
 * ʹ��jsonWriter��JsonReaderʵ��json�ļ��Ķ�д����Ҫandroid sdk 11�����ϲſ���ʹ�ã�
 * */
public class JsonParserUtil {

	/**��CDEntity����д��Json��.
	 * @throws IOException */
	private static void getJsonObject(CDEntity entity,JsonWriter write) throws IOException{
		//�������ʼд��ǰ��ִ��beginObject()����
		write.beginObject();
		//�Ա�����ΪKey������ֵΪvalue�������е�����д��json�����
		write.name("title").value(entity.getTitle());
		write.name("artist").value(entity.getArtist());
		write.name("country").value(entity.getCountry());
		write.name("company").value(entity.getCompany());
		write.name("price").value(entity.getPrice());
		write.name("year").value(entity.getYear());
		//�������д�������Ҫִ��endObject()����
		write.endObject();
	}
	
	/**��ArrayList����д��OutputStream���� �÷�����Sdk11���Ͽ���
	 * @throws IOException */
	public static void WriteJson(ArrayList<CDEntity> cds,OutputStream out) throws IOException{
		//ͨ����������������Json�ַ����������
		JsonWriter write = new JsonWriter(new OutputStreamWriter(out));
			//д������ǰ��Ҫִ��beginArray()����
			write.beginArray();
			//����List�������е�ÿһ��Ԫ��д�������
			for(CDEntity entity : cds){
				getJsonObject(entity,write);
			}
			//д�����������Ҫִ��endArray()����
			write.endArray();
			//ִ��������д�������Ҫ�ر���������
			write.close();
	}
	
	/**��Json�������ΪArrayList���� �÷�����Sdk11���Ͽ���
	 * @throws IOException */
	public static ArrayList<CDEntity> ReadJson(InputStream in) throws IOException{
		ArrayList<CDEntity> cds = new ArrayList<CDEntity>();
		//����json����������
		JsonReader read = new JsonReader(new InputStreamReader(in));
		//��ȡ����ǰ��Ҫִ��beginArray����
		read.beginArray();
		while(read.hasNext()){  //ѭ����ȡ����������������뵽ArrayList�У�ֱ����������û������
			cds.add(ReadEntity(read));
		}
		read.endArray();
		return cds;
	}
	
	/**��Json����������ΪCDEntity����
	 * @throws IOException */
	private static CDEntity ReadEntity(JsonReader read) throws IOException{
		CDEntity entity = null;
		read.beginObject();
		entity = new CDEntity();
		while(read.hasNext()){
			String key = read.nextName();
			if("title".equals(key)){
				entity.setTitle(read.nextString());
			}else if("artist".equals(key)){
				entity.setArtist(read.nextString());
			}else if("country".equals(key)){
				entity.setCountry(read.nextString());
			}else if("company".equals(key)){
				entity.setCompany(read.nextString());
			}else if("price".equals(key)){
				entity.setPrice(Float.parseFloat(read.nextString()));
			}else if("year".equals(key)){
				entity.setYear(Integer.parseInt(read.nextString()));
			}else{
				read.skipValue();  //��������key��Ӧ��ֵ
			}
		}
		read.endObject();
		return entity;
	}
}
