package com.hadyandev.charalist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class CardViewCharacterAdapter extends RecyclerView.Adapter<CardViewCharacterAdapter.CardViewViewHolder> {
    private Context context;
    private ArrayList<Character> listCharacter;

    public CardViewCharacterAdapter(Context context) {
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
    public CardViewCharacterAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_character, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewCharacterAdapter.CardViewViewHolder cardViewViewHolder, int i) {
        Character c = getListCharacter().get(i);
        Glide.with(context)
                .load(c.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(cardViewViewHolder.imgPhoto);
        cardViewViewHolder.tvName.setText(c.getName());
        cardViewViewHolder.tvAnime.setText(c.getAnime());
        cardViewViewHolder.btnFavorite.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Favorite "+getListCharacter().get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        }));
        cardViewViewHolder.btnShare.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Share "+getListCharacter().get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public int getItemCount() {
        return getListCharacter().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvAnime;
        Button btnFavorite, btnShare;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvAnime = itemView.findViewById(R.id.tv_item_anime);
            btnFavorite = itemView.findViewById(R.id.btn_set_favorite);
            btnShare = itemView.findViewById(R.id.btn_set_share);
        }
    }
}
