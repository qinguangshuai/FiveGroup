package com.bw.movie.cinema.event;

/**
 * date:2018/12/30    14:24
 * author:张文龙(张文龙)
 * fileName:SeatEvent
 */
public class SeatEvent {
    private int id;

    public SeatEvent(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

