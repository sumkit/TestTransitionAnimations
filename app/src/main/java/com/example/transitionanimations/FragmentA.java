package com.example.transitionanimations;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.SharedElementCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.transition.MaterialContainerTransform;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FragmentA extends Fragment {

    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mAdapter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList data = new ArrayList<String>();
        for (int i = 0; i < 6; i++) {
            data.add(Integer.toString(i));
        }
        mAdapter = new MyRecyclerViewAdapter(this, data);

        MaterialContainerTransform transform = new MaterialContainerTransform(getContext());
        transform.setDuration(500);
        setSharedElementReturnTransition(transform);
        setExitSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                View view = mAdapter.getClickedItem();
                if (view != null) {
                    sharedElements.clear();
                    String name = names.get(0);
                    sharedElements.put(name, view);
                }
            }
        });
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }
}
