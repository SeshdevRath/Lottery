package com.lottery.app.appUseCases.pick.fragments;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lottery.app.R;
import com.lottery.app.appUseCases.pick.adapters.AdapterPathEntry;
import com.lottery.app.appUseCases.pick.interfaces.InterfacePick;
import com.lottery.app.appUseCases.pick.presenters.PresenterFragmentPick;
import com.lottery.app.helperPackages.baseClasses.BaseFragment;

import java.util.HashMap;
import java.util.List;

public class FragmentPick extends BaseFragment implements InterfacePick.IFragment {
    private PresenterFragmentPick presenterFragmentPick;
    private OnFragmentInteractionListener mListener;
    private Button buttonTakeAmount;
    private RecyclerView pathListView;

    private static final int ENTRY_AMOUNT = 1;
    private static final int MAX_LENGTH = 10;

    public FragmentPick() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pick, container, false);
        initViews(rootView);

        presenterFragmentPick = new PresenterFragmentPick();
        presenterFragmentPick.onCreateView(savedInstanceState, this, container);
        presenterFragmentPick.generatePath(ENTRY_AMOUNT, MAX_LENGTH);

        return rootView;
    }

    private void initViews(View rootView) {
        pathListView = rootView.findViewById(R.id.path_list_view);
        buttonTakeAmount = rootView.findViewById(R.id.take_amount_button);

        buttonTakeAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void renderPath(final List<HashMap<String, Object>> randomPath) {
        AdapterPathEntry adapterPathEntry = new AdapterPathEntry(getActivity(), randomPath, new AdapterPathEntry.ClickListener() {
            @Override
            public void onRowSelected(int position, boolean shouldVisible) {

                buttonTakeAmount.setVisibility(shouldVisible ? View.VISIBLE : View.GONE);
                buttonTakeAmount.setText(String.format("Take %s", randomPath.get(position).get("value")));
            }
        });
        pathListView.setAdapter(adapterPathEntry);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        pathListView.setLayoutManager(layoutManager);
    }

    public interface OnFragmentInteractionListener {

    }
}
