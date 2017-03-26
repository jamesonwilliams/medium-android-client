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

import android.graphics.Bitmap;

import com.medium.api.model.User;
import com.medium.api.Medium;

/**
 * UserPresenter receives requests from the UserView, and interacts with
 * the UserProvider to obtain models. Once it has obtained a model, it
 * performs presentation logic on it before calling display related
 * methods on the view.
 */
public class UserPresenter implements UserProvider.Observer {

    /**
     * The view to present into.
     */
    private UserView mView;

    /**
     * The provider of User models.
     */
    private UserProvider mProvider;

    /**
     * Constructs a new UserPresenter.
     * 
     * @param view the view to present into
     */
    public UserPresenter(final Medium medium, final UserView view) {
        this.mView = view;
        this.mProvider = new UserProvider(medium, this);
    }

    @Override
    public void onUserAvailable(final User user) {
        /*
         * Present the image resource url as a Bitmap.
         */
        new DownloadImageTask(new DownloadImageTask.Listener() {
            @Override
            public void onImageDownloaded(final Bitmap bitmap) {
                mView.displayUsername(user.getUsername(), user.getUrl());
                mView.displayName(user.getName());
                mView.displayAvatar(bitmap, user.getImageUrl());
                mView.displayId(user.getId());
            }
        }).download(user.getImageUrl());
    }

    /**
     * Request the presenter to present data from the model.
     */
    public void present() {
        mProvider.getUser();
    }
}

