package com.example.transitionanimations;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.material.transition.MaterialContainerTransform;

public class FragmentB extends Fragment {
    private ImageView mImageView;
    private final String mTransitionName;

    public FragmentB(String transitionName) {
        mTransitionName = transitionName;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MaterialContainerTransform transform = new MaterialContainerTransform(getContext());
        transform.setDuration(500);
        setSharedElementEnterTransition(transform);
        /*setEnterSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                if (mImageView != null) {
                    sharedElements.clear();
                    String name = names.get(0);
                    sharedElements.put(name, mImageView);
                }
            }
        }); */
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mImageView = view.findViewById(R.id.image);
        mImageView.setImageResource(R.drawable.ic_launcher_foreground);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageView.setTransitionName(mTransitionName);
        }
    }
}
