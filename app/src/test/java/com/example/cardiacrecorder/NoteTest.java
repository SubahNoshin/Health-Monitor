package com.example.cardiacrecorder;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteTest {
    @Test
    public void testGetDate() {
        Note note = getNote();

        assertEquals("20/12/2023", note.getDate());
    }
    @Test
     public void testGetCmnt() {
        Note note1 = getNote();
        assertEquals("unwell", note1.getCmnt());
    }
    @Test
    public void testGetDia() {
        Note note2 = getNote();
        assertEquals("23", note2.getDia());
    }
    @Test
    public void testGetSystolic() {
        Note note3 = getNote();
        assertEquals("123", note3.getSystolic());
    }
    @Test
    public void testGetTime() {
        Note note4=getNote();
        assertEquals("10:20",note4.getTime());

    }
    @Test
    public void testGetHeart() {
        Note note5=getNote();
        assertEquals("120",note5.getHeart());
    }

    public Note getNote(){
        Note note = new Note();
        note.setCmnt("unwell");
        note.setDate("20/12/2023");
        note.setDia("23");
        note.setHeart("120");
        note.setSystolic("123");
        note.setTime("10:20");
        return  note;
    }
}
