package com.example.pandemic;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TestListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
