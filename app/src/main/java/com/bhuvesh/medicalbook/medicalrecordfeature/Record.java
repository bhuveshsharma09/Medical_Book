package com.bhuvesh.medicalbook.medicalrecordfeature;

public class Record {
    /*Record class contains data related to medical records.
    Each record will have Id, record title and description
    * */


    // declare variables to be used in class
    private int id;
    private String recordTitle;
    private String recordDescription;

    // Initialising the constructor
    public Record(String recordTitle, String recordDescription) {
        this.recordTitle = recordTitle;
        this.recordDescription = recordDescription;
    }

    // another constructor
    public Record() {

    }

    // getter and setter methods to interact with Record class
    public int getId() {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getRecordTitle()
    {
        return recordTitle;
    }

    public void setRecordTitle(String recordTitle)
    {
        this.recordTitle = recordTitle;
    }

    public String getRecordDescription()
    {
        return recordDescription;
    }

    public void setRecordDescription(String recordDescription)
    {
        this.recordDescription = recordDescription;
    }
}
