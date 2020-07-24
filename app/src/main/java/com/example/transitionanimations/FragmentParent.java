package com.example.transitionanimations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentParent extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_parent, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getChildFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, new FragmentA())
            .addToBackStack("A_fragment")
            .commit();
    }
}
