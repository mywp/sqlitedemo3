package com.example.scorpio.sqlitedemo3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.scorpio.sqlitedemo.R;
import com.example.scorpio.sqlitedemo3.dao.PersonDAO;
import com.example.scorpio.sqlitedemo3.entities.Person;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

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
            View view = null;

            if (convertView == null) {


                //布局填充器对象，用于把xml对象转换成view对象
                LayoutInflater inflater = MainActivity2.this.getLayoutInflater();
                view = inflater.inflate(R.layout.listview_item, null);
            } else {
                view = convertView;
            }

            //给view中的姓名和年龄赋值
            TextView tvName = (TextView) view.findViewById(R.id.tv_listview_item_name);
            TextView tvAge = (TextView) view.findViewById(R.id.tv_listview_item_age);

            Person person = personList.get(position);

            tvName.setText("姓名:" + person.getName());
            tvAge.setText("年龄：" + person.getAge());

            return view;
        }
    }
}
