package com.example.pandemic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TestListAdapter extends RecyclerView.Adapter<TestListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Test> testArray = new ArrayList<>();

    public TestListAdapter() {
    }

    public TestListAdapter(Context context, ArrayList<Test> testArray) {
        this.context = context;
        this.testArray = testArray;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Test> getTestArray() {
        return testArray;
    }

    public void setTestArray(ArrayList<Test> testArray) {
        this.testArray = testArray;
    }

    @NonNull
    @Override
    public TestListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.test_layout, parent, false);
        TestListAdapter.ViewHolder viewHolder = new TestListAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TestListAdapter.ViewHolder holder, int position) {
        holder.patientNameTv.setText(testArray.get(position).getPatientName());
        holder.testDateTv.setText(testArray.get(position).getTestDate());
        holder.testCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,TesterMenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return testArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView patientNameTv, testDateTv;
        CardView testCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            patientNameTv = itemView.findViewById(R.id.patientNameTextView);
            testDateTv = itemView.findViewById(R.id.testDateTextView);
            testCardView = itemView.findViewById(R.id.testCardView);

        }

    }
}
