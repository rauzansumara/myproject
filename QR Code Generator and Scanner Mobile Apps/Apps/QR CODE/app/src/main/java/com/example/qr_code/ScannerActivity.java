package com.example.qr_code;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
	// class for carrying out Scanning activity.
	///<summary>
	///The class consists of one attribute.
	///</summary>
    private ZXingScannerView mScannerView;
	
	// onCreate() is a builtin method defined in 'appcompt' library.
	// This mthod is run for the first time when this activity is created.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }
	
	// handleResult() is a builtin function defined in 'zxing' library.
	// The function is executed to handle the result by substituting the result to the object 'tvresult'.
    @Override
    public void handleResult(Result rawResult) {
        ScannerResultActivity.tvresult.setText(rawResult.getText());
        onBackPressed();

        mScannerView.resumeCameraPreview(this);
    }
	
	// onPause() is a builtin method defined in 'appcompat' library.
	// The function is executed to handle the activity, when it is paused.
    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }
	
	// onResume() is a builtin method defined in 'appcompat' library.
	// The function is executed to handle the activity, when it is resumed.
    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

	// onPointerCaptureChanged() is a built in method defined in 'appcompat' library.
	// The function is responsible to record all the changes made on the screen.
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
