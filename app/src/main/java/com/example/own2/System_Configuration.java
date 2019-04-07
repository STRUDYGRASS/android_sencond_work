package com.example.own2;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class System_Configuration extends Activity {

    @BindView(R.id.text_orientation) EditText text_orientation;
    @BindView(R.id.text_navigation) EditText text_navigation;
    @BindView(R.id.text_touch) EditText text_touch;
    @BindView(R.id.text_mnc) EditText text_mnc;
    @BindView(R.id.btn_phone) Button btn_phone;

    @OnClick(R2.id.btn_phone)
    public void get_Info(){
        Configuration cfg = new Configuration();
        String screen = cfg.orientation ==Configuration.ORIENTATION_LANDSCAPE ? "横向屏幕" : "竖向屏幕";
        String mncCode = cfg.mnc + ""; //?
        String naviName = cfg.orientation == Configuration.NAVIGATION_NONAV ? "没有方向控制" : cfg.orientation == Configuration.NAVIGATION_WHEEL ? "滚轮控制方向" : cfg.orientation == Configuration.NAVIGATION_DPAD ? "方向键控制方向" :"轨迹球控制方向";
        String touchName = cfg.touchscreen == Configuration.TOUCHSCREEN_NOTOUCH ? "无触摸屏": cfg.touchscreen == Configuration.TOUCHSCREEN_STYLUS ? "触摸笔式触摸屏": "接受手指的触摸屏";

        text_orientation.setText(screen);
        text_mnc.setText(mncCode);
        text_navigation.setText(naviName);
        text_touch.setText(touchName);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system__configuration);
        //绑定初始化ButterKnife
        ButterKnife.bind(this);
    }
}
