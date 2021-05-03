package com.example.qr_code.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qr_code.Adapter.ListAssetsAdapter;
import com.example.qr_code.Database.Assets;
import com.example.qr_code.Database.DatabaseHelper;
import com.example.qr_code.MainActivity;
import com.example.qr_code.R;

import java.util.ArrayList;

public class AssetsListActivity extends AppCompatActivity {
	//Class is defined to record the asset activity in a list
	///<summary>
	///The class consists of four attributes.
	///</summary>
    DatabaseHelper dbcenter;
    RecyclerView assetsList;
    ListAssetsAdapter assetsAdapter;
    DividerItemDecoration dividerItemDecoration;

	// onCreate() is a builtin method defined in 'appcompt' library.
	// This mthod is run for the first time when this activity is created.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assets_list_activity);

        dbcenter = new DatabaseHelper(this);
        showData();
    }

	//onBackPressed() is a builtin method, defined in 'appcompat' library.
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent myIntent = new Intent(AssetsListActivity.this, MainActivity.class);
        AssetsListActivity.this.startActivity(myIntent);
    }

	// showData() is a user defined function intended to show the assets after processing.
    public void showData(){
        ArrayList<Assets> asset = dbcenter.getAssets();

        assetsList = (RecyclerView) findViewById(R.id.rc_assets);
        assetsAdapter = new ListAssetsAdapter(this, asset);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        assetsList.setLayoutManager(layoutManager);

        dividerItemDecoration = new DividerItemDecoration(assetsList.getContext(), LinearLayoutManager.VERTICAL);
        assetsList.addItemDecoration(dividerItemDecoration);

        assetsList.setItemAnimator(new DefaultItemAnimator());
        assetsList.setAdapter(assetsAdapter);
    }
}
