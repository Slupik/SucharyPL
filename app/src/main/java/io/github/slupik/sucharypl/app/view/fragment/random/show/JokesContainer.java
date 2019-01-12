/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.sucharypl.app.view.fragment.random.show;

import io.github.slupik.domain.entity.joke.JokeOnline;

public interface JokesContainer {
    int getCount();
    JokeOnline getJoke(int index);
}
