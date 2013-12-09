package com.yelinaung.volly;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


/**
 * Created by Ye Lin Aung on 13/12/09.
 */
public class MyFragment extends Fragment {
    Button okay;
    TextView result;
    EditText edit;
    String url = "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
    RequestQueue queue;

    public MyFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        assert rootView != null;
        okay = (Button) rootView.findViewById(R.id.okay);
        result = (TextView) rootView.findViewById(R.id.result);
        edit = (EditText) rootView.findViewById(R.id.enter);

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String query = url + edit.getText().toString();
                Log.e("wtf", "Query " + query);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, query, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        result.setText("Response " + jsonObject.toString());
                        Log.e("wtf", "Response " + jsonObject.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        result.setText("Error " + volleyError.toString());
                    }
                }
                );
                queue.add(jsonObjectRequest);
            }
        });

        return rootView;
    }

}
