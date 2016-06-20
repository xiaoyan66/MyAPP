package fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.example.administrator.ui.BrandAdapter;
import com.example.administrator.ui.BrandDAO;
import com.example.administrator.ui.GuidePageAdapter;
import com.example.administrator.ui.PartsAdapter;
import com.example.administrator.ui.PartsDAO;

import entity.Parts;
import ui.Main;
import ui.Search;
import ui.ShowParts;
import ui.ShowSerial;


public class OneFragment extends Fragment {
	private View view;
	// ���
	private ViewPager viewPager;

	// More
	private ImageView mIvBrandMore;
	private ImageView mIvPartsMore;

	// GridView
	private GridView mGvbrand;
	private GridView mGvParts;

	// ����
	private ImageView mImgSearch;

	// �������
	private List<View> ar;
	private GuidePageAdapter adapter;

	private AtomicInteger atomicInteger = new AtomicInteger();

	// ImageView
	private ImageView mImages[];
	private ImageView mImage;

	private BrandDAO brandDAO;
	private List brandList;
	private BrandAdapter brandAdapter;

	private PartsDAO partsDAO;
	private Parts parts;
	private List partsList;
	private PartsAdapter partsAdapter;

	// �����������ʵ��fragment֮��ͨ�ŵĽӿ�
	private Main.MyCommunication myCommunication;

	// ��ת����һ��Fragment
	public void jumpFragment(Main.MyCommunication myCommunication) {
		this.myCommunication = myCommunication;
	}

