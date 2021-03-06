package com.example.json_parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.util.JsonReader;
import android.util.JsonWriter;

/**用来演示android对json数据的读写操作
 * 使用jsonWriter和JsonReader实现json文件的读写（需要android sdk 11及以上才可以使用）
 * */
public class JsonParserUtil {

	/**将CDEntity对象写入Json流.
	 * @throws IOException */
	private static void getJsonObject(CDEntity entity,JsonWriter write) throws IOException{
		//数组对象开始写入前先执行beginObject()方法
		write.beginObject();
		//以变量名为Key，变量值为value将对象中的属性写入json输出流
		write.name("title").value(entity.getTitle());
		write.name("artist").value(entity.getArtist());
		write.name("country").value(entity.getCountry());
		write.name("company").value(entity.getCompany());
		write.name("price").value(entity.getPrice());
		write.name("year").value(entity.getYear());
		//数组对象写入结束需要执行endObject()方法
		write.endObject();
	}
	
	/**将ArrayList对象写入OutputStream对象 该方法需Sdk11以上可用
	 * @throws IOException */
	public static void WriteJson(ArrayList<CDEntity> cds,OutputStream out) throws IOException{
		//通过传入的输出流生成Json字符输出流对象
		JsonWriter write = new JsonWriter(new OutputStreamWriter(out));
			//写入数组前需要执行beginArray()方法
			write.beginArray();
			//遍历List，将其中的每一个元素写入输出流
			for(CDEntity entity : cds){
				getJsonObject(entity,write);
			}
			//写入数组完成需要执行endArray()方法
			write.endArray();
			//执行完所有写入操作需要关闭输入流。
			write.close();
	}
	
	/**将Json数组解析为ArrayList对象 该方法需Sdk11以上可用
	 * @throws IOException */
	public static ArrayList<CDEntity> ReadJson(InputStream in) throws IOException{
		ArrayList<CDEntity> cds = new ArrayList<CDEntity>();
		//构建json输入流对象
		JsonReader read = new JsonReader(new InputStreamReader(in));
		//读取数组前需要执行beginArray方法
		read.beginArray();
		while(read.hasNext()){  //循环读取输入流，并将其加入到ArrayList中，直到输入流中没有数据
			cds.add(ReadEntity(read));
		}
		read.endArray();
		return cds;
	}
	
	/**将Json输入流解析为CDEntity对象
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
				read.skipValue();  //忽略其他key对应的值
			}
		}
		read.endObject();
		return entity;
	}
}
