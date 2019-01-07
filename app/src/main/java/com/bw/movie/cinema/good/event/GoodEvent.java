package com.bw.movie.cinema.good.event;

public class GoodEvent {
    private int commentid;

    public GoodEvent(int commentid) {
        this.commentid = commentid;
    }

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }
}
