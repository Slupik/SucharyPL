/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.sucharypl.app.view.fragment.random.show;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.slupik.domain.entity.joke.JokeOnline;
import io.github.slupik.sucharypl.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShowRandomJokesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShowRandomJokesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowRandomJokesFragment extends Fragment {

    @BindView(R.id.pager_random_jokes)
    ViewPager pager;

    private OnFragmentInteractionListener mListener;

    public ShowRandomJokesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShowRandomJokesFragment.
     */
    public static ShowRandomJokesFragment newInstance(JokeOnline jokeData) {
        ShowRandomJokesFragment fragment = new ShowRandomJokesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_random_jokes, container, false);
        ButterKnife.bind(this, view);

        JokePagerAdapter pagerAdapter = new JokePagerAdapter();
        pager.setAdapter(pagerAdapter);

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
        JokeController getJokeController();
        JokesContainer getJokesContainer();
    }

    //TODO add swipe animation
    private class JokePagerAdapter extends PagerAdapter {

        @NonNull
        @Override
        public Object instantiateItem(@NonNull final ViewGroup container, int position) {
            JokeDisplay item = new JokeDisplay(container.getContext(),
                mListener.getJokesContainer().getJoke(position),
                mListener.getJokeController());

            container.addView(item);
            return item;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return mListener.getJokesContainer().getCount();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return o==view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Joke with id: " + position;
        }
    }
}
