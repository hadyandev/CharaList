package com.hadyandev.charalist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListCharacterAdapter extends RecyclerView.Adapter<ListCharacterAdapter.CategoryViewHolder> {
    private Context context;
    private ArrayList<Character> listCharacter;

    public ListCharacterAdapter(Context context) {
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
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_character, viewGroup, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int position) {
        categoryViewHolder.tvName.setText(getListCharacter().get(position).getName());
        categoryViewHolder.tvAnime.setText(getListCharacter().get(position).getAnime());
        Glide.with(context)
                .load(getListCharacter().get(position).getPhoto())
                .apply(new RequestOptions().override(55, 55))
                .into(categoryViewHolder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return getListCharacter().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvAnime;
        ImageView imgPhoto;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvAnime = itemView.findViewById(R.id.tv_item_anime);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }
}
