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

import com.medium.api.Medium;
import com.medium.api.model.User;

/**
 * UserProvider provides domain models to the presenter.
 */
public class UserProvider implements GetUserTask.Listener {

    /**
     * A handle the instance of the Medium client.
     */
    private final Medium mMedium;

    /**
     * A handle to the observer of this provider, who will receive a
     * callback when a user is available.
     */
    private final Observer mObserver;

    /**
     * Constructs a new UserProvider.
     *
     * @param medium a handle to medium client
     * @param observer the observer which will receive callbacks when we
     *                 are able to provide a user.
     */
    public UserProvider(final Medium medium, final Observer observer) {
        this.mMedium = medium;
        this.mObserver = observer;
    }

    /**
     * Requests that the provider obtain a user record.
     */
    public void getUser() {
        new GetUserTask(mMedium, this).getUser();
    }

    @Override
    public void onUserAvailable(final User user) {
       mObserver.onUserAvailable(user); 
    }

    /**
     * An observer of the UserProvider will be informed when new user
     * models are available.
     */
    public interface Observer {

        /**
         * Called when a user becomes available.
         *
         * @param user the newly available user
         */
        void onUserAvailable(final User user);
    }
}

