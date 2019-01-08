package com.bw.movie.cinema.event;

/**
 * date:2018/12/27    19:54
 * author:张文龙(张文龙)
 * fileName:FindMovieScheduleListEvent
 */
public class FindMovieScheduleListEvent {

    private int id;

    public FindMovieScheduleListEvent(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
