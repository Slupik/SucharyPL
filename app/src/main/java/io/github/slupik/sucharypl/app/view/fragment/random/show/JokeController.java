/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.sucharypl.app.view.fragment.random.show;

public interface JokeController {
    void onReport(boolean report);
    void onLikeState(short state);
    void addFavourite(int id);
    void removeFavourite(int id);
    void addToLearn(int id);
    void removeToLearn(int id);
}
