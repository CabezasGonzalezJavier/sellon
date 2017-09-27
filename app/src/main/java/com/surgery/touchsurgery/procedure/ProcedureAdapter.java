package com.surgery.touchsurgery.procedure;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.surgery.touchsurgery.R;
import com.surgery.touchsurgery.data.Procedure;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by javierg on 27/09/2017.
 */

public class ProcedureAdapter extends RecyclerView
        .Adapter<ProcedureAdapter
        .DataObjectHolder> {

    private Context mContext;
    private List<Procedure> mList;
    private static ProcedureAdapter.MyClickListener sClickListener;

    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        @BindView(R.id.procedure_list_item_textView)
        TextView mName;

        @BindView(R.id.procedure_list_item_imageView)
        ImageView mImageView;

        DataObjectHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            sClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    void setOnItemClickListener(ProcedureAdapter.MyClickListener myClickListener) {
        this.sClickListener = myClickListener;
    }


    public ProcedureAdapter(Context mContext, List<Procedure> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public ProcedureAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.procedure_list_item, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(ProcedureAdapter.DataObjectHolder holder, int position) {

        holder.mName.setText(mList.get(position).getName());
        Glide.with(mContext).load(mList.get(position).getIcon()).into(holder.mImageView);
        // Here you apply the animation when the view is bound
        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    interface MyClickListener {
        void onItemClick(int position, View v);
    }
}
