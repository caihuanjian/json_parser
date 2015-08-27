package com.example.json_parser;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**用来展现专辑信息列表*/
public class CDListActivity extends ListActivity {
	
	private ArrayList<CDEntity> cds;
	private ArrayList<StudentEntity> sEntities = new ArrayList<StudentEntity>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		cds = (ArrayList<CDEntity>) getIntent().getExtras().get("cds");
//		setListAdapter(new ArrayAdapter<CDEntity>(CDListActivity.this,android.R.layout.simple_list_item_1,cds));
		
		for (int i = 0; i < 10; i++) {
			StudentEntity sEntity = new StudentEntity("huanjian"+i, "36ban", 12);
			sEntities.add(sEntity);
		}
		//传的是一个StudentEntity列表,所以需要在StudentEntity类里重写tostring方法,返回值将显示在listview的item中
		setListAdapter(new ArrayAdapter<StudentEntity>(CDListActivity.this, android.R.layout.simple_list_item_1, sEntities));
		
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				View v = getLayoutInflater().inflate(R.layout.dialog_info, null);
				CDEntity entity = cds.get(position);
				((TextView)v.findViewById(R.id.textView1)).setText("专辑标题："+entity.getTitle());
				((TextView)v.findViewById(R.id.textView2)).setText("专辑歌手："+entity.getArtist());
				((TextView)v.findViewById(R.id.textView3)).setText("专辑国家："+entity.getCountry());
				((TextView)v.findViewById(R.id.textView4)).setText("所属公司："+entity.getCompany());
				((TextView)v.findViewById(R.id.textView5)).setText("价格："+entity.getPrice());
				((TextView)v.findViewById(R.id.textView6)).setText("发布年份："+entity.getYear());
				
				new AlertDialog.Builder(CDListActivity.this).setTitle("专辑信息").setIcon(android.R.drawable.ic_dialog_info)
				.setPositiveButton("确定", null).setView(v).create().show();
			}
		});
	}
	
}
