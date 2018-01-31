package co.tanemura.kyle.tecaceproject;
import android.os.Bundle;
import java.util.ArrayList;

/**
 * Created by Kyle on 1/29/2018.
 * Class for 3rd fragment
 */

public class Fragment3 extends TecAceFragment {
    private String TAG = "Fragment3"; /* Tag for logging */

    public static Fragment3 newInstance(String titleContent, int fragmentNumber, ArrayList<String> jsonFiles) {
        Bundle arguments = new Bundle();
        Fragment3 cf = new Fragment3();
        arguments.putString("titleContent", titleContent);
        arguments.putStringArrayList("jsonFiles", jsonFiles);
        arguments.putInt("fragmentNumber", fragmentNumber);
        cf.setArguments(arguments);
        return cf;
    }
}