	// �ø�����������
	private void startFragment(int position) {
		// ���ø������ӿ��ж���ķ���
		myCommunication.getResultFragment(position);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.one_fragment, null);
		// ��ʼ���ؼ�
		initView();
		// ��������
		initData();
		// ���¼�
		initEvent();
		return view;
	}

	// ���¼�
	private void initEvent() {
		mImgSearch.setOnClickListener(listener);
		mIvBrandMore.setOnClickListener(listener);
		mIvPartsMore.setOnClickListener(listener);
		mGvbrand.setOnItemClickListener(gv_listener);
		mGvParts.setOnItemClickListener(gv_parts_listener);

	}

	// ��������
	private void initData() {
		brandDAO = new BrandDAO(getActivity());
		brandList = brandDAO.getEightBrand();
		brandAdapter = new BrandAdapter(getActivity(), brandList);
		mGvbrand.setAdapter(brandAdapter);

		partsDAO = new PartsDAO(getActivity());
		partsList = partsDAO.getFourParts();
		partsAdapter = new PartsAdapter(getActivity(), partsList);
		mGvParts.setAdapter(partsAdapter);

	}

	// ��ʼ���ؼ�
	private void initView() {
		// ��һ������ʼ��ViewPager
		viewPager = (ViewPager) view.findViewById(R.id.vp_advertise);

		mIvBrandMore = (ImageView) view.findViewById(R.id.iv_brand_more);
		mIvPartsMore = (ImageView) view.findViewById(R.id.iv_prant_more);

		mGvbrand = (GridView) view.findViewById(R.id.gv_brand);
		mGvParts = (GridView) view.findViewById(R.id.gv_parts);
		mImgSearch = (ImageView) view.findViewById(R.id.iv_search);

		// ����ViewGroup�����������ͼƬ����
		ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.rounddot);

		// �ڶ���������������
		ar = new ArrayList<View>();
		View v0 = getActivity().getLayoutInflater().inflate(
				R.layout.advertise_item, null);
		LinearLayout l0 = (LinearLayout) v0.findViewById(R.id.advertise_item);
		l0.setBackgroundResource(R.drawable.main_page1);
		ar.add(l0);

		View v1 = getActivity().getLayoutInflater().inflate(
				R.layout.advertise_item, null);
		LinearLayout l1 = (LinearLayout) v1.findViewById(R.id.advertise_item);
		l1.setBackgroundResource(R.drawable.main_page2);
		ar.add(l1);

		View v2 = getActivity().getLayoutInflater().inflate(
				R.layout.advertise_item, null);
		LinearLayout l2 = (LinearLayout) v2.findViewById(R.id.advertise_item);
		l2.setBackgroundResource(R.drawable.main_page3);
		ar.add(l2);

		View v3 = getActivity().getLayoutInflater().inflate(
				R.layout.advertise_item, null);
		LinearLayout l3 = (LinearLayout) v3.findViewById(R.id.advertise_item);
		l3.setBackgroundResource(R.drawable.main_page4);
		ar.add(l3);

		adapter = new GuidePageAdapter(getActivity(), ar);
		viewPager.setAdapter(adapter);

		mImages = new ImageView[ar.size()];
		for (int i = 0; i < ar.size(); i++) {
			mImage = new ImageView(getActivity());
			// ����ͼƬ��͸�
			LayoutParams layoutParams = new LayoutParams(9, 9);
			layoutParams.setMargins(10, 5, 10, 5);
			mImage.setLayoutParams(layoutParams);

			mImages[i] = mImage;

			if (i == 0) {
				mImages[i].setBackgroundResource(R.drawable.small_bg);
			} else {
				mImages[i].setBackgroundResource(R.drawable.small_bg1);
			}
			viewGroup.addView(mImages[i]);
		}

		viewPager.setOnPageChangeListener(vp_listener);

		// ������ʱ��
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				handler.sendEmptyMessage(atomicInteger.incrementAndGet() - 1);
			}
		};

		timer.schedule(task, 2000, 2000);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// ��ʾ�ڼ���
			viewPager.setCurrentItem(msg.what);

			if (atomicInteger.get() == ar.size()) {
				atomicInteger.set(0);
			}
		};
	};

	OnPageChangeListener vp_listener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			atomicInteger.getAndSet(arg0);
			for (int i = 0; i < mImages.length; i++) {
				mImages[i].setBackgroundResource(R.drawable.small_bg1);
				if (arg0 != i) {
					mImages[i].setBackgroundResource(R.drawable.small_bg);
				}
			}

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}
	};

	// �����¼�
	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.iv_search:
				startActivity(new Intent(getActivity(), Search.class));
				break;
			case R.id.iv_brand_more:
				// ��ת��TwoFragment
				startFragment(2);
				break;
			case R.id.iv_prant_more:
				// ��ת��TwoFragment
				startFragment(3);
				break;
			default:
				break;
			}

		}
	};

	/**
	 * Ʒ��GridView�����¼�
	 */
	OnItemClickListener gv_listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Map map = (Map) brandList.get(arg2);
			// Ʒ�Ʊ��
			int bid = Integer.parseInt(map.get("bid").toString());
			// ����Ʒ�Ʊ�Ų�ѯ���г�ϵ
			Intent intent = new Intent(getActivity(), ShowSerial.class);
			intent.putExtra("bid", bid);
			startActivity(intent);

		}
	};

	/**
	 * ��ƷGridView�����¼�
	 */
	OnItemClickListener gv_parts_listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			Map map = (Map) partsList.get(arg2);
			// Ʒ�Ʊ��
			int pid = Integer.parseInt(map.get("pid").toString());

			// ����Ʒ�Ʊ�Ų�ѯ���г�ϵ

			partsDAO = new PartsDAO(getActivity());
			parts = partsDAO.getAllPartsById(pid);

			// ��Ʒ����(��ţ����ƣ�ͼƬ����棬ԭ�ۣ��ּۣ�Ʒ�ƣ�����)

			String pname = parts.getPname();
			int pimage = parts.getPimage();
			int pinventory = parts.getPinventory();
			float poldprice = parts.getPoldprice();
			float pnewprice = parts.getPnewprice();
			String pbrand = parts.getPbrand();
			String preferral = parts.getPreferral();

			Intent intent = new Intent(getActivity(), ShowParts.class);

			intent.putExtra("pid", pid);
			intent.putExtra("pname", pname);
			intent.putExtra("pimage", pimage);
			intent.putExtra("pinventory", pinventory);
			intent.putExtra("poldprice", poldprice);
			intent.putExtra("pnewprice", pnewprice);
			intent.putExtra("pbrand", pbrand);
			intent.putExtra("preferral", preferral);
			startActivity(intent);
		}
	};

}
