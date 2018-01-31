package co.tanemura.kyle.tecaceproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Kyle on 1/30/2018.
 * Abstract class representing core functionality of the fragments displayed in this app
 */

abstract public class TecAceFragment extends Fragment{
    private Button b; /* Button to next fragment */
    private TextView title; /* Textbox to display current fragment name argument*/
    private String titleContent; /* Text for textbox */
    private FragmentInfo info; /* JSON info for fragment from file */
    private int num; /* Fragment number */

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater,
                              @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState) {
        // Get arguments from parent/previous fragment
        Bundle arguments = getArguments();
        this.titleContent = arguments.getString("titleContent");
        this.num = arguments.getInt("fragmentNumber");
        final ArrayList<String> jsonFiles = arguments.getStringArrayList("jsonFiles");

        // Get fragment info
        this.info = new FragmentInfo(jsonFiles.get(this.num));

        // Get layout file id
        int id = getResources().getIdentifier(info.getLayoutFile(), "layout", "co.tanemura.kyle.tecaceproject");

        // Create view
        View v = inflater.inflate(id, container, false);
        title = (TextView) v.findViewById(R.id.fragment_text);
        b = (Button) v.findViewById(R.id.fragment_button);

        // Set view content
        title.setText(this.titleContent);
        b.setText("To Next Fragment");

        // Set button action to go to next fragment
        // Need to set fragmentNumber and pass argument for next fragment's "titleContent"
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create next fragment
                if (!titleContent.equals("")) {
                    titleContent += ", " + info.getClassName();
                }
                else {
                    titleContent = info.getClassName();
                }
                num = (num < 2) ? num + 1 : 0;

                // Get next fragment JSON data
                FragmentInfo nextFragment = new FragmentInfo(jsonFiles.get(num));

                //Inflate fragment dynamically by replacing view object and set custom animation
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Set custom animation (first pair are next fragment's enter with this fragment's exit, second pair is this fragment's enter with next fragment's exit)
                int a1 = getResources().getIdentifier(nextFragment.getEnterAnimation(), "animator", "co.tanemura.kyle.tecaceproject");
                int a4 = getResources().getIdentifier(nextFragment.getExitAnimation(), "animator", "co.tanemura.kyle.tecaceproject");
                int a3 = getResources().getIdentifier(info.getEnterAnimation(), "animator", "co.tanemura.kyle.tecaceproject");
                int a2 = getResources().getIdentifier(info.getExitAnimation(), "animator", "co.tanemura.kyle.tecaceproject");
                fragmentTransaction.setCustomAnimations(a1, a2, a3, a4);
                Class<?> c = null;
                try {
                    // Use classpath from json file
                    c = Class.forName(nextFragment.getClassPath());

                    // Set method param types
                    Class[] methodParams = new Class[] {String.class, Integer.TYPE, ArrayList.class};
                    Object[] params = new Object[]{new String(titleContent), new Integer(num), jsonFiles};

                    // Invoke newInstance method for fragment, inflate returned value, add value to backstack
                    Method newInstanceMethod = c.getDeclaredMethod("newInstance", methodParams);
                    Fragment fragObj = (Fragment) newInstanceMethod.invoke(c.newInstance(), params);
                    fragmentTransaction.replace(R.id.view, fragObj).addToBackStack(null).commit();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
        return v;
    }
}
