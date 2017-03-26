# medium-android-client

This is a toy application to demonstrate [medium-sdk-java][sdk-java].

Current it will just call `getUser()` and populate a simple view from
the `User` model in the SDK. You can click on the user name and the user
image and it will take you to the resources they are linked to.

## How To Use It

### Add Your Access Token

Put your access token in `strings.xml`:
```xml
    ...
    <string name="access_token">YOUR_ACCESS_TOKEN_HERE</string>
    ...
```

### Build it

```
./gradlew build
adb install -r build/outputs/apk/medium-client-android-debug.apk
```

Or just:

```
./deploy.sh
```

[sdk-java]: https://github.com/jamesonwilliams/medium-sdk-java
