package com.example.rakesh7.sqlitedbex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText etName,etDesignation,etMobile;
    Button btAdd;
    DatabaseManager dm;
    ArrayList<String> listData;

    String[] Name,Designation,Mobile;



//    int images[] = {R.drawable.w1,R.drawable.w2,R.drawable.w3,
//            R.drawable.w4,R.drawable.w5,R.drawable.w6,
//            R.drawable.w7,R.drawable.w8,R.drawable.w9,
//            R.drawable.w10,R.drawable.w11,R.drawable.w12,
//            R.drawable.w13,R.drawable.w14,R.drawable.w15};

    String[] names = {"Rakesh Patel", "Deepak Panda", "Rameshwer Gupta",
            "Dinesh Sahu", "Satya Nayak", "Venkatesh Kumar",
            "Rohan Gupta", "Mahendra Verma", "Yogesh Mohan",
            "Bharat Kumar", "Mohit Sharma", "Sampat Dash",
            "Chandrapratap Singh", "Dikesh Upadhyay", "Chandradeep Katju"};

    String[] designation = {"Software Engineer", "Software Developer", "Web Developer", "Database Administrator", "PL/SQL Developer", "Angular Developer",
            "Testing Engineer", "Accountant", "Dotnet Developer", "Php Developer", "Android Developer", "Java Developer", "Financer",
            "Investor", "Support Engineer"};

    String[] phone = {"9424183391", "9424183391", "9424183391", "9424183391", "9424183391",
            "9424183391", "9424183391", "9424183391", "9424183391", "9424183391",
            "9424183391", "9424183391", "9424183391", "9424183391", "9424183391"};

    boolean switchr=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            dm = new DatabaseManager(MainActivity.this);
            dm.open();
            Cursor cursor_da = dm.fetch();
            if(cursor_da.getCount()==0) {
                for (int i = 0; i < names.length; i++) {
                    dm.insert(names[i], designation[i], phone[i]);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        listData = new ArrayList<>();

        etName = (EditText)findViewById(R.id.etName);
        etDesignation = (EditText)findViewById(R.id.etDesignation);
        etMobile = (EditText)findViewById(R.id.etMobile);
        btAdd = (Button) findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dm.insert(etName.getText().toString(),etDesignation.getText().toString(),etMobile.getText().toString());
                Toast.makeText(MainActivity.this,"Data added in ListView!!",Toast.LENGTH_SHORT).show();
                BindTableData();

            }
        });
        listView = (ListView) findViewById(R.id.listView1);
        BindTableData();
        /*MyListAdapter adapter = new MyListAdapter(MainActivity.this);
        listView.setAdapter(adapter);*/
    }

    public void BindTableData() {
        try {

            Cursor cursor_data = dm.fetch();
            if (cursor_data.getCount() > 0) {

                Name = new String[cursor_data.getCount()];
                Designation = new String[cursor_data.getCount()];
                Mobile = new String[cursor_data.getCount()];
                int i = 0;
                if (cursor_data.moveToNext()) {
                    {
                        do {
                            String name = cursor_data.getString(cursor_data.getColumnIndex("name"));
                            String designation = cursor_data.getString(cursor_data.getColumnIndex("designation"));
                            String mobile = cursor_data.getString(cursor_data.getColumnIndex("mobile"));

                            Name[i] = name;
                            Designation[i] = designation;
                            Mobile[i] = mobile;
                            i++;
                        } while (cursor_data.moveToNext());
                        listView.setAdapter(new MyListAdapter(MainActivity.this,Name,Designation,Mobile));
                        listView.notifyAll();
//                        listView.setAdapter(new dataListAdapter(Stud_SN, Stud_Id, Stud_Name, tick_data));
                    }
                }
            } else {
                listView.setAdapter(null);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private class MyListAdapter extends BaseAdapter {
        Context context;
        String[] Name,Designation,Mobile;

        public MyListAdapter(Context context,String[] Name,String[] Designation,String[] Mobile) {
            this.context = context;
            this.Name = Name;
            this.Designation = Designation;
            this.Mobile = Mobile;
        }

        @Override
        public int getCount() {
            return Name.length;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            try {
                if (view == null) {
                    //view = LayoutInflater.inflate(R.layout.rest_countries_listview_child, null);
                    view = LayoutInflater.from(context).inflate(R.layout.listview_child1, null);
                    viewHolder = new ViewHolder(view);
                    view.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) view.getTag();
                }
/*
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(getResources(), images[i], options);
                int imageHeight = options.outHeight;
                int imageWidth = options.outWidth;
                String imageType = options.outMimeType;

                viewHolder.imageView.setImageBitmap(
                        decodeSampledBitmapFromResource(getResources(), images[i], 100, 100));*/

                //viewHolder.imageView.setImageResource();
                viewHolder.name.setText(Name[i]);
                viewHolder.desig.setText(Designation[i]);
                viewHolder.phone.setText(Mobile[i]);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return view;
        }

        public class ViewHolder {
            TextView name;
            TextView desig;
            TextView phone;
            //ImageView imageView;

            private ViewHolder(View view) {
                name = (TextView) view.findViewById(R.id.name);
                desig = (TextView) view.findViewById(R.id.designation);
                phone = (TextView) view.findViewById(R.id.mobile);
                //imageView = (ImageView) view.findViewById(R.id.image1);
            }
        }
    }

}
