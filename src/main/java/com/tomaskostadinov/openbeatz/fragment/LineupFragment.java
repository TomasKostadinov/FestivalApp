package com.tomaskostadinov.openbeatz.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.tomaskostadinov.openbeatz.adapter.LineupRecyclerViewAdapter;
import com.tomaskostadinov.openbeatz.R;
import com.tomaskostadinov.openbeatz.helpers.ConnectRestClient;
import com.tomaskostadinov.openbeatz.model.Artist;
import com.tomaskostadinov.openbeatz.model.Stage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class LineupFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    public LineupRecyclerViewAdapter stageAdapter;
    public boolean loaded = false;
    public String dayId = "";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LineupFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static LineupFragment newInstance(int columnCount) {
        LineupFragment fragment = new LineupFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            dayId = bundle.getString("url", "2223");

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lineup_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            stageAdapter = new LineupRecyclerViewAdapter(this.getLineup(), mListener);
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(stageAdapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Stage item);
    }


    private List<Stage> l = new ArrayList<>();

    public List<Stage> getLineup() {
        ConnectRestClient.getUrl(LineupFragment.this.dayId, null, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                try {
                    JSONArray stages = response.getJSONArray("stages");
                    for (int i = 0; i < stages.length(); i++) {
                        try {
                            JSONObject stage = stages.getJSONObject(i);
                            List<Artist> artistsList = new ArrayList<>();
                            try {
                                JSONArray artists = stage.getJSONArray("artists");
                                for (int a = 0; a < artists.length(); a++) {
                                    JSONObject artist = artists.getJSONObject(a);
                                    artistsList.add(
                                            new Artist(
                                                    artist.getString("title"),
                                                    artist.getInt("id"),
                                                    artist.getString("image"),
                                                    artist.getString("thumbnail"),
                                                    artist.getString("start"),
                                                    artist.getString("end"),
                                                    artist.getString("day"),
                                                    artist.getInt("day_id"),
                                                    artist.getString("date"),
                                                    artist.getString("stage"),
                                                    artist.getInt("stage_id")
                                            )
                                    );
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            Stage s = new Stage(
                                    stage.getString("title"),
                                    stage.getInt("id"),
                                    stage.getString("background"),
                                    artistsList
                            );
                            // prevent loading twice
                            if (!LineupFragment.this.loaded) {
                                LineupFragment.this.l.add(s);
                                LineupFragment.this.stageAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    LineupFragment.this.loaded = true;

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

        return this.l;

    }


}
