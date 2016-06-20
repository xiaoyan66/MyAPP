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

import ui.WaitIncomeGoods;


/**
 * ��ѯ�����Ѹ���Ķ���
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
		// ��ʼ���ؼ�
		initView();
		// ��������
		initData();

		return view;
	}

	/**
	 * ��������
	 */
	private void initData()
	{
		detailDAO = new DetailDAO(getActivity());
		ar = detailDAO.getAllDetailsByDstate(1);
		detailAdapter = new DetailAdapter(getActivity(), ar);
		mLvIndent.setAdapter(detailAdapter);
	}

	/**
	 * ��ʼ���ؼ�
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
			// ������
			String inumber = map.get("inumber").toString();
			// ���ݶ����Ų�ѯ������
			Intent intent = new Intent(getActivity(), WaitIncomeGoods.class);
			intent.putExtra("inumber", inumber);
			startActivity(intent);

		}
	};
}
