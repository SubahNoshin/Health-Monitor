package com.example.cardiacrecorder;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import io.grpc.Context;

public class infoadapter extends FirestoreRecyclerAdapter<Note,infoadapter.infoviewholder> {
   Activity context;
    public infoadapter(@NonNull FirestoreRecyclerOptions<Note> options, Activity context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull infoviewholder holder, int position, @NonNull Note note) {
      holder.date1.setText("Date:  "+note.date);
        holder.time1.setText("Time:  "+note.time);
        holder.systolic1.setText("Systolic pressure:  "+note.systolic);
        holder.dia1.setText("Diastolic pressure:  "+note.dia);
        holder.heart1.setText("Heart Rate:  "+note.heart);
        holder.comment1.setText("Comment:  "+note.cmnt);

        holder.itemView.setOnClickListener((v)->{
            Intent intent = new Intent(context, update_delete.class);
            intent.putExtra("date", note.date);
            intent.putExtra("time", note.time);
            intent.putExtra("systolic", note.systolic);
            intent.putExtra("diastolic", note.dia);
            intent.putExtra("heart", note.heart);
            intent.putExtra("cmnt", note.cmnt);

            String docId = this.getSnapshots().getSnapshot(position).getId();
            intent.putExtra("docId", docId);
            context.startActivity(intent);

        });


    }

    @NonNull
    @Override
    public infoviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_note_item,parent,false);
        return new infoviewholder(view);
    }

    class infoviewholder extends RecyclerView.ViewHolder{
          TextView date1,time1,systolic1,dia1,heart1,comment1;

        public infoviewholder(@NonNull View itemView) {
            super(itemView);
            date1=itemView.findViewById(R.id.date1);
            time1=itemView.findViewById(R.id.time1);
            systolic1=itemView.findViewById(R.id.systolic1);
            dia1=itemView.findViewById(R.id.dia1);
            heart1=itemView.findViewById(R.id.heart1);
            comment1=itemView.findViewById(R.id.comment1);



        }
    }
}
