package com.test.memotest2;

import android.app.ActionBar;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //List部追記
        String[] memoLists = new String[]{
                "memo1","memo2","memo3"
        };
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, memoLists);

        ListView listView = (ListView)findViewById(R.id.ListView01);
        listView.setAdapter(adapter);
        //Listここまで
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //ActionBar部
        //元文//getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem actionItem = menu.add("New Memo");
        actionItem.setIcon(android.R.drawable.ic_menu_add);
        actionItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        //ActionBar部　ここまで

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //ActionBar部
        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        //ActionBar部　ここまで

        return super.onOptionsItemSelected(item);
    }
}
