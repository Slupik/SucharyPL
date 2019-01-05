/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.domain.entity.joke;

import io.github.slupik.domain.entity.category.JokeCategory;

public class JokeOnlinePOJO extends JokePOJO implements JokeOnline {

    private float rate = 0;

    public JokeOnlinePOJO(float rate, int id, JokeCategory[] categories, String content, boolean toLearn, boolean favourite, boolean report, short likeState) {
        super(id, categories, content, toLearn, favourite, report, likeState);
        this.rate = rate;
    }

    public JokeOnlinePOJO() {
        super();
    }

    @Override
    public float getRate() {
        return rate;
    }
}
