/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.domain.entity.joke;

import java.util.ArrayList;
import java.util.List;

import io.github.slupik.domain.entity.category.JokeCategory;

public class JokeSelectionPOJO {

    private JokeCategory[] categories = new JokeCategory[0];
    private List<Integer> selectedCategories = new ArrayList<>();
    private int jokeLength = 100;

    public JokeCategory[] getCategories() {
        return categories;
    }

    public void setCategories(JokeCategory[] categories) {
        this.categories = categories;
    }

    public List<Integer> getSelectedCategories() {
        return selectedCategories;
    }

    public void setSelectedCategories(List<Integer> selectedCategories) {
        this.selectedCategories = selectedCategories;
    }

    public int getJokeLength() {
        return jokeLength;
    }

    public void setJokeLength(int jokeLength) {
        this.jokeLength = jokeLength;
    }

}