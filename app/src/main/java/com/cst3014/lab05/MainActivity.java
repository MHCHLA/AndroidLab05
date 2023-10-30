package com.cst3014.lab05;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //ArrayList<String> elements = new ArrayList<>();
    MyListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myList = findViewById(R.id.theListView);
        myList.setAdapter(myAdapter = new MyListAdapter());
        TextView chatText = findViewById(R.id.chatText);
        Button sendButton = findViewById(R.id.sendBtn);
        Button receiveButton = findViewById(R.id.receiveBtn);
        sendButton.setOnClickListener( click -> {
            if(!chatText.getText().toString().isEmpty()){
                Msg.addMsg(chatText.getText().toString(),0);
                myAdapter.notifyDataSetChanged();
                chatText.setText("");
            }
        });
        receiveButton.setOnClickListener( click -> {
            if(!chatText.getText().toString().isEmpty()){
                Msg.addMsg(chatText.getText().toString(),1);
                myAdapter.notifyDataSetChanged();
                chatText.setText("");
            }
        });

        myList.setOnItemLongClickListener( (p, b, pos, id) -> {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle(R.string.alertTitle)
                    .setPositiveButton("Yes", (click, arg) -> {
                        Msg.removeMsg(pos);
                        myAdapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("No", (click, arg) -> { })
                    .create().show();
            return true;
        });

    }

    private class MyListAdapter extends BaseAdapter {

        public int getCount() { return Msg.list.size();}

        public Object getItem(int position) { return Msg.list.get(position); }

        public long getItemId(int position) { return (long) position; }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = getLayoutInflater();
            if(convertView == null){
                convertView = inflater.inflate(R.layout.chat, parent, false);
            }
            TextView tView = convertView.findViewById(R.id.textGoesHere);
            TextView tView2 = convertView.findViewById(R.id.textGoesHere2);

            if(Msg.list.get(position).getType() == 0){
                tView.setText(Msg.list.get(position).getText());
            }else if(Msg.list.get(position).getType() == 1){
                tView2.setText(Msg.list.get(position).getText());
            }
            return convertView;
        }
    }
}