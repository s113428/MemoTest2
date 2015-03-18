package com.test.memotest2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class MemoEditActivity extends ActionBarActivity {

    private LinkedList<String> memoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_edit);

        //DB保存部
        Button dbSaveButton = (Button)findViewById(R.id.saveButton);
        dbSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<HashMap<String, Object>> memoLists;
                MemoDao dao = new MemoDao(getApplicationContext());
                dao.connection();

                // メモ内容を追加
                EditText editText = (EditText)findViewById(R.id.editText);
                String memo = editText.getText().toString();
                dao.addMemo(memo);

                dao.close();
            }
        });
        //DB保存　ここまで

        //DB読み込み
        Button dbOpenButton = (Button)findViewById(R.id.openButton);
        dbOpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<HashMap<String, Object>> memoLists;
                MemoDao dao = new MemoDao(getApplicationContext());
                dao.connection();

                dao.searchAll();

                dao.close();
            }
        });
        //DB読み込み　ここまで
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_memo_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
