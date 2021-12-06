package com.example.yourdate2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.core.app.NotificationCompatSideChannelService;

import java.util.ArrayList;

public class my_listview_adapter extends BaseAdapter {
    private int cnt;
    LayoutInflater layoutInflater;
    private Context context;
    private int idx=1;
    boolean test=false;
    private ArrayList<jizhang_item>list_item=new ArrayList<jizhang_item>(500);
    static class ViewHolder
    {

        public TextView textView_name;
        public TextView textView_date;
        public TextView textView_detil;
        public CheckBox checkBox;
    }
    my_listview_adapter(Context context,int idx)
    {
        this.context =context;
        layoutInflater=LayoutInflater.from(context);
        cnt=10;
        test=true;
    }
    my_listview_adapter(Context context)
    {
        this.context =context;
        layoutInflater=LayoutInflater.from(context);
        cnt=list_item.size();
    }
    my_listview_adapter(Context context,ArrayList<jizhang_item> arrayList)
    {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
        this.list_item=arrayList;
        cnt=list_item.size();
    }
    public void update(ArrayList<jizhang_item> arrayList)
    {
        this.list_item=arrayList;
    }
    @Override
    public int getCount() {
        return cnt;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public ViewHolder viewHolder;
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewHolder=null;
        if(viewHolder==null)
        {
            view=layoutInflater.inflate(R.layout.listview_style,null);
            viewHolder=new ViewHolder();
            viewHolder.textView_name=view.findViewById(R.id.text_name);
            viewHolder.textView_date=view.findViewById(R.id.text_date);
            viewHolder.textView_detil=view.findViewById(R.id.text_detil);
            viewHolder.checkBox=view.findViewById(R.id.checkBox);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) view.getTag();
        }
        if(test)
        {
            viewHolder.textView_name.setText("name");
            viewHolder.textView_date.setText("date_time");
            viewHolder.textView_detil.setText("detil");
        }
        else
        {
            viewHolder.textView_name.setText(list_item.get(i).name.toString());
            viewHolder.textView_date.setText(list_item.get(i).date.toString()+" "+list_item.get(i).time.toString());
            viewHolder.textView_detil.setText(list_item.get(i).detil.toString());
        }
        return view;
    }
}
