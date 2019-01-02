/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.sucharypl.app.view.fragment.random.select;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.flexbox.FlexboxLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.slupik.sucharypl.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectRandomJokesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectRandomJokesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectRandomJokesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.flexboxLayout2)
    FlexboxLayout fbl;

    public SelectRandomJokesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectRandomJokesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectRandomJokesFragment newInstance(String param1, String param2) {
        SelectRandomJokesFragment fragment = new SelectRandomJokesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_random_jokes, container, false);
        ButterKnife.bind(this, view);
        fbl.addView(LabelFactory.createLabel(getContext(), "Lorem", null, "X"));
        fbl.addView(LabelFactory.createLabel(getContext(), "Lorem", null, "X"));
        fbl.addView(LabelFactory.createLabel(getContext(), "Lorem", null, "X"));
        fbl.addView(LabelFactory.createLabel(getContext(), "Lorem", null, "X"));
        fbl.addView(LabelFactory.createLabel(getContext(), "Lorem", null, "X"));
        fbl.addView(LabelFactory.createLabel(getContext(), "Lorem", null, "X"));
        fbl.addView(LabelFactory.createLabel(getContext(), "Lorem", null, "X"));
        fbl.addView(LabelFactory.createLabel(getContext(), "Lorem", null, "X"));
        fbl.addView(LabelFactory.createLabel(getContext(), "Lorem", null, "X"));
        fbl.addView(LabelFactory.createLabel(getContext(), "Lorem", null, "X"));
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
