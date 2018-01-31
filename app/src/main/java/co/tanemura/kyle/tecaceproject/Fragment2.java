package co.tanemura.kyle.tecaceproject;
import android.os.Bundle;
import java.util.ArrayList;

/**
 * Created by Kyle on 1/29/2018.
 * Class for second fragment
 */

public class Fragment2 extends TecAceFragment {
    private String TAG = "Fragment2"; /* Tag for logging */

    public static Fragment2 newInstance(String titleContent, int fragmentNumber, ArrayList<String> jsonFiles) {
        Bundle arguments = new Bundle();
        Fragment2 cf = new Fragment2();
        arguments.putString("titleContent", titleContent);
        arguments.putStringArrayList("jsonFiles", jsonFiles);
        arguments.putInt("fragmentNumber", fragmentNumber);
        cf.setArguments(arguments);
        return cf;
    }
}
