package fragment;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import com.example.administrator.ui.BrandDAO;
import com.example.administrator.ui.CategoryAdapter;

import ui.R;
import ui.ShowCars;


public class TwoFragment extends Fragment {
	private View view;
	private ExpandableListView mElList;
	private List ar;
	private BrandDAO brandDAO;
	private CategoryAdapter categoryAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.two_fragment, null);
		// 初始化控件
		initView();
		// 加载数据
		initData();
		return view;
	}

	// 加载数据
	private void initData() {
		brandDAO = new BrandDAO(getActivity());
		ar = brandDAO.getAllBrand();
		categoryAdapter = new CategoryAdapter(getActivity(), ar);
		mElList.setAdapter(categoryAdapter);
		mElList.setGroupIndicator(null);

	}

	// 初始化控件
	private void initView() {
		mElList = (ExpandableListView) view.findViewById(R.id.el_list);

		mElList.setOnChildClickListener(el_listener);
	}

	OnChildClickListener el_listener = new OnChildClickListener() {

		@Override
		public boolean onChildClick(ExpandableListView arg0, View arg1,
									int arg2, int arg3, long arg4) {
			int id = (Integer) arg1.findViewById(R.id.tv_sname).getTag();
			// Toast.makeText(getActivity(), "你点击的是：" + id, 1).show();
			Intent intent = new Intent(getActivity(), ShowCars.class);
			intent.putExtra("sid", id);
			startActivity(intent);
			return false;
		}
	};
}
