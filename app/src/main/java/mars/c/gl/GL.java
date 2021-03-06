package mars.c.gl;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.os.Build;

/**
 * Created by Constantine Mars on 6/28/15.
 */
public abstract class GL {
    public static boolean supportsGLES2(Context c){
        ActivityManager am= (ActivityManager) c.getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo ci=am.getDeviceConfigurationInfo();
        return ci.reqGlEsVersion>=0x20000
        || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1 && (Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")));
    }
}
