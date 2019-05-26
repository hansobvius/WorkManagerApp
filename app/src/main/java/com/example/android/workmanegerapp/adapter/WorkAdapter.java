package com.example.android.workmanegerapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.workmanegerapp.R;
import com.example.android.workmanegerapp.entity.Work;

import java.util.ArrayList;
import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.WorkViewHolder> {

    private static final int EMPTY_COUNT = 0;

    private Context context;
    private List<Work> workList = new ArrayList<>();

    public WorkAdapter(Context context, List<Work> workList){
        this.context = context;
        this.workList = workList;
    }

    @Override
    public WorkAdapter.WorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(this.context).inflate(R.layout.recycler_main_content, parent, false);
        return new WorkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkViewHolder holder, int position){
        Work action = workList.get(position);
        holder.mTextView.setText(action.getString());
        holder.mDateView.setText(String.valueOf(action.getDate()));
    }

    @Override
    public int getItemCount(){
        return workList != null ? workList.size() : EMPTY_COUNT;
    }

    public class WorkViewHolder extends RecyclerView.ViewHolder{

        TextView mTextView;
        TextView mDateView;

        private WorkViewHolder(final View view){
            super(view);
            mTextView = view.findViewById(R.id.recycler_state_manager_view);
            mDateView = view.findViewById(R.id.timestamp_text_view);
        }
    }
}
