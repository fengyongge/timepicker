package com.zzti.fengyongge.timepicker.timeview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.zzti.fengyongge.timepicker.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 使用说明：1.showLongTerm()是否显示长期选项
 * 2.setTimePickerGone隐藏时间选择
 * 3.接口DateChooseInterface
 * <p/>
 * 用于时间日期的选择
 * Created by liuhongxia on 2016/4/16.
 */
public class DateChooseWheelViewDialog extends Dialog implements View.OnClickListener {
    //控件
    private WheelView mYearWheelView;
    private WheelView mMouthWheelView;
    private WheelView mDateWheelView;

    private CalendarTextAdapter mDateAdapter;
    private CalendarTextAdapter mYearAdapter;
    private CalendarTextAdapter mMouthAdapter;
    private int selectyear;
    private TextView mTitleTextView;
    private TextView mSureButton;
    private Dialog mDialog;
    private TextView mCloseDialog;



    //变量
    private ArrayList<String> arry_mouth = new ArrayList<String>();
    private ArrayList<String> arry_year = new ArrayList<String>();

    private ArrayList<String> arry_date = new ArrayList<String>();


    private int nowmouthId = 0;
    private int nowYearId = 0;
    private int nowDateId = 0;


    private String mYearStr;
    private String mMouthStr;
    private String mDateStr;


    //常量
    private final int MAX_TEXT_SIZE = 16;
    private final int MIN_TEXT_SIZE = 14;

    private Context mContext;
    private DateChooseInterface dateChooseInterface;
    private final int nowYear;
    private final Calendar nowCalendar;
    private final int nowMonth;
    private final int nowDay;
    private int selectmouth;
    boolean isthird;

    //isthird = true 是年月日

    public DateChooseWheelViewDialog(Context context, boolean isthird, DateChooseInterface dateChooseInterface) {
        super(context);
        this.mContext = context;
        this.dateChooseInterface = dateChooseInterface;
        this.isthird = isthird;


//        mDialog = new Dialog(context, R.style.dialog);
        mDialog = new Dialog(context, R.style.ActionSheet);
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);

        nowCalendar = Calendar.getInstance();
        nowYear = nowCalendar.get(Calendar.YEAR);
        nowMonth = nowCalendar.get(Calendar.MONTH) + 1;
        nowDay = nowCalendar.get(Calendar.DAY_OF_MONTH);
        initView();
        initYear();

        initListener();
    }


    /**
     * 初始化滚动监听事件
     */
    private void initListener() {
        //年份*****************************
        mYearWheelView.addChangingListener(new OnWheelChangedListener() {


            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) mYearAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, mYearAdapter);
                mYearStr = arry_year.get(wheel.getCurrentItem()) + "年";
                mYearStr = mYearStr.replace("年", "");
                selectyear = Integer.parseInt(mYearStr);

                initMouth();
            }
        });

        mYearWheelView.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) mYearAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, mYearAdapter);


            }
        });

        //日期********************
        mDateWheelView.addChangingListener(new OnWheelChangedListener() {


            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) mDateAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, mDateAdapter);


            }
        });

        mDateWheelView.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) mDateAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, mDateAdapter);
                mDateStr = arry_date.get(wheel.getCurrentItem());
                mDateStr = mDateStr.replace("日", "");
            }
        });


        //自定义时间
        mMouthWheelView.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) mMouthAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, mMouthAdapter);

                mMouthStr = arry_mouth.get(wheel.getCurrentItem());
                mMouthStr = mMouthStr.replace("月", "");
                selectmouth = Integer.parseInt(mMouthStr);

                setDate(selectyear,selectmouth);
            }
        });

        mMouthWheelView.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {


            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) mMouthAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, mMouthAdapter);


            }
        });

    }


    /**
     * 初始化年
     */
    private void initYear() {

        arry_year.clear();
        for (int i = 0; i <= 20; i++) {
            int year = nowYear - 10 + i;
            arry_year.add(year + "年");
            if (nowYear == year) {
                nowYearId = arry_year.size() - 1;
            }
        }
        mYearAdapter = new CalendarTextAdapter(mContext, arry_year, nowYearId, MAX_TEXT_SIZE, MIN_TEXT_SIZE);
        mYearWheelView.setVisibleItems(5);
        mYearWheelView.setViewAdapter(mYearAdapter);
        mYearWheelView.setCurrentItem(nowYearId);
        mYearStr = arry_year.get(nowYearId);

        initMouth();

    }

    private void initView() {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_date_choose, null);
