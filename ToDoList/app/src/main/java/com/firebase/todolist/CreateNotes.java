package com.firebase.todolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class CreateNotes extends AppCompatActivity {

    FirebaseDatabase fDb;
    DatabaseReference dBref;
    FirebaseAuth fAuth;
    EditText title,description,notification;
    Switch notify;
    TextView notf;
    Button addBtn;
    FloatingActionButton actBtn;
    LinearLayout notifyLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notes);
        definition();
        actions();
    }


    private void definition() {
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        notification = findViewById(R.id.notification);
        notify = findViewById(R.id.notify);
        notf = findViewById(R.id.notf);
        actBtn = findViewById(R.id.picktime);
        notifyLayout = findViewById(R.id.notifyLayout);
        addBtn = findViewById(R.id.addButton);
        fDb = FirebaseDatabase.getInstance();
        fAuth = FirebaseAuth.getInstance();

        if (!notify.isChecked()){notifyLayout.setVisibility(View.GONE);}
    }

    private void actions() {
        notify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                showDatePicker();
                if (notify.isChecked()){notifyLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        actBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dBref = fDb.getReference("notes/"+fAuth.getCurrentUser().getUid());
                HashMap map = new HashMap();
                map.put("title",title.getText().toString());
                map.put("description",description.getText().toString());
                map.put("added",new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Timestamp(System.currentTimeMillis())));
                    if (notify.isChecked()){
                        map.put("notify",true);
                        map.put("notification",notification.getText().toString());
                    }else {map.put("notify",false);
                        map.put("notification","none");}


                dBref.child(String.valueOf(new Timestamp(System.currentTimeMillis()).getTime())).setValue(map);
                //Log.i("datepicked","Ekleme Başarılı"+String.valueOf(new Timestamp(System.currentTimeMillis()))); //String.valueOf(new Timestamp(System.currentTimeMillis())).split(".")[0]
                //Log.i("datepicked","GET TIME"+String.valueOf(new Timestamp(System.currentTimeMillis()).getTime()));
                //Log.i("datepicked", "FORMATTED"+new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Timestamp(System.currentTimeMillis())));
            }
        });
    }

    public void showDatePicker() {
        DialogFragment newFragment = new MyDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "date picker");
    }
}