package com.example.yourdate2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Forget_Activity extends Activity {
    static public ListView mlistView;
    private Button btn_add;
    private Button btn_del;
    private Button btn_change;
    static public ArrayList<jizhang_item> items;
    private ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        items=new ArrayList<>(500);
        mlistView=findViewById(R.id.listview_at2);
//        SimpleDateFormat sdf_date=new SimpleDateFormat();
//        sdf_date.applyPattern("yyyy.MM.dd");
//        SimpleDateFormat sdf_time=new SimpleDateFormat();
//        sdf_time.applyPattern("HH:mm:ss");
//        Timer timer=new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                arrayAdapter_test.notifyDataSetChanged();
//                for(int i=0;i<items.size();i++)
//                {
//                    items.get(i).setTime(sdf_time.format(new Date()));
//                    items.get(i).setDate(sdf_date.format(new Date()));
//                }
//                if(items.size()!=0)
//                {
//                    arrayAdapter.notifyDataSetChanged();
//                }
//                arrayAdapter.notifyDataSetChanged();
//            }
//        },0,6000);
        btn_add=findViewById(R.id.btn_additem);
        btn_del=findViewById(R.id.btn_delitem);
        btn_change=findViewById(R.id.btn_chang);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Forget_Activity.this,addItem.class);
                startActivityForResult(intent,1);
            }
        });
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Forget_Activity.this,"修改了2",Toast.LENGTH_SHORT).show();
            }
        });
        mlistView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(Forget_Activity.this,EditItem.class);
                intent.putExtra("id",""+i);
                startActivity(intent);
                return true;
            }
        });
        String data=read();
        int start=0;int p;
        char[] chars=new char[5000000];
        String init_name="";String init_detil="";String init_date="";String init_time="";
        p=-1;
        while(start<data.length()&&data.length()>0)
        {
            init_name="";init_detil="";init_date="";init_time="";
            p=-1;
            for(int i=start;i<data.length();i++)
            {

                if(data.charAt(i)=='|')
                {
                    start=i+1;
                    break;
                }
                p++;
                chars[p]=data.charAt(i);
            }
            for(int i=0;i<=p;i++)
            {
                init_name+=String.valueOf(chars[i]);
            }
            p=-1;
            for(int i=start;i<data.length();i++)
            {

                if(data.charAt(i)=='|')
                {
                    start=i+1;
                    break;
                }
                p++;
                chars[p]=data.charAt(i);
            }
            for(int i=0;i<=p;i++)
            {
                init_detil+=String.valueOf(chars[i]);
            }
            p=-1;
            for(int i=start;i<data.length();i++)
            {

                if(data.charAt(i)=='|')
                {
                    start=i+1;
                    break;
                }
                p++;
                chars[p]=data.charAt(i);
            }
            for(int i=0;i<=p;i++)
            {
                init_date+=String.valueOf(chars[i]);
            }
            p=-1;
            for(int i=start;i<data.length();i++)
            {
                if(data.charAt(i)=='|')
                {
                    start=i+1;
                    break;
                }
                p++;
                chars[p]=data.charAt(i);
            }
            for(int i=0;i<=p;i++)
            {
                init_time+=String.valueOf(chars[i]);
            }

            jizhang_item ji=new jizhang_item(init_name,init_date,init_time,init_detil);
            items.add(ji);
        }
        mlistView.setAdapter(new my_listview_adapter(Forget_Activity.this,items));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        String data="";
        //data+=read();
        for(int i=0;i<items.size();i++)
        {
            data+=items.get(i).getName()+"|"+items.get(i).getDetil()+"|"+items.get(i).getDate()+"|"+items.get(i).getTime()+"|";
        }
        save(data);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1 && requestCode == 1) {
            Bundle bundle=data.getExtras();
            jizhang_item ji=new jizhang_item(bundle.getString("name"),bundle.getString("date"),bundle.getString("time"),bundle.getString("detil"));
            items.add(ji);
            mlistView.setAdapter(new my_listview_adapter(Forget_Activity.this,items));
        }
    }
    private void save(String content)
    {
        FileOutputStream fileOutputStream=null;
        try
        {
            fileOutputStream=openFileOutput("forget.ldy",MODE_PRIVATE);
            fileOutputStream.write(content.getBytes());
        } catch (FileNotFoundException e) {
            save("");
        } catch (IOException e) {
            save("");
        } finally {
            if(fileOutputStream!=null)
            {
                try{
                    fileOutputStream.close();
                } catch (IOException e) {
                    save("");
                }
            }
        }
    }
    private String read()
    {
        FileInputStream fileInputStream=null;
        try
        {
            fileInputStream =openFileInput("forget.ldy");
            byte[] buff=new byte[1024];
            StringBuilder sb=new StringBuilder("");
            int len=0;
            while((len=fileInputStream.read(buff))>0)
            {
                sb.append(new String(buff,0,len));
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            save("");
        }
        catch (IOException e) {
            save("");
        }
        return null;
    }
}