package com.bw.movie.cinema.event;

/**
 * date:2018/12/27    18:22
 * author:张文龙(张文龙)
 * fileName:FollowEvent
 */
public class FollowEvent {

    private int id;

    public FollowEvent(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
