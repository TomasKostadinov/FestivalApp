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
import com.tomaskostadinov.openbeatz.model.Day;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LineupDayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LineupDayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LineupDayFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentTabHost mTabHost;
    private OnFragmentInteractionListener mListener;

    public LineupDayFragment() {
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
    public static LineupDayFragment newInstance(String param1, String param2) {
        LineupDayFragment fragment = new LineupDayFragment();
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

        getDays();
        return rootView;
    }

    private void setTabs(List<Day> days){

        for (int i = 0; i < days.size(); i++){
            Day day = days.get(i);
            Bundle b = new Bundle();
            b.putString("url", day.getStages_url());
            mTabHost.addTab(mTabHost.newTabSpec(day.getDay()).setIndicator(day.getDay()), LineupFragment.class, b);
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


    public void getDays() {
        ConnectRestClient.get("/lineup/days/", null, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                List<Day> l = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject day = response.getJSONObject(i);
                        Day d = new Day(
                        day.getString("day"),
                        day.getString("date"),
                        day.getString("last_change"),
                        day.getString("stages_url"));
                        l.add(d);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                LineupDayFragment.this.setTabs(l);
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
