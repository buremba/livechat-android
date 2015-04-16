package org.rakam.live;

import java.util.Date;

/**
 * Created by buremba on 15/04/15.
 */
public class ChatMessage {
    private final String name, message;
    private final boolean fromOperator;
    private final boolean seen;
    private final Date date;

    public ChatMessage(String name, String message, boolean fromOperator, Date date, boolean seen) {
        this.name = name;
        this.message = message;
        this.fromOperator = fromOperator;
        this.date = date;
        this.seen = seen;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public boolean isFromOperator() {
        return fromOperator;
    }

    public Date getDate() {
        return date;
    }
}
