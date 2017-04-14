package com.tomaskostadinov.openbeatz.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.tomaskostadinov.openbeatz.R;
import com.tomaskostadinov.openbeatz.helpers.ConnectRestClient;
import com.tomaskostadinov.openbeatz.model.Guide;
import com.tomaskostadinov.openbeatz.model.GuideSection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GuideDetailTabFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GuideDetailTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuideDetailTabFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public String guideUrl;

    private FragmentTabHost mTabHost;
    private OnFragmentInteractionListener mListener;

    public GuideDetailTabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LineupDayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GuideDetailTabFragment newInstance(String param1, String param2) {
        GuideDetailTabFragment fragment = new GuideDetailTabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_lineup_day, container, false);


        mTabHost = (FragmentTabHost) rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            guideUrl = bundle.getString("url", "2223");
        } else {
            guideUrl = "https://connect.nearstage.com/v1/openbeatz/guide/1033/";
        }
        getGuide();
        return rootView;

    }

    private void setTabs(Guide guides) {
        for (int i = 0; i < guides.getSections().size(); i++) {
            GuideSection guide = guides.getSections().get(i);
            Bundle b = new Bundle();
            b.putString("text", guide.getContent());
            mTabHost.addTab(mTabHost.newTabSpec(guide.getId().toString()).setIndicator(guide.getTitle()), GuideDetailFragment.class, b);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void getGuide() {
        ConnectRestClient.getUrl(GuideDetailTabFragment.this.guideUrl, null, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject guide) {
                // called when response HTTP status is "200 OK"

                try {
                    JSONArray sections = guide.getJSONArray("sections");
                    List<GuideSection> sectionList = new ArrayList<>();
                    for (int a = 0; a < sections.length(); a++) {
                        try {
                            JSONObject section = sections.getJSONObject(a);
                            GuideSection s = new GuideSection(
                                    section.getInt("id"),
                                    section.getString("title"),
                                    section.getString("content")
                            );
                            sectionList.add(s);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    GuideDetailTabFragment.this.setTabs(new Guide(
                            guide.getString("title"),
                            guide.getString("subtitle"),
                            guide.getString("icon"),
                            guide.getString("type"),
                            guide.getInt("id"),
                            sectionList
                    ));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

            }


            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });

    }


}
