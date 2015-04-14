package com.example.eilon.todolist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoListManagerActivity extends ActionBarActivity {
    ArrayList<ToDoItm> todoList;
    ArrayAdapter<ToDoItm> adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_manager);
        todoList = new ArrayList<ToDoItm>();
        adapter = new ToDoAdapter(this, R.layout.activity_todo_item, todoList);
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
        int id = item.getItemId();

        if (id == R.id.menuItemAdd) {
            Intent intent = new Intent(this, AddNewTodoItemActivity.class);
            startActivityForResult(intent, 42);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    protected void onActivityResult(int reqCode, int resCode, Intent data) {
        if (resCode == Activity.RESULT_OK && reqCode == 42) {
            String title = data.getStringExtra("mission");
            Date dueDate = (Date) data.getSerializableExtra("date");
            todoList.add(new ToDoItm(title, dueDate));
            adapter.notifyDataSetChanged();
        }
    }

    private class OnItemLongClickListenerCustom implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> av, View v, final int pos, final long id) {
            final AlertDialog.Builder bld = new AlertDialog.Builder(TodoListManagerActivity.this);
            bld.setIcon(android.R.drawable.ic_dialog_alert);
            ToDoItm msg = todoList.get(pos);
            bld.setMessage(msg.getMission());
            if(msg.getMission().toLowerCase().contains("call")){
                final String num = msg.getMission().substring(5);
                AlertDialog.Builder dialog = new AlertDialog.Builder(TodoListManagerActivity.this);
                dialog.setNegativeButton("Call" + num, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + num));
                        startActivity(dial);
                        todoList.remove(pos);
                        adapter.notifyDataSetChanged();
                    }
                });
                todoList.remove(pos);
                adapter.notifyDataSetChanged();
            }
            else
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

