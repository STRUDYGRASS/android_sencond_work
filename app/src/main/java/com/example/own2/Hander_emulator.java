package com.example.own2;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

public class Hander_emulator extends Activity {

    Button download;
    final int PROGRESS_DIALOG = 0x153;
    // 该程序模拟填充长度为100的数组
    private int[] data = new int[100];
    int hasData = 0;
    int progressStatus = 0;
    ProgressDialog pd;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hander_emulator);
        download = findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progressStatus == 0) {
                    showDialog(PROGRESS_DIALOG);
                }
                else{
                    hasData = 0;
                    progressStatus = 0;
                    showDialog(PROGRESS_DIALOG);
                }
            }
        });
    }

    @Override
    public Dialog onCreateDialog(int id, Bundle status) {
        System.out.println("----creat----");
        switch (id) {
            case PROGRESS_DIALOG:
                pd = new ProgressDialog(this);
                pd.setMax(100);
                // 设置对话框的标题
                pd.setTitle("Waiting...");
                // 设置对话框 显示的内容
                pd.setMessage("Loading...");
                // 设置对话框不能用“取消”按钮关闭
                pd.setCancelable(false);
                // 设置对话框的进度条风格
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                // 设置对话框的进度条是否显示进度
                pd.setIndeterminate(false);
                break;
        }
        return pd;
    }

    @Override
    public void onPrepareDialog(int id, Dialog dialog) {
        System.out.println("----prepare----");
        super.onPrepareDialog(id, dialog);
        switch (id) {
            case PROGRESS_DIALOG:
                pd.incrementProgressBy(-pd.getProgress());
                new Thread() {
                    public void run() {
                        while (progressStatus < 100) {
                            // 获取耗时操作的完成百分比
                            progressStatus = doWork();
                            // 发送消息到Handler，请补全代码
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    pd.incrementProgressBy(progressStatus);
                                }
                            });
                        }
                        // 如果任务已经完成
                        if (progressStatus >= 100) {
                            // 关闭对话框
                            pd.dismiss();
                        }
                    }
                }.start();
                break;
        }
    }

    // 模拟一个耗时的操作。
    public int doWork() {
        // 为数组元素赋值
        data[hasData++] = (int) (Math.random() * 100);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hasData;
    }
}

