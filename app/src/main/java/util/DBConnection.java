package util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ui.R;

public class DBConnection extends SQLiteOpenHelper {

	public DBConnection(Context context) {
		super(context, "usedcars.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 品牌表(编号，名称，介绍，图片)
		db.execSQL("create table brand(_id integer primary key autoincrement,bname varchar(50),breferral varchar(1000),bimage integer ) ");
		// 插入品牌表数据
		db.execSQL("insert into brand values(null,'宝马','BMW宝马公司创建于1916年，总部设在德国巴伐利亚州的慕尼黑。BMW的蓝白标志宝马公司总部所在地巴伐利亚州州旗的颜色。宝马也被译为巴依尔。',"
				+ R.drawable.car_bmw + ")");
		db.execSQL("insert into brand values(null,'奥迪','奥迪是世界著名的汽车开发商和制造商，于1898年创立，其标志为四个圆环，寓意为四个汽车品牌联盟。',"
				+ R.drawable.car_audi + ")");
		db.execSQL("insert into brand values(null,'奔驰','梅赛德斯-奔驰（Mercedes-Benz），德国汽车品牌，被认为是世界上最成功的高档汽车品牌之一。',"
				+ R.drawable.car_benz + ")");
		db.execSQL("insert into brand values(null,'别克','别克最开始是个独立的汽车制造商。1903年5月19日大卫 ·邓巴· 别克在布里斯科兄弟的帮助下创建美国别克汽车公司。',"
				+ R.drawable.car_buick + ")");
		db.execSQL("insert into brand values(null,'长城','长城汽车是长城汽车股份有限公司的简称，长城汽车的前身是长城工业公司，是一家集体所有制企业，成立于1984年，公司总部位于河北省保定市。',"
				+ R.drawable.car_changcheng + ")");
		db.execSQL("insert into brand values(null,'法拉利','法拉利(Ferrari)是一家意大利汽车生产商，1929年由恩佐·法拉利(Enzo Ferrari)创办，主要制造一级方程式赛车、赛车及高性能跑车。',"
				+ R.drawable.car_ferrari + ")");
		db.execSQL("insert into brand values(null,'兰博基尼','作为全球顶级跑车制造商及欧洲奢侈品标志之一，兰博基尼一贯秉承将极致速度与时尚风格融为一体的品牌理念，不断创新并寻求全新品牌突破。',"
				+ R.drawable.car_lamborghini + ")");
		db.execSQL("insert into brand values(null,'雷克萨斯','雷克萨斯(LEXUS)是日本丰田集团旗下全球著名豪华汽车品牌。创立于1983年，仅仅用了十几年的时间，在北美便超过了奔驰、宝马的销量。',"
				+ R.drawable.car_lexus + ")");
		db.execSQL("insert into brand values(null,'保时捷','保时捷（Porsche）又称之为波尔舍，保时捷是香港人粤语的译音，波尔舍为普通话的译音。Porsche公司的中文名称一般都称为保时捷公司。',"
				+ R.drawable.car_porsche + ")");
		db.execSQL("insert into brand values(null,'大众','大众汽车（德语：Volkswagen）是一家总部位于德国沃尔夫斯堡的汽车制造公司，也是世界四大汽车生产商之一的大众集团的核心企业。',"
				+ R.drawable.car_volkswagen + ")");
		db.execSQL("insert into brand values(null,'现代','现代汽车公司是韩国最大的汽车企业，原属现代集团，世界20家最大汽车公司之一。成立于1967年，创始人是原现代集团会长郑周永。',"
				+ R.drawable.car_xiandai + ")");

		// 车系（编号，名称，图片，品牌（外键））
		db.execSQL("create table serial(_id integer primary key autoincrement,sname varchar(100),simage integer,bid integer references brand(_id) )");
		// 插入车系数据
		// 宝马系列0
		db.execSQL("insert into serial values(null,'宝马X1'," + R.drawable.bmw_x1
				+ ",1)");
		db.execSQL("insert into serial values(null,'宝马X2'," + R.drawable.bmw_x2
				+ ",1)");
		db.execSQL("insert into serial values(null,'宝马X3'," + R.drawable.bmw_x3
				+ ",1)");
		db.execSQL("insert into serial values(null,'宝马X4'," + R.drawable.bmw_x4
				+ ",1)");
		db.execSQL("insert into serial values(null,'宝马X5'," + R.drawable.bmw_x5
				+ ",1)");
		db.execSQL("insert into serial values(null,'宝马X6'," + R.drawable.bmw_x6
				+ ",1)");
		db.execSQL("insert into serial values(null,'宝马M3'," + R.drawable.bmw_m3
				+ ",1)");
		db.execSQL("insert into serial values(null,'宝马M4'," + R.drawable.bmw_m4
				+ ",1)");
		db.execSQL("insert into serial values(null,'宝马M5'," + R.drawable.bmw_m5
				+ ",1)");
		db.execSQL("insert into serial values(null,'宝马M6'," + R.drawable.bmw_m6
				+ ",1)");

		//
		// 奥迪系列
		db.execSQL("insert into serial values(null,'奥迪A1',"
				+ R.drawable.auti_a1 + ",2)");
		db.execSQL("insert into serial values(null,'奥迪A3',"
				+ R.drawable.auti_a3 + ",2)");
		db.execSQL("insert into serial values(null,'奥迪A4',"
				+ R.drawable.auti_a4 + ",2)");
		db.execSQL("insert into serial values(null,'奥迪A5',"
				+ R.drawable.auti_a5 + ",2)");
		db.execSQL("insert into serial values(null,'奥迪A6',"
				+ R.drawable.auti_a6 + ",2)");
		db.execSQL("insert into serial values(null,'奥迪A7',"
				+ R.drawable.auti_a7 + ",2)");
		db.execSQL("insert into serial values(null,'奥迪A8',"
				+ R.drawable.auti_a8 + ",2)");
		// 奔驰系列
		db.execSQL("insert into serial values(null,'奔驰C系'," + R.drawable.benz_c
				+ ",3)");
		db.execSQL("insert into serial values(null,'奔驰E系'," + R.drawable.benz_e
				+ ",3)");
		db.execSQL("insert into serial values(null,'奔驰GLA',"
				+ R.drawable.benz_gla + ",3)");
		db.execSQL("insert into serial values(null,'奔驰GLC',"
				+ R.drawable.benz_glc + ",3)");
		db.execSQL("insert into serial values(null,'奔驰GLK',"
				+ R.drawable.benz_glk + ",3)");

		// 别克
		db.execSQL("insert into serial values(null,'别克G18',"
				+ R.drawable.buick_gl8 + ",4)");
		db.execSQL("insert into serial values(null,'别克君威',"
				+ R.drawable.buick_jw + ",4)");
		db.execSQL("insert into serial values(null,'别克凯越',"
				+ R.drawable.buick_ky + ",4)");
		db.execSQL("insert into serial values(null,'别克威朗',"
				+ R.drawable.buick_wl + ",4)");
		db.execSQL("insert into serial values(null,'别克英朗',"
				+ R.drawable.buick_yl + ",4)");

		// 长城
		db.execSQL("insert into serial values(null,'长城C20R',"
				+ R.drawable.changcheng_c20r + ",5)");
		db.execSQL("insert into serial values(null,'长城C30',"
				+ R.drawable.changcheng_c30 + ",5)");
		db.execSQL("insert into serial values(null,'长城C50',"
				+ R.drawable.changcheng_c50 + ",5)");
		db.execSQL("insert into serial values(null,'长城M2',"
				+ R.drawable.changcheng_m2 + ",5)");
		db.execSQL("insert into serial values(null,'长城m4',"
				+ R.drawable.changcheng_m4 + ",5)");

		// 法拉利
		db.execSQL("insert into serial values(null,'法拉利360',"
				+ R.drawable.ferrari_360 + ",6)");
		db.execSQL("insert into serial values(null,'法拉利458',"
				+ R.drawable.ferrari_458 + ",6)");
		db.execSQL("insert into serial values(null,'法拉利488',"
				+ R.drawable.ferrari_488 + ",6)");
		db.execSQL("insert into serial values(null,'法拉利575M',"
				+ R.drawable.ferrari_575m + ",6)");
		db.execSQL("insert into serial values(null,'法拉利599',"
				+ R.drawable.ferrari_599 + ",6)");
		db.execSQL("insert into serial values(null,'法拉利612',"
				+ R.drawable.ferrari_612 + ",6)");
		db.execSQL("insert into serial values(null,'法拉利F430',"
				+ R.drawable.ferrari_f430 + ",6)");

		// 兰博基尼
		db.execSQL("insert into serial values(null,'兰博基尼Aventador',"
				+ R.drawable.lamborghini_aventador + ",7)");
		db.execSQL("insert into serial values(null,'兰博基尼Gallardo',"
				+ R.drawable.lamborghini_gallardo + ",7)");
		db.execSQL("insert into serial values(null,'兰博基尼Huracan',"
				+ R.drawable.lamborghini_huracan + ",7)");
		db.execSQL("insert into serial values(null,'兰博基尼Murcielago',"
				+ R.drawable.lamborghini_murcielago + ",7)");
		db.execSQL("insert into serial values(null,'兰博基尼Reventon',"
				+ R.drawable.lamborghini_reventon + ",7)");

		// 雷克萨斯
		db.execSQL("insert into serial values(null,'雷克萨斯CT',"
				+ R.drawable.lexus_ct + ",8)");
		db.execSQL("insert into serial values(null,'雷克萨斯ES',"
				+ R.drawable.lexus_es + ",8)");
		db.execSQL("insert into serial values(null,'雷克萨斯GS',"
				+ R.drawable.lexus_gs + ",8)");
		db.execSQL("insert into serial values(null,'雷克萨斯IS',"
				+ R.drawable.lexus_is + ",8)");
		db.execSQL("insert into serial values(null,'雷克萨斯LS',"
				+ R.drawable.lexus_ls + ",8)");
		db.execSQL("insert into serial values(null,'雷克萨斯NX',"
				+ R.drawable.lexus_nx + ",8)");

		// 保时捷
		db.execSQL("insert into serial values(null,'保时捷911',"
				+ R.drawable.porsche_911 + ",9)");
		db.execSQL("insert into serial values(null,'保时捷918',"
				+ R.drawable.porsche_918 + ",9)");
		db.execSQL("insert into serial values(null,'保时捷Boxster',"
				+ R.drawable.porsche_boxster + ",9)");
		db.execSQL("insert into serial values(null,'保时捷Cayman',"
				+ R.drawable.porsche_cayman + ",9)");
		db.execSQL("insert into serial values(null,'保时捷Ky',"
				+ R.drawable.porsche_ky + ",9)");
		db.execSQL("insert into serial values(null,'保时捷Macan',"
				+ R.drawable.porsche_macan + ",9)");
		db.execSQL("insert into serial values(null,'保时捷Panamera',"
				+ R.drawable.porsche_panamera + ",9)");

		// 大众
		db.execSQL("insert into serial values(null,'大众凌渡',"
				+ R.drawable.volkswagen_ld + ",10)");
		db.execSQL("insert into serial values(null,'大众朗境',"
				+ R.drawable.volkswagen_lj + ",10)");
		db.execSQL("insert into serial values(null,'大众朗行',"
				+ R.drawable.volkswagen_lx + ",10)");
		db.execSQL("insert into serial values(null,'大众凌逸',"
				+ R.drawable.volkswagen_ly + ",10)");
		db.execSQL("insert into serial values(null,'大众Polo',"
				+ R.drawable.volkswagen_polo + ",10)");
		db.execSQL("insert into serial values(null,'大众途观',"
				+ R.drawable.volkswagen_tg + ",10)");

		// 现代
		db.execSQL("insert into serial values(null,'现代朗动',"
				+ R.drawable.xiandai_ld + ",11)");
		db.execSQL("insert into serial values(null,'现代名图',"
				+ R.drawable.xiandai_mt + ",11)");
		db.execSQL("insert into serial values(null,'现代悦动',"
				+ R.drawable.xiandai_yd + ",11)");
		db.execSQL("insert into serial values(null,'现代瑞纳',"
				+ R.drawable.xiandai_rn + ",11)");
		db.execSQL("insert into serial values(null,'现代瑞奕',"
				+ R.drawable.xiandai_ry + ",11)");

		// 车辆（编号，车系，名称，图片，级别、发动机、变速箱、结构、原价、现价）
		db.execSQL("create table car(_id integer primary key autoincrement,sid integer references serial(_id),cname varchar(50),cimage integer,crank varchar(50),cengine varchar(100),cgearbox varchar(100),cstructure varchar(100),coldprice float,cnewprice float)");
		// 宝马X1系列（编号，车系，名称，图片，级别、发动机、变速箱、结构、原价、现价）
		db.execSQL("insert into car values(null,1,'宝马X1 2014款 sDrive18i 手动型',"
				+ R.drawable.bmw_x1_1
				+ ",'紧凑型SUV','2.0T 156马力 L4','6挡手动','SUV',250000.9,230000.8)");

		db.execSQL("insert into car values(null,1,'宝马X1 2015款 sDrive18i 时尚晋级版',"
				+ R.drawable.bmw_x1_2
				+ ",'紧凑型SUV','2.0T 156马力 L4','8挡手自一体','SUV',280000.5,270000.5)");
		db.execSQL("insert into car values(null,1,'宝马X1 2015款 sDrive18i 领先晋级版',"
				+ R.drawable.bmw_x1_3
				+ ",'紧凑型SUV','2.0T 156马力 L4','8挡手自一体','SUV',310000.9,300000.9)");
		db.execSQL("insert into car values(null,1,'宝马X1 2014款 sDrive18i 时尚型',"
				+ R.drawable.bmw_x1_4
				+ ",'紧凑型SUV','2.0T 156马力 L4','8挡手自一体','SUV',280000.5,260000.0)");

		db.execSQL("insert into car values(null,1,'宝马X1 2014款 sDrive18i 领先型',"
				+ R.drawable.bmw_x1_5
				+ ",'紧凑型SUV','2.0T 156马力 L4','8挡手自一体','SUV',310000.9,290000.0)");
		db.execSQL("insert into car values(null,1,'宝马X1 2014款 sDrive18i 运动设计套装',"
				+ R.drawable.bmw_x1_6
				+ ",'紧凑型SUV','2.0T 156马力 L4','8挡手自一体','SUV',330000.9,320000.9)");
		db.execSQL("insert into car values(null,1,'宝马X1 2014款 sDrive18i X设计套装',"
				+ R.drawable.bmw_x1_7
				+ ",'紧凑型SUV','2.0T 156马力 L4','8挡手自一体','SUV',330000.9,320000.3)");

		// 配饰(编号，名称，图片，库存，原价，现价，品牌，介绍)
		db.execSQL("create table parts(_id integer primary key autoincrement,pname varchar(50),pimage integer,pinventory integer,poldprice float,pnewprice float,pbrand varchar(100),preferral varchar(1000))");

		// 插入配饰数据
		db.execSQL("insert into parts values(null,'车充',"
				+ R.drawable.parts1
				+ ",300,40,35,'先科(SAST)','车载充电器 车充双USB车充电源 智能检测电压 LED显示点烟模式一拖二车充 ')");
		db.execSQL("insert into parts values(null,'车蜡'," + R.drawable.parts2
				+ ",220,60,50,'美孚','划痕蜡 抛光蜡 新车蜡 汽车蜡 去污蜡 划痕修复蜡')");

		db.execSQL("insert into parts values(null,'摆件'," + R.drawable.parts3
				+ ",330,60,50,'圣民源','小和尚卡通佛汽车摆件弥勒佛摇头可爱公仔')");

		db.execSQL("insert into parts values(null,'方向盘套'," + R.drawable.parts4
				+ ",205,'99','79','手艺人','汽车纯羊毛方向盘套冬季车内饰品四季通用')");

		db.execSQL("insert into parts values(null,'汽车香水'," + R.drawable.parts5
				+ ",380,100,80,'途行记','车用香水座YG601101 一生平安')");

		db.execSQL("insert into parts values(null,'汽车坐垫'," + R.drawable.parts6
				+ ",210,170,120,'紫风铃','四季通用亚麻汽车坐垫冬季迈腾途观速腾坐垫四季垫')");

		db.execSQL("insert into parts values(null,'行车记录仪'," + R.drawable.parts7
				+ ",330,500,450,'爱国者','D66行车记录仪 高清夜视超1080P广角安霸A7芯片')");

		db.execSQL("insert into parts values(null,'导航仪'," + R.drawable.parts8
				+ ",344,600,550,'凯立德','7英寸电容屏 高清车载GPS导航仪')");

		// 用户表(编号，账号，密码，头像)
		db.execSQL("create table userinfo(_id integer primary key autoincrement,uname varchar(50),upwd varchar(50),uimage integer)");
		// 插入用户
		db.execSQL("insert into userinfo values(null,'123456','123456',"
				+ R.drawable.kevin_uimage + ")");

		// 购物车表(shoppingcart)
		// 编号、用户编号、物品主键、数量、分类（汽车or配饰）、是否购买
		db.execSQL("create table shoppingcart(_id integer primary key autoincrement,uid integer references userinfo(_id),gid integer,gcount integer,gsource integer,gbuy integer)");

		// 收藏表(编号、用户编号、物品编号、物品名称、物品价格,物品来源,物品图片)
		db.execSQL("create table collect(_id integer primary key autoincrement,uid integer references userinfo(_id),gid integer,gname varchar(100),gprice float,gsource integer,gimage integer)");

		// 订单表（indent）（编号、图片、名称、单价、数量、订单号、用户，状态）
		db.execSQL("create table indent(_id integer primary key autoincrement,iimage int,iname varchar(100),iprice float,icount integer,inumber varchar(100),uid integer references userinfo(_id),dstate integer)");

		// 订单明细表（detail）（编号、订单号、总价、下单日期、状态、用户）
		db.execSQL("create table detail(_id integer primary key autoincrement,inumber varchar(100) references indent(inumber),dallprice float,dtime varchar(100),dstate boolean,uid integer references userinfo(_id))");

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
