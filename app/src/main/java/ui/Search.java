package ui;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.administrator.ui.BrandAdapter;
import com.example.administrator.ui.BrandDAO;

import util.ActivityCollector;

/**
 * 
 * ģ����ѯ
 * 
 * @author YinWenBing Create at 2016-3-13 ����23:33
 * 
 */
public class Search extends Activity
{
	// �ؼ�
	private ImageView mIvBack;
	private EditText mEtSearch;
	private ImageView mIvSearch;
	private GridView mGvBrands;

	private BrandDAO brandDAO;
	private BrandAdapter brandAdapter;
	private String bname;
	private List ar;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_search);
		ActivityCollector.addActivity(this);
		brandDAO = new BrandDAO(Search.this);
		initView();
	}

	@Override
	protected void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}

	private void initView()
	{
		mIvBack = (ImageView) this.findViewById(R.id.iv_back);
		mEtSearch = (EditText) this.findViewById(R.id.et_search);
		mIvSearch = (ImageView) this.findViewById(R.id.iv_search);
		mGvBrands = (GridView) this.findViewById(R.id.gv_brands);
		mIvSearch.setOnClickListener(listener);
		mIvBack.setOnClickListener(listener);
	}

	OnClickListener listener = new OnClickListener()
	{

		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.iv_back:
				Search.this.finish();
				break;
			case R.id.iv_search:
				bname = mEtSearch.getText().toString();
				ar = brandDAO.getAllBrandByName(bname);
				brandAdapter = new BrandAdapter(Search.this, ar);
				mGvBrands.setAdapter(brandAdapter);
				break;
			default:
				break;
			}
		}
	};

}
