package com.example.jomin.hospitality;

import java.util.List;

interface GetUserCallback {

    /**
     * Invoked when background task is completed
     */

    public abstract void done(User returnedUser);
    public abstract void done();
    public abstract void done( List<ReminderObject> returnedUser);

}