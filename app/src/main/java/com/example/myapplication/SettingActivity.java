package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ImageButton back = findViewById(R.id.imgback);//获取返回图标id
        LinearLayout update = findViewById(R.id.linear1);
        LinearLayout set = findViewById(R.id.linear2);
        LinearLayout contact = findViewById(R.id.linear3);

        back.setOnClickListener(new View.OnClickListener() {//设置返回mainactivity点击事件
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity.this,"都说是隐私了就别设置了吧",Toast.LENGTH_SHORT).show();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity.this,"暂无可用更新",Toast.LENGTH_SHORT).show();
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {//点击联系我们跳转到百度网页
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://m.baidu.com/s?word=%E5%B0%8F%E7%8B%AC&ts=5169034&t_kt=0&ie=utf-8&rsv_iqid=3604991547&rsv_t=c9714QWmNnB84LfxUBwfyqPr5br7mS6XMT6pHiGffpdmeXSCEgfu&sa=ib&rsv_pq=3604991547&rsv_sug4=4415&ss=111&inputT=1689&sugid=1844702102464311617&tj=1");
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(uri);
                startActivity(intent);
            }
        });
    }
}