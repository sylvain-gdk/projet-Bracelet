package com.example.android.creationsmp;

import android.content.Intent;

/**
 * Created by sylvain on 2017-08-24.
 */

public class EventManager {

    // The "new" intent request code
    final static int REQUEST_NEW_PIECE = 1;

    // The "modify" intent request code
    final static int REQUEST_MODIFY_PIECE = 2;

    // The "delete" intent request code
    final static int REQUEST_DELETE_PIECE = 3;

    public static class EventIntentController {

        private Intent eventIntent;

        public EventIntentController(Intent eventIntent){
            this.eventIntent = eventIntent;
        }

        public Intent getEventIntentController(){
            return eventIntent;
        }
    }

    public static class EventIntentDetail {

        private Intent eventIntent;

        public EventIntentDetail(Intent eventIntent){
            this.eventIntent = eventIntent;
        }

        public Intent getEventIntentDetail(){
            return eventIntent;
        }
    }
}
