// Generated by view binder compiler. Do not edit!
package com.example.karsa.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.example.karsa.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final CardView cardView;

  @NonNull
  public final Button fiturDeteksi;

  @NonNull
  public final Button fiturKamus;

  @NonNull
  public final RecyclerView rvBerita;

  @NonNull
  public final TextInputEditText searching;

  @NonNull
  public final TextInputLayout textInput1;

  @NonNull
  public final TextView tv1;

  @NonNull
  public final TextView tv2;

  @NonNull
  public final TextView tvHello;

  private FragmentHomeBinding(@NonNull ScrollView rootView, @NonNull CardView cardView,
      @NonNull Button fiturDeteksi, @NonNull Button fiturKamus, @NonNull RecyclerView rvBerita,
      @NonNull TextInputEditText searching, @NonNull TextInputLayout textInput1,
      @NonNull TextView tv1, @NonNull TextView tv2, @NonNull TextView tvHello) {
    this.rootView = rootView;
    this.cardView = cardView;
    this.fiturDeteksi = fiturDeteksi;
    this.fiturKamus = fiturKamus;
    this.rvBerita = rvBerita;
    this.searching = searching;
    this.textInput1 = textInput1;
    this.tv1 = tv1;
    this.tv2 = tv2;
    this.tvHello = tvHello;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cardView;
      CardView cardView = rootView.findViewById(id);
      if (cardView == null) {
        break missingId;
      }

      id = R.id.fiturDeteksi;
      Button fiturDeteksi = rootView.findViewById(id);
      if (fiturDeteksi == null) {
        break missingId;
      }

      id = R.id.fiturKamus;
      Button fiturKamus = rootView.findViewById(id);
      if (fiturKamus == null) {
        break missingId;
      }

      id = R.id.rvBerita;
      RecyclerView rvBerita = rootView.findViewById(id);
      if (rvBerita == null) {
        break missingId;
      }

      id = R.id.searching;
      TextInputEditText searching = rootView.findViewById(id);
      if (searching == null) {
        break missingId;
      }

      id = R.id.textInput1;
      TextInputLayout textInput1 = rootView.findViewById(id);
      if (textInput1 == null) {
        break missingId;
      }

      id = R.id.tv1;
      TextView tv1 = rootView.findViewById(id);
      if (tv1 == null) {
        break missingId;
      }

      id = R.id.tv2;
      TextView tv2 = rootView.findViewById(id);
      if (tv2 == null) {
        break missingId;
      }

      id = R.id.tvHello;
      TextView tvHello = rootView.findViewById(id);
      if (tvHello == null) {
        break missingId;
      }

      return new FragmentHomeBinding((ScrollView) rootView, cardView, fiturDeteksi, fiturKamus,
          rvBerita, searching, textInput1, tv1, tv2, tvHello);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}