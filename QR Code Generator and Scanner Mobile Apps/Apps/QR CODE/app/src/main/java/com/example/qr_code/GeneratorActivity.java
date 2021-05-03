package com.example.qr_code;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.qr_code.RecyclerView.AddAssetsActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GeneratorActivity extends AppCompatActivity {
	// class for generation of avtivity in the app.
	///<summary>
	///The class consists of 8 attributes.
	///</summary>
	
    ImageView imageView;
    Button button, save, insertData;
    EditText editText;
    String EditTextValue ;
    public final static int QRcodeWidth = 500 ;
    Bitmap bitmap ;
    String TAG = "DEBUG";
	
	// onCreate() is a builtin method defined in 'appcompt' library.
	// This mthod is run for the first time when this activity is created.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);

        imageView = findViewById(R.id.imageView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        save = findViewById(R.id.save);
        insertData = findViewById(R.id.insert);

        button.setOnClickListener(new View.OnClickListener() {
			
			//onClick() is an instantiation of the object button. Its a built in method, defined in 'view' class.
            @Override
            public void onClick(View v) {
                EditTextValue = editText.getText().toString();
                if(EditTextValue.equals("")){
                    Toast.makeText(getApplicationContext(),"Please Insert Asset ID", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        bitmap = TextToImageEncode(EditTextValue);
                        imageView.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTextValue = editText.getText().toString();
                if(EditTextValue.equals("")){
                    Toast.makeText(getApplicationContext(),"Please Insert Asset ID", Toast.LENGTH_SHORT).show();
                }
                else {
                    saveImage(bitmap);
                }
            }
        });

        insertData.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditTextValue = editText.getText().toString();
                if(EditTextValue.equals("")){
                    Toast.makeText(getApplicationContext(),"Please Insert Asset ID", Toast.LENGTH_SHORT).show();
                }
                else {
                    Bundle bundle = new Bundle();
                    bundle.putString("assets_id", EditTextValue);
                    Intent myIntent = new Intent(GeneratorActivity.this, AddAssetsActivity.class);
                    myIntent.putExtras(bundle);
                    GeneratorActivity.this.startActivity(myIntent);
                }
            }
        });
    }

	//onBackPressed() is a builtin method, defined in 'appcompat' class.
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent myIntent = new Intent(GeneratorActivity.this, MainActivity.class);
        GeneratorActivity.this.startActivity(myIntent);
    }
	
	// TextToImageEncode() is a builtin function defined in 'xzing' library.
    Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {
            return null;
        }

        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeight = bitMatrix.getHeight();
        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {
                pixels[offset + x] = bitMatrix.get(x, y) ?
                        ContextCompat.getColor(getApplicationContext(), R.color.QRCodeBlackColor):
                        ContextCompat.getColor(getApplicationContext(), R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
	
	// saveImage() is a builtin function executed to save the QR Code image in memory.
    private String saveImage(Bitmap image) {
        String savedImagePath = null;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US);
        Date now = new Date();
        String imageFileName = "QR-CODE_"+formatter.format(now)+".jpg";

        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/QR-CODE");
        boolean success = true;

        if (!storageDir.exists()) {
            Log.e(TAG, "saveImage: The process here");
            success = storageDir.mkdirs();
        }
        if (success) {
            File imageFile = new File(storageDir, imageFileName);
            savedImagePath = imageFile.getAbsolutePath();
            try {
                OutputStream fOut = new FileOutputStream(imageFile);
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                fOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Add the image to the system gallery
            galleryAddPic(savedImagePath);
            Toast.makeText(getApplicationContext(), "IMAGE SAVED", Toast.LENGTH_SHORT).show();
        }
        return savedImagePath;
    }
	
	//galleryAddPic() is defined to register the taken QR-Code picture in to the device's gallery.
    private void galleryAddPic(String imagePath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(imagePath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);
    }
}