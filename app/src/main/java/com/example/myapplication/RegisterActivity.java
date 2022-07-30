package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {

    private EditText et_username,et_password,et_password2;
    private Button bt1,bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        et_password2 = findViewById(R.id.et_password2);
        bt1 = findViewById(R.id.btn1);
        bt2 = findViewById(R.id.btn2);
        bt1.setOnClickListener(new Registerbutton());
        bt2.setOnClickListener(new Registerbutton());

    }

    public class Registerbutton implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String username = et_username.getText().toString().trim();
            String password = et_password.getText().toString().trim();
            String password2 = et_password2.getText().toString().trim();
            switch (v.getId()){
                case R.id.btn1:
                    if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)||TextUtils.isEmpty(password2))
                        Toast.makeText(RegisterActivity.this,"各项不能为空",Toast.LENGTH_SHORT).show();
                    else if(isExistusername(username)){
                        Toast.makeText(RegisterActivity.this,"用户名已存在！",Toast.LENGTH_SHORT).show();
                    }else if(!TextUtils.equals(password,password2)){
                        Toast.makeText(RegisterActivity.this,"两次输入的密码不一样！",Toast.LENGTH_SHORT).show();
                    }else{
                                saveUserinfo(username,password);
                            Toast.makeText(RegisterActivity.this,"注册成功,请返回登录",Toast.LENGTH_SHORT).show();
                        }
                    break;
                case R.id.btn2:
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    break;
            }
        }

        private void saveUserinfo(String username,String password){//保存账号和密码到SharedPreferences中
            SharedPreferences sp = getSharedPreferences("data",MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(username,password);
            editor.commit();
        }

        private boolean isExistusername(String username){//判断用户名是否已存在
            boolean has_username = false;
            SharedPreferences sp = getSharedPreferences("data",MODE_PRIVATE);
            String spwd = sp.getString(username,"");
            if(!TextUtils.isEmpty((spwd))) {
                has_username=true;
            }
            return has_username;
        }
    }
}