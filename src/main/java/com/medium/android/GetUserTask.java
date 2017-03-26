/*
 * Copyright 2017 nosemaj.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.medium.android;

import android.os.AsyncTask;

import com.medium.api.Medium;
import com.medium.api.model.User;

/**
 * GetUserTask calls the Medium API in a background thread to obtain
 * information about the user.
 *
 * When information becomes available, a listener is notified via
 * callback.
 */
public class GetUserTask extends AsyncTask<Void, Void, User> {

    private final Medium mMedium;
    private final Listener mListener;

    public GetUserTask(final Medium medium, final Listener listener) {
        this.mMedium = medium;
        this.mListener = listener;
    }

    @Override
    protected User doInBackground(Void... voids) {
        return mMedium.getUser();
    }

    @Override
    protected void onPostExecute(final User user) {
        mListener.onUserAvailable(user);
    }

    /**
     * Requests to a get user.
     */
    public void getUser() {
        executeOnExecutor(THREAD_POOL_EXECUTOR);
    }

    /**
     * Listener will be notified when users become available.
     */
    public interface Listener {
        /**
         * Invoked when a user has become available.
         *
         * @param user the user that has become available
         */
        void onUserAvailable(final User user);
    }
}
