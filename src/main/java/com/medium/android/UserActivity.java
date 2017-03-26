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

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.medium.api.model.User;

/**
 * UserActivity is a UserView which shows information about a user.
 */
public class UserActivity extends Activity implements UserView {

    /**
     * The view for the user's avatar.
     */
    private ImageView mAvatar;

    /**
     * The view fro the user's id.
     */
    private TextView mId;

    /**
     * The view for the user's username.
     */
    private TextView mUsername;

    /**
     * The view for the user's name.
     */
    private TextView mName;

    /**
     * A handle to the user presenter.
     */
    private UserPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindToXmlViews();

        mPresenter = new UserPresenter(
            ((MediumApplication) getApplicationContext()).getMedium(),
            this
        );

        mPresenter.present();
    }

    /**
     * Assimilate the XML portion of the logical "View" into this Java
     * instance.
     */
    private void bindToXmlViews() {
        setContentView(R.layout.user);

        mAvatar = (ImageView) findViewById(R.id.user_image);
        mId = (TextView) findViewById(R.id.user_id);
        mUsername = (TextView) findViewById(R.id.user_username);
        mName = (TextView) findViewById(R.id.user_name);
    }

    @Override
    public void displayUsername(final String username, final String url) {
        mUsername.setText(username);
        mUsername.setOnClickListener(visitUrlOnClick(url));
    }

    @Override
    public void displayName(final String name) {
        mName.setText(name);
    }

    @Override
    public void displayAvatar(final Bitmap avatar, final String url) {
        mAvatar.setImageBitmap(avatar);
        mAvatar.setOnClickListener(visitUrlOnClick(url));
    }

    @Override
    public void displayId(final String id) {
        mId.setText(id);
    }

    /**
     * Creates a new click listener which will fire an intent to open a
     * browser to view an URL.
     */
    private View.OnClickListener visitUrlOnClick(final String url) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                    new Intent(Intent.ACTION_VIEW, Uri.parse(url))
                );
            }
        };
    }
}

