package com.edu.neu.homework1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 修改个人信息的页面
 */
public class InfoActivity extends AppCompatActivity implements View.OnClickListener {

    User findUser3;

    private EditText etUsername;
    private EditText etPassword;
    private EditText etPhone;
    private EditText etAdress;
    private MultiAutoCompleteTextView emailMutiAuto1;
    private Button btSubmit;
    private MyHelper myHelper;

    String username;
    String password;
    String email;
    String phone;
    String address;

    //邮箱自动补全的后缀
    private static final String[] emailSuffix = {
            "@qq.com", "@163.com", "@126.com", "@gmail.com", "@sina.com", "@foxmail.com", "@139.com"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        initView();

        //初始化数据库
        myHelper=new MyHelper(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.item1);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_left_back); //修改actionbar左上角返回按钮的图标



    }

    public void initView(){
        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
        etPhone=findViewById(R.id.etPhone);
        etAdress=findViewById(R.id.etAdress);
        btSubmit=findViewById(R.id.btSubmit);
        emailMutiAuto1=findViewById(R.id.emailMutiAuto1);
        //设置用户名只读
        etUsername.setInputType(InputType.TYPE_NULL);
        etUsername.setTextColor(Color.GRAY);

        //提交按钮设置监听器
        btSubmit.setOnClickListener(this);

        //邮箱输入@自动联想
        MailBoxAssociateTokenizer tokenizer = new MailBoxAssociateTokenizer();
        //定义数据适配器
        ArrayAdapter<String> adapter=new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,emailSuffix);
        //为emailsuffix设置数据适配器
        emailMutiAuto1.setAdapter(adapter);
        emailMutiAuto1.setTokenizer(tokenizer);

        //实现在显示登陆者的所有信息
        Bundle bundle4 = this.getIntent().getExtras();
        findUser3=(User) bundle4.getSerializable("FindUser");
        if(findUser3!=null){
            etUsername.setText(findUser3.getUsername());
            etPassword.setText(findUser3.getPassword());
            etPhone.setText(findUser3.getPhone());
            etAdress.setText(findUser3.getAddress());
            emailMutiAuto1.setText(findUser3.getEmail());


            Log.d("msg",username +" de "+email);

        }else{
            Toast.makeText(this, "没获得到用户", Toast.LENGTH_SHORT).show();
        }

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
           case R.id.btSubmit:
               username=etUsername.getText().toString().trim();
               password=etPassword.getText().toString().trim();
               email=emailMutiAuto1.getText().toString().trim();
               phone=etPhone.getText().toString().trim();
               address=etAdress.getText().toString().trim();
               myHelper.update(username,password,email,phone,address);
               Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
               break;

       }

    }


}