package com.example.own2;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity implements View.OnClickListener{

    @BindView(R.id.btn_1) Button btn_1;
    @BindView(R.id.btn_2) Button btn_2;
    //@BindView(R.id.btn_3) Button btn_3;
    @BindView(R.id.btn_4) Button btn_4;
    @BindView(R.id.btn_5) Button btn_5;
    //@BindView(R.id.btn_6) Button btn_6;
    @BindView(R.id.btn_7) Button btn_7;
    @BindView(R.id.btn_8) Button btn_8;

    @OnClick(R2.id.btn_6)
    public void showToast(){
        Toast.makeText(this,"使用ButterKnife监听",Toast.LENGTH_SHORT).show();
    }
    @OnClick(R2.id.btn_7)
    public void Intent_Configuration(){
        Intent intent = new Intent(MainActivity.this,System_Configuration.class);
        startActivity(intent);
    }
    @OnClick(R2.id.btn_8)
    public void hander_emulator(){
        Intent intent = new Intent(MainActivity.this,Hander_emulator.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定初始化ButterKnife
        ButterKnife.bind(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"使用内部匿名类形式监听",Toast.LENGTH_SHORT).show();
            }
        });
        MyButtonListener_inside listener_inside = new MyButtonListener_inside();

        btn_4.setOnClickListener(listener_inside);
        btn_5.setOnClickListener(new MyButtonListener_outside(){
            @Override
            public void onClick(View v)
            {
                super.onClick(v);
            }
        });
    }
    public void clickButton (View view){
        Toast.makeText(MainActivity.this,"使用Tag监听",Toast.LENGTH_SHORT).show();
    }
    public void onClick(View v){
        Toast.makeText(MainActivity.this,"使用Activity本身监听",Toast.LENGTH_SHORT).show();
    }
    class MyButtonListener_inside implements View.OnClickListener{
        @Override
        public void onClick(View v){
            Toast.makeText(MainActivity.this,"使用内部类形式监听",Toast.LENGTH_SHORT).show();
        }
    }
}

class  MyButtonListener_outside implements  View.OnClickListener{
    @Override
    public void onClick(View v){
        Toast.makeText(v.getContext(),"使用外部类形式监听",Toast.LENGTH_SHORT).show();
    }
}

