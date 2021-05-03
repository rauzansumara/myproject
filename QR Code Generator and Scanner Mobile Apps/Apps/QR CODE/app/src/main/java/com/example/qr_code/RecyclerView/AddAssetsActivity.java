package com.example.qr_code.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qr_code.Database.Assets;
import com.example.qr_code.Database.DatabaseHelper;
import com.example.qr_code.MainActivity;
import com.example.qr_code.R;

import java.util.ArrayList;

public class AddAssetsActivity extends AppCompatActivity {
	// class is defined to add asset activity to the data base
	///<summary>
	///The class consists of ten attributes.
	///</summary>

    String assets_id, assets_name, assets_desc, assets_amount, assets_location;
    Button addToButton;
    EditText nameEditText, descEditText, amountEditText, locationEditText;

    ArrayList<Assets> assets_data = new ArrayList<>();
    DatabaseHelper dbcenter;

	// onCreate() is a builtin method defined in 'appcompt' library.
	// This method is run for the first time when this activity is created.
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        dbcenter = new DatabaseHelper(this);

        nameEditText = findViewById(R.id.name_edittext);
        descEditText = findViewById(R.id.description_edittext);
        amountEditText = findViewById(R.id.amount_edittext);
        locationEditText = findViewById(R.id.location_edittext);

        addToButton = findViewById(R.id.add_record);

        Bundle bundle = getIntent().getExtras();
        assets_id = bundle.getString("assets_id");

        Log.e("DATA : ", assets_id);

        addToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assets_name = nameEditText.getText().toString();
                assets_desc = descEditText.getText().toString();
                assets_amount = amountEditText.getText().toString();
                assets_location = locationEditText.getText().toString();

                if (assets_name != null && !assets_name.isEmpty() ){
                    if(assets_desc != null && !assets_desc.isEmpty() ){
                       if (assets_amount != null && !assets_amount.isEmpty()){
                           if(assets_location != null && !assets_location.isEmpty()){
                               Assets assets = new Assets();

                               assets.setId(assets_id);
                               assets.setName(assets_name);
                               assets.setDescription(assets_desc);
                               assets.setAmount(assets_amount);
                               assets.setLocation(assets_location);

                               dbcenter.insertAssets(assets);
                               Toast.makeText(getApplicationContext(),"Successful", Toast.LENGTH_SHORT).show();

                               Intent myIntent = new Intent(AddAssetsActivity.this, MainActivity.class);
                               AddAssetsActivity.this.startActivity(myIntent);
                           } else{
                               Toast.makeText(getApplicationContext(), "Please Insert Asset Location", Toast.LENGTH_SHORT).show();
                           }
                       } else {
                           Toast.makeText(getApplicationContext(),"Please fill amount column", Toast.LENGTH_SHORT).show();
                       }
                    } else {
                        Toast.makeText(getApplicationContext(),"Please fill description column", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Please fill name column", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

	//onBackPressed() is a builtin method, defined in 'appcompat' library.
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent myIntent = new Intent(AddAssetsActivity.this, MainActivity.class);
        AddAssetsActivity.this.startActivity(myIntent);
    }
}
