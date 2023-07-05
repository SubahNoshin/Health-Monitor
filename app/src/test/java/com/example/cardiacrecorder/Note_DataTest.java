package com.example.cardiacrecorder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Note_DataTest {
    private Note_Data mockList()
    {
        Note_Data DataList=new Note_Data();
        DataList.add(mockMeasure());
        return DataList;
    }
    private Note mockMeasure()
    {
        return new Note("normal","200","120","80","11:47am","23/07/2023");
    }
    @Test
    public void testAdd()
    {
        Note_Data DataList=mockList();
        assertEquals(1,DataList.getNote().size());

        Note m=new Note("normal","200","120","80","11:47am","23/07/2023");
        DataList.add(m);

        assertEquals(2,DataList.getNote().size());
        assertTrue(DataList.getNote().contains(m));
    }
    @Test
    public void testAddException() {
        Note_Data mList = new Note_Data();
        Note note = mockMeasure();
        mList.add(note);

        assertThrows(IllegalArgumentException.class, () -> {
            mList.add(note);
        });
    }
    @Test
    public void testEdit()
    {
        Note_Data DataList=mockList();
        Note note = new Note("normal","200","120","80","11:47am","23/07/2023");
        DataList.add(note);
        assertTrue(DataList.getNote().contains(note));
        Note another=new Note("high","100","121","60","11:48","24/07/2023");
        DataList.edit(1,another);
        assertFalse(DataList.getNote().contains(note));
        assertTrue(DataList.getNote().contains(another));
    }
    @Test
    public void testDelete()
    {
        Note_Data mList=mockList();
        Note note = new Note("normal","70","120","70","1:45am","23/07/2023");
        mList.add(note);
        assertTrue(mList.getNote().contains(note));
        mList.delete(note);
        assertFalse(mList.getNote().contains(note));


    }
    @Test
    public void testDeleteException()
    {
        Note_Data mList=mockList();
        Note note = new Note("normal","70","120","70","1:45pm","23/07/2023");
        mList.add(note);
        mList.delete(note);
        assertThrows(IllegalArgumentException.class,()->{
            mList.delete(note);
        });
    }




}
