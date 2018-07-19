package id.kelongmakassar.kelongmakassar.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.kelongmakassar.kelongmakassar.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnboardingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnboardingFragment extends Fragment {

    private static final String DESC = "OnboardingFragment.DESC";

    @BindView(R.id.text_onboarding) TextView onboardingTextView;

    private String mDesc;

    public OnboardingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param desc Description.
     * @return A new instance of fragment OnboardingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OnboardingFragment newInstance(String desc) {
        OnboardingFragment fragment = new OnboardingFragment();
        Bundle args = new Bundle();
        args.putString(DESC, desc);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDesc = getArguments().getString(DESC);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_onboarding, container, false);
        ButterKnife.bind(this, rootView);

        onboardingTextView.setText(mDesc);

        return rootView;
    }

}
