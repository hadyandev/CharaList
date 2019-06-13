package com.hadyandev.charalist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvCategory;
    private ArrayList<Character> list = new ArrayList<>();
    final String STATE_TITLE = "state_string";
    final String STATE_LIST = "state_list";
    final String STATE_MODE = "state_mode";
    int mode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        if (savedInstanceState == null) {
            setActionBarTitle("Mode List");
            list.addAll(CharacterData.getListData());
            showRecyclerList();
            mode = R.id.action_list;
        } else {
            String stateTitle = savedInstanceState.getString(STATE_TITLE);
            ArrayList<Character> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            int stateMode = savedInstanceState.getInt(STATE_MODE);
            setActionBarTitle(stateTitle);
            list.addAll(stateList);
            setMode(stateMode);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_TITLE, getSupportActionBar().getTitle().toString());
        outState.putParcelableArrayList(STATE_LIST, list);
        outState.putInt(STATE_MODE, mode);
    }

    private void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    private void showRecyclerList(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        ListCharacterAdapter listCharacterAdapter = new ListCharacterAdapter(this);
        listCharacterAdapter.setListCharacter(list);
        rvCategory.setAdapter(listCharacterAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
              showCharacterDetails(list.get(position));
            }
        });
    }

    private void showRecyclerGrid(){
        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        GridCharacterAdapter gridCharacterAdapter = new GridCharacterAdapter(this);
        gridCharacterAdapter.setListCharacter(list);
        rvCategory.setAdapter(gridCharacterAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showCharacterDetails(list.get(position));
            }
        });
    }
    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardViewCharacterAdapter cardViewCharacterAdapter = new CardViewCharacterAdapter(this);
        cardViewCharacterAdapter.setListCharacter(list);
        rvCategory.setAdapter(cardViewCharacterAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showCharacterDetails(list.get(position));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedMode) {
        String title = null;
        switch (selectedMode) {
            case R.id.action_list:
                title = "Mode List";
                showRecyclerList();
                break;

            case R.id.action_grid:
                title = "Mode Grid";
                showRecyclerGrid();
                break;

            case R.id.action_cardview:
                title = "Mode CardView";
                showRecyclerCardView();
                break;
        }
        mode = selectedMode;
        setActionBarTitle(title);
    }

    private void showCharacterDetails(Character character){
        Intent characterDetailsIntent = new Intent(MainActivity.this, CharacterDetailsActivity.class);
        characterDetailsIntent.putExtra(CharacterDetailsActivity.CHARACTER_NAME, character.getName());
        characterDetailsIntent.putExtra(CharacterDetailsActivity.CHARACTER_ANIME, character.getAnime());
        characterDetailsIntent.putExtra(CharacterDetailsActivity.CHARACTER_DESCRIPTION, character.getDescription());
        characterDetailsIntent.putExtra(CharacterDetailsActivity.CHARACTER_QUOTE, character.getQuote());
        characterDetailsIntent.putExtra(CharacterDetailsActivity.CHARACTER_PHOTO, character.getPhoto());
        startActivity(characterDetailsIntent);
    }

}
