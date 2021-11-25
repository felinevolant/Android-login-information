package com.edu.neu.homework1;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyHelper extends SQLiteOpenHelper {

    /**
     * 声明一个AndroidSDK自带的数据库变量db
     */
    private SQLiteDatabase db;

    /**
     * 写一个这个类的构造函数，参数为上下文context，所谓上下文就是这个类所在包的路径
     * 指明上下文，数据库名，工厂默认空值，版本号默认从1开始
     * super(context,"db_test",null,1);
     * 把数据库设置成可写入状态，除非内存已满，那时候会自动设置为只读模式
     * 不过，以现如今的内存容量，估计一辈子也见不到几次内存占满的状态
     * db = getReadableDatabase();
     *
     *     public MyHelper(Context context){
     *         super(context,"db_user",null,1);
     *         db = getReadableDatabase();
     *     }
     */

    //构造函数
    public MyHelper(Context context){
              super(context,"db_user",null,1);
              db = getReadableDatabase();
          }


    //数据库的初始化操作，该方法只在数据库第一次创建时调用，主要进行表的创建
    /**
     * 重写两个必须要重写的方法，因为class DBOpenHelper extends SQLiteOpenHelper
     * 而这两个方法是 abstract 类 SQLiteOpenHelper 中声明的 abstract 方法
     * 所以必须在子类 DBOpenHelper 中重写 abstract 方法
     * 想想也是，为啥规定这么死必须重写？
     * 因为，一个数据库表，首先是要被创建的，然后免不了是要进行增删改操作的
     * 所以就有onCreate()、onUpgrade()两个方法
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table if not exists User(id integer primary key AUTOINCREMENT," +
                "username text, password text," +
                "email text,phone text,address text,birthday text)";


        db.execSQL(sql);

        //不知道为什么这一行就是添加不进去
        //sql = "insert into User values (1,'wzx','123456','1404305908@qq.com','18765486555','辽宁省沈阳市','2002-02-26');";
        //db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists User");
        onCreate(db);
    }

    /**
     * 接下来写自定义的增删改查方法
     * 这些方法，写在这里归写在这里，以后不一定都用
     * add()
     * delete()
     * update()
     * getAllData()
     */
    public long add(String username,String password,String email,String phone,String adress){
        ContentValues values= new ContentValues();
        values.put("username",username);
        values.put("password",password);
        values.put("email",email);
        values.put("phone",phone);
        values.put("address",adress);
        long rowid = db.insert("User",null,values);
        return rowid;
        //db.execSQL("INSERT INTO User (name,password) VALUES(?,?)",new Object[]{name,password});
    }

    /**
     * 前三个没啥说的，都是一套的看懂一个其他的都能懂了
     * 下面重点说一下查询表user全部内容的方法
     * 我们查询出来的内容，需要有个容器存放，以供使用，
     * 所以定义了一个ArrayList类的list
     * 有了容器，接下来就该从表中查询数据了，
     * 这里使用游标Cursor，这就是数据库的功底了，
     * 在Android中我就不细说了，因为我数据库功底也不是很厚，
     * 但我知道，如果需要用Cursor的话，第一个参数："表名"，中间5个：null，
     *                                                     最后是查询出来内容的排序方式："name DESC"
     * 游标定义好了，接下来写一个while循环，让游标从表头游到表尾
     * 在游的过程中把游出来的数据存放到list容器中
     * @return
     */
    public ArrayList<User> getAllData(){

        //https://www.cnblogs.com/to-creat/p/5189634.html
        //游标的用法
        ArrayList<User> list = new ArrayList<User>();
        Cursor cursor = db.query("User",null,null,null,null,null,"username DESC");
        while(cursor.moveToNext()){
            @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
            @SuppressLint("Range") String email= cursor.getString(cursor.getColumnIndex("email"));
            @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex("phone"));
            @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex("address"));
            @SuppressLint("Range") String birthday = cursor.getString(cursor.getColumnIndex("birthday"));


            list.add(new User(username,password,email,phone,address,birthday));
        }
        return list;
    }
}
