package com.example.keivannorouzi.ehealthtest;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.keivannorouzi.ehealthtest.data.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.MyViewHolder> {

    LayoutInflater mInflater;
    Context mContext;
    public List<Patient> mPatients = new ArrayList<>();

    public PatientListAdapter(Context context, List<Patient> patients){
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mPatients = patients;
    }

    @NonNull
    @Override
    public PatientListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.patient_view_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientListAdapter.MyViewHolder holder, int position) {
        holder.bindView(mPatients.get(position));
    }

    @Override
    public int getItemCount() {
        return mPatients.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        Button detailBtn;
        MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            detailBtn = itemView.findViewById(R.id.detailButton);

        }

        void bindView(final Patient patient) {
            String prefix =  patient.getName() == null ? "" : patient.getName() + "-" ;
            name.setText(prefix+ patient.getId());
            detailBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext,DetailActivity.class);
                    intent.putExtra("name",patient.getName());
                    intent.putExtra("gender" , patient.getGender());
                    intent.putExtra("birth",patient.getBirthDate());
                    intent.putExtra("family",patient.getFamily());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
