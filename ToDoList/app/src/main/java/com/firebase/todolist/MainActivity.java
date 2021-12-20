package com.firebase.todolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    DatabaseReference fDb;
    FirebaseAuth fAuth;
    FirebaseUser currentUser;
    Button actButton;
    TextView info;
    ArrayList<NotesModel> notList = new ArrayList<>();
    NotesAdapter noteAdapter;
    ListView oListview;
    static String titlem,descm,addtimem,notftimem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        definition();
        kontrol();
        actions();
        showNotes();
        listNotes();
    }


    private void definition() {
        mToolbar = findViewById(R.id.mtoolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("ToDoList");
        fAuth = FirebaseAuth.getInstance();
        currentUser = fAuth.getCurrentUser();
        fDb = FirebaseDatabase.getInstance().getReference("notes");
        actButton = findViewById(R.id.actButton);
        info = findViewById(R.id.info);
        noteAdapter = new NotesAdapter(MainActivity.this,notList);
        oListview = findViewById(R.id.listview);
    }

    private void kontrol() {
        if (currentUser==null){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            Toast.makeText(getApplicationContext(),"Hoş Geldiniz...",Toast.LENGTH_SHORT).show();
        }else{Toast.makeText(getApplicationContext(),/*currentUser.getUid()*/"Hoş Geldiniz...",Toast.LENGTH_SHORT).show();}
    }

    void actions()
    {
        actButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CreateNotes.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        switch (id){
            case R.id.logout:
                fAuth.signOut();;
                Toast.makeText(getApplicationContext(),"Oturum kapatıldı...",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
        return true;
    }

    private void showNotes() {
        fDb = FirebaseDatabase.getInstance().getReference("notes/"+fAuth.getCurrentUser().getUid());
        fDb .addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.i("datepicked","*********** veri **************"+snapshot.getValue().toString());
                NotesModel notModel = snapshot.getValue(NotesModel.class);

                notList.add(notModel);
                noteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    void listNotes() {
        oListview.setAdapter(noteAdapter);
        oListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                DialogFragment newFragment = new NoteFragment();
                titlem= notList.get(i).title;
                descm= notList.get(i).description;
                addtimem= notList.get(i).added;
                notftimem= notList.get(i).notification;
                newFragment.show(getSupportFragmentManager(), "note dialog");
                return true;
            }
        });
    }
}