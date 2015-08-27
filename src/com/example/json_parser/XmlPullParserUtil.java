package com.example.json_parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**android������pull���������dom������˵pull��ʡ�ڴ�.*/
public class XmlPullParserUtil {

	public static ArrayList<CDEntity> pullParser(InputStream in){
		ArrayList<CDEntity> cds = null;
		try {
			//����pull��������
			XmlPullParserFactory factory;
			factory = XmlPullParserFactory.newInstance();
			//����pull����������
			XmlPullParser parser =  factory.newPullParser();
			//���ý�����������Դ
			parser.setInput(new InputStreamReader(in));
			//��ȡ�¼�����ʼ���н���
			int eventType = parser.getEventType();
			//��Ҫ���ɵ�CD����
			CDEntity entity = null;
			//ѭ������xml�ĵ���ֱ���������ĵ�ĩβ
			while(eventType != XmlPullParser.END_DOCUMENT){
				switch(eventType){
				//��xml�ĵ���ʼ��ʱ�򹹽�ArrayList����
				case XmlPullParser.START_DOCUMENT:
					cds = new ArrayList<CDEntity>();
					break;
				//�ڱ�ǩ��ʼʱ�Ա�ǩ�������ж�
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					//��ǩ��ΪCDʱ������CD����
					if("CD".equals(name)){
						entity = new CDEntity();
					//�����ǩΪtitle����cd�϶���Ϊ�գ��һ�ȡ�����ı�Ϊcd���⣬��cd�ı�����������Ϊtitle��ǩ���ı�
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
				//��ǩ�������жϽ�����ǩ��ʲô�����cd��ǩ������������cd������ɣ�Ӧ�ý�����ӵ�ArrayList��
				case XmlPullParser.END_TAG:
					if("CD".equals(parser.getName())){
						cds.add(entity);
					}
					break;
				}
				//ѭ�������������Ҫ����ǩ����Ϊ��һ����ǩ����������ѭ��
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
