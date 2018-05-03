package id.kelongmakassar.kelongmakassar.util;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

public class Utility {

    public static Uri resolveLocalMediaResourceUri(int resId, Context context) {

        return new Uri.Builder()
                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .authority(context.getResources().getResourcePackageName(resId))
                .appendPath(context.getResources().getResourceTypeName(resId))
                .appendPath(context.getResources().getResourceEntryName(resId))
                .build();
    }
}
