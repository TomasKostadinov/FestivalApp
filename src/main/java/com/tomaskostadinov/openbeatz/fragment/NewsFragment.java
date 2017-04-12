package com.tomaskostadinov.openbeatz;

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
import com.tomaskostadinov.openbeatz.helpers.ConnectRestClient;
import com.tomaskostadinov.openbeatz.model.Message;

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
public class NewsFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    public NewsRecyclerViewAdapter newsAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NewsFragment newInstance(int columnCount) {
        NewsFragment fragment = new NewsFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            newsAdapter = new NewsRecyclerViewAdapter(this.getPosts(), mListener, getContext());
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(newsAdapter);
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
        void onListFragmentInteraction(Message item);
    }


    private List<Message> l = new ArrayList<>();

    public List<Message> getPosts() {
        ConnectRestClient.get("/posts/", null, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                String type, datetime, content, state, imageUrl, galleryUrl;
                Integer galleryId;
                try {
                    JSONArray messages = response.getJSONArray("messages");
                    for (int i = 0; i < messages.length(); i++) {
                        try {
                            JSONObject message = messages.getJSONObject(i);
                            switch (message.getString("type")) {
                                case "post-text":
                                    type = message.getString("type");
                                    datetime = message.getString("datetime");
                                    content = message.getString("content");
                                    state = message.getString("state");
                                    imageUrl = "";
                                    galleryUrl = "";
                                    galleryId = 0;
                                    break;
                                case "post-image":
                                    type = message.getString("type");
                                    datetime = message.getString("datetime");
                                    content = message.getString("content");
                                    state = message.getString("state");
                                    imageUrl = message.getString("imageUrl");
                                    galleryUrl = "";
                                    galleryId = 0;
                                    break;
                                case "post-gallery":
                                    type = message.getString("type");
                                    datetime = message.getString("datetime");
                                    content = message.getString("content");
                                    state = message.getString("state");
                                    imageUrl = "";
                                    galleryUrl = message.getString("galleryUrl");
                                    galleryId = message.getInt("galleryId");
                                    break;
                                default:
                                    type = message.getString("type");
                                    datetime = message.getString("datetime");
                                    content = message.getString("content");
                                    state = message.getString("state");
                                    galleryUrl = "";
                                    galleryId = 0;
                                    imageUrl = "";
                                    break;
                            }
                            Message m = new Message(
                                    type,
                                    datetime,
                                    content,
                                    state,
                                    imageUrl,
                                    galleryId,
                                    galleryUrl);

                            NewsFragment.this.l.add(m);
                            NewsFragment.this.newsAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

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
