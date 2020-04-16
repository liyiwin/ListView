package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context ct = this;

    RelativeLayout btn;

    ImageView send_btn;

    ListView lv;

    lv_Adapter lv_adapter;

    ArrayList<Integer>  lv_array = new ArrayList<>();

    Integer number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
        setActions();
        update_UI();

    }

    void setUI(){

        btn = findViewById(R.id.btn);

        send_btn = findViewById(R.id.send_btn);

        lv = findViewById(R.id.lv);

        lv_adapter = new lv_Adapter(ct,lv_array);

        lv.setAdapter(lv_adapter);

    }

    void setActions(){

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ct,"test",Toast.LENGTH_LONG).show();
                Log.e("this","test");
                number++ ;
                lv_array.add(number);

                lv_adapter.update(lv_array);

            }
        });


    }

    void update_UI(){


    }

    class lv_Adapter extends BaseAdapter {

        ArrayList<Integer> innerList = new  ArrayList<>();

        public lv_Adapter(Context ct, ArrayList<Integer> outerList){

            innerList = outerList;

        }


        @Override
        public int getCount() {
            return  innerList.size();
        }

        @Override
        public Integer getItem(int position) {
            return innerList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View groupView;

            ViewHolder holder;

            if (convertView == null){

               groupView = LayoutInflater.from(ct).inflate(R.layout.itemview,parent,false);

               holder = new ViewHolder();

               holder.tx = groupView.findViewById(R.id.item_text);

               groupView.setTag(holder);

            }

            else{

                holder = (ViewHolder) convertView.getTag();

                groupView = convertView;

            }

            Integer this_Data = getItem(position);

            holder.tx.setText(this_Data.toString());

            return groupView;
        }

        void update (ArrayList<Integer> new_Array){

            innerList = new_Array;

            notifyDataSetChanged();

        }

        private class ViewHolder{

            TextView tx;

        }
    }
}
