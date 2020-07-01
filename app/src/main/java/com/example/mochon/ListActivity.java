package com.example.mochon;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListActivity extends AppCompatActivity {
    public static ArrayList<MainItem> list = new ArrayList<>();
    private MainAdapter mainAdapter;
    private MainItem item;
    RecyclerView recyclerView;
    String[] Title;
    String[] Description;
    String tText, dText;
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.list_recycler);

        FloatingActionButton fb = findViewById(R.id.add_fb);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, AddActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        init();

        mainAdapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(ListActivity.this, EditActivity.class);
                intent.putExtra("pos", position);
                intent.putExtra("title_edit", list.get(position).getTitle());
                intent.putExtra("des_edit", list.get(position).getDes());
                intent.putExtra("import_edit", list.get(position).getRating_import());
                intent.putExtra("time_edit", list.get(position).getRating_time());
                intent.putExtra("success_edit", list.get(position).getRating_success());

                startActivityForResult(intent, 2);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == 1){
                item = new MainItem(data.getStringExtra("title"), data.getStringExtra("des"),
                        data.getFloatExtra("import_rate", 0), data.getFloatExtra("time_rate", 0),
                        data.getFloatExtra("success_rate", 0));
                list.add(item);
                mainAdapter.notifyDataSetChanged();
                Log.d("List_PRINT", String.valueOf(item.getRating_import()));

            }else if (requestCode == 2) {
                item = new MainItem(data.getStringExtra("title_edit"), data.getStringExtra("des_edit"),
                        data.getFloatExtra("import_edit", 0),data.getFloatExtra("time_edit", 0),
                        data.getFloatExtra("success_edit", 0));
                list.set(data.getIntExtra("position", 0), item);
                mainAdapter.notifyDataSetChanged();
                Log.d("List_PRINT", String.valueOf(item.getRating_import()));

            }
        }
    }


    void init(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        mainAdapter = new MainAdapter(list);
        recyclerView.setAdapter(mainAdapter);

        ItemTouchHelper.Callback callback = new ItemMoveCallback(mainAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        getArrayList("items");
    }


    @Override
    protected void onPause() {
        super.onPause();

        listSelected();
        saveArrayList(list, "items");
    }


    public void saveArrayList(ArrayList<MainItem> items, String key){
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(items);
        editor.putString(key, json);
        editor.apply();
        if(!list.isEmpty()){
            Log.d("LIST_PRINT", list.get(0).getTitle());
        }
    }

    public ArrayList<MainItem> getArrayList(String key){
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<MainItem>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void listSelected(){
        int size = list.size();
        for(int i = 0; i < size; i++){
            if(list.get(i).isSelected()){
                list.add(list.get(i));
                list.remove(i);
                size--;
                i--;
            }
        }
        mainAdapter.notifyDataSetChanged();
    }



}
