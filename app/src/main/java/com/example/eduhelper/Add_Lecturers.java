package com.example.eduhelper;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Lecturers extends AppCompatActivity {

     private static final int PICK_IMG_REQUEST = 1;
     EditText lecturerName, lecturerModule, lecturerLocation, lecturerEmail, lecturerContact;
     Button insertBtn, chooseImageBtn;
    // ImageView lecturerImage;
     FirebaseDatabase rootNode;
     DatabaseReference mDatabase;

//    String mStoragePath = "Lecturers/";
//    String mDatabasePath = "lecturer";
//    Uri mFilePathUri;
//    StorageReference mStorageReference;
//    ProgressDialog mProgressDialog;
//    int IMAGE_REQUEST_CODE = 5;


     Uri fileuri;

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == IMAGE_REQUEST_CODE
//                && requestCode == RESULT_OK
//                && data != null
//                && data.getData() != null){
//            mFilePathUri = data.getData();
//
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mFilePathUri);
//                lecturerImage.setImageBitmap(bitmap);
//            }
//            catch (Exception e){
//                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__lecturers);

        lecturerName = findViewById(R.id.name);
        lecturerModule = findViewById(R.id.moduleCode);
        lecturerLocation = findViewById(R.id.location);
        lecturerEmail = findViewById(R.id.email);
        lecturerContact = findViewById(R.id.contactNumber);

        insertBtn = findViewById(R.id.insertLecturer);
        chooseImageBtn = findViewById(R.id.chooseImage);

        //lecturerImage = findViewById(R.id.imgAdd);

//        lecturerImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Image"),IMAGE_REQUEST_CODE);
//            }
//        });

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootNode = FirebaseDatabase.getInstance();
                mDatabase = rootNode.getReference("lecturer");
                //mStorageReference  = FirebaseStorage.getInstance().getReference();
                //mProgressDialog = new ProgressDialog(Add_Lecturers.this);

                String lname =  lecturerName.getText().toString();
                String lmoduleCode = lecturerModule.getText().toString();
                String llocation = lecturerLocation.getText().toString();
                String lemail = lecturerEmail.getText().toString();
                String lcontactNumber = lecturerContact.getText().toString();

                lecturerHelper lecHelper = new lecturerHelper(lname, lmoduleCode, llocation, lemail, lcontactNumber);
                if (mDatabase != null) {
                    mDatabase.child(lname).setValue(lecHelper);
                    Toast.makeText(Add_Lecturers.this,"Insert Successful.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), List_Lecturers.class));
                }else{
                    Toast.makeText(Add_Lecturers.this, "Error !", Toast.LENGTH_SHORT).show();
                }
                
                //uploadDataToFirebase();
            }

        });

//            mStorageReference = FirebaseStorage.getInstance().getReference();
//            mDatabase = FirebaseDatabase.getInstance().getReference(mDatabasePath);
//            mProgressDialog = new ProgressDialog(Add_Lecturers.this);





//        chooseImageBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                OpenImageChooser();
//            }
//        });
    }

//    private void uploadDataToFirebase() {
//        if (mFilePathUri != null){
//            mProgressDialog.setTitle("Image is Uploading..");
//            mProgressDialog.show();
//            StorageReference storageReference2nd = mStorageReference.child(mStoragePath + System.currentTimeMillis()+"." + getFileExtension(mFilePathUri));
//
//            storageReference2nd.putFile(mFilePathUri)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            String lname =  lecturerName.getText().toString();
//                            String lmoduleCode = lecturerModule.getText().toString();
//                            String llocation = lecturerLocation.getText().toString();
//                            String lemail = lecturerEmail.getText().toString();
//                            String lcontactNumber = lecturerContact.getText().toString();
//
//                            mProgressDialog.dismiss();
//                            Toast.makeText(Add_Lecturers.this, "Image Uploded..", Toast.LENGTH_SHORT).show();
//                            lecturerHelper lecHelper = new lecturerHelper(lname, lmoduleCode, llocation, lemail, lcontactNumber, taskSnapshot.getUploadSessionUri().toString());
//                            String imageUploadId = mDatabase.push().getKey();
//                            mDatabase.child(imageUploadId).setValue(lecHelper);
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            mProgressDialog.dismiss();
//                            Toast.makeText(Add_Lecturers.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
//                            mProgressDialog.setTitle("Image is Uploading");
//                        }
//                    });
//        }
//        else{
//            Toast.makeText(this,"please select an image or add image name", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private String getFileExtension(Uri uri) {
//        ContentResolver contentResolver = getContentResolver();
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
//
//    }

//    private void OpenImageChooser() {
//        Intent intent = new Intent();
//
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent,PICK_IMG_REQUEST);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == PICK_IMG_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
//
//            fileuri = data.getData();
//        }
//    }
}
