package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {
    private Button btlogin, register;
    private EditText etname;
    private EditText etpassword;
    private ViewFlipper flipper;
    private int[] imagePaths = {R.drawable.view, R.drawable.view2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btlogin = findViewById(R.id.login1);
        register = findViewById(R.id.register);
        etname = findViewById(R.id.username);
        etpassword = findViewById(R.id.password);

        btlogin.setOnClickListener(new MyButton());
        register.setOnClickListener(new MyButton());

        flipper = (ViewFlipper) findViewById(R.id.fli);//获取轮播图片
        for (int i = 0; i < imagePaths.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imagePaths[i]);
            flipper.addView(imageView);
        }
        flipper.startFlipping();
    }

    public class MyButton implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            String name = etname.getText().toString().trim();
            String pwd = etpassword.getText().toString().trim();
            String psw = readPsw(name);
            switch (v.getId()) {
                case R.id.login1://登录按钮事件
                    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
                        Toast.makeText(LoginActivity.this, "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
                    } else if (pwd.equals(psw)) {
                        Intent data = new Intent(LoginActivity.this, MainActivity.class);
                        data.putExtra("name", name);
                        startActivity(data);
                    } else if (psw != null && !TextUtils.isEmpty(psw) && !pwd.equals(psw)) {
                        Toast.makeText(LoginActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "用户名不存在！", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.register://跳转到注册界面
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    break;
            }
        }

        private String readPsw(String name) {//从sharepreferences中通过用户名读取密码
            SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
            return sp.getString(name, "");
        }
    }
}
