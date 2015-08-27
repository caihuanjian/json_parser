package com.example.json_parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**����������صĹ�����*/
public class StreamUtil {

	/**�������������е�����ת�Ƶ����������
	 * @throws IOException */
	public static void getInputStream(InputStream in,OutputStream out) throws IOException{
		if(in == null || out == null){
			return;
		}
		//���建����
		byte[] buf = new byte[1024];
		int len = -1;
		while((len = in.read(buf)) != -1){  //ѭ����ȡ�������е����ݣ���д���������ֱ��������ĩβ
			out.write(buf,0,len);
		}
		out.flush();
	}
	
	/**���������е����������ַ���
	 * @throws IOException */
	public static String getString(InputStream in) throws IOException{
		String str = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		getInputStream(in, out);
		str = new String(out.toByteArray());
		in.close();
		return str;
	}
}
