package com.example.pandemic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TestKitListAdapter extends RecyclerView.Adapter<TestKitListAdapter.ViewHolder>{

    private Context context;
    private ArrayList<TestKit> TestKit = new ArrayList<>();

    public TestKitListAdapter() {
    }

    public TestKitListAdapter(Context context, ArrayList<com.example.pandemic.TestKit> testKit) {
        this.context = context;
        TestKit = testKit;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.test_kit_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return TestKit.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView testKitNameTv, testKitAmountTv;
        ImageView upBtn, downBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
