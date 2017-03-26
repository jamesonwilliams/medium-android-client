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
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.collect.ImmutableMap;

import com.medium.api.model.User;

import java.util.Map;

/**
 * UserActivity shows infromation about a Medium user.
 */
public class UserActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);

        presentUser(
            ((MediumApplication) getApplicationContext()).getMedium()
                .getUser()
        );
    }

    /**
     * Presents a user into a user view, from a user model.
     *
     * @param user the data model from which to gather data and populate
     *             the view
     */
    private void presentUser(final User user) {
        loadImagesAsync(ImmutableMap.of(
            R.id.user_image, user.getImageUrl()
        ));

        loadText(ImmutableMap.of(
            R.id.user_name, user.getName(),
            R.id.user_username, user.getUsername(),
            R.id.user_id, user.getId()
        ));

        bindClickListeners(ImmutableMap.of(
            R.id.user_image, buildOnClickVisitUrl(user.getImageUrl()),
            R.id.user_username, buildOnClickVisitUrl(user.getUrl())
        ));
    }

    /**
     * Loads a collections of texts into textviews referenced by
     * resource ids.
     *
     * @param bindings a map of resource id to string content to
     *                 populate into the views referenced by those ids.
     */
    private void loadText(final Map<Integer, String> bindings) {
        for (final Map.Entry<Integer, String> binding : bindings.entrySet()) {
            ((TextView) findViewById(binding.getKey())).setText(binding.getValue());
        }
    }

    /**
     * Binds a collection of {@link View.OnClickListener}s to a
     * collection of {@link View}s, specified resource ids to their
     * definitions in a resources file.
     *
     * @param bindings a map of resource id to click listeners
     */
    private void bindClickListeners(final Map<Integer, View.OnClickListener> bindings) {
        for (final Map.Entry<Integer, View.OnClickListener> binding : bindings.entrySet()) {
            findViewById(binding.getKey()).setOnClickListener(binding.getValue());
        }
    }

    /**
     * Loads a collection of images from URLs asynchronously into a
     * collection of {@link ImageView}s referenced by resource ids.
     *
     * @param bindings a map of resource id to image url
     */
    private void loadImagesAsync(final Map<Integer, String> bindings) {
        for (final Map.Entry<Integer, String> binding : bindings.entrySet()) {
            new DownloadImageTask(new DownloadImageTask.Listener() {
                @Override
                public void onImageDownloaded(final Bitmap bitmap) {
                    ((ImageView) findViewById(binding.getKey()))
                        .setImageBitmap(bitmap);
                }
            }).execute(binding.getValue());
        }
    }

    /**
     * Builds a {@link View.OnClickListener} which, upon being
     * triggered, will initiate the activity to view the content at the
     * provided URL.
     *
     * @param url the url that will be visited when the click listener
     *            activated (ostensibly, because a view has been
     *            clicked.)
     */
    private View.OnClickListener buildOnClickVisitUrl(final String url) {
        return new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(
                    Intent.ACTION_VIEW, Uri.parse(url)
                ));
            }
        };
    }
}

