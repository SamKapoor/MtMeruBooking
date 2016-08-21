package in.infinium.mtmerubooking.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import in.infinium.mtmerubooking.R;

/**
 * Created by Sanjay on 3/9/2016.
 * This fragment is using for the listing purchase history
 */
public class FragCompanySelection extends Fragment {


    private Activity context;
    private LinearLayout llPetrol;
    private LinearLayout llGas;

    public FragCompanySelection() {
    }


    /**
     * predefine on create view for the create the layout view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_login, container, false);
        context = getActivity();
        initialize(rootView);

        return rootView;
    }

    /**
     * initialize views of the screen
     *
     * @param rootView
     */
    private void initialize(View rootView) {
        llPetrol = (LinearLayout) rootView.findViewById(R.id.ll_nav_petrol);
        llGas = (LinearLayout) rootView.findViewById(R.id.ll_nav_gas);


    }

}