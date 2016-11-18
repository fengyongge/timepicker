package com.zzti.fengyongge.timepicker.timeview;//package com.huiyi.ruyishe.timeview;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Context;
//import android.os.Looper;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.huiyi.ruyishe.R;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//
///**
// * 使用说明：1.showLongTerm()是否显示长期选项
// * 2.setTimePickerGone隐藏时间选择
// * 3.接口DateChooseInterface
// * <p/>
// * 用于时间日期的选择
// * Created by liuhongxia on 2016/4/16.
// */
//public class DateChooseWheelViewDialog1 extends Dialog implements View.OnClickListener {
//    //控件
//    private WheelView mYearWheelView;
//    //    private WheelView mine_dataWheelView;
//    private WheelView mDateWheelView;
//
//    private CalendarTextAdapter mDateAdapter;
//    private CalendarTextAdapter mYearAdapter;
//    private CalendarTextAdapter mineAdapter;
//
//    private TextView mTitleTextView;
//    private TextView mSureButton;
//    private Dialog mDialog;
//    private TextView mCloseDialog;
//    private LinearLayout mLongTermLayout;
//
//
//
//    int type = 0;
//    //变量
//    private ArrayList<String> arry_date = new ArrayList<String>();
//    private ArrayList<String> arry_year = new ArrayList<String>();
//
//    private ArrayList<String> mine_data = new ArrayList<String>();
//    private ArrayList<String> mine_templist = new ArrayList<String>();
//
//
//    private ArrayList<String> mine_sunday_templist = new ArrayList<String>();
//    private ArrayList<String> mine_saturday_templist = new ArrayList<String>();
//
//    private int nowDateId = 0;
//    private int nowYearId = 0;
//    private int mineId = 0;
//
//
//    private String mYearStr;
//    private String mDateStr;
//
//    int begingtime;
//
//
//    //常量
//    private final int MAX_TEXT_SIZE = 16;
//    private final int MIN_TEXT_SIZE = 14;
//
//    private Context mContext;
//    private DateChooseInterface dateChooseInterface;
//    private String choicebegingtime;
//    private String choiceendtime;
////    private TextView begintime_anim;
//
//
//    public DateChooseWheelViewDialog1(Context context, int begingtime, DateChooseInterface dateChooseInterface) {
//        super(context);
//        this.mContext = context;
//        this.dateChooseInterface = dateChooseInterface;
//
//        this.begingtime = begingtime;
//        type=begingtime;
//        mDialog = new Dialog(context, R.style.dialog);
//        Window dialogWindow = mDialog.getWindow();
//        dialogWindow.setGravity(Gravity.BOTTOM);
//        initView();
//        initData();
//    }
//
//
//    private void initData() {
//
//        initYear();
//        initDate(begingtime);
//        initListener();
//    }
//
//
//    /**
//     * 初始化滚动监听事件
//     */
//    private void initListener() {
//        //年份*****************************
//        mYearWheelView.addChangingListener(new OnWheelChangedListener() {
//
//            @Override
//            public void onChanged(WheelView wheel, int oldValue, int newValue) {
//                String currentText = (String) mYearAdapter.getItemText(wheel.getCurrentItem());
//                setTextViewStyle(currentText, mYearAdapter);
//                mYearStr = arry_year.get(wheel.getCurrentItem()) + "";
//
//            }
//        });
//
//        mYearWheelView.addScrollingListener(new OnWheelScrollListener() {
//
//            @Override
//            public void onScrollingStarted(WheelView wheel) {
//
//            }
//
//            @Override
//            public void onScrollingFinished(WheelView wheel) {
//                String currentText = (String) mYearAdapter.getItemText(wheel.getCurrentItem());
//                setTextViewStyle(currentText, mYearAdapter);
//
//
//
//            }
//        });
//
//        //日期********************
//        mDateWheelView.addChangingListener(new OnWheelChangedListener() {
//
//
//            @Override
//            public void onChanged(WheelView wheel, int oldValue, int newValue) {
//                String currentText = (String) mDateAdapter.getItemText(wheel.getCurrentItem());
//                setTextViewStyle(currentText, mDateAdapter);
//                mDateStr = arry_date.get(wheel.getCurrentItem());
//
//
//            }
//        });
//
//
//        mDateWheelView.addScrollingListener(new OnWheelScrollListener() {
//            @Override
//            public void onScrollingStarted(WheelView wheel) {
//            }
//
//            @Override
//            public void onScrollingFinished(WheelView wheel) {
//                String currentText = (String) mDateAdapter.getItemText(wheel.getCurrentItem());
//                setTextViewStyle(currentText, mDateAdapter);
//
//            }
//        });}
//
//
//    private void initmineData(int mineIdtype) {
//
//        if (mineIdtype == 2) {//月
//
//            mDateWheelView.setVisibility(View.VISIBLE);
//            mYearWheelView.setVisibility(View.VISIBLE);
//            mine_data.clear();
//            initDate(2);
//        } else if (mineIdtype == 4) {//zdy
//
//            mDateWheelView.setVisibility(View.VISIBLE);
//            mYearWheelView.setVisibility(View.VISIBLE);
//        }
//
//
//    }
//
//
//    /**
//     * 初始化年
//     */
//    private void initYear() {
//        Calendar nowCalendar = Calendar.getInstance();
//        int nowYear = nowCalendar.get(Calendar.YEAR);
//        arry_year.clear();
//        for (int i = 0; i <= 40; i++) {
//            int year = nowYear -10 + i;
//            arry_year.add(year + "年");
//            if (nowYear == year) {
//                nowYearId = arry_year.size() - 1;
//            }
//        }
//        mYearAdapter = new CalendarTextAdapter(mContext, arry_year, nowYearId, MAX_TEXT_SIZE, MIN_TEXT_SIZE);
//        mYearWheelView.setVisibleItems(5);
//        mYearWheelView.setViewAdapter(mYearAdapter);
//        mYearWheelView.setCurrentItem(nowYearId);
//        mYearStr = arry_year.get(nowYearId);
//    }
//
//    private void initView() {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_date_choose, null);
//        mDialog.setContentView(view);
//        mYearWheelView = (WheelView) view.findViewById(R.id.year_wv);
//        mDateWheelView = (WheelView) view.findViewById(R.id.date_wv);
//
//        mTitleTextView = (TextView) view.findViewById(R.id.title_tv);
//        mSureButton = (TextView) view.findViewById(R.id.sure_btn);
//        mCloseDialog = (TextView) view.findViewById(R.id.date_choose_close_btn);
//        mLongTermLayout = (LinearLayout) view.findViewById(R.id.long_term_layout);
//
//
//        mSureButton.setOnClickListener(this);
//        mCloseDialog.setOnClickListener(this);
//    }
//
//    /**
//     * 初始化日期
//     */
//    private void initDate(int type) {
//        if (type == 2) {
//
//            arry_date.clear();
//            arry_date.add("01月");
//            arry_date.add("02月");
//            arry_date.add("03月");
//            arry_date.add("04月");
//            arry_date.add("05月");
//            arry_date.add("06月");
//            arry_date.add("07月");
//            arry_date.add("08月");
//            arry_date.add("09月");
//            arry_date.add("10月");
//            arry_date.add("11月");
//            arry_date.add("12月");
//            nowDateId = 0;
//
//
////            Calendar nowCalendar = Calendar.getInstance();
////            int nowMonth = nowCalendar.get(Calendar.MONTH) + 1;
//
//            mDateAdapter = new CalendarTextAdapter(mContext, arry_date, nowDateId, MAX_TEXT_SIZE, MIN_TEXT_SIZE);
//            mDateWheelView.setVisibleItems(5);
//            mDateWheelView.setViewAdapter(mDateAdapter);
//            mDateWheelView.setCurrentItem(0);
//
//            mDateStr = arry_date.get(nowDateId);
//            setTextViewStyle(mDateStr, mDateAdapter);
//        } else {
//            Calendar nowCalendar = Calendar.getInstance();
//            int nowYear = nowCalendar.get(Calendar.YEAR);
//            arry_date.clear();
//            setDate(nowYear);
//            mDateAdapter = new CalendarTextAdapter(mContext, arry_date, nowDateId, MAX_TEXT_SIZE, MIN_TEXT_SIZE);
//            mDateWheelView.setVisibleItems(5);
//            mDateWheelView.setViewAdapter(mDateAdapter);
//            mDateWheelView.setCurrentItem(nowDateId);
//
//            mDateStr = arry_date.get(nowDateId);
//            setTextViewStyle(mDateStr, mDateAdapter);
//        }
//
//
//    }
//
//    public void setDateDialogTitle(String title) {
//        mTitleTextView.setText(title);
//    }
//
//
//    public void showLongTerm(boolean show) {
//        if (show) {
//            mLongTermLayout.setVisibility(View.VISIBLE);
//        } else {
//            mLongTermLayout.setVisibility(View.GONE);
//        }
//
//    }
//
//
//    /**
//     * 将改年的所有日期写入数组
//     *
//     * @param year
//     */
//    private void setDate(int year) {
//        boolean isRun = isRunNian(year);
//        Calendar nowCalendar = Calendar.getInstance();
//        int nowMonth = nowCalendar.get(Calendar.MONTH) + 1;
//        int nowDay = nowCalendar.get(Calendar.DAY_OF_MONTH);
//        for (int month = 1; month <= 12; month++) {
//            switch (month) {
//                case 1:
//                case 3:
//                case 5:
//                case 7:
//                case 8:
//
//                    for (int day = 1; day <= 31; day++) {
//                        if (day < 10) {
//                            arry_date.add("0" + month + "月0" + day + "日");
//                        } else {
//                            arry_date.add("0" + month + "月" + day + "日");
//                        }
//
//                        if (month == nowMonth && day == nowDay) {
//                            nowDateId = arry_date.size() - 1;
//                        }
//                    }
//
//                    break;
//                case 10:
//                case 12:
//                    for (int day = 1; day <= 31; day++) {
//                        if (day < 10) {
//                            arry_date.add(month + "月0" + day + "日");
//                        } else {
//                            arry_date.add(month + "月" + day + "日");
//                        }
//
//
//                        if (month == nowMonth && day == nowDay) {
//                            nowDateId = arry_date.size() - 1;
//                        }
//                    }
//                    break;
//                case 2:
//                    if (isRun) {
//                        for (int day = 1; day <= 29; day++) {
//                            arry_date.add(month + "月" + day + "日");
//                            if (month == nowMonth && day == nowDay) {
//                                nowDateId = arry_date.size() - 1;
//                            }
//                        }
//                    } else {
//                        for (int day = 1; day <= 28; day++) {
//                            arry_date.add(month + "月" + day + "日");
//                            if (month == nowMonth && day == nowDay) {
//                                nowDateId = arry_date.size() - 1;
//                            }
//                        }
//                    }
//                    break;
//                case 4:
//                case 6:
//                case 9:
//                    for (int day = 1; day <= 30; day++) {
//                        arry_date.add("0" + month + "月" + day + "日");
//                        if (month == nowMonth && day == nowDay) {
//                            nowDateId = arry_date.size() - 1;
//                        }
//                    }
//                    break;
//
//                case 11:
//                    for (int day = 1; day <= 30; day++) {
//                        arry_date.add(month + "月" + day + "日");
//                        if (month == nowMonth && day == nowDay) {
//                            nowDateId = arry_date.size() - 1;
//                        }
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
//    }
//
//    /**
//     * 判断是否是闰年
//     *
//     * @param year
//     * @return
//     */
//    private boolean isRunNian(int year) {
//        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    /**
//     * 设置文字的大小
//     *
//     * @param curriteItemText
//     * @param adapter
//     */
//    public void setTextViewStyle(String curriteItemText, CalendarTextAdapter adapter) {
//        ArrayList<View> arrayList = adapter.getTestViews();
//        int size = arrayList.size();
//        String currentText;
//        for (int i = 0; i < size; i++) {
//            TextView textvew = (TextView) arrayList.get(i);
//            currentText = textvew.getText().toString();
//            if (curriteItemText.equals(currentText)) {
//                textvew.setTextSize(MAX_TEXT_SIZE);
//                textvew.setTextColor(mContext.getResources().getColor(R.color.text_10));
//            } else {
//                textvew.setTextSize(MIN_TEXT_SIZE);
//                textvew.setTextColor(mContext.getResources().getColor(R.color.text_11));
//            }
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.sure_btn://确定选择按钮监听
//
//
//                if (type == 0) {
//
//                } else if (type == 1) {
//
//                } else if (type == 2) {
//                    String remarktem=mYearStr+mDateStr;
//                    if (mDateStr.equals("2月")) {
//
//
//                        mYearStr = mYearStr.replace("年", "");
//                        mDateStr = mDateStr.replace("月", "");
//                        int mDateStrint = Integer.parseInt(mDateStr);
//                        if (isRunNian(mDateStrint)) {
//                            choicebegingtime = mDateStrint + "-02-" + "01";
//                            choiceendtime = mDateStrint + "-02-" + "29";
//                        } else {
//                            choicebegingtime = mDateStrint + "-02-" + "01";
//                            choiceendtime = mDateStrint + "-02-" + "28";
//
//                        }
//
//
//                    } else if (mDateStr.equals("01月") || mDateStr.equals("03月") || mDateStr.equals("05月") || mDateStr.equals("07月") || mDateStr.equals("08月") || mDateStr.equals("10月") || mDateStr.equals("12月")) {
//                        mYearStr = mYearStr.replace("年", "");
//                        mDateStr = mDateStr.replace("月", "");
//
//                        choicebegingtime = mYearStr + "-" + mDateStr + "-" + "01";
//                        choiceendtime = mYearStr + "-" + mDateStr + "-" + "31";
//
//                    } else {
//
//                        mYearStr = mYearStr.replace("年", "");
//                        mDateStr = mDateStr.replace("月", "");
//
//                        choicebegingtime = mYearStr + "-" + mDateStr + "-" + "01";
//                        choiceendtime = mYearStr + "-" + mDateStr + "-" + "30";
//
//
//                    }
//                    dateChooseInterface.getDateTime(2, choicebegingtime, choiceendtime,remarktem);
//                    dismissDialog();
//
//                }  else if (type == 4) {
//
//
//                    dateChooseInterface.getDateTime(4, strTimeToDateFormat(mYearStr, mDateStr), strTimeToDateFormat(mYearStr, mDateStr),"0");
//                    dismissDialog();
//
//                }
//
//
//                break;
//            case R.id.date_choose_close_btn://关闭日期选择对话框
//                dismissDialog();
//                break;
//            default:
//                break;
//        }
//    }
//
//    /**
//     * 对话框消失
//     */
//    private void dismissDialog() {
//
//        if (Looper.myLooper() != Looper.getMainLooper()) {
//
//            return;
//        }
//
//        if (null == mDialog || !mDialog.isShowing() || null == mContext
//                || ((Activity) mContext).isFinishing()) {
//
//            return;
//        }
//
//        mDialog.dismiss();
//        this.dismiss();
//    }
//
//    /**
//     * 显示日期选择dialog
//     */
//    public void showDateChooseDialog() {
//
//        if (Looper.myLooper() != Looper.getMainLooper()) {
//
//            return;
//        }
//
//        if (null == mContext || ((Activity) mContext).isFinishing()) {
//
//            // 界面已被销毁
//            return;
//        }
//
//        if (null != mDialog) {
//
//            mDialog.show();
//            return;
//        }
//
//        if (null == mDialog) {
//
//            return;
//        }
//
//        mDialog.setCanceledOnTouchOutside(true);
//        mDialog.show();
//    }
//
//
//    private String strTimeToDateFormat(String yearStr, String dateStr) {
//
//        return yearStr.replace("年", "-") + dateStr.replace("月", "-").replace("日", "");
//    }
//
//
//
//    /**
//     * 滚轮的adapter
//     */
//    private class CalendarTextAdapter extends AbstractWheelTextAdapter {
//        ArrayList<String> list;
//
//        protected CalendarTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
//            super(context, R.layout.item_birth_year, R.id.tempValue, currentItem, maxsize, minsize);
//            this.list = list;
//        }
//
//        @Override
//        public View getItem(int index, View cachedView, ViewGroup parent) {
//            View view = super.getItem(index, cachedView, parent);
//            return view;
//        }
//
//        @Override
//        public int getItemsCount() {
//            return list.size();
//        }
//
//        @Override
//        protected CharSequence getItemText(int index) {
//            String str = list.get(index) + "";
//            return str;
//        }
//    }
//
//    /**
//     * 回调选中的时间（默认时间格式"yyyy-MM-dd HH:mm:ss"）
//     */
//    public interface DateChooseInterface {
//        void getDateTime(int type, String begintime, String endtime,String remark);
//    }
//
//
//
//
//
//}
