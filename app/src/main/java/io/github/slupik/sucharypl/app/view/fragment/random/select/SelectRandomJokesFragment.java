/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.sucharypl.app.view.fragment.random.select;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.slupik.domain.entity.category.JokeCategory;
import io.github.slupik.domain.entity.joke.JokeSelectionPOJO;
import io.github.slupik.sucharypl.R;
import io.github.slupik.sucharypl.app.view.custom.element.LabelWithAction;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectRandomJokesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectRandomJokesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectRandomJokesFragment extends Fragment {

    private static final String INNER_DATA = "inner_data";

    private OnFragmentInteractionListener mListener;
    private JokeSelectionPOJO mInitData;

    @BindView(R.id.flJokeCategories)
    FlexboxLayout mJokeCategories;
    @BindView(R.id.flJokeFilters)
    FlexboxLayout mJokeFilters;
    @BindView(R.id.sbJokeLength)
    SeekBar mJokeLength;
    @BindView(R.id.tvSelectCategoriesTitle)
    TextView mCategoriesTitle;
    @BindView(R.id.tvSelectFiltersTitle)
    TextView mFilterTitle;

    private final List<LabelWithAction> mAvailableCategories = new ArrayList<>();

    public SelectRandomJokesFragment() {
        // Required empty public constructor
    }

    public static SelectRandomJokesFragment newInstance(JokeSelectionPOJO data) {
        SelectRandomJokesFragment fragment = new SelectRandomJokesFragment();
        Bundle args = new Bundle();
        args.putString(INNER_DATA, new Gson().toJson(data));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //For test purposes
//        JokeCategory category = new JokeCategoryImpl(0, true, "Lorem");
//        JokeCategory category1 = new JokeCategoryImpl(1, false, "Ipsum");
//        JokeCategory category2 = new JokeCategoryImpl(2, true, "Doloret");
//        JokeCategory category3 = new JokeCategoryImpl(3, false, "Esa at");
//        JokeCategory category4 = new JokeCategoryImpl(4, false, "Vashinima");
//        JokeCategory category5 = new JokeCategoryImpl(5, true, "Makumba czer");
//        JokeSelectionPOJO pojo = new JokeSelectionPOJO();
//        pojo.setJokeLength(50);
//        List<JokeCategory> cat = new ArrayList<>();
//        cat.add(category);
//        cat.add(category1);
//        cat.add(category2);
//        cat.add(category3);
//        cat.add(category4);
//        cat.add(category5);
//        pojo.setCategories(cat.toArray(new JokeCategory[0]));
//        List<Integer> sele = new ArrayList<>();
//        sele.add(0);
//        sele.add(3);
//        sele.add(4);
//        pojo.setSelectedCategories(sele);
//        mInitData = pojo;

        if (getArguments() != null) {
            String param = getArguments().getString(INNER_DATA);
            mInitData = new Gson().fromJson(param, JokeSelectionPOJO.class);
        } else {
            mInitData = new JokeSelectionPOJO();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_random_jokes, container, false);
        ButterKnife.bind(this, view);
        mAvailableCategories.clear();

        int totalCategories = 0;
        int totalFilters = 0;
        for(JokeCategory category: mInitData.getCategories()) {
            LabelWithAction label = LabelFactory.createLabel(getContext(), category);
            LabelFactory.markSelected(label, mInitData.getSelectedCategories().contains(category.getId()));
            if(category.isFilter()) {
                mJokeFilters.addView(label);
                totalFilters++;
            } else {
                mJokeCategories.addView(label);
                totalCategories++;
            }
            label.putTempData(category);
            mAvailableCategories.add(label);
        }

        if(totalCategories==0) {
            mCategoriesTitle.setVisibility(View.GONE);
            mJokeCategories.setVisibility(View.GONE);
        }
        if(totalFilters==0) {
            mFilterTitle.setVisibility(View.GONE);
            mJokeFilters.setVisibility(View.GONE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mJokeLength.setProgress(mInitData.getJokeLength(), true);
        } else {
            mJokeLength.setProgress(mInitData.getJokeLength());
        }

        return view;
    }

    @OnClick(R.id.btnShowRandomJokes)
    public void onSearchRandomAction() {
        mListener.onSetupComplete(getEnteredData());
    }

    @OnClick(R.id.btnShowContinueJokes)
    public void onContinueAction() {
        mListener.onContinue();
    }

    private JokeSelectionPOJO getEnteredData() {
        JokeSelectionPOJO jokeSelectionPOJO = new JokeSelectionPOJO();

        jokeSelectionPOJO.setJokeLength(mJokeLength.getProgress());

        List<JokeCategory> categories = new ArrayList<>();
        List<Integer> selected = new ArrayList<>();

        for(LabelWithAction labelWithAction:mAvailableCategories) {
            if(labelWithAction.getTempData() instanceof LabelWithAction) {
                JokeCategory category = (JokeCategory) labelWithAction.getTempData();
                categories.add(category);
                if(LabelUtils.isSelected(labelWithAction)) {
                    selected.add(category.getId());
                }
            }
        }

        jokeSelectionPOJO.setCategories(categories.toArray(new JokeCategory[0]));
        jokeSelectionPOJO.setSelectedCategories(selected);

        return jokeSelectionPOJO;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void onSetupComplete(JokeSelectionPOJO enteredData);
        void onContinue();
    }

}
