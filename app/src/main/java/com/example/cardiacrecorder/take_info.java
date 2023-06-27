package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

import com.google.firebase.Timestamp;


public class take_info extends AppCompatActivity {
    EditText cmnt,dia,heart,systolic,time,date;
    ImageButton save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_info);
        cmnt=findViewById(R.id.cmnt);
        dia=findViewById(R.id.dia);
        heart=findViewById(R.id.heart);
        systolic=findViewById(R.id.systolic);
        time=findViewById(R.id.time);
        date=findViewById(R.id.date);
        save=findViewById(R.id.save);
        save.setOnClickListener((v) ->saveNote());

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
        documentReference=Utility.getCollectionReferenceForNotes().document();
        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Utility.showToast(take_info.this,"info added successfully");
                    finish();
                }
                else
                {
                    Utility.showToast(take_info.this,"Failed while adding");
                }
            }
        });



    }
}
