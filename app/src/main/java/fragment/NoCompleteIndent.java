package fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.ui.IndentAdapter;
import com.example.administrator.ui.IndentDAO;


public class NoCompleteIndent extends Fragment {
	private View view;
	private ListView mLvIndent;
	private List ar;
	private IndentAdapter indentAdapter;
	private IndentDAO indentDAO;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.no_complete_fm, null);
		// ��ʼ���ؼ�
		initView();
		// ��������
		initData();
		return view;
	}

	/**
	 * ��������
	 */
	private void initData() {
		indentDAO = new IndentDAO(getActivity());
		ar = indentDAO.getAllIndentByDstate(0);
		indentAdapter = new IndentAdapter(getActivity(), ar);
		mLvIndent.setAdapter(indentAdapter);
	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		mLvIndent = (ListView) view.findViewById(R.id.lv_indents_2);
	}
}
