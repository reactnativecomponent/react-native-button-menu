package com.ui.menu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.net.URL;

public class JsDevImageLoader {

    public static Drawable loadIcon(Context context, String iconDevUri) {
        try {
            StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());

            Drawable drawable = tryLoadIcon(context, iconDevUri);

            StrictMode.setThreadPolicy(threadPolicy);
            return drawable;
        } catch (Exception e) {
            throw new RuntimeException(iconDevUri, e);
        }
    }

    @NonNull
    private static Drawable tryLoadIcon(Context context, String iconDevUri) throws IOException {
        URL url = new URL(iconDevUri);
        Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
        return new BitmapDrawable(context.getResources(), bitmap);
    }
}
