package com.example.cardiacrecorder;

import java.util.ArrayList;
import java.util.List;

public class Note_Data {
    List<Note> mockList=new ArrayList<>();


    public void add(Note note)
    {
        if(mockList.contains(note))
        {
            throw new IllegalArgumentException();
        }
        mockList.add(note);
    }

    public void delete(Note note)
    {
        if(mockList.contains(note))
        {
            mockList.remove(note);
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }



    public void edit(int pos, Note note)
    {
        mockList.set(pos,note);
    }



    public List<Note>getNote()
    {
        return mockList;
    }
}