//        mDialog.setContentView(view);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_date_choose, null);
        final int cFullFillWidth = 10000;
        view.setMinimumWidth(cFullFillWidth);
        mDialog.setContentView(view);


        mYearWheelView = (WheelView) view.findViewById(R.id.year_wv);
        mDateWheelView = (WheelView) view.findViewById(R.id.date_wv);
        mMouthWheelView = (WheelView) view.findViewById(R.id.mouth_wv);
        if (isthird){
            mDateWheelView.setVisibility(View.VISIBLE
            );
        }else{
            mDateWheelView.setVisibility(View.GONE
            );


        }


        mTitleTextView = (TextView) view.findViewById(R.id.title_tv);
        mSureButton = (TextView) view.findViewById(R.id.sure_btn);
        mCloseDialog = (TextView) view.findViewById(R.id.date_choose_close_btn);

        mSureButton.setOnClickListener(this);
        mCloseDialog.setOnClickListener(this);
    }

    /**
     * 初始化日期
     */
    private void initMouth() {

//
        arry_mouth.clear();
        arry_mouth.add("01月");
        arry_mouth.add("02月");
        arry_mouth.add("03月");
        arry_mouth.add("04月");
        arry_mouth.add("05月");
        arry_mouth.add("06月");
        arry_mouth.add("07月");
        arry_mouth.add("08月");
        arry_mouth.add("09月");
        arry_mouth.add("10月");
        arry_mouth.add("11月");
        arry_mouth.add("12月");

        mMouthAdapter = new CalendarTextAdapter(mContext, arry_mouth, nowmouthId, MAX_TEXT_SIZE, MIN_TEXT_SIZE);
        mMouthWheelView.setVisibleItems(5);
        mMouthWheelView.setViewAdapter(mMouthAdapter);
        mMouthWheelView.setCurrentItem(nowMonth-1);

        mMouthStr = arry_mouth.get(nowMonth-1);
        setTextViewStyle(mMouthStr, mMouthAdapter);
        setDate(selectyear,selectmouth);

    }

    public void setDateDialogTitle(String title) {
        mTitleTextView.setText(title);
    }



    private void setDate(int year,int mouth) {
        boolean isRun = isRunNian(year);

        arry_date.clear();
        if (mouth==1||mouth==3||mouth==5||mouth==7||mouth==8||mouth==10||mouth==12){

            for (int day = 1; day <= 31; day++) {
                if (day < 10) {
                    arry_date.add(day + "日");
                } else {
                    arry_date.add(day + "日");
                }}

        }else if(mouth==4||mouth==6||mouth==9||mouth==11) {
            for (int day = 1; day <= 30; day++) {
                if (day < 10) {
                    arry_date.add(day + "日");
                } else {
                    arry_date.add(day + "日");
                }
            }
        }else{

            if (isRun) {
                for (int day = 1; day <= 29; day++) {
                    arry_date.add(day + "日");

                }}else{

                for (int day = 1; day <= 28; day++) {
                    arry_date.add(day + "日");

                }

            }


        }


        nowDateId = nowDay;
        mDateAdapter = new CalendarTextAdapter(mContext, arry_date, nowDay, MAX_TEXT_SIZE, MIN_TEXT_SIZE);
        mDateWheelView.setVisibleItems(5);
        mDateWheelView.setViewAdapter(mDateAdapter);
        mDateWheelView.setCurrentItem(nowDay-1);
        mDateStr = arry_date.get(nowDay-1);
        setTextViewStyle(mDateStr, mDateAdapter);

    }










    /**
     * 判断是否是闰年
     *
     * @param year
     * @return
     */
    private boolean isRunNian(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 设置文字的大小
     *
     * @param curriteItemText
     * @param adapter
     */
    public void setTextViewStyle(String curriteItemText, CalendarTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(MAX_TEXT_SIZE);
                textvew.setTextColor(mContext.getResources().getColor(R.color.text_10));
            } else {
                textvew.setTextSize(MIN_TEXT_SIZE);
                textvew.setTextColor(mContext.getResources().getColor(R.color.text_11));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sure_btn://确定选择按钮监听

                mYearStr = mYearStr.replace("年", "");
                mMouthStr = mMouthStr.replace("月", "");
                mDateStr= mDateStr.replace("日", "");

                Log.i("fyg","mYearStr:"+mYearStr+"mDateStr:"+mDateStr+"mMouthStr:"+mMouthStr);

                String year_month_day =  mYearStr + "-" + mMouthStr + "-" + mDateStr;

                dateChooseInterface.getDateTime( year_month_day);
                dismissDialog();
                break;
            case R.id.date_choose_close_btn://关闭日期选择对话框
                dismissDialog();
                break;
            default:
                break;
        }
    }

    /**
     * 对话框消失
     */
    private void dismissDialog() {

        if (Looper.myLooper() != Looper.getMainLooper()) {

            return;
        }

        if (null == mDialog || !mDialog.isShowing() || null == mContext
                || ((Activity) mContext).isFinishing()) {

            return;
        }

        mDialog.dismiss();
        this.dismiss();
    }

    /**
     * 显示日期选择dialog
     */
    public void showDateChooseDialog() {

        if (Looper.myLooper() != Looper.getMainLooper()) {

            return;
        }

        if (null == mContext || ((Activity) mContext).isFinishing()) {

            // 界面已被销毁
            return;
        }

        if (null != mDialog) {

            mDialog.show();
            return;
        }

        if (null == mDialog) {

            return;
        }

        mDialog.setCanceledOnTouchOutside(true);
        mDialog.show();
    }

//    /**
//     * xx年xx月xx日xx时xx分转成yyyy-MM-dd HH:mm
//     *
//     * @param yearStr
//     * @param dateStr
//     * @param hourStr
//     * @param minuteStr
//     * @return
//     */
//    private String strTimeToDateFormat(String yearStr, String dateStr, String hourStr, String minuteStr) {
//
//        return yearStr.replace("年", "-") + dateStr.replace("月", "-").replace("日", " ")
//                + hourStr + ":" + minuteStr;
//    }
//
//    private String strTimeToDateFormat(String yearStr, String dateStr) {
//
//        return yearStr.replace("年", "-") + dateStr.replace("月", "-").replace("日", "");
//    }
//
//    private String strTimeToDateFormat(String yearStr) {
//
//        return yearStr.toString();
//    }
//    private String strTimeToDateFormat(String yearStr, String dateStr, String hourStr) {
//
//        return yearStr.toString();
//    }



    /**
     * 滚轮的adapter
     */
    private class CalendarTextAdapter extends AbstractWheelTextAdapter {
        ArrayList<String> list;

        protected CalendarTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
            super(context, R.layout.item_birth_year, R.id.tempValue, currentItem, maxsize, minsize);
            this.list = list;
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            String str = list.get(index) + "";
            return str;
        }
    }

    /**
     * 回调选中的时间（默认时间格式"yyyy-MM-dd HH:mm:ss"）
     */
    public interface DateChooseInterface {
        void getDateTime(String time);
    }


}
