package com.edu.neu.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * 此类 implements View.OnClickListener 之后，
 * 就可以把onClick事件写到onCreate()方法之外
 * 这样，onCreate()方法中的代码就不会显得很冗余
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 声明自己写的 MyHelper 对象
     * MyHelper(extends SQLiteOpenHelper) 主要用来
     * 创建数据表
     * 然后再进行数据表的增、删、改、查操作
     */
    private MyHelper myHelper;
    private EditText editText1;
    private EditText editText2;
    private EditText editText8;
    private Button button1;
    private TextView textView1;
    private ImageView imageView;
    private String realCode;
    //创建一个对象用来传递
    User findUser ;


    /**
     * 创建 Activity 时先来重写 onCreate() 方法
     * 保存实例状态
     * super.onCreate(savedInstanceState);
     * 设置视图内容的配置文件
     * setContentView(R.layout.activity_login);
     * 上面这行代码真正实现了把视图层 View 也就是 layout 的内容放到 Activity 中进行显示
     * 初始化视图中的控件对象 initView()
     * 实例化 DBOpenHelper，待会进行登录验证的时候要用来进行数据查询
     * myHelper = new MypenHelper(this);
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        myHelper = new MyHelper(this);

        initView();

        //将验证码用图片的形式显示出来
        imageView.setImageBitmap(VerCode.getInstance().createBitmap());
        realCode=VerCode.getInstance().getCode().toLowerCase();
    }

    /**
     * onCreae()中大的布局已经摆放好了，接下来就该把layout里的东西
     * 声明、实例化对象然后有行为的赋予其行为
     * 这样就可以把视图层View也就是layout 与 控制层 Java 结合起来了
     */
    private void initView(){
        //初始化控件
        editText1=findViewById(R.id.editText1);
        editText2=findViewById(R.id.editText2);
        editText8=findViewById(R.id.editText8);
        button1=findViewById(R.id.button1);
        textView1=findViewById(R.id.textView1);
        imageView=findViewById(R.id.verCodeImage);

        //设置事件监听器
        button1.setOnClickListener(this);
        textView1.setOnClickListener(this);
        imageView.setOnClickListener(this);//可以刷新验证码图片
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textView1:
                //跳转到注册页面
                startActivity(new Intent(this,RegisterActivity.class));
                //把finish注释掉就能实现点击返回键回到登陆页面
                //finish();
                break;

            case R.id.verCodeImage:    //改变随机验证码的生成
                imageView.setImageBitmap(VerCode.getInstance().createBitmap());
                realCode = VerCode.getInstance().getCode().toLowerCase();
                break;

            case R.id.button1:
                //点击登陆按钮,进行登陆验证

                //从EditText的对象上获取文本编辑框输入的数据，并把左右两边的空格去掉
                String username = editText1.getText().toString().trim();
                String password = editText2.getText().toString().trim();
                String verCode = editText8.getText().toString().toLowerCase();



                //进行匹配验证，,先判断一下用户名密码是否为空，
                if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) &&!TextUtils.isEmpty(verCode)){
                    ArrayList<User> data = myHelper.getAllData();
                    boolean match = false;
                    //再进而for循环判断是否与数据库中的数据相匹配
                    for(int i=0;i<data.size();i++){
                        User user = data.get(i);
                        if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                            // 一旦匹配，立即将match = true
                            match=true;
                            //把找到的对象进行引用
                            findUser=user;
                            break;
                        }else{
                            //否则 一直匹配到结束 match = false；
                            match=false;
                        }
                    }
                    //登录成功之后，进行页面跳转
                    if(verCode.equals(realCode)){
                        //用户名和密码匹配
                        if(match){

                            // bundle
                            Bundle bundle1 = new Bundle();
                            bundle1.putSerializable("FindUser",findUser);

                            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();

                            //intent
                            Intent intent = new Intent(this, MainActivity.class);
                            intent.putExtras(bundle1);

                            // navigate
                            startActivity(intent);
                            finish();//销毁此Activity
                        }else {
                            Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}