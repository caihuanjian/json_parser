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

/**����չ��ר����Ϣ�б�*/
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
		//������һ��StudentEntity�б�,������Ҫ��StudentEntity������дtostring����,����ֵ����ʾ��listview��item��
		setListAdapter(new ArrayAdapter<StudentEntity>(CDListActivity.this, android.R.layout.simple_list_item_1, sEntities));
		
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				View v = getLayoutInflater().inflate(R.layout.dialog_info, null);
				CDEntity entity = cds.get(position);
				((TextView)v.findViewById(R.id.textView1)).setText("ר�����⣺"+entity.getTitle());
				((TextView)v.findViewById(R.id.textView2)).setText("ר�����֣�"+entity.getArtist());
				((TextView)v.findViewById(R.id.textView3)).setText("ר�����ң�"+entity.getCountry());
				((TextView)v.findViewById(R.id.textView4)).setText("������˾��"+entity.getCompany());
				((TextView)v.findViewById(R.id.textView5)).setText("�۸�"+entity.getPrice());
				((TextView)v.findViewById(R.id.textView6)).setText("������ݣ�"+entity.getYear());
				
				new AlertDialog.Builder(CDListActivity.this).setTitle("ר����Ϣ").setIcon(android.R.drawable.ic_dialog_info)
				.setPositiveButton("ȷ��", null).setView(v).create().show();
			}
		});
	}
	
}
