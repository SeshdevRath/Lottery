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
    private int selectedPosition = -1;
    private boolean hasFinished = false;

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
        boolean hasCrossed = pathEntry.get("hasCrossed") != null && (boolean) pathEntry.get("hasCrossed");
        boolean isLeftHasValue = pathEntry.get("left") != null && (boolean) pathEntry.get("left");
        boolean isRightHasValue = pathEntry.get("right") != null && (boolean) pathEntry.get("right");
        boolean shouldShowCross = pathEntry.get("shouldShowCross") != null && (boolean) pathEntry.get("shouldShowCross");

        holder.leftPointerImage.setVisibility(  isActive ? View.VISIBLE : View.INVISIBLE);
        holder.rightPointerImage.setVisibility( isActive ? View.VISIBLE : View.INVISIBLE);
        holder.leftCardContainer.setBackgroundColor(  context.getColor( (hasFinished || hasCrossed) && isLeftHasValue  ? R.color.black : isActive ? R.color.green : R.color.white) );
        holder.rightCardContainer.setBackgroundColor( context.getColor( (hasFinished || hasCrossed) && isRightHasValue ? R.color.black : isActive ? R.color.green : R.color.white) );
        holder.leftCardValue.setText( String.valueOf(pathEntry.get("value")) );
        holder.rightCardValue.setText(String.valueOf(pathEntry.get("value")) );

        holder.leftCardValue.setTextColor( context.getColor((hasFinished || hasCrossed) && isLeftHasValue  ? R.color.white : isActive ? R.color.white : R.color.black));
        holder.rightCardValue.setTextColor(context.getColor((hasFinished || hasCrossed) && isRightHasValue ? R.color.white : isActive ? R.color.white : R.color.black));

        holder.leftCardValue.setVisibility( shouldShowCross && !isLeftHasValue  ? View.GONE : View.VISIBLE);
        holder.rightCardValue.setVisibility( shouldShowCross && !isRightHasValue ? View.GONE : View.VISIBLE);

        if (shouldShowCross) {
            holder.leftCardCrossImage.setVisibility( !isLeftHasValue  ? View.VISIBLE : View.GONE);
            holder.rightCardCrossImage.setVisibility( !isRightHasValue ? View.VISIBLE : View.GONE);
        }

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
        ImageView leftCardCrossImage, rightCardCrossImage;
        private WeakReference<ClickListener> listenerRef;

        PathEntryViewHolder(View pathEntryView) {
            super(pathEntryView);

            leftPointerImage = pathEntryView.findViewById(R.id.left_pointer_image);
            rightPointerImage = pathEntryView.findViewById(R.id.right_pointer_image);
            leftCardContainer = pathEntryView.findViewById(R.id.left_card_container);
            rightCardContainer = pathEntryView.findViewById(R.id.right_card_container);
            leftCardValue = pathEntryView.findViewById(R.id.left_card_value);
            rightCardValue = pathEntryView.findViewById(R.id.right_card_value);
            leftCardCrossImage = pathEntryView.findViewById(R.id.left_card_cross_image);
            rightCardCrossImage = pathEntryView.findViewById(R.id.right_card_cross_image);
            listenerRef = new WeakReference<>(clickListener);

            leftCardContainer.setOnClickListener(this);
            rightCardContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            selectedPosition = getAdapterPosition();

            if ( (view == leftCardContainer  && (boolean) pathEntryList.get(selectedPosition).get("left") ) ||
                 (view == rightCardContainer && (boolean) pathEntryList.get(selectedPosition).get("right")) ) {
                activateNextAvailableRow();
                notifyDataSetChanged();
                listenerRef.get().onRowSelected(selectedPosition, true);
            }
            else {
                deactivateSelectedRow();
                notifyDataSetChanged();
                listenerRef.get().onRowSelected(selectedPosition, false);
            }
        }
    }

    public void updateData(List<HashMap<String, Object>> pathEntryList) {
        this.pathEntryList = pathEntryList;
        notifyDataSetChanged();
    }

    private void deactivateSelectedRow() {
        if (pathEntryList != null && pathEntryList.size() > 0 && selectedPosition < pathEntryList.size()) {
            hasFinished = true;
            pathEntryList.get(selectedPosition).put("isActive", false);
            pathEntryList.get(selectedPosition).put("shouldShowCross", true);
        }
    }

    private void activateNextAvailableRow() {
        if (pathEntryList != null && pathEntryList.size() > 0) {

            if (selectedPosition <= pathEntryList.size()) {
                pathEntryList.get(selectedPosition).put("isActive", false);
                pathEntryList.get(selectedPosition).put("hasCrossed", true);
            }

            if (selectedPosition < pathEntryList.size()-1) {
                int nextRow = selectedPosition + 1;
                pathEntryList.get(nextRow).put("isActive", true);
            }
        }
    }

    public interface ClickListener {
        void onRowSelected(int position, boolean shouldVisible);
    }
}
