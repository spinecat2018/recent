
//把 字面时间 转为 毫秒数
/*比如查询时输入年月日范围，得到毫秒数范围，用于数据库检索逻辑
主页绘制范围：7日
按时间顺序显示各种颜色块
颜色块对应数据库的记录
点击颜色块进入对记录的修改


当日日期的获得：1 由当前毫秒数通过模板转为年月日（Date）
2由年月日得到当天的毫秒数范围（getTime()）
3进而计算得到近七天的毫秒数范围

每日的绘制过程：先按日期检索数据表，得到cursor，依次取出每条记录的
毫秒数、颜色码和备注，把数据组合为｛毫秒数；颜色码；备注｝并存入list，
每日时间为一整行，以12：00为界分为前后两段
依数组元素绘制颜色块，指定背景色为元素颜色，指定id为毫秒数
再重复处理下一日

布局绘制过程：
	布局单元：a.xml
						<LinearLayout 
							android:layout_width="0sp"
							android:layout_height="wrap_content"
							android:orientation="vertical"
							android:layout_weight="1.0">   
								<TextView
									android:layout_width="10sp"
									android:layout_height="wrap_content"
									android:layout_gravity="center"
									android:background="#ff0000"/>
						</LinearLayout>
	创建a对应的活动类 定义点击事件：以id为moment值查找数据并打开修改页面

	创建布局单元类 A inflate a.xml 可动态加载指定个数布局单元
		插入布局单元，改id
		重复插入
	
*/
SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
try {
    long time = dateformat.parse("2016-09-02 23:02:17").getTime();
    System.out.println(time);
} catch (ParseException e) {
    e.printStackTrace();
｝