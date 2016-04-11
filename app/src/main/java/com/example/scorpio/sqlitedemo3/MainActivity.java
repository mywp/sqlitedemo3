package com.example.scorpio.sqlitedemo3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.scorpio.sqlitedemo.R;
import com.example.scorpio.sqlitedemo3.dao.PersonDAO;
import com.example.scorpio.sqlitedemo3.entities.Person;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Person> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mListView = (ListView) findViewById(R.id.listview);

        PersonDAO dao = new PersonDAO(this);
        personList = dao.queryAll();

        //把niew层对象ListView和控制器Adapter关联起来
        mListView.setAdapter(new MyAdapter());
    }

    /*@author andong
    * 数据适配器*/
    class MyAdapter extends BaseAdapter {

        public static final String TAG = "MyAdapter";

        //定义ListView的数据的长度
        @Override
        public int getCount() {
            return personList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /*此方法返回的是ListView的列表中的某一行的View对象
        * position 当前返回的View的索引位置
        * convertView 缓存对象
        * parent 就是ListView对象*/
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           

            TextView tv = null;

            if (convertView != null) {//判断缓存对象是否为null,不为null时已经缓存了对象
                Log.i(TAG, "getView:复用缓存" + position);
                tv= (TextView) convertView;
            } else {//等于null，说明第一次显示，新创建对象
                Log.i(TAG, "getView:新建" + position);
                tv = new TextView(MainActivity.this);
            }

            tv.setTextSize(25);

            Person person = personList.get(position);//获得指定位置的数据，进行对TextView的绑定
            tv.setText(person.toString());
            return tv;
        }
    }
}
