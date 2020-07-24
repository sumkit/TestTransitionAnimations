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
    private View mClickedView;

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
        imageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(final View view) {
                String name = view.getTransitionName();
                Fragment b = new FragmentB(name);
                mClickedView = view;
                FragmentTransaction transaction = mFragment.getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, b);
                transaction.addSharedElement(mClickedView, name);
                transaction.addToBackStack(null).setReorderingAllowed(true);
                transaction.commit();
            }
        });
        return new ViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(R.drawable.ic_launcher_foreground);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String name = Integer.toString(position);
            holder.imageView.setTransitionName(name);
        }
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

    public View getClickedItem() {
        return mClickedView;
    }
}
