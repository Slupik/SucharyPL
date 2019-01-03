/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.domain.entity.category;

public class JokeCategoryImpl implements JokeCategory {

    private final int id;
    private final boolean isFilter;
    private final String name;

    public JokeCategoryImpl(int id, boolean isFilter, String name) {
        this.id = id;
        this.isFilter = isFilter;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean isFilter() {
        return isFilter;
    }

    @Override
    public String getDisplayName() {
        return name;
    }
}
