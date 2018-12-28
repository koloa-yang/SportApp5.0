package com.example.lenovo.app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import MyRetrofit.LoginRetrofit;
import PersonalHelper.personalHelper;
import PersonalHelper.personalProvider;

/**
 * Created by koloa on 18/12/23
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login_bt;
    private TextView tosign;
    private Tencent mTencent;
    private String password;
    private TextView toQQ;//腾讯QQ登录
    private BaseUiListener qqCallback;//腾讯QQ登录监听事件
    private UserInfo userInfo;
    private personalHelper personalhelper;
    private personalProvider personalprovider;
    private SQLiteDatabase db;
    private String name;
    private  String level;
    private Image user_head;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        password="123456";
        qqCallback = new BaseUiListener();
        mTencent = Tencent.createInstance("1107998741", this.getApplicationContext());//初始化QQ
        login_bt = (Button) findViewById(R.id.login);
        tosign = (TextView) findViewById(R.id.toSignin);
        toQQ = (TextView) findViewById(R.id.toQQ);
        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.password);
                String password=editText.getText().toString();
                if(password.equals(password)) {
                    LoginRetrofit loginRetrofit = new LoginRetrofit(password, password);
                    //finish后加载个人信息
                    finish();
                    //从数据库中加载个人姓名
                    loadinguser();
                }
                else{
                    LoginRetrofit loginRetrofit = new LoginRetrofit(password, password);
                    AlertDialog alertDialog1 = new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Error")//标题
                            .setMessage("密码错误")//内容
                            .setIcon(R.drawable.duang)//图标
                            .create();
                    alertDialog1.show();
                }

            }
        });
        tosign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity2.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        toQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTencent.login(LoginActivity.this, "all", qqCallback);
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
    }

    private  void loadinguser(){}

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object o) {

        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 官方文档没有写但是很重要的回调函数
        mTencent.onActivityResultData(requestCode, resultCode, data, qqCallback);
        mTencent.handleResultData(data, qqCallback);
    }

    /**
     * 获取QQ用户信息
     *
     * @param openid
     */
    public void getQQUserInfo(final String openid) {
        userInfo = new UserInfo(LoginActivity.this, mTencent.getQQToken());
        userInfo.getUserInfo(new IUiListener() {
            @Override
            public void onComplete(Object o) {
            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        });

    }


    private void loadingUser(){
        personalhelper=new personalHelper(this);
        db=personalhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(null, null);
        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex("name"));
            level = cursor.getString(cursor.getColumnIndex("level"));
        }

    }

}
