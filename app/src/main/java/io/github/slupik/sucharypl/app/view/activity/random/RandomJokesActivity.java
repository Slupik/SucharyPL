/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.sucharypl.app.view.activity.random;

import android.net.Uri;
import android.os.Bundle;
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
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onSetupComplete(JokeSelectionPOJO enteredData) {

    }
}
