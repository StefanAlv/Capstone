package com.example.karsa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.karsa.TensorFlowImageClassifier.Classifier;
import com.example.karsa.TensorFlowImageClassifier.TensorFlowImageClassifier;
import com.example.karsa.data.Model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class FiturDeteksiActivity extends AppCompatActivity {

    private static final String MODEL_PATH = "mobilenet_quant_v1_224.tflite";
    private static final String LABEL_PATH = "labels.txt";
    private static final int INPUT_SIZE = 224;

    private Classifier classifier;
    private CompositeDisposable compositeDisposable;

    private CameraView cameraView;
    private Button btnCapture, btnReCapture;
    private ImageView imgPreview, previewResult;
    private TextView tvResult, tv1, tv2;
    private String strr;
    private CardView cardView, cardViewTerjemah;

    private ArrayList<Model> namaModel;
    private DatabaseReference sampel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitur_deteksi);
        compositeDisposable = new CompositeDisposable();

        cameraView = findViewById(R.id.camera);
        btnCapture = findViewById(R.id.btn_capture);
        imgPreview = findViewById(R.id.preview);
        tvResult = findViewById(R.id.tv_result);
        btnReCapture = findViewById(R.id.btn_recapture);
        previewResult = findViewById(R.id.previewResult);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        cardView = findViewById(R.id.cardView);
        cardViewTerjemah = findViewById(R.id.cardViewTerjemah);

        cameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {
                Log.d("CameraKitListener", "image captured!");
                Bitmap bitmap = cameraKitImage.getBitmap();
                bitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, false);
                final List<Classifier.Recognition> results = classifier.recognizeImage(bitmap);
                showPreview(true, bitmap, generateResults(results));
            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }
        });
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraView.captureImage();
            }
        });

        btnReCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPreview(false,null,null);
            }
        });

        initTensorFlow().subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onComplete() {
                Log.i("initTensorFlow", "complete");
                showPreview(false,null,null);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("initTensorFlow", e.getMessage());
            }
        });
    }

    private void dbSampel() {
        namaModel = new ArrayList();
        sampel = FirebaseDatabase.getInstance().getReference("ImageSampel");
        sampel.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Model model = dataSnapshot.getValue(Model.class);
                    if (model.getNamaSampel().equals(strr)) {
                        namaModel.add(model);
                        Glide.with(FiturDeteksiActivity.this)
                            .load(model.getImageUri())
                                .into(previewResult);
                        Toast.makeText(FiturDeteksiActivity.this, model.getImageUri(), Toast.LENGTH_SHORT).show();
                    } else {
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        cameraView.stop();
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        closeClassifier().subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onComplete() {
                Log.i("closeClassifier","completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("closeClassifier", e.getMessage());
            }
        });
        super.onDestroy();
        compositeDisposable.clear();
    }
    private Completable closeClassifier(){
        return Completable.fromAction(new Action() {
            @Override
            public void run() {
                classifier.close();
            }
        }).subscribeOn(Schedulers.newThread());
    }
    private Completable initTensorFlow(){
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws IOException {
                classifier = TensorFlowImageClassifier.create(
                        getAssets(),
                        MODEL_PATH,
                        LABEL_PATH,
                        INPUT_SIZE);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    private String generateResults(List<Classifier.Recognition> data){
        String result = "";
        result = result +"\n" + data.get(0);
        return result;
    }

    private void showPreview(boolean show, @Nullable Bitmap img, @Nullable String results){

        if (show) {
            cameraView.setVisibility(View.GONE);
            btnCapture.setVisibility(View.GONE);
            btnReCapture.setVisibility(View.VISIBLE);
            imgPreview.setVisibility(View.VISIBLE);
            imgPreview.setImageBitmap(img);
            tv1.setVisibility(View.VISIBLE);
            tv2.setVisibility(View.VISIBLE);
            cardViewTerjemah.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.VISIBLE);
            tvResult.setVisibility(View.VISIBLE);
            String str = results.replaceAll("\\d","");
            strr = str.replace("(,%)", "").trim();
            tvResult.setText(strr);
            dbSampel();
        } else {
            cameraView.setVisibility(View.VISIBLE);
            btnCapture.setVisibility(View.VISIBLE);
            btnReCapture.setVisibility(View.GONE);
            imgPreview.setVisibility(View.GONE);
            tvResult.setVisibility(View.GONE);
            tv1.setVisibility(View.GONE);
            tv2.setVisibility(View.GONE);
            cardView.setVisibility(View.GONE);
            cardViewTerjemah.setVisibility(View.GONE);
        }
    }
}