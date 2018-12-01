package com.example.keivannorouzi.ehealthtest;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keivannorouzi.ehealthtest.data.Patient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    PatientListViewModel mViewModel;
    PatientListAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    TextView empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.patientsList);
        empty = findViewById(R.id.empty);
        initViewModel();
        adapter = new PatientListAdapter(this,mViewModel.getPatients().getValue() == null ? new ArrayList<Patient>() : mViewModel.getPatients().getValue());
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mViewModel.getPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(@Nullable List<Patient> patients) {
                adapter.mPatients = mViewModel.getPatients().getValue();
                adapter.notifyDataSetChanged();
                empty.setVisibility( adapter.getItemCount() > 0 ?  View.GONE : View.VISIBLE);
            }
        });
      loadDataInBackground();
    }


    public void loadDataInBackground(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mViewModel.loadData();
            }
        }).start();
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(PatientListViewModel.class);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.refresh:
                loadDataInBackground();
                break;

        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        empty.setVisibility( adapter.getItemCount() > 0 ?  View.GONE : View.VISIBLE);

    }
}
