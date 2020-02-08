package com.example.app2.SampleProject.NotificationSample;

import android.app.Activity;
import android.content.Intent;

public final class Navigator {

    private Navigator() {}

    public static void navigateToDetails(final Activity activity, final long noteId) {
        final Intent intent = new Intent(activity, NotificationDetailActivity.class);
        intent.putExtra(NotificationDetailActivity.CITY_ID, noteId);
        activity.startActivity(intent);
    }
}