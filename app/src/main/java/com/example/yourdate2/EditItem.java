package com.example.yourdate2;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class EditItem extends Activity {
    private EditText edit_name;
    private EditText edit_detil;
    private Button btn_change;
    private Button btn_edit_date;
    private Button btn_edit_time;
    private Button btn_delete;
    private TextView text_show_date;
    private TextView text_show_time;
    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Intent intent=getIntent();
        //Toast.makeText(EditItem.this,intent.getStringExtra("id"),Toast.LENGTH_LONG).show();
        int id=0;
        String s=intent.getStringExtra("id");
        id=Integer.parseInt(s);
        int finalId = id;
        edit_name=findViewById(R.id.edit_item_name);
        edit_name.setText(Forget_Activity.items.get(finalId).getName());
        edit_detil=findViewById(R.id.edit_item_detil);
        edit_detil.setText(Forget_Activity.items.get(finalId).getDetil());
        btn_change=findViewById(R.id.edit_item_change);
        btn_delete=findViewById(R.id.edit_item_delete);
        btn_edit_time=findViewById(R.id.edit_item_btn_time);
        btn_edit_date=findViewById(R.id.edit_item_btn_date);
        text_show_date=findViewById(R.id.edit_item_show_date);
        text_show_date.setText(Forget_Activity.items.get(finalId).getDate());
        text_show_time=findViewById(R.id.edit_item_show_time);
        text_show_time.setText(Forget_Activity.items.get(finalId).getTime());
        calendar=Calendar.getInstance();
        btn_edit_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(EditItem.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        text_show_date.setText(""+i+"."+(i1+1)+"."+i2);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btn_edit_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(EditItem.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        String s1,s2;
                        if(i<10)
                        {
                            s1="0"+i;
                        }
                        else
                        {
                            s1=""+i;
                        }
                        if(i1<10)
                        {
                            s2="0"+i1;
                        }
                        else
                        {
                            s2=""+i1;
                        }
                        text_show_time.setText(s1+":"+s2);
                    }
                },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
            }
        });
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Forget_Activity.items.get(finalId).setName(edit_name.getText().toString());
                Forget_Activity.items.get(finalId).setDetil(edit_detil.getText().toString());
                Forget_Activity.items.get(finalId).setDate(text_show_date.getText().toString());
                Forget_Activity.items.get(finalId).setTime(text_show_time.getText().toString());
                Forget_Activity.mlistView.setAdapter(new my_listview_adapter(EditItem.this,Forget_Activity.items));
                finish();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Forget_Activity.items.remove(finalId);
                Forget_Activity.mlistView.setAdapter(new my_listview_adapter(EditItem.this,Forget_Activity.items));
                finish();
            }
        });
    }
}