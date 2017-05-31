package com.jatin.epilentask.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jatin.epilentask.R;
import com.jatin.epilentask.model.Speciality;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by uw on 31/5/17.
 */

public class SpecialityAdapter extends RecyclerView.Adapter<SpecialityAdapter.ViewHolder> {

    List<Speciality> specialityList = new ArrayList<>();

    public SpecialityAdapter(List<Speciality> specialityList) {
        this.specialityList = specialityList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.speciality_list_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Speciality speciality = specialityList.get(position);
        holder.txt_name.setText(speciality.getName());
    }

    @Override
    public int getItemCount() {
        return specialityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_name)
        TextView txt_name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
