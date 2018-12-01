package com.example.keivannorouzi.ehealthtest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.keivannorouzi.ehealthtest.data.Patient;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void getAndSetPatientName() {

        List<Patient> patients = Patient.listAll(Patient.class);
        if (patients.size() > 0) {
            Patient toBeTested = patients.get(0);
            int id = toBeTested.getId().intValue();
            String testName = "KeivanTest";
            toBeTested.setName(testName);
            toBeTested.save();
            Patient patient = Patient.findById(Patient.class,id);
            assertEquals(testName,patient.getName());

        }
    }
}
