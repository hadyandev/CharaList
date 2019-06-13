package com.hadyandev.charalist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class GridCharacterAdapter extends RecyclerView.Adapter<GridCharacterAdapter.GridViewHolder>{
    private Context context;
    private ArrayList<Character> listCharacter;

    public GridCharacterAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Character> getListCharacter() {
        return listCharacter;
    }

    public void setListCharacter(ArrayList<Character> listCharacter) {
        this.listCharacter = listCharacter;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_character, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        Glide.with(context)
                .load(getListCharacter().get(position).getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return getListCharacter().size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }
}
