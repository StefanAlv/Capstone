package com.example.karsa.TensorFlowImageClassifier;

import android.graphics.Bitmap;

import java.util.List;
import java.util.Locale;

public interface Classifier {
    class Recognition{
        private final String title;
        private final Float confidence;

        public Recognition(String title, Float confidence) {
            this.title = title;
            this.confidence = confidence;
        }


        public String getTitle() {
            return title;
        }

        public Float getConfidence() {
            return confidence;
        }

        @Override
        public String toString() {
            String resultString = "";

            if (title != null) {
                resultString += title + " ";
            }

            if (confidence != null) {
                resultString += String.format(
                        Locale.getDefault(),"(%.1f%%) ", confidence * 100.0f);
            }

            return resultString.trim();
        }
    }
    List<Recognition> recognizeImage(Bitmap bitmap);
    void close();
}
