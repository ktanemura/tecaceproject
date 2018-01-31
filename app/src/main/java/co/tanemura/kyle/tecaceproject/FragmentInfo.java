package co.tanemura.kyle.tecaceproject;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kyle on 1/30/2018.
 *
 * Holds info about the fragment class
 */

public class FragmentInfo {
    private String className;
    private String layoutFile;
    private String classPath;
    private String enterAnimation;
    private String exitAnimation;
    private String TAG = "FragmentInfo";

    /**
     * Parse JSON File for fragment info
     * @param json JSON from file to be parsed
     */
    public FragmentInfo (String json) {
        // Log.d(TAG, "FragmentInfo: " + json);
        try {
            JSONObject o = new JSONObject(json);
            this.layoutFile = o.getString("layoutFile");
            this.className = o.getString("className");
            this.classPath = o.getString("classPath");
            this.enterAnimation = o.getString("animationEnter");
            this.exitAnimation = o.getString("animationExit");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getClassName() {
        return className;
    }

    public String getLayoutFile() {
        return layoutFile;
    }

    public String getClassPath() {
        return classPath;
    }

    public String getEnterAnimation() {
        return enterAnimation;
    }

    public String getExitAnimation() {
        return exitAnimation;
    }
}
