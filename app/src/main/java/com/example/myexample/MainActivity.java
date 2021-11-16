package com.example.myexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.os.Bundle;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private Button btnRequest;
    private ArrayList<CourseModel> courseModelArrayList;
    private RecyclerView courseRV;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String urlPower1 = "https://emoncms.org/feed/get.json?id=444677&field=value&apikey=1af79b8aa2d605ea3e350c3783a88c2d";
    private String urlPower2 = "https://emoncms.org/feed/get.json?id=444678&field=value&apikey=1af79b8aa2d605ea3e350c3783a88c2d";
    private String urlPower3 = "https://emoncms.org/feed/get.json?id=444677&field=value&apikey=1af79b8aa2d605ea3e350c3783a88c2d";
    private String urlBalelas = "https://emoncms.org/feed/get.json?id=444671&field=value&apikey=1af79b8aa2d605ea3e350c3783a88c2d";
    private String texto ="30";

    //private TextView mTextView = (TextView) findViewById(R.id.textview1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        courseRV = findViewById(R.id.idRVCourse);

        String testation = sendAndRequestResponse(urlBalelas);

        int power1 =Integer.parseInt(sendAndRequestResponse(urlPower1));
        int power2 =Integer.parseInt(sendAndRequestResponse(urlPower2));
        int power3 =Integer.parseInt(sendAndRequestResponse(urlPower3));
        int balelas =Integer.parseInt(testation);



        courseModelArrayList = new ArrayList<>();
        /*
        courseModelArrayList.add(new CourseModel("Power1", Integer.parseInt(sendAndRequestResponse(urlPower1)), R.drawable.ic_launcher_background));
        courseModelArrayList.add(new CourseModel("Power2", Integer.parseInt(sendAndRequestResponse(urlPower2)), R.drawable.ic_launcher_background));
        courseModelArrayList.add(new CourseModel("Power1", Integer.parseInt(sendAndRequestResponse(urlPower3)), R.drawable.ic_launcher_background));
        courseModelArrayList.add(new CourseModel("Power1", Integer.parseInt(sendAndRequestResponse(urlPower1)), R.drawable.ic_launcher_background));
*/
        courseModelArrayList.add(new CourseModel("power1", power1, R.drawable.ic_launcher_foreground));
        Log.e("erro teste", "asd"+sendAndRequestResponse(urlPower1));
        courseModelArrayList.add(new CourseModel("Power2", power2, R.drawable.ic_launcher_foreground));
        courseModelArrayList.add(new CourseModel("Power3", power3, R.drawable.ic_launcher_foreground));
        courseModelArrayList.add(new CourseModel("Balelas", balelas, R.drawable.ic_launcher_foreground));
        // we are initializing our adapter class and passing our arraylist to it.
        CourseAdapter courseAdapter = new CourseAdapter(this, courseModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(courseAdapter);

        // globally
        TextView textViewTucas = (TextView)findViewById(R.id.textViewTucas);


//in your OnCreate() method
        textViewTucas.setText(sendAndRequestResponse(urlPower1));

//        btnRequest = (Button) findViewById(R.id.buttonRequest);



    }


    public String sendAndRequestResponse(String url){

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);
        TextView textViewTucas = (TextView)findViewById(R.id.textViewTucas);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                texto = response;
                textViewTucas.setText(response);
                Log.e("erro teste Response", "asd"+response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG, "Error :" + error.toString());
                texto="Error :teste";
            }
        });

        mRequestQueue.add(mStringRequest);
        return texto;
    }

}