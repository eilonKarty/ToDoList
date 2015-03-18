package com.example.eilon.todolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TodoListManagerActivity extends ActionBarActivity {
    ArrayList<String> todoList;
    ArrayAdapter<String> adapter;

    public class CustomAdapter extends ArrayAdapter<String> {

        public CustomAdapter(Context context, int resource, List<String> list) {
            super(context, resource, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView v = (TextView)super.getView(position, convertView, parent);
            if (position % 2 == 0) {
                v.setTextColor(Color.BLUE);
            }
            else {
                v.setTextColor(Color.RED);
            }
            return v;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_manager);
        todoList = new ArrayList<String>();
        adapter = new CustomAdapter(this, android.R.layout.simple_list_item_1, todoList);
        ListView todoItems = (ListView) findViewById(R.id.lstTodoItems);
        todoItems.setAdapter(adapter);
        todoItems.setOnItemLongClickListener(new OnItemLongClickListenerCustom());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo_list_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menuItemAdd) {
            TextView newItem = (TextView) findViewById(R.id.edtNewItem);
            String itemToAdd = newItem.getText().toString();
            newItem.setText("");
            if(!itemToAdd.isEmpty()){
                todoList.add(itemToAdd);
                adapter.notifyDataSetChanged();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private class OnItemLongClickListenerCustom implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> av, View v, final int pos, final long id) {
            final AlertDialog.Builder bld = new AlertDialog.Builder(TodoListManagerActivity.this);
            bld.setIcon(android.R.drawable.ic_dialog_alert);
            String msg = todoList.get(pos);
            bld.setMessage(msg);
            bld.setPositiveButton("Delete Item", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                todoList.remove(pos);
                adapter.notifyDataSetChanged();
                }
            });

            bld.show();
            return true;
        }
}
}

