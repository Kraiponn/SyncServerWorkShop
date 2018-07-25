package com.example.ksn_dev.syncserverworkshop.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ksn_dev.syncserverworkshop.R;
import com.example.ksn_dev.syncserverworkshop.dao.ItemUpload;
import com.example.ksn_dev.syncserverworkshop.manager.HttpUploadFile;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitUploadActivity extends AppCompatActivity {
    private Button btnUpload, btnSelectImage;
    private TextView tvResult;
    private ImageView imgUpload;
    private final int REQUEST_IMAGE_ID = 9999;
    private Bitmap mBmp;
    private String part_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_upload);
        if (Build.VERSION.SDK_INT >= 21) {
            requestRunTimePermission();
        } else {
            //
        }

        initInstance();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_ID)
        {
            if (data == null) {
                toastMsg("Select picture fail");
                return;
            }

            Uri imgUri = data.getData();
            String[] filePathCol = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(
                    imgUri, filePathCol,
                    null, null, null
            );

            if (cursor != null) {
                cursor.moveToFirst();
                int imgIndex = cursor.getColumnIndex(filePathCol[0]);
                part_image = cursor.getString(imgIndex);

                if (part_image != null && !part_image.equals("")) {
                    File fileImage = new File(part_image);
                    imgUpload.setImageBitmap(
                            BitmapFactory.decodeFile(fileImage.getAbsolutePath())
                    );
                } else {
                    toastMsg("Picture is fail");
                }
            }

        }

    }



    private void requestRunTimePermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                )
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        //
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }


    private void initInstance() {
        imgUpload = findViewById(R.id.image_Upload);
        btnUpload = findViewById(R.id.button_upload);
        btnSelectImage = findViewById(R.id.button_select);
        tvResult = findViewById(R.id.text_result);

        btnSelectImage.setOnClickListener(btnSelectImgClick);
        btnUpload.setOnClickListener(btnUploadClickListener);
        imgUpload.setOnClickListener(imgUploadClickListener);
    }


    private void uploadFile() {
        if (imgUpload.getDrawable() == null) {
            toastMsg("Please pick image for upload");
            return;
        }

        File imgFile = new File(part_image);
        final RequestBody reqBody = RequestBody.create(
                MediaType.parse("multipart/form-file"), imgFile
        );
        MultipartBody.Part imgPart = MultipartBody.Part.createFormData(
                "imageupload", imgFile.getName(), reqBody
        );

        Call<ItemUpload> call =
                HttpUploadFile.getInstance().getApi().uploadImage(imgPart);

        call.enqueue(new Callback<ItemUpload>() {
            @Override
            public void onResponse(Call<ItemUpload> call,
                                   Response<ItemUpload> response) {
                if (response.isSuccessful()) {
                    ItemUpload item = response.body();
                    tvResult.setText(item.getErrMsg());
                    String str = item.getErrFlag();
                    str += item.getErrMsg();
                    tvResult.setText(str);
                    toastMsg(str);
                } else {
                    tvResult.setText(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ItemUpload> call, Throwable t) {
                toastMsg(t.getMessage());
                tvResult.setText("Throwable " + t.getMessage());
            }
        });
    }


    private void selectImage() {
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        );
        startActivityForResult(
                Intent.createChooser(intent,
                        "open gallery"),REQUEST_IMAGE_ID);
    }


    private void toastMsg(String text) {
        Toast.makeText(this,
                text,
                Toast.LENGTH_SHORT).show();
    }


    /**************************************
     *  Listener Zone
     */
    View.OnClickListener btnUploadClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            uploadFile();
        }
    };

    View.OnClickListener imgUploadClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //
        }
    };

    View.OnClickListener btnSelectImgClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            selectImage();
        }
    };


}
