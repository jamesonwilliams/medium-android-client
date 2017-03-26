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

import android.app.Application;

import com.medium.api.Medium;
import com.medium.api.MediumClient;

/**
 * Base application members available across all activities and
 * services.
 */
public class MediumApplication extends Application {

    /**
     * Handle to the Medium client.
     */
    private Medium mMedium;

    /**
     * Gets the singleton instance of the Medium API.
     *
     * @return the singleton instance of the Medium client.
     */
    public synchronized Medium getMedium() {
        if (null == mMedium) {
            mMedium = new MediumClient(
                getResources().getString(R.string.access_token)
            );
        }

        return mMedium;
    }
}
