package com.example.json_parser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	ArrayList<CDEntity> cds;
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private TextView tv;
	
	//���������json�ļ����浽sdcard�У�ע�����sdcard��ҪȨ�ޣ�<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
	File file = new File(Environment.getExternalStorageDirectory()+File.separator+"cdjson.json");
	
	private void initView(){
		btn1 = (Button)findViewById(R.id.button1);
		btn2 = (Button)findViewById(R.id.button2);
		btn3 = (Button)findViewById(R.id.button3);
		btn4 = (Button)findViewById(R.id.button4);
		tv = (TextView)findViewById(R.id.textView1);
		tv.setMovementMethod(new ScrollingMovementMethod());  //Textview����ʾ�����ݽ϶࣬��Ҫ����������ʾ��ȫ��
		
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					FileOutputStream fout =  new FileOutputStream(file);
					JsonParserUtil.WriteJson(cds,fout);  //��ArrayList�е�����д��json�ļ���
					fout.close();
					FileInputStream in = new FileInputStream(file);
					String str = StreamUtil.getString(in);  //��json�ļ��е�����תΪ�ַ���
					tv.setText(str);
					Toast.makeText(MainActivity.this, "json�ļ��������", 0).show();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
					cds = null;
					if(!file.exists()){
						Toast.makeText(MainActivity.this, "json�ļ���δ����", 0).show();
						return;
					}
					FileInputStream in = new FileInputStream(file);
					cds = JsonParserUtil.ReadJson(in);
					Toast.makeText(MainActivity.this, "json�����ɹ�", 0).show();
				}catch(Exception e){
					e.printStackTrace();
				}
				Intent intent = new Intent(MainActivity.this,CDListActivity.class);
				intent.putExtra("cds", cds);
				startActivity(intent);
			}
		});
		
		btn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					String str = JsonParserUtil2.writeJson(cds);
					FileOutputStream out = new FileOutputStream(file);
					ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());
					StreamUtil.getInputStream(in, out);
					in.close();
					out.close();
					tv.setText(str);
					Toast.makeText(MainActivity.this, "json�ļ��������", 0).show();
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		btn4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cds = null;
				if(!file.exists()){
					Toast.makeText(MainActivity.this, "json�ļ���δ����", 0).show();
					return;
				}
				try {
					//���ļ��ж�ȡ������
					FileInputStream in = new FileInputStream(file);
					String str = StreamUtil.getString(in);
					cds = JsonParserUtil2.readJson(str);
					Toast.makeText(MainActivity.this, "json��ȡ�ɹ�", 0).show();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
				Intent intent = new Intent(MainActivity.this,CDListActivity.class);
				intent.putExtra("cds", cds);
				startActivity(intent);
			}
		});
	}
	
	private void init(){
		InputStream in;
		try {
			//ͨ������xml�ļ���ȡ��ʼ����
			in = getAssets().open("cd_catalog.xml");
			cds = XmlPullParserUtil.pullParser(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
