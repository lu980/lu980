package com.luxiang.a3_27_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.MediaRouteActionProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.jar.Attributes;

public class MainActivity extends Activity {

    private MyOpenHelper myOpenHelper;
    private ArrayList<Person> list;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         lv = (ListView) findViewById(R.id.lv);
        list = new ArrayList<Person>();




        myOpenHelper = new MyOpenHelper(getApplicationContext());

    }

    public void click1(View v){

        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        /**
         * table 表名
         * values
         */
        ContentValues values = new ContentValues();
        values.put("name","李四");
        values.put("phone","123456");

        long insert = db.insert("info", null, values);
        db.close();
        if (insert > 0){
            Toast.makeText(getApplicationContext(),"添加成功",1).show();

        }else {
            Toast.makeText(getApplicationContext(),"添加失败", 1).show();
        }


    }
    public void click2(View v){
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        int delete = db.delete("info", "name = ?", new String[]{"李四"});
        db.close();
        Toast.makeText(getApplicationContext(),"删除了"+delete+"行",0).show();

    }
    public void click3(View v){
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("phone",1111);


        int update = db.update("info", values, "name = ?", new String[]{"李四"});

        db.close();
        Toast.makeText(getApplicationContext(),"更新了"+update+"行",0).show();

    }
    public void click4(View v){
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();

        Cursor cursor = db.query("info", null, null,null, null, null, null);

        if (cursor != null && cursor.getCount() > 0){
            while (cursor.moveToNext()){
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
//               把数据放入javabean
                Person person = new Person();
                person.setName(name);
                person.setPhone(phone);
//                放入集合
                list.add(person);


            }

          lv.setAdapter(new MyAdapter());


        }

    }

//    定义listview数据适配器
    public class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view;
            if (convertView == null) {
                view = View.inflate(getApplicationContext(), R.layout.item, null);

            } else {
                view = convertView;

            }
//            找到控件显示数据
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            TextView tv_phone = (TextView) view.findViewById(R.id.tv_phone);
//            在集合内取数据
            Person person = list.get(position);
            tv_name.setText(person.getName());
            tv_phone.setText(person.getPhone());

            return view;
        }

    }

}
