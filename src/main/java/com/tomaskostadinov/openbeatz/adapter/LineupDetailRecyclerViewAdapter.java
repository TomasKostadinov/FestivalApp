package com.tomaskostadinov.openbeatz.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;
import com.github.vipulasri.timelineview.Utils;
import com.loopj.android.image.SmartImageView;
import com.tomaskostadinov.openbeatz.R;
import com.tomaskostadinov.openbeatz.helpers.Utilities;
import com.tomaskostadinov.openbeatz.model.Artist;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Artist}
 * TODO: Replace the implementation with code for your data type.
 */
public class LineupDetailRecyclerViewAdapter extends RecyclerView.Adapter<LineupDetailRecyclerViewAdapter.ViewHolder> {

    private final List<Artist> mValues;

    public LineupDetailRecyclerViewAdapter(List<Artist> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, parent, false);
        return new ViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).getTitle());
        holder.mContentView.setText(mValues.get(position).getStart());
        //holder.imageView.setImageUrl(mValues.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mContentView;
        public Artist mItem;
        public final SmartImageView imageView;
        public TimelineView mTimelineView;


        public ViewHolder(View view, int viewType) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.title);
            mContentView = (TextView) view.findViewById(R.id.content);
            imageView = (SmartImageView) view.findViewById(R.id.image);
            mTimelineView = (TimelineView) view.findViewById(R.id.time_marker);
            mTimelineView.initLine(viewType);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }
}
