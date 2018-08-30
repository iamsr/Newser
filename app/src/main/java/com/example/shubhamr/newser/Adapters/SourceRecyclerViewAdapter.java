package com.example.shubhamr.newser.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shubhamr.newser.Interface.ClickListenerInterface;
import com.example.shubhamr.newser.ModelClass.Source;
import com.example.shubhamr.newser.R;

import java.util.List;

public class SourceRecyclerViewAdapter extends RecyclerView.Adapter<SourceRecyclerViewAdapter.SourceViewHolder> {

    private List<Source> sourceList;
    private ClickListenerInterface clickListeners = null;



    public SourceRecyclerViewAdapter(List<Source> sourceList){
        this.sourceList=sourceList;
    }


    public class SourceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView sourceTitle, sourceDescription;

        public SourceViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            sourceTitle = view.findViewById(R.id.sourceTitle);
            sourceDescription = view.findViewById(R.id.sourceDescription);
        }


        @Override
        public void onClick(View view) {
            if (clickListeners != null) {
                clickListeners.itemClicked(view, getAdapterPosition());
            }

        }
    }



    @Override
    public SourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.source_recyclerview_layout, parent, false);
        return new SourceViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull SourceViewHolder holder, int position) {

        // Retrieving source from list and setting it in respected textView
        Source source = sourceList.get(position);
        holder.sourceTitle.setText(source.getName());
        holder.sourceDescription.setText(source.getDescription());

    }



    // Return number of sources in list
    @Override
    public int getItemCount() {
        return sourceList.size();
    }

    public Source getSourceFromList(int index){
        return sourceList.get(index);
    }


    // Setting listener
    public void setClickListeners(ClickListenerInterface clickListeners){
        this.clickListeners = clickListeners;
    }






    }
