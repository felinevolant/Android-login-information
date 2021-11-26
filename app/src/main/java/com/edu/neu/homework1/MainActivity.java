package com.edu.neu.homework1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView showName;
    User huntUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    public void onClick(View v) {

    }

    public void initView(){
        //实例化控件
        showName=findViewById(R.id.showName);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);


        //实现在主页显示登陆者的姓名
        Bundle bundle2 = this.getIntent().getExtras();
        huntUser=(User) bundle2.getSerializable("FindUser");
        if(huntUser!=null){
            showName.setText(huntUser.getUsername());
        }else{
            Toast.makeText(this, "没获得到用户", Toast.LENGTH_SHORT).show();
        }

        //底部导航栏
        NavController navController;
                //= Navigation.findNavController(this,R.id.fragmentContainerView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        navController=navHostFragment.getNavController();
        //工具条的配置
        AppBarConfiguration configuration=new AppBarConfiguration.Builder(bottomNavigationView.getMenu()).build();
        //装配
        NavigationUI.setupActionBarWithNavController(this,navController,configuration);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }
}