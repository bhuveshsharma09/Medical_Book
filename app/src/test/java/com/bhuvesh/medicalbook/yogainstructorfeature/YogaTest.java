package com.bhuvesh.medicalbook.yogainstructorfeature;

import junit.framework.TestCase;

public class YogaTest extends TestCase {
    Yoga yoga = new Yoga("Yoga_test", "Yoga_test_details");
    public void testGetYogaName() {
        assertEquals("Yoga_test",yoga.getYogaName());
    }

    public void testGetYogaDetail() {
        assertEquals("Yoga_test_details",yoga.getYogaDetail());
    }

    public void testTestToString() {
        assertEquals("Yoga{"+
                "yogaName='" + yoga.getYogaName() + '\'' +
                '}',yoga.toString());


    }
}