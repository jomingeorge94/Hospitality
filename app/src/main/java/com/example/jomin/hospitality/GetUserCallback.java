package com.example.jomin.hospitality;

interface GetUserCallback {

    /**
     * Invoked when background task is completed
     */

    public abstract void done(User returnedUser);
    public abstract void done();
    public abstract void done(ReminderObject returnedUser);

}