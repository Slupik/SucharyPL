/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.sucharypl.app.view.fragment.random.show;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.slupik.domain.entity.category.JokeCategory;
import io.github.slupik.domain.entity.joke.JokeOnline;
import io.github.slupik.domain.entity.joke.JokeOnlinePOJO;
import io.github.slupik.sucharypl.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShowRandomJokes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShowRandomJokes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowRandomJokes extends Fragment {
    private static final String ARG_JOKE_DATA = "joke-data";
    private JokeOnline mJokeData = new JokeOnlinePOJO();

    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.tvJokeCategories)
    TextView tvJokeCategories;
    @BindView(R.id.tvJokeRate)
    TextView tvRateInfo;
    @BindView(R.id.ivFavourite)
    ImageView ivFavourite;
    @BindView(R.id.ivLearn)
    ImageView ivLearn;
    @BindView(R.id.ivReport)
    ImageView ivReport;
    @BindView(R.id.ivJokeLike)
    ImageView ivLike;
    @BindView(R.id.ivJokeDislike)
    ImageView ivDislike;

    private OnFragmentInteractionListener mListener;
    private boolean favourite;
    private boolean report;
    private boolean toLearn;
    private short likeState;

    public ShowRandomJokes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShowRandomJokes.
     */
    public static ShowRandomJokes newInstance(JokeOnline jokeData) {
        ShowRandomJokes fragment = new ShowRandomJokes();
        Bundle args = new Bundle();
        args.putString(ARG_JOKE_DATA, new Gson().toJson(jokeData));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mJokeData = new JokeOnlinePOJO(8.32f, 1, new JokeCategory[0], "Lorem ipsum doloret",
                true, true, true, (short) 1);

        if (getArguments() != null) {
            mJokeData = new Gson().fromJson(getArguments().getString(ARG_JOKE_DATA), JokeOnlinePOJO.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_random_jokes, container, false);
        ButterKnife.bind(this, view);

        if(mJokeData.isReport()) {
            ivReport.setVisibility(View.GONE);
        } else {
            ivReport.setVisibility(View.VISIBLE);
        }

        switch (mJokeData.getLikeState()) {
            case -1: {
                ivLike.setColorFilter(R.color.colorSecondaryTextLight);
                ivDislike.setColorFilter(Color.RED);
                break;
            }
            case 1: {
                ivLike.setColorFilter(Color.GREEN);
                ivDislike.setColorFilter(R.color.colorSecondaryTextLight);
                break;
            }
            default: {
                ivLike.setColorFilter(R.color.colorSecondaryTextLight);
                ivDislike.setColorFilter(R.color.colorSecondaryTextLight);
                break;
            }
        }

        tvContent.setText(mJokeData.getContent());

        StringBuilder text = new StringBuilder();
        for(int i=0;i<mJokeData.getCategories().length;i++) {
            JokeCategory category = mJokeData.getCategories()[i];
            text.append(category.getDisplayName());
            if(i<mJokeData.getCategories().length-1) {
                text.append(", ");
            }
        }
        tvJokeCategories.setText(text.toString());
        if(text.length()==0) {
            tvJokeCategories.setVisibility(View.GONE);
        }

        String rate = Math.round(mJokeData.getRate()*100)/100+"/10";
        tvRateInfo.setText(rate);

        if(mJokeData.isToLearn()) {
            ivLearn.setImageDrawable(getContext().getDrawable(R.drawable.ic_notebook_checked));
        } else {
            ivLearn.setImageDrawable(getContext().getDrawable(R.drawable.ic_notebook));
        }

        if(mJokeData.isFavourite()) {
            ivFavourite.setImageDrawable(getContext().getDrawable(R.drawable.ic_full_star));
        } else {
            ivFavourite.setImageDrawable(getContext().getDrawable(R.drawable.ic_empty_star));
        }

        return view;
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
