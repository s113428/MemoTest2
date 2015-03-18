package com.test.memotest2;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    //クラス内変数を定義
    private LinkedList<String> memoList;
    //クラス内変数　ここまで

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //List部追記
        memoList = new LinkedList<String>();
        //memoList.add("memo1");

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, memoList);

        ListView listView = (ListView)findViewById(R.id.ListView01);
        listView.setAdapter(adapter);
        //Listここまで

        //List クリック時の処理
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                //Log.d("LIST" ,String.valueOf(id));
                Intent emIntent = new Intent(MainActivity.this,MemoEditActivity.class);
                startActivity(emIntent);
            }
        });
        //List クリック時の処理　ここまで

//        //MemoEdit部
//        Button memoEditButton = (Button)findViewById(R.id.editButton);
//        memoEditButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent emIntent = new Intent(MainActivity.this,MemoEditActivity.class);
//                startActivity(emIntent);
//            }
//        });
//        //MemoEdit　ここまで

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //ActionBar部
        //元文//getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem actionItem = menu.add(Menu.NONE,1,1,"New Memo");
        actionItem.setIcon(android.R.drawable.ic_menu_add);
        actionItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return true;
        //ActionBar部　ここまで
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //ActionBar部
        //noinspection SimplifiableIfStatement
        if (id == 1) {
            //Toast.makeText(this,"リストを追加",Toast.LENGTH_LONG).show();
            showDialog();
            return true;
        }
        //ActionBar部　ここまで

        return super.onOptionsItemSelected(item);
    }

//    private void setListView(ArrayList<HashMap<String, Object>> memoLists){
//        MemoListAdapter memoListAdapter = new MemoListAdapeter(getApplicationContext());
//        memoListAdapter .setMemoLists(memoLists);
//        listView.setAdapter(memoListAdapter);
//        listView.setOnItemClickListener(new ClickEventOnMemoActivity());
//    }

    //Dialog部
    EditText editText;
    private void showDialog() {
        editText = new EditText(this);
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("メモ追加");
        ad.setView(editText);
        ad.setIcon(android.R.drawable.ic_menu_edit);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                memoList.add(MainActivity.this.editText.getText().toString());
                //Toast.makeText(MainActivity.this,editText.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });
        ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        ad.show();
    }
    //Dialog ここまで

}
