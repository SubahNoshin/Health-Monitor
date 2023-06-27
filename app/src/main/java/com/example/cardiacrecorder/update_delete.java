package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

import com.google.firebase.Timestamp;


public class update_delete extends AppCompatActivity {

    EditText cmnt,dia,heart,systolic,time,date;
    ImageButton save;
    Button dltbtn;
    String dater, timer, systolicr, diastolicr, heartr, cmntr, docId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        cmnt=findViewById(R.id.cmnt);
        dia=findViewById(R.id.dia);
        heart=findViewById(R.id.heart);
        systolic=findViewById(R.id.systolic);
        time=findViewById(R.id.time);
        date=findViewById(R.id.date);
        save=findViewById(R.id.save);
        dltbtn = findViewById(R.id.deletebtn);

        //receive data
        dater = getIntent().getStringExtra("date");
        timer = getIntent().getStringExtra("time");
        systolicr = getIntent().getStringExtra("systolic");
        diastolicr = getIntent().getStringExtra("diastolic");
        heartr = getIntent().getStringExtra("heart");
        cmntr = getIntent().getStringExtra("cmnt");
        docId = getIntent().getStringExtra("docId");


        //setting data
        date.setText(dater);
        time.setText(timer);
        systolic.setText(systolicr);
        dia.setText(diastolicr);
        heart.setText(heartr);
        cmnt.setText(cmntr);


        save.setOnClickListener((v) ->saveNote());
        dltbtn.setOnClickListener((v)->deleteRecordFromFirebase());


    }
    void saveNote()
    {
        String s_cmnt=cmnt.getText().toString();
        String s_dia=dia.getText().toString();
        String s_heart=heart.getText().toString();
        String s_systolic=systolic.getText().toString();
        String s_time=time.getText().toString();
        String s_date=date.getText().toString();
        int num1=Integer.parseInt(s_systolic);
        int num2=Integer.parseInt(s_dia);

        if(num1>200||num1<0)
        {
            systolic.setError("Invalid value");
            return;
        }
        if(num2>200||num2<0)
        {
            dia.setError("Invalid value");
            return;
        }

        Note note=new Note();
        note.setCmnt(s_cmnt);
        note.setDate(s_date);
        note.setDia(s_dia);
        note.setSystolic(s_systolic);
        note.setTime(s_time);
        note.setHeart(s_heart);


        saveNoteToFirebase(note);
    }
    void saveNoteToFirebase(Note note)
    {
        DocumentReference documentReference;
        documentReference=Utility.getCollectionReferenceForNotes().document(docId);
        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Utility.showToast(update_delete.this,"Record is updated successfully");
                    finish();
                }
                else
                {
                    Utility.showToast(update_delete.this,"Failed while updating");
                }
            }
        });
    }

    void deleteRecordFromFirebase(){
        DocumentReference documentReference;
        documentReference=Utility.getCollectionReferenceForNotes().document(docId);
        documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Utility.showToast(update_delete.this,"Record is deleted successfully");
                    finish();
                }
                else
                {
                    Utility.showToast(update_delete.this,"Failed while deleting");
                }
            }
        });


    }
}