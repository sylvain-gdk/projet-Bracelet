package com.example.android.creationsmp;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by sylvain on 2017-08-24.
 */

public class EventManager {

    // The "new" PieceModel object request code
    final static int REQUEST_NEW_PIECE = 1;
    // The "modify" PieceModel object request code
    final static int REQUEST_MODIFY_PIECE = 2;
    // The "delete" PieceModel object request code
    final static int REQUEST_DELETE_PIECE = 3;

    public static class EventIntent{

        private Intent eventIntent;

        public EventIntent(Intent eventIntent){
            this.eventIntent = eventIntent;
        }

        public Intent getEventIntent(){
            return eventIntent;
        }
    }

    public static class EventBundle{

        private Bundle eventBundle;

        public EventBundle(Bundle eventBundle){
            this.eventBundle = eventBundle;
        }

        public Bundle getEventBundle(){
            return eventBundle;
        }

    }
}
