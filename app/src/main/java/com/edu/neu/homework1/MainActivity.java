package com.edu.neu.homework1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView showName;
    MyHelper myHelper;
    User huntUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化数据库
        myHelper=new MyHelper(this);

        initView();


    }

    @Override
    public void onClick(View v) {

    }

    public void initView(){
        //实例化控件
        showName=findViewById(R.id.showName);



        //实现在主页显示登陆者的姓名
        Bundle bundle2 = this.getIntent().getExtras();
        huntUser=(User) bundle2.getSerializable("FindUser");
        String thisUsername = huntUser.getUsername();
        huntUser=myHelper.findUserByUsername(thisUsername);
        Log.d("msg",huntUser.getAddress());

        if(huntUser!=null){
            showName.setText(huntUser.getUsername());
        }else{
            Toast.makeText(this, "没获得到用户", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //活动界面上加载菜单（memu菜单文件）
        this.getMenuInflater().inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String thisUsername = huntUser.getUsername();
        huntUser=myHelper.findUserByUsername(thisUsername);
        //点击进入修改活动的activity
        // bundle
        Bundle bundle3 = new Bundle();
        bundle3.putSerializable("FindUser",huntUser);
        //intent
        Intent intent3 = new Intent(this, InfoActivity.class);
        intent3.putExtras(bundle3);
        //intent2.putExtras(bundle2);

        // navigate
        startActivity(intent3);
        return super.onOptionsItemSelected(item);
    }
}