/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.domain.entity.joke;

public abstract class JokeLikeState {

    public static final short DISLIKE = -1;
    public static final short NEUTRAL = 0;
    public static final short LIKE = 1;

    private JokeLikeState(){}
}
