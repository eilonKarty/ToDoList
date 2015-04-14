
/**
 * Created by Eilon on 14/04/2015.
 */
package com.example.eilon.todolist;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

class ToDoAdapter extends ArrayAdapter<ToDoItm> {

        private ArrayList<ToDoItm> objects;

        public ToDoAdapter(Context context, int textViewResourceId, ArrayList<ToDoItm> objects) {
            super(context, textViewResourceId, objects);
            this.objects = objects;
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.activity_todo_item, null);

            TextView edtNewItem = (TextView) view.findViewById(R.id.IDmission);
            TextView edtDate = (TextView) view.findViewById(R.id.IDdatePicker);
            ToDoItm t = getItem(position);
            Date d = t.getDate();

            edtNewItem.setText(t.getMission());

            SimpleDateFormat dateByFormat = new SimpleDateFormat("dd/MM/yyyy");
            edtDate.setText(dateByFormat.format(d));
            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            today.set(Calendar.MILLISECOND, 0);
            if (today.getTime().after(d)) {
                edtDate.setTextColor(Color.RED);
                edtNewItem.setTextColor(Color.RED);
            }

            return view;
        }
    }
