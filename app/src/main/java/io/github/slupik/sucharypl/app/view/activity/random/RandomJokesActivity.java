/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.sucharypl.app.view.activity.random;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import io.github.slupik.domain.entity.joke.JokeSelectionPOJO;
import io.github.slupik.sucharypl.R;
import io.github.slupik.sucharypl.app.view.fragment.random.select.SelectRandomJokesFragment;
import io.github.slupik.sucharypl.app.view.fragment.random.show.ShowRandomJokes;

public class RandomJokesActivity extends AppCompatActivity implements SelectRandomJokesFragment.OnFragmentInteractionListener, ShowRandomJokes.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_jokes);

        showFragmentWithJokeSelection();
    }

    private void showFragmentWithJokeSelection() {
        Fragment newFragment = new SelectRandomJokesFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.random_joke_container, newFragment);
        transaction.commit();
    }

    //TODO implement

    @Override
    public void onSetupComplete(JokeSelectionPOJO enteredData) {
        showFragmentWithJoke();
        //...
    }

    @Override
    public void onContinue() {
        showFragmentWithJoke();
        //...
    }

    private void showFragmentWithJoke() {
        Fragment newFragment = new ShowRandomJokes();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.random_joke_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onReport(boolean report) {


    }

    @Override
    public void onLikeState(short state) {

    }

    @Override
    public void addFavourite(int id) {

    }

    @Override
    public void removeFavourite(int id) {

    }

    @Override
    public void addToLearn(int id) {

    }

    @Override
    public void removeToLearn(int id) {

    }
}
