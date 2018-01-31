package co.tanemura.kyle.tecaceproject;
import android.os.Bundle;
import java.util.ArrayList;

/**
 * Created by Kyle on 1/29/2018.
 * Class for first fragment
 */

public class Fragment1 extends TecAceFragment {
    private String TAG = "Fragment 1"; /* Tag for logging */

    public static Fragment1 newInstance(String titleContent, int fragmentNumber, ArrayList<String> jsonFiles) {
        Bundle arguments = new Bundle();
        Fragment1 cf = new Fragment1();
        arguments.putString("titleContent", titleContent);
        arguments.putStringArrayList("jsonFiles", jsonFiles);
        arguments.putInt("fragmentNumber", fragmentNumber);
        cf.setArguments(arguments);
        return cf;
    }


}
