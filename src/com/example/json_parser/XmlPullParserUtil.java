package com.example.json_parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**android内置了pull解析，相对dom解析来说pull更省内存.*/
public class XmlPullParserUtil {

	public static ArrayList<CDEntity> pullParser(InputStream in){
		ArrayList<CDEntity> cds = null;
		try {
			//构建pull解析工厂
			XmlPullParserFactory factory;
			factory = XmlPullParserFactory.newInstance();
			//构建pull解析器对象
			XmlPullParser parser =  factory.newPullParser();
			//设置解析器的数据源
			parser.setInput(new InputStreamReader(in));
			//获取事件，开始进行解析
			int eventType = parser.getEventType();
			//将要生成的CD对象
			CDEntity entity = null;
			//循环遍历xml文档，直到遍历到文档末尾
			while(eventType != XmlPullParser.END_DOCUMENT){
				switch(eventType){
				//在xml文档开始的时候构建ArrayList对象。
				case XmlPullParser.START_DOCUMENT:
					cds = new ArrayList<CDEntity>();
					break;
				//在标签开始时对标签名进行判断
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					//标签名为CD时，构建CD对象
					if("CD".equals(name)){
						entity = new CDEntity();
					//如果标签为title，则cd肯定不为空，且获取到的文本为cd标题，则将cd的标题属性设置为title标签的文本
					}else if("TITLE".equals(name)){
						entity.setTitle(parser.nextText());
					}else if("ARTIST".equals(name)){
						entity.setArtist(parser.nextText());
					}else if("COUNTRY".equals(name)){
						entity.setCountry(parser.nextText());
					}else if("COMPANY".equals(name)){
						entity.setCompany(parser.nextText());
					}else if("PRICE".equals(name)){
						entity.setPrice(Float.parseFloat(parser.nextText()));
					}else if("YEAR".equals(name)){
						entity.setYear(Integer.parseInt(parser.nextText()));
					}
					break;
				//标签结束后，判断结束标签是什么。如果cd标签结束，则生成cd对象完成，应该将其添加到ArrayList中
				case XmlPullParser.END_TAG:
					if("CD".equals(parser.getName())){
						cds.add(entity);
					}
					break;
				}
				//循环处理结束后，需要将标签设置为下一个标签，避免无限循环
				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(cds);
		return cds;
	}
}
