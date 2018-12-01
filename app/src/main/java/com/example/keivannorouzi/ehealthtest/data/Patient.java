package com.example.keivannorouzi.ehealthtest.data;

import com.orm.SugarRecord;


public class Patient extends SugarRecord {

    private String name;
    private String birthDate;
    private String gender;
    private String family;


    public Patient(){}

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
}
