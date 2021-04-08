package com.example.pandemic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ManagerListAdapter extends RecyclerView.Adapter<ManagerListAdapter.ViewHolder> {
   private Context context;
   private ArrayList<Users> managerArray = new ArrayList<>();

   public ManagerListAdapter() {
   }

   public ManagerListAdapter(Context context, ArrayList<Users> managerArray) {
      this.context = context;
      this.managerArray = managerArray;
   }

   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(context);
      View view = inflater.inflate(R.layout.manager_list_tamplate, parent, false);
      ViewHolder viewHolder = new ViewHolder(view);

      return viewHolder;
   }

   @Override
   public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      holder.managerNameTv.setText(managerArray.get(position).getName());
      if (managerArray.get(position).getStatus().equals("n")){
         holder.managerStatusTv.setText("Waiting for Approval");
      }else if (managerArray.get(position).getStatus().equals("r")){
         holder.managerStatusTv.setText("Need to Register Test Center");
      }else{
         holder.managerStatusTv.setText("Approved");
      }
   }

   @Override
   public int getItemCount() {
      return managerArray.size();
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      TextView managerNameTv, managerStatusTv;
      CardView managerCard;

      public ViewHolder(@NonNull View itemView) {
         super(itemView);
         managerNameTv = itemView.findViewById(R.id.managerNameTv);
         managerStatusTv = itemView.findViewById(R.id.statusTv);
         managerCard = itemView.findViewById(R.id.managerCard);
      }
   }
}
