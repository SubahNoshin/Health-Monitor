package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton add_note_btn;
    RecyclerView recyclerview;
    ImageButton menu;
    infoadapter infoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add_note_btn = findViewById(R.id.add_note_btn);
        recyclerview = findViewById(R.id.recyclerview);
        menu = findViewById(R.id.menu);

        add_note_btn.setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, take_info.class)));
        menu.setOnClickListener((v) -> showmenu());
        setuprecyclerview();

    }

    void showmenu() {
        PopupMenu popupMenu=new PopupMenu(MainActivity.this,menu);
        popupMenu.getMenu().add("Logout");
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getTitle()=="Logout")
                {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(MainActivity.this,Login.class));
                    finish();
                    return true;
                }
                return false;
            }
        });

    }

    void setuprecyclerview()
    {
        Query query=Utility.getCollectionReferenceForNotes();
        FirestoreRecyclerOptions<Note>options=new FirestoreRecyclerOptions.Builder<Note>().setQuery(query,Note.class).build();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        infoAdapter=new infoadapter(options,this);
        recyclerview.setAdapter(infoAdapter);


    }
    @Override
    protected void onStart(){
        super.onStart();
        infoAdapter.startListening();
    }
    @Override
    protected void onStop(){
        super.onStop();
        infoAdapter.stopListening();
    }
    @Override
    protected void onResume(){
        super.onResume();
        infoAdapter.notifyDataSetChanged();
    }


}
