# medium-android-client

This is a toy application to demonstrate [medium-sdk-java][sdk-java].

Current it will just call the SDK `getUser()` and populate a simple view
from the `User` model returned.

You can click on the user name and the user image and it will take you
to the resources they are linked to.

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

## MVP

This is also an example of the Model-View-Presenter paradigm:

 - The Model is `User`, from the SDK. They are provided by the
  `UserProvider`, in this package. The `UserProvider` is a simple wrapper
  around an `AsyncTask` called `GetUserTask`, which will fetch a user in
  the background and notify a `UserProvider.Observer` when the user is
  available

 - The Presenter is the `UserProvider.Observer` mentioned above. When it
   gets a new `User`, it will call methods on the view to present it. It
   also needs to convert the `user.getImageUrl()` into a bonafide image
   to present. To do this, it uses an `AsyncTask` called
   `DownloadImageTask`.

 - The View is `UserActivity.java`. It responds to requests from the
   presenter to load various assets into the template. It is a compound of
   the XML in `res/layouts/user.xml` and the interface defined in
   `UserView.java`.

[sdk-java]: https://github.com/jamesonwilliams/medium-sdk-java
