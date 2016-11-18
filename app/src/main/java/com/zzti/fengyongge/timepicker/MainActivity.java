package com.zzti.fengyongge.timepicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zzti.fengyongge.timepicker.timeview.DateChooseWheelViewDialog;


public class MainActivity extends AppCompatActivity {


    private TextView tv_year_month_day,tv_year_month;
    private boolean is_detail_date=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv_year_month_day = (TextView) findViewById(R.id.tv_year_month_day);
        tv_year_month = (TextView) findViewById(R.id.tv_year_month);

        tv_year_month_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(is_detail_date) {
                    DateChooseWheelViewDialog endDateChooseDialog = new DateChooseWheelViewDialog(MainActivity.this, true,
                            new DateChooseWheelViewDialog.DateChooseInterface() {


                                @Override
                                public void getDateTime(String time) {

                                    tv_year_month_day.setText(TimeUtils.convertTime(time));

                                }


                            });
                    endDateChooseDialog.setDateDialogTitle("时间选择");
                    endDateChooseDialog.showDateChooseDialog();
                }else{

                    DateChooseWheelViewDialog endDateChooseDialog = new DateChooseWheelViewDialog(MainActivity.this, false,
                            new DateChooseWheelViewDialog.DateChooseInterface() {


                                @Override
                                public void getDateTime(String time) {

                                    tv_year_month_day.setText(TimeUtils.convertTime1(time));

                                }
                            });
                    endDateChooseDialog.setDateDialogTitle("时间选择");
                    endDateChooseDialog.showDateChooseDialog();
                }
            }
        });


        tv_year_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DateChooseWheelViewDialog endDateChooseDialog = new DateChooseWheelViewDialog(MainActivity.this, false,
                        new DateChooseWheelViewDialog.DateChooseInterface() {


                            @Override
                            public void getDateTime(String time) {

                                tv_year_month.setText(TimeUtils.convertTime1(time));

                            }
                        });
                endDateChooseDialog.setDateDialogTitle("时间选择");
                endDateChooseDialog.showDateChooseDialog();
            }
        });
    }


}
