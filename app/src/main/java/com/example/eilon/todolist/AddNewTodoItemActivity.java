package com.example.eilon.todolist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;


public class AddNewTodoItemActivity extends Activity {

    // adding up all the info when clicking the add button
    private void setInfo(){
        Intent addIntent = new Intent();
        String edtNewItem = ((EditText) findViewById(R.id.edtNewItem)).getText().toString();
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        Calendar calender = Calendar.getInstance();
        calender.set(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth(),0,0,0);
        addIntent.putExtra("mission", edtNewItem);
        addIntent.putExtra("date", calender.getTime());
        if(!edtNewItem.isEmpty()){
            setResult(RESULT_OK, addIntent);
        }
        else{
            setResult(RESULT_CANCELED);
        }
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_todo_item);
        final Button btnOK = (Button)findViewById(R.id.btnOK);
        final Button btnCancel = (Button)findViewById(R.id.btnCancel);
        btnOK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {setInfo();}});
        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent cancelIntent = new Intent();
                setResult(RESULT_CANCELED, cancelIntent);
                finish();
            }});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_todo_item, menu);
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
