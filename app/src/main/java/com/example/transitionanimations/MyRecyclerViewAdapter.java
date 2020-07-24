package com.example.transitionanimations;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<String> mData;
    private final Fragment mFragment;
    private final Context mContext;

    MyRecyclerViewAdapter(Fragment fragment, List<String> data) {
        mContext = fragment.getContext();
        mFragment = fragment;
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
        return new ViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        String name = Integer.toString(position);
        holder.imageView.setTransitionName(name);

        holder.imageView.setImageResource(R.drawable.ic_launcher_foreground);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(final View view) {
                Fragment b = new FragmentB();
                mFragment.getParentFragment().getChildFragmentManager()
                    .beginTransaction()
                    .addSharedElement(holder.imageView, "fragment_b_transition_name")
                    .replace(R.id.fragment_container, b, "B_TAG")
                    .addToBackStack("B_fragment")
                    //.setReorderingAllowed(true)
                    .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView;
        }
    }
}
