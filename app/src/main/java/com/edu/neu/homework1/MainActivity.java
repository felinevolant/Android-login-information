package com.edu.neu.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

        Bundle bundle2 = this.getIntent().getExtras();
        huntUser=(User) bundle2.getSerializable("FindUser");
        if(huntUser!=null){
            showName.setText(huntUser.getUsername());
        }else{
            Toast.makeText(this, "没获得到用户", Toast.LENGTH_SHORT).show();
        }
    }
}