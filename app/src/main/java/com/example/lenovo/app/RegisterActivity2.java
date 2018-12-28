package com.example.lenovo.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import MyRetrofit.RegisterRetrofit;

public class RegisterActivity2 extends AppCompatActivity {

    private Button register_bt;
    private TextView tologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register_bt = (Button) findViewById(R.id.signin);
        tologin = (TextView) findViewById(R.id.toLogin);
        register_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.passwordSetting);
                String password=editText.getText().toString();
                EditText editText2 = (EditText) findViewById(R.id.passwordConfirm);
                String confirm=editText2.getText().toString();
                if(password.equals(confirm)) {
                    RegisterRetrofit loginRetrofit = new RegisterRetrofit("123456", "123456");
                    finish();
                }
                else{
                    RegisterRetrofit RegisterRetrofit = new RegisterRetrofit("123456", password);
                    AlertDialog alertDialog1 = new AlertDialog.Builder(RegisterActivity2.this)
                            .setTitle("Error")//标题
                            .setMessage("密码不一致")//内容
                            .setIcon(R.drawable.duang)//图标
                            .create();
                    alertDialog1.show();
                }
            }
        });
        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}
