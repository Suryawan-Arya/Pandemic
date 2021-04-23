package com.example.pandemic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

   Context context;
   ArrayList<Test> testArray = new ArrayList<>();

   public TestAdapter() {
   }

   public TestAdapter(Context context, ArrayList<Test> testArray) {
      this.context = context;
      this.testArray = testArray;
   }

   @NonNull
   @Override
   public TestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(context);
      View view = inflater.inflate(R.layout.test_list_layout, parent, false);
      ViewHolder viewHolder = new ViewHolder(view);

      return viewHolder;
   }

   @Override
   public void onBindViewHolder(@NonNull TestAdapter.ViewHolder holder, int position) {
      holder.patientNameTv.setText(testArray.get(position).getPatientName());
      holder.recordedDateTv.setText(testArray.get(position).getTestDate());
      holder.resultDateTv.setText(testArray.get(position).getResultDate());
      holder.patientTest.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent intent = new Intent(context,TestDetailActivity.class);
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
      TextView patientNameTv, recordedDateTv, resultDateTv;
      LinearLayout patientTest;

      public ViewHolder(@NonNull View itemView) {
         super(itemView);
         patientNameTv = itemView.findViewById(R.id.patientNameText);
         recordedDateTv = itemView.findViewById(R.id.recordedDateText);
         resultDateTv = itemView.findViewById(R.id.resultDateText);
         patientTest = itemView.findViewById(R.id.patientTest);
      }
   }
}
