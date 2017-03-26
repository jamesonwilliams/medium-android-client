#!/bin/bash

readonly PACKAGE_NAME="com.medium.android"
readonly APK_PATH="build/outputs/apk/medium-client-android-debug.apk"

./gradlew build
adb uninstall "$PACKAGE_NAME" 
wait

adb logcat -c
adb install -r "$APK_PATH"
adb shell am start -n "$PACKAGE_NAME/.UserActivity"
adb logcat -d

