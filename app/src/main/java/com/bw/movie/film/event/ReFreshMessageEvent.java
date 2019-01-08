package com.bw.movie.film.event;
/*
* ReFreshMessageEvent
* */
public class ReFreshMessageEvent {
    private int MessageId;

    public ReFreshMessageEvent(int messageId) {
        MessageId = messageId;
    }

    public int getMessageId() {
        return MessageId;
    }

    public void setMessageId(int messageId) {
        MessageId = messageId;
    }
}
