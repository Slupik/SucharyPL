/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.domain.entity.joke;

import io.github.slupik.domain.entity.category.JokeCategory;

public class JokePOJO implements Joke {

    private int id = -1;
    private JokeCategory[] categories = new JokeCategory[0];
    private String content;
    private boolean toLearn = false;
    private boolean favourite = false;
    private boolean report = false;
    private short likeState = 0;

    public JokePOJO(int id, JokeCategory[] categories, String content, boolean toLearn, boolean favourite, boolean report, short likeState) {
        this.id = id;
        this.categories = categories;
        this.content = content;
        this.toLearn = toLearn;
        this.favourite = favourite;
        this.report = report;
        this.likeState = likeState;
    }

    public JokePOJO() {

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public JokeCategory[] getCategories() {
        return categories;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public boolean isToLearn() {
        return toLearn;
    }

    @Override
    public boolean isFavourite() {
        return favourite;
    }

    @Override
    public boolean isReport() {
        return report;
    }

    @Override
    public short getLikeState() {
        return likeState;
    }
}
