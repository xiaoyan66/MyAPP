package fragment;

import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.administrator.ui.DetailAdapter;
import com.example.administrator.ui.DetailDAO;

import ui.R;
import ui.WaitIncomeGoods;


/**
 * 查询所有已付款的订单
 * 
 * @author YinWenBing
 * 
 */
public class CompleteIndent extends Fragment
{
	private View view;
	private ListView mLvIndent;
	private List ar;
	private DetailAdapter detailAdapter;
	private DetailDAO detailDAO;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{
		view = inflater.inflate(R.layout.complete_fm, null);
		// 初始化控件
		initView();
		// 加载数据
		initData();

		return view;
	}

	/**
	 * 加载数据
	 */
	private void initData()
	{
		detailDAO = new DetailDAO(getActivity());
		ar = detailDAO.getAllDetailsByDstate(1);
		detailAdapter = new DetailAdapter(getActivity(), ar);
		mLvIndent.setAdapter(detailAdapter);
	}

	/**
	 * 初始化控件
	 */
	private void initView()
	{
		mLvIndent = (ListView) view.findViewById(R.id.lv_indents_1);

		mLvIndent.setOnItemClickListener(lv_listener);
	}

	OnItemClickListener lv_listener = new OnItemClickListener()
	{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
								long arg3)
		{
			Map map = (Map) ar.get(arg2);
			// 订单号
			String inumber = map.get("inumber").toString();
			// 根据订单号查询订单表
			Intent intent = new Intent(getActivity(), WaitIncomeGoods.class);
			intent.putExtra("inumber", inumber);
			startActivity(intent);

		}
	};
}
