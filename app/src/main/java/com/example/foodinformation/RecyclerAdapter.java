package com.example.foodinformation;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<Model.categories> arrayList;
    BottomsheetClickListner bottomListner;

    public RecyclerAdapter(ArrayList<Model.categories> arrayList) {
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(R.layout.list_items, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Model.categories categories = arrayList.get(position);
        holder.title.setText(categories.idCategory);
        holder.message.setText(categories.strCategory);
        holder.message2.setText((categories.strCategoryDescription));
        Picasso.with(holder.itemView.getContext())
                .load(arrayList.get(position).getStrCategoryThumb())
                .into(holder.message3);
        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomListner != null) {
                    bottomListner.onItemClicked(arrayList.get(position).getStrCategoryDescription());
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView message;
        TextView message2;
        ShapeableImageView message3;
        private RecyclerView.ViewHolder holder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            message = itemView.findViewById(R.id.message);
            message2 = itemView.findViewById(R.id.message2);
            message3 = itemView.findViewById(R.id.message3);
        }
    }
}
