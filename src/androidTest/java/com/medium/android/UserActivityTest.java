package com.medium.android;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.medium.android.UserActivityTest \
 * com.medium.android.tests/android.test.InstrumentationTestRunner
 */
public class UserActivityTest extends ActivityInstrumentationTestCase2<UserActivity> {

    public UserActivityTest() {
        super("com.medium.android", UserActivity.class);
    }

}
