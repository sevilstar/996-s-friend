package com.example.yourdate2;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.Calendar;

public class addItem extends Activity {

    public EditText name;
    public EditText detil;
    public Button btn_add;
    private Bundle mBundel;
    private Button mbtn_choosedate;
    private Button mbtn_choosetime;
    private Calendar calendar=Calendar.getInstance();
    private TextView text_showdate;
    private TextView text_showtime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toast.makeText(addItem.this,"2333",Toast.LENGTH_SHORT);
        name=findViewById(R.id.add_item_name);
        detil=findViewById(R.id.add_item_detil);
        btn_add=findViewById(R.id.add_item_change);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(name.getText()))
                {
                    Toast.makeText(addItem.this,"不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent();
                intent.putExtra("name",name.getText().toString());
                intent.putExtra("date",text_showdate.getText().toString());
                intent.putExtra("detil",detil.getText().toString());
                intent.putExtra("time",text_showtime.getText().toString());
                setResult(1,intent);
                finish();
            }
        });
        Toast.makeText(addItem.this,"2333",Toast.LENGTH_SHORT);
        text_showdate=findViewById(R.id.add_item_show_date);
        text_showtime=findViewById(R.id.add_item_show_time);
        mbtn_choosedate=findViewById(R.id.add_item_btn_date);
        Toast.makeText(addItem.this,"2333",Toast.LENGTH_SHORT);
        mbtn_choosedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(addItem.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        text_showdate.setText(""+i+"."+(i1+1)+"."+i2);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        mbtn_choosetime=findViewById(R.id.add_item_btn_time);
        mbtn_choosetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(addItem.this, new TimePickerDialog.OnTimeSetListener() {
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
                        text_showtime.setText(s1+":"+s2);
                    }
                },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
            }
        });
    }
}