package com.example.qr_code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qr_code.RecyclerView.AssetsListActivity;

public class MainActivity extends AppCompatActivity {
	
	// Class for Main Activity of the App
	///<summary>
	///This class includes three attributes and methods: onCreate
	///</summary>

    Button generator, scanner, listData;

	// onCreate() is a builtin method defined in 'appcompt' library.
	// This mthod is run for the first time when this activity is created.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generator = findViewById(R.id.generate);
        scanner = findViewById(R.id.scan);
        listData = findViewById(R.id.list);

        generator.setOnClickListener(new View.OnClickListener() {
			//onClick() is an instantiation of the object generator. Its a built in method, defined in 'view' library.
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, GeneratorActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, ScannerResultActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        listData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, AssetsListActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
	
	//onBackPressed() is a builtin method, defined in 'appcompat' library.
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
