#!/bin/bash

android create project \
    --package com.medium.android \
    --path medium-client-android \
    --name MediumAndroid \
    --activity UserActivity \
    --target android-25 \
    --gradle \
    --gradle-version 0.11.+ 
