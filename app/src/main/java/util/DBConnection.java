package util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnection extends SQLiteOpenHelper {

	public DBConnection(Context context) {
		super(context, "usedcars.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Ʒ�Ʊ�(��ţ����ƣ����ܣ�ͼƬ)
		db.execSQL("create table brand(_id integer primary key autoincrement,bname varchar(50),breferral varchar(1000),bimage integer ) ");
		// ����Ʒ�Ʊ�����
		db.execSQL("insert into brand values(null,'����','BMW����˾������1916�꣬�ܲ����ڵ¹��ͷ������ݵ�Ľ��ڡ�BMW�����ױ�־����˾�ܲ����ڵذͷ��������������ɫ������Ҳ����Ϊ��������',"
				+ R.drawable.car_bmw + ")");
		db.execSQL("insert into brand values(null,'�µ�','�µ����������������������̺������̣���1898�괴�������־Ϊ�ĸ�Բ����Ԣ��Ϊ�ĸ�����Ʒ�����ˡ�',"
				+ R.drawable.car_audi + ")");
		db.execSQL("insert into brand values(null,'����','÷����˹-���ۣ�Mercedes-Benz�����¹�����Ʒ�ƣ�����Ϊ����������ɹ��ĸߵ�����Ʒ��֮һ��',"
				+ R.drawable.car_benz + ")");
		db.execSQL("insert into brand values(null,'���','����ʼ�Ǹ����������������̡�1903��5��19�մ��� ���˰͡� ����ڲ���˹���ֵܵİ����´����������������˾��',"
				+ R.drawable.car_buick + ")");
		db.execSQL("insert into brand values(null,'����','���������ǳ��������ɷ����޹�˾�ļ�ƣ�����������ǰ���ǳ��ǹ�ҵ��˾����һ�Ҽ�����������ҵ��������1984�꣬��˾�ܲ�λ�ںӱ�ʡ�����С�',"
				+ R.drawable.car_changcheng + ")");
		db.execSQL("insert into brand values(null,'������','������(Ferrari)��һ����������������̣�1929���ɶ�����������(Enzo Ferrari)���죬��Ҫ����һ������ʽ�������������������ܳ���',"
				+ R.drawable.car_ferrari + ")");
		db.execSQL("insert into brand values(null,'��������','��Ϊȫ�򶥼��ܳ������̼�ŷ���ݳ�Ʒ��־֮һ����������һ����н������ٶ���ʱ�з����Ϊһ���Ʒ��������ϴ��²�Ѱ��ȫ��Ʒ��ͻ�ơ�',"
				+ R.drawable.car_lamborghini + ")");
		db.execSQL("insert into brand values(null,'�׿���˹','�׿���˹(LEXUS)���ձ����Ｏ������ȫ��������������Ʒ�ơ�������1983�꣬��������ʮ�����ʱ�䣬�ڱ����㳬���˱��ۡ������������',"
				+ R.drawable.car_lexus + ")");
		db.execSQL("insert into brand values(null,'��ʱ��','��ʱ�ݣ�Porsche���ֳ�֮Ϊ�����ᣬ��ʱ��������������������������Ϊ��ͨ����������Porsche��˾����������һ�㶼��Ϊ��ʱ�ݹ�˾��',"
				+ R.drawable.car_porsche + ")");
		db.execSQL("insert into brand values(null,'����','�������������Volkswagen����һ���ܲ�λ�ڵ¹��ֶ���˹�����������칫˾��Ҳ�������Ĵ�����������֮һ�Ĵ��ڼ��ŵĺ�����ҵ��',"
				+ R.drawable.car_volkswagen + ")");
		db.execSQL("insert into brand values(null,'�ִ�','�ִ�������˾�Ǻ�������������ҵ��ԭ���ִ����ţ�����20�����������˾֮һ��������1967�꣬��ʼ����ԭ�ִ����Ż᳤֣������',"
				+ R.drawable.car_xiandai + ")");

		// ��ϵ����ţ����ƣ�ͼƬ��Ʒ�ƣ��������
		db.execSQL("create table serial(_id integer primary key autoincrement,sname varchar(100),simage integer,bid integer references brand(_id) )");
		// ���복ϵ����
		// ����ϵ��0
		db.execSQL("insert into serial values(null,'����X1'," + R.drawable.bmw_x1
				+ ",1)");
		db.execSQL("insert into serial values(null,'����X2'," + R.drawable.bmw_x2
				+ ",1)");
		db.execSQL("insert into serial values(null,'����X3'," + R.drawable.bmw_x3
				+ ",1)");
		db.execSQL("insert into serial values(null,'����X4'," + R.drawable.bmw_x4
				+ ",1)");
		db.execSQL("insert into serial values(null,'����X5'," + R.drawable.bmw_x5
				+ ",1)");
		db.execSQL("insert into serial values(null,'����X6'," + R.drawable.bmw_x6
				+ ",1)");
		db.execSQL("insert into serial values(null,'����M3'," + R.drawable.bmw_m3
				+ ",1)");
		db.execSQL("insert into serial values(null,'����M4'," + R.drawable.bmw_m4
				+ ",1)");
		db.execSQL("insert into serial values(null,'����M5'," + R.drawable.bmw_m5
				+ ",1)");
		db.execSQL("insert into serial values(null,'����M6'," + R.drawable.bmw_m6
				+ ",1)");

		//
		// �µ�ϵ��
		db.execSQL("insert into serial values(null,'�µ�A1',"
				+ R.drawable.auti_a1 + ",2)");
		db.execSQL("insert into serial values(null,'�µ�A3',"
				+ R.drawable.auti_a3 + ",2)");
		db.execSQL("insert into serial values(null,'�µ�A4',"
				+ R.drawable.auti_a4 + ",2)");
		db.execSQL("insert into serial values(null,'�µ�A5',"
				+ R.drawable.auti_a5 + ",2)");
		db.execSQL("insert into serial values(null,'�µ�A6',"
				+ R.drawable.auti_a6 + ",2)");
		db.execSQL("insert into serial values(null,'�µ�A7',"
				+ R.drawable.auti_a7 + ",2)");
		db.execSQL("insert into serial values(null,'�µ�A8',"
				+ R.drawable.auti_a8 + ",2)");
		// ����ϵ��
		db.execSQL("insert into serial values(null,'����Cϵ'," + R.drawable.benz_c
				+ ",3)");
		db.execSQL("insert into serial values(null,'����Eϵ'," + R.drawable.benz_e
				+ ",3)");
		db.execSQL("insert into serial values(null,'����GLA',"
				+ R.drawable.benz_gla + ",3)");
		db.execSQL("insert into serial values(null,'����GLC',"
				+ R.drawable.benz_glc + ",3)");
		db.execSQL("insert into serial values(null,'����GLK',"
				+ R.drawable.benz_glk + ",3)");

		// ���
		db.execSQL("insert into serial values(null,'���G18',"
				+ R.drawable.buick_gl8 + ",4)");
		db.execSQL("insert into serial values(null,'��˾���',"
				+ R.drawable.buick_jw + ",4)");
		db.execSQL("insert into serial values(null,'��˿�Խ',"
				+ R.drawable.buick_ky + ",4)");
		db.execSQL("insert into serial values(null,'�������',"
				+ R.drawable.buick_wl + ",4)");
		db.execSQL("insert into serial values(null,'���Ӣ��',"
				+ R.drawable.buick_yl + ",4)");

		// ����
		db.execSQL("insert into serial values(null,'����C20R',"
				+ R.drawable.changcheng_c20r + ",5)");
		db.execSQL("insert into serial values(null,'����C30',"
				+ R.drawable.changcheng_c30 + ",5)");
		db.execSQL("insert into serial values(null,'����C50',"
				+ R.drawable.changcheng_c50 + ",5)");
		db.execSQL("insert into serial values(null,'����M2',"
				+ R.drawable.changcheng_m2 + ",5)");
		db.execSQL("insert into serial values(null,'����m4',"
				+ R.drawable.changcheng_m4 + ",5)");

		// ������
		db.execSQL("insert into serial values(null,'������360',"
				+ R.drawable.ferrari_360 + ",6)");
		db.execSQL("insert into serial values(null,'������458',"
				+ R.drawable.ferrari_458 + ",6)");
		db.execSQL("insert into serial values(null,'������488',"
				+ R.drawable.ferrari_488 + ",6)");
		db.execSQL("insert into serial values(null,'������575M',"
				+ R.drawable.ferrari_575m + ",6)");
		db.execSQL("insert into serial values(null,'������599',"
				+ R.drawable.ferrari_599 + ",6)");
		db.execSQL("insert into serial values(null,'������612',"
				+ R.drawable.ferrari_612 + ",6)");
		db.execSQL("insert into serial values(null,'������F430',"
				+ R.drawable.ferrari_f430 + ",6)");

		// ��������
		db.execSQL("insert into serial values(null,'��������Aventador',"
				+ R.drawable.lamborghini_aventador + ",7)");
		db.execSQL("insert into serial values(null,'��������Gallardo',"
				+ R.drawable.lamborghini_gallardo + ",7)");
		db.execSQL("insert into serial values(null,'��������Huracan',"
				+ R.drawable.lamborghini_huracan + ",7)");
		db.execSQL("insert into serial values(null,'��������Murcielago',"
				+ R.drawable.lamborghini_murcielago + ",7)");
		db.execSQL("insert into serial values(null,'��������Reventon',"
				+ R.drawable.lamborghini_reventon + ",7)");

		// �׿���˹
		db.execSQL("insert into serial values(null,'�׿���˹CT',"
				+ R.drawable.lexus_ct + ",8)");
		db.execSQL("insert into serial values(null,'�׿���˹ES',"
				+ R.drawable.lexus_es + ",8)");
		db.execSQL("insert into serial values(null,'�׿���˹GS',"
				+ R.drawable.lexus_gs + ",8)");
		db.execSQL("insert into serial values(null,'�׿���˹IS',"
				+ R.drawable.lexus_is + ",8)");
		db.execSQL("insert into serial values(null,'�׿���˹LS',"
				+ R.drawable.lexus_ls + ",8)");
		db.execSQL("insert into serial values(null,'�׿���˹NX',"
				+ R.drawable.lexus_nx + ",8)");

		// ��ʱ��
		db.execSQL("insert into serial values(null,'��ʱ��911',"
				+ R.drawable.porsche_911 + ",9)");
		db.execSQL("insert into serial values(null,'��ʱ��918',"
				+ R.drawable.porsche_918 + ",9)");
		db.execSQL("insert into serial values(null,'��ʱ��Boxster',"
				+ R.drawable.porsche_boxster + ",9)");
		db.execSQL("insert into serial values(null,'��ʱ��Cayman',"
				+ R.drawable.porsche_cayman + ",9)");
		db.execSQL("insert into serial values(null,'��ʱ��Ky',"
				+ R.drawable.porsche_ky + ",9)");
		db.execSQL("insert into serial values(null,'��ʱ��Macan',"
				+ R.drawable.porsche_macan + ",9)");
		db.execSQL("insert into serial values(null,'��ʱ��Panamera',"
				+ R.drawable.porsche_panamera + ",9)");

		// ����
		db.execSQL("insert into serial values(null,'�������',"
				+ R.drawable.volkswagen_ld + ",10)");
		db.execSQL("insert into serial values(null,'�����ʾ�',"
				+ R.drawable.volkswagen_lj + ",10)");
		db.execSQL("insert into serial values(null,'��������',"
				+ R.drawable.volkswagen_lx + ",10)");
		db.execSQL("insert into serial values(null,'��������',"
				+ R.drawable.volkswagen_ly + ",10)");
		db.execSQL("insert into serial values(null,'����Polo',"
				+ R.drawable.volkswagen_polo + ",10)");
		db.execSQL("insert into serial values(null,'����;��',"
				+ R.drawable.volkswagen_tg + ",10)");

		// �ִ�
		db.execSQL("insert into serial values(null,'�ִ��ʶ�',"
				+ R.drawable.xiandai_ld + ",11)");
		db.execSQL("insert into serial values(null,'�ִ���ͼ',"
				+ R.drawable.xiandai_mt + ",11)");
		db.execSQL("insert into serial values(null,'�ִ��ö�',"
				+ R.drawable.xiandai_yd + ",11)");
		db.execSQL("insert into serial values(null,'�ִ�����',"
				+ R.drawable.xiandai_rn + ",11)");
		db.execSQL("insert into serial values(null,'�ִ�����',"
				+ R.drawable.xiandai_ry + ",11)");

		// ��������ţ���ϵ�����ƣ�ͼƬ�����𡢷������������䡢�ṹ��ԭ�ۡ��ּۣ�
		db.execSQL("create table car(_id integer primary key autoincrement,sid integer references serial(_id),cname varchar(50),cimage integer,crank varchar(50),cengine varchar(100),cgearbox varchar(100),cstructure varchar(100),coldprice float,cnewprice float)");
		// ����X1ϵ�У���ţ���ϵ�����ƣ�ͼƬ�����𡢷������������䡢�ṹ��ԭ�ۡ��ּۣ�
		db.execSQL("insert into car values(null,1,'����X1 2014�� sDrive18i �ֶ���',"
				+ R.drawable.bmw_x1_1
				+ ",'������SUV','2.0T 156���� L4','6���ֶ�','SUV',250000.9,230000.8)");

		db.execSQL("insert into car values(null,1,'����X1 2015�� sDrive18i ʱ�н�����',"
				+ R.drawable.bmw_x1_2
				+ ",'������SUV','2.0T 156���� L4','8������һ��','SUV',280000.5,270000.5)");
		db.execSQL("insert into car values(null,1,'����X1 2015�� sDrive18i ���Ƚ�����',"
				+ R.drawable.bmw_x1_3
				+ ",'������SUV','2.0T 156���� L4','8������һ��','SUV',310000.9,300000.9)");
		db.execSQL("insert into car values(null,1,'����X1 2014�� sDrive18i ʱ����',"
				+ R.drawable.bmw_x1_4
				+ ",'������SUV','2.0T 156���� L4','8������һ��','SUV',280000.5,260000.0)");

		db.execSQL("insert into car values(null,1,'����X1 2014�� sDrive18i ������',"
				+ R.drawable.bmw_x1_5
				+ ",'������SUV','2.0T 156���� L4','8������һ��','SUV',310000.9,290000.0)");
		db.execSQL("insert into car values(null,1,'����X1 2014�� sDrive18i �˶������װ',"
				+ R.drawable.bmw_x1_6
				+ ",'������SUV','2.0T 156���� L4','8������һ��','SUV',330000.9,320000.9)");
		db.execSQL("insert into car values(null,1,'����X1 2014�� sDrive18i X�����װ',"
				+ R.drawable.bmw_x1_7
				+ ",'������SUV','2.0T 156���� L4','8������һ��','SUV',330000.9,320000.3)");

		// ����(��ţ����ƣ�ͼƬ����棬ԭ�ۣ��ּۣ�Ʒ�ƣ�����)
		db.execSQL("create table parts(_id integer primary key autoincrement,pname varchar(50),pimage integer,pinventory integer,poldprice float,pnewprice float,pbrand varchar(100),preferral varchar(1000))");

		// ������������
		db.execSQL("insert into parts values(null,'����',"
				+ R.drawable.parts1
				+ ",300,40,35,'�ȿ�(SAST)','���س���� ����˫USB�����Դ ���ܼ���ѹ LED��ʾ����ģʽһ�϶����� ')");
		db.execSQL("insert into parts values(null,'����'," + R.drawable.parts2
				+ ",220,60,50,'����','������ �׹��� �³��� ������ ȥ���� �����޸���')");

		db.execSQL("insert into parts values(null,'�ڼ�'," + R.drawable.parts3
				+ ",330,60,50,'ʥ��Դ','С���п�ͨ�������ڼ����շ�ҡͷ�ɰ�����')");

		db.execSQL("insert into parts values(null,'��������'," + R.drawable.parts4
				+ ",205,'99','79','������','��������ë�������׶���������Ʒ�ļ�ͨ��')");

		db.execSQL("insert into parts values(null,'������ˮ'," + R.drawable.parts5
				+ ",380,100,80,';�м�','������ˮ��YG601101 һ��ƽ��')");

		db.execSQL("insert into parts values(null,'��������'," + R.drawable.parts6
				+ ",210,170,120,'�Ϸ���','�ļ�ͨ�������������涬������;�����������ļ���')");

		db.execSQL("insert into parts values(null,'�г���¼��'," + R.drawable.parts7
				+ ",330,500,450,'������','D66�г���¼�� ����ҹ�ӳ�1080P��ǰ���A7оƬ')");

		db.execSQL("insert into parts values(null,'������'," + R.drawable.parts8
				+ ",344,600,550,'������','7Ӣ������� ���峵��GPS������')");

		// �û���(��ţ��˺ţ����룬ͷ��)
		db.execSQL("create table userinfo(_id integer primary key autoincrement,uname varchar(50),upwd varchar(50),uimage integer)");
		// �����û�
		db.execSQL("insert into userinfo values(null,'123456','123456',"
				+ R.drawable.kevin_uimage + ")");

		// ���ﳵ��(shoppingcart)
		// ��š��û���š���Ʒ���������������ࣨ����or���Σ����Ƿ���
		db.execSQL("create table shoppingcart(_id integer primary key autoincrement,uid integer references userinfo(_id),gid integer,gcount integer,gsource integer,gbuy integer)");

		// �ղر�(��š��û���š���Ʒ��š���Ʒ���ơ���Ʒ�۸�,��Ʒ��Դ,��ƷͼƬ)
		db.execSQL("create table collect(_id integer primary key autoincrement,uid integer references userinfo(_id),gid integer,gname varchar(100),gprice float,gsource integer,gimage integer)");

		// ������indent������š�ͼƬ�����ơ����ۡ������������š��û���״̬��
		db.execSQL("create table indent(_id integer primary key autoincrement,iimage int,iname varchar(100),iprice float,icount integer,inumber varchar(100),uid integer references userinfo(_id),dstate integer)");

		// ������ϸ��detail������š������š��ܼۡ��µ����ڡ�״̬���û���
		db.execSQL("create table detail(_id integer primary key autoincrement,inumber varchar(100) references indent(inumber),dallprice float,dtime varchar(100),dstate boolean,uid integer references userinfo(_id))");

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
