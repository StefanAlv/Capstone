package com.example.karsa.data;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.karsa.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;

public class DialogForm extends DialogFragment {
    String objek, sampel;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ImageSampel");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private Uri imageUri;

    public DialogForm() {
        this.objek = objek;
        this.sampel = sampel;
    }

    TextView inputNamaSampel;
    TextView inputNamaObjek;

    ImageView imagePreview;

    ProgressBar progressBar;

    Button btnSelesai;
    Button btnChooseFile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.form_input_sampel, container, false);

        imagePreview = view.findViewById(R.id.imagePreview);
        btnChooseFile = view.findViewById(R.id.btnChooseFile);

        inputNamaSampel = view.findViewById(R.id.inputSampel);
        inputNamaObjek = view.findViewById(R.id.inputObjek);
        btnSelesai = view.findViewById(R.id.btnSelesai);

        inputNamaSampel.setText(sampel);
        inputNamaObjek.setText(objek);
        progressBar = view.findViewById(R.id.progressBar);

        btnChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galeri = new Intent();
                galeri.setAction(Intent.ACTION_GET_CONTENT);
                galeri.setType("image/*");
                startActivityForResult(galeri, 2);
            }
        });

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadToFirebase(imageUri);
            }
        });
        return view;
    }

    public void uploadToFirebase(Uri uri) {

        StorageReference fileRef = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Model model = new Model(uri.toString());
                        String modelId = databaseReference.push().getKey();
                        databaseReference.child(modelId).setValue(model);

                       Toast.makeText(getContext(), "Upload sukses", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                progressBar.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "Gagal mengupload", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==2 && resultCode == RESULT_OK && data !=null) {
            imageUri = data.getData();
            imagePreview.setImageURI(imageUri);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    private void input(EditText txt, String s) {
        txt.setError(s+ "Tidak boleh kosong");
        txt.requestFocus();
    }

    private String getFileExtension(Uri mUri) {

        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(mUri));
    }
}
