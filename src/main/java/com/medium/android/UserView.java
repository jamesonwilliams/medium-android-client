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

/**
 * Defines the interface to the View, in our MVP setup.
 */
public interface UserView {

    /**
     * Displays a user's username, and attaches the visible asset to a
     * click listener which will open an url.
     *
     * @param username the text for the username field
     * @param url the url to open when the username is clicked
     */
    void displayUsername(final String username, final String url);

    /**
     * Displays a user's name in a text field.
     *
     * @param name the text for the name field.
     */
    void displayName(final String name);

    /**
     * Displays a user's avatar in an image field, and sets up an
     * onclick listener to navigate to the provided url.
     *
     * @param bitmap the bitmap to use for the avatar
     * @param url the url to open when the avatar is clicked
     */
    void displayAvatar(final Bitmap bitmap, final String url);

    /**
     * Displays a user's id in a text field.
     *
     * @param id the text for the id field
     */
    void displayId(final String id);
}

