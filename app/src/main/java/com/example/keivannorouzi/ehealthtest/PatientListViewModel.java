package com.example.keivannorouzi.ehealthtest;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.keivannorouzi.ehealthtest.data.Patient;
import com.google.gson.JsonObject;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.keivannorouzi.ehealthtest.ApiHelper.isInternetAvailable;

public class PatientListViewModel extends AndroidViewModel {

    private MutableLiveData<List<Patient>> mPatients = new MutableLiveData<>();

    public PatientListViewModel(@NonNull Application application) {
        super(application);
        //getPatientsFromApi();
    }

    public void loadPatientsFromApi() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://hapi.fhir.org/baseDstu3/Patient?_pretty=true", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("TEST" , "success");
                try {
                    JSONArray patients = response.getJSONArray("entry");
                    if (patients!=null){
                        int size = patients.length();
                        int cnt = 0;
                        List<Patient> patientList = new ArrayList<>();
                        for (int i=0; i <  size;i++ ) {
                            JSONObject jsonPatient = patients.getJSONObject(i).getJSONObject("resource");
                            Patient patient = new Patient();
                            if (jsonPatient.has("name")) {
                                JSONArray nameArray =jsonPatient.getJSONArray("name");
                                if (nameArray.length()>0) {
                                    JSONObject nameObject  = nameArray.getJSONObject(0);
                                    if (!nameObject.isNull("given")) {
                                        String given = nameObject.getJSONArray("given").getString(0);
                                        String family = "";
                                        if (nameObject.isNull("family")) {
                                             family = nameObject.getString("family");
                                        }
                                        patient.setName(given);
                                        patient.setFamily(family);
                                    }
                                }

                            }
                            if (jsonPatient.has("birthDate") && !jsonPatient.isNull("birthDate"))
                                patient.setBirthDate(jsonPatient.getString("birthDate"));
                            if (jsonPatient.has("gender") &&  !jsonPatient.isNull("gender"))
                                patient.setGender(jsonPatient.getString("gender"));
                            patient.setId(jsonPatient.getLong("id"));
                            patient.save();
                            patientList.add(patient);
                            cnt++;
                            if (cnt == 10)
                                break;

                        }
                        mPatients.setValue(patientList);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("TEST" , e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TEST" , "fail");
            }
        });
        AppController.getInstance().addToRequestQueue(request);

    }

    public void loadData(){

        if (isInternetAvailable()){
            loadPatientsFromApi();
        }else
            loadPatientsFromDB();

    }



    public void loadPatientsFromDB(){
        mPatients.postValue(Patient.find(Patient.class,null,null,null,null,"10"));

    }

    public MutableLiveData<List<Patient>> getPatients(){
        return mPatients;
    }


}
