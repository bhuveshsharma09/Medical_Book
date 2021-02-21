package com.bhuvesh.medicalbook.medicalrecordfeature;

import junit.framework.TestCase;


public class RecordTest extends TestCase {
    Record record = new Record("Record Title","Record Description");
    int recordId;
    public void testSetId() {
        record.setId(1);
        assertEquals(1,record.getId());
    }

    public void testGetId() {
        record.setId(1);
        assertEquals(1,record.getId());
    }

    public void testGetRecordTitle() {
        assertEquals("Record Title",record.getRecordTitle());
    }

    public void testSetRecordTitle() {
        record.setRecordTitle("New Title");
        assertEquals("New Title",record.getRecordTitle());
    }

    public void testGetRecordDescription() {
        assertEquals("Record Description",record.getRecordDescription());
    }

    public void testSetRecordDescription() {
        record.setRecordDescription("New Des");
        assertEquals("New Des",record.getRecordDescription());
    }
}