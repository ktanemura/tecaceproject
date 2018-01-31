package co.tanemura.kyle.tecaceproject;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String layout; /* Filename of init fragment layout */
    private String className; /* Init fragment classname */
    protected ArrayList<String> jsonFiles = new ArrayList<String>(); /* List of JSON file data */
    private String TAG = "MainActivity"; /* Log.d tag */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Store all JSON files in a list
        try {
            JsonReader r;
            //Log.d(TAG, "Start file read");
            for (String f : getAssets().list("json")) {
                //Log.d(TAG, f);

                //Create JSON Reader for file f
                r = new JsonReader(new InputStreamReader(getBaseContext().getAssets().open("json/"+ f)));
                r.beginObject();

                // Get the 4 variables stored in JSON
                JSONObject o = new JSONObject();
                o.put(r.nextName(), r.nextString());
                o.put(r.nextName(), r.nextString());
                o.put(r.nextName(), r.nextString());
                o.put(r.nextName(), r.nextString());
                o.put(r.nextName(), r.nextString());
                jsonFiles.add(o.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Get initial fragment JSON info
        FragmentInfo init = new FragmentInfo(this.jsonFiles.get(0));

        //Inflate fragment dynamically by replacing view object
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Class<?> c = null;
        try {
            // Use classpath from json file
            c = Class.forName(init.getClassPath());

            // Set method param types
            Class[] methodParams = new Class[] {String.class, Integer.TYPE, ArrayList.class};
            Object[] params = new Object[]{new String(""), new Integer(0), this.jsonFiles};

            // Invoke newInstance method  for fragment and inflate returned value
            Method newInstanceMethod = c.getDeclaredMethod("newInstance", methodParams);
            Fragment fragObj = (Fragment) newInstanceMethod.invoke(c.newInstance(), params);
            fragmentTransaction.add(R.id.view, fragObj);
            fragmentTransaction.commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle back button press
     */
    @Override
    public void onBackPressed(){
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            // Go to previous fragment
            //Log.d(TAG, "popping backstack");
            fm.popBackStack();
        } else {
            // Close activity
            //Log.d(TAG, "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }
}
