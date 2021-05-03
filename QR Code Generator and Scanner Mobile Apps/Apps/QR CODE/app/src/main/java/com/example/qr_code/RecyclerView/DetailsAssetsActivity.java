package com.example.qr_code.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qr_code.Database.Assets;
import com.example.qr_code.Database.DatabaseHelper;
import com.example.qr_code.MainActivity;
import com.example.qr_code.R;

public class DetailsAssetsActivity extends AppCompatActivity {

	//the class is defined to store the details of the assets activities in the data base.
	///<summary>
	///the class consists of six attributes.
	///</summary>
    Button update, delete;
    TextView name, description, amount, location;

    DatabaseHelper db;

	// onCreate() is a builtin method defined in 'appcompt' library.
	// This mthod is run for the first time when this activity is created.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_assets);

        db = new DatabaseHelper(this);

        name = findViewById(R.id.editTextName);
        description = findViewById(R.id.editTextDescription);
        amount = findViewById(R.id.editTextAmount);
        location = findViewById(R.id.editTextLocation);

        update = findViewById(R.id.button_update);
        delete = findViewById(R.id.button_delete);

        Bundle bundle = getIntent().getExtras();
        final String id = bundle.getString("id");

        final Assets asts = db.getAssetsById(id);

        Log.e("Message : ", String.valueOf(asts.getId()));

        if (asts.getId() != null ){
            name.setText(asts.getName());
            description.setText(asts.getDescription());
            amount.setText(asts.getAmount());
            location.setText(asts.getLocation());
        }else {
            Toast.makeText(getApplicationContext(), "Data does not exist", Toast.LENGTH_LONG).show();

            Intent myIntent = new Intent(DetailsAssetsActivity.this, AssetsListActivity.class);
            DetailsAssetsActivity.this.startActivity(myIntent);
        }

        delete.setOnClickListener(new View.OnClickListener() {
			// onCreate() is a builtin method defined in 'appcompt' library.
			// This method is run for the first time when setonClickListner() is called.
            @Override
            public void onClick(View v) {
                db.deleteAssets(id);

                Intent myIntent = new Intent(DetailsAssetsActivity.this, AssetsListActivity.class);
                DetailsAssetsActivity.this.startActivity(myIntent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
			// onCreate() is a builtin method defined in 'appcompt' library.
			// This method is run for the first time when setonClickListner() is called.
            @Override
            public void onClick(View v) {
                String updateName = name.getText().toString();
                String updateDesc = description.getText().toString();
                String updateAmount = amount.getText().toString();
                String updateLocation = location.getText().toString();

                if (updateName != null && !updateName.isEmpty()) {
                    if(updateDesc != null && !updateDesc.isEmpty() ){
                        if (updateAmount != null && !updateAmount.isEmpty()){
                            if(updateLocation != null && !updateLocation.isEmpty()){

                                Assets assets = new Assets();
                                assets.setId(asts.getId());
                                assets.setName(updateName);
                                assets.setDescription(updateDesc);
                                assets.setAmount(updateAmount);
                                assets.setLocation(updateLocation);

                                db.updateAssets(assets);
                                Intent myIntent = new Intent(DetailsAssetsActivity.this, MainActivity.class);
                                DetailsAssetsActivity.this.startActivity(myIntent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Please fill Location column", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Please fill amount column", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Please fill description column", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Please fill name column", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

	//onBackPressed() is a builtin method, defined in 'appcompat' library.
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent myIntent = new Intent(DetailsAssetsActivity.this, MainActivity.class);
        DetailsAssetsActivity.this.startActivity(myIntent);
    }
}
