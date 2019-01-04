package com.bw.movie.cinema.search.event;

public class CinameEvent {
    private int cinemaId;

    public CinameEvent(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }
}
