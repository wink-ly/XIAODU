package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private ViewPager pager;
    private RadioGroup rg;
    private int[] rb = {R.id.tv1, R.id.tv2, R.id.tv3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initView();//设置事件
        String name=getIntent().getStringExtra("name");
        Toast.makeText(MainActivity.this,"       登录成功\n欢迎"+name+"来到小独",Toast.LENGTH_SHORT).show();
    }

    private  void initView(){
        pager = findViewById(R.id.vp);
        rg = findViewById(R.id.rg);
        findViewById(R.id.tv1).setOnClickListener(this);
        findViewById(R.id.tv2).setOnClickListener(this);
        findViewById(R.id.tv3).setOnClickListener(this);
        findViewById(R.id.time1).setOnClickListener(this);
        findViewById(R.id.img).setOnClickListener(this);

        final ArrayList<Fragment> list = new ArrayList<>();//添加fragment页
        list.add(new AFragment());
        list.add(new BFragment());
        list.add(new CFragment());

        pager.setAdapter(new FragmentPagerAdapter(this.getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }
            @Override
            public int getCount() {
                return list.size();
            }
            public void destroyItem(ViewGroup container, int position, Object object) {//重载该方法，防止其它视图被销毁，防止加载视图卡顿
//                super.destroyItem(container, position, object);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv1://Viewpager的滑动事件
                pager.setCurrentItem(0,true);break;
            case R.id.tv2:
                pager.setCurrentItem(1,true);break;
            case R.id.tv3:
                pager.setCurrentItem(2,true);break;
            case R.id.img://跳转设置界面
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,SettingActivity.class);
                startActivity(intent);break;
            case R.id.time1://跳转日期列表界面
                Intent intent1 = new Intent();
                intent1.setClass(MainActivity.this, RecycleViewActivity.class);
                startActivity(intent1);
            default:break;
        }
    }
    @Override
    public void onPageSelected(int position) {//Viewpager的点击事件,position就是当前滑动到的页面
        rg.check(rb[position]);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
    @Override
    public void onPageScrollStateChanged(int state) {}

}


