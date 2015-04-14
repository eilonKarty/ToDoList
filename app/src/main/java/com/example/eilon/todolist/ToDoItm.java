package com.example.eilon.todolist;

import java.util.Date;

/**
 * Created by Eilon on 14/04/2015.
 */
public class ToDoItm {

    private String mission;
    private Date datePicker;

    public ToDoItm(String mission, Date datePicker){
        this.mission = mission;
        this.datePicker = datePicker;
    }

    public Date getDate(){
        return this.datePicker;
    }

    public String getMission(){
        return this.mission;
    }


}
