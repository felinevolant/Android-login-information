package com.edu.neu.homework1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 此类 implements View.OnClickListener 之后，
 * 就可以把onClick事件写到onCreate()方法之外
 * 这样，onCreate()方法中的代码就不会显得很冗余
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    //邮箱自动补全的后缀
    private static final String[] emailSuffix = {
            "@qq.com", "@163.com", "@126.com", "@gmail.com", "@sina.com", "@foxmail.com", "@139.com"};
    //电话号码正则判断
    public final static String PHONE_PATTERN = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    //组件们
    private MultiAutoCompleteTextView emailMutiAuto;
    private EditText editText3;
    private EditText editText4;
    private EditText editText9;
    private EditText editText6;
    private EditText editText7;
    private Button button2;
    private MyHelper myHelper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        myHelper=new MyHelper(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_left_back); //修改actionbar左上角返回按钮的图标

        //为register设置监听器
        button2.setOnClickListener(this);
    }

    //activity类中的方法，实现返回键
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button2:
                //获取输入的信息
                String username= editText3.getText().toString().trim();
                String password = editText4.getText().toString().trim();
                String password1 = editText9.getText().toString().trim();
                String email = emailMutiAuto.getText().toString().trim();
                String phone=editText6.getText().toString().trim();
                String address=editText7.getText().toString().trim();

                if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(password1)){
                        if(password.equals(password1)){

                            //电话正则验证，应该放在提交按钮里面
                            Boolean isPhone=isPhoneMatchered(PHONE_PATTERN,phone);
                            if(isPhone){
                                long rowid1=myHelper.add(username,password,email,phone,address);
                                if(rowid1!=-1){
                                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                                    Intent intent2 = new Intent(this, MainActivity.class);
                                    startActivity(intent2);
                                }else{
                                    Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(this, "请输入正确的11位号码", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(this, "请重新确认密码", Toast.LENGTH_SHORT).show();
                        }
                }else{
                    Toast.makeText(this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                }
                break;

        }


    }

    public void initView(){
        //初始化控件，实例化对象
        emailMutiAuto=findViewById(R.id.emailMutiAuto);
        editText3=findViewById(R.id.editText3);
        editText4=findViewById(R.id.editText4);
        editText9=findViewById(R.id.editText9);
        editText6=findViewById(R.id.editText6);
        editText7=findViewById(R.id.editText7);
        button2=findViewById(R.id.button2);


        //邮箱输入@自动联想
        MailBoxAssociateTokenizer tokenizer = new MailBoxAssociateTokenizer();
        //定义数据适配器
        ArrayAdapter<String> adapter=new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,emailSuffix);
        //为emailsuffix设置数据适配器
        emailMutiAuto.setAdapter(adapter);
        emailMutiAuto.setTokenizer(tokenizer);







    }


    /**
     * 正则表达式匹配判断
     * @param patternStr 匹配规则
     * @param input 需要做匹配操作的字符串
     * @return true if matched, else false
     */
    public static boolean isPhoneMatchered(String patternStr, CharSequence input) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

}