package id.kelongmakassar.kelongmakassar.util;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import java.util.Random;

public class Utility {

    public static Uri resolveLocalMediaResourceUri(int resId, Context context) {

        return new Uri.Builder()
                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .authority(context.getResources().getResourcePackageName(resId))
                .appendPath(context.getResources().getResourceTypeName(resId))
                .appendPath(context.getResources().getResourceEntryName(resId))
                .build();
    }

    public static int generateRandomInteger(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
