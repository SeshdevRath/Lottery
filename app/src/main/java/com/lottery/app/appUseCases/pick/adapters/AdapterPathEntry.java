package com.lottery.app.appUseCases.pick.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lottery.app.R;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

public class AdapterPathEntry extends RecyclerView.Adapter<AdapterPathEntry.PathEntryViewHolder> {
    private Context context;
    private List<HashMap<String, Object>> pathEntryList;
    private LayoutInflater inflater;
    private ClickListener clickListener;
    private int selectedPosition = 0;

    public AdapterPathEntry(Context context, List<HashMap<String, Object>> pathEntryList, ClickListener clickListener) {
        this.context = context;
        this.pathEntryList = pathEntryList;
        this.clickListener = clickListener;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AdapterPathEntry.PathEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.view_pick_entry, parent, false);

        return new AdapterPathEntry.PathEntryViewHolder(view);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull AdapterPathEntry.PathEntryViewHolder holder, int position) {
        HashMap pathEntry = pathEntryList.get(position);

        boolean isActive = pathEntry.get("isActive") != null && (boolean) pathEntry.get("isActive");
        boolean hasWon = pathEntry.get("hasWon") != null && (boolean) pathEntry.get("hasWon");
        boolean isLeftHasValue = (boolean) pathEntry.get("left");
        boolean isRightHasValue = (boolean) pathEntry.get("right");

        holder.leftPointerImage.setVisibility(  isActive ? View.VISIBLE : View.INVISIBLE);
        holder.rightPointerImage.setVisibility( isActive ? View.VISIBLE : View.INVISIBLE);
        holder.leftCardContainer.setBackgroundColor(  context.getColor( isLeftHasValue && hasWon ? R.color.black : isActive ? R.color.green : R.color.white) );
        holder.rightCardContainer.setBackgroundColor( context.getColor( isRightHasValue && hasWon ? R.color.black : isActive ? R.color.green : R.color.white) );
        holder.leftCardValue.setText( String.valueOf(pathEntry.get("value")));
        holder.rightCardValue.setText(String.valueOf(pathEntry.get("value")));

        holder.leftCardValue.setTextColor(context.getColor( isLeftHasValue && hasWon ? R.color.white : isActive ? R.color.white : R.color.black));
        holder.rightCardValue.setTextColor(context.getColor(isRightHasValue && hasWon ? R.color.white : isActive ? R.color.white : R.color.black));

        holder.leftCardContainer.setEnabled(isActive);
        holder.rightCardContainer.setEnabled(isActive);
    }

    @Override
    public int getItemCount() {
        return pathEntryList.size();
    }

    class PathEntryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView leftPointerImage, rightPointerImage;
        LinearLayout leftCardContainer, rightCardContainer;
        TextView leftCardValue, rightCardValue;
        private WeakReference<ClickListener> listenerRef;

        PathEntryViewHolder(View pathEntryView) {
            super(pathEntryView);

            leftPointerImage = pathEntryView.findViewById(R.id.left_pointer_image);
            rightPointerImage = pathEntryView.findViewById(R.id.right_pointer_image);
            leftCardContainer = pathEntryView.findViewById(R.id.left_card_container);
            rightCardContainer = pathEntryView.findViewById(R.id.right_card_container);
            leftCardValue = pathEntryView.findViewById(R.id.left_card_value);
            rightCardValue = pathEntryView.findViewById(R.id.right_card_value);
            listenerRef = new WeakReference<>(clickListener);

            leftCardContainer.setOnClickListener(this);
            rightCardContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            selectedPosition = getAdapterPosition();

            if (view == leftCardContainer && (boolean) pathEntryList.get(selectedPosition).get("left") ) {
                activateNextAvailableRow();
                listenerRef.get().onRowSelected(selectedPosition-1, true);
            }
            else if (view == rightCardContainer && (boolean) pathEntryList.get(selectedPosition).get("right") ) {
                activateNextAvailableRow();
                listenerRef.get().onRowSelected(selectedPosition-1, true);
            }
            else {
                deactivateSelectedRow();
                listenerRef.get().onRowSelected(selectedPosition, false);
            }

            notifyDataSetChanged();
        }
    }

    private void deactivateSelectedRow() {
        if (pathEntryList != null && pathEntryList.size() > 0 && selectedPosition < pathEntryList.size()-1) {
            pathEntryList.get(selectedPosition).put("isActive", false);
        }
    }

    private void activateNextAvailableRow() {
        if (pathEntryList != null && pathEntryList.size() > 0) {

            if (selectedPosition <= pathEntryList.size()-1) {
                pathEntryList.get(selectedPosition).put("isActive", false);
                pathEntryList.get(selectedPosition).put("hasWon", true);
            }

            if (selectedPosition < pathEntryList.size()-1)
                pathEntryList.get(++selectedPosition).put("isActive", true);
        }
    }

    public interface ClickListener {
        void onRowSelected(int position, boolean shouldVisible);
    }
}
