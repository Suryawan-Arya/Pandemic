package com.example.pandemic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class TestKitListAdapter extends RecyclerView.Adapter<TestKitListAdapter.ViewHolder>{

    private String URL_UPDATE_TEST_KIT = "https://pandemic-bit302.000webhostapp.com/updateTestKit.php";

    private Context context;
    private ArrayList<TestKit> TestKit = new ArrayList<>();

    //private int amount;

    public TestKitListAdapter() {
    }

    public TestKitListAdapter(Context context, ArrayList<TestKit> testKit) {
        this.context = context;
        TestKit = testKit;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.test_kit_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.testKitNameTv.setText(TestKit.get(position).getTestKitName());
        holder.testKitAmountTv.setText(TestKit.get(position).getStock());
        holder.amount = parseInt(TestKit.get(position).getStock());
        holder.upBtn.setImageResource(R.drawable.up);
        holder.downBtn.setImageResource(R.drawable.down);
        holder.upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.amount++;
                System.out.println(holder.amount);
                holder.testKitAmountTv.setText(String.valueOf(holder.amount));
                holder.updateTestKit(TestKit.get(position).getId(), String.valueOf(holder.amount));
            }
        });
        holder.downBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.amount--;
                System.out.println(holder.amount);
                holder.testKitAmountTv.setText(String.valueOf(holder.amount));
                holder.updateTestKit(TestKit.get(position).getId(), String.valueOf(holder.amount));
            }
        });
    }

    @Override
    public int getItemCount() {
        return TestKit.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        int amount;
        TextView testKitNameTv, testKitAmountTv;
        ImageView upBtn, downBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            testKitNameTv = itemView.findViewById(R.id.testKitNameTv);
            testKitAmountTv = itemView.findViewById(R.id.amontTv);
            upBtn = itemView.findViewById(R.id.upBtn);
            downBtn = itemView.findViewById(R.id.downBtn);
            upBtn.setImageResource(R.drawable.up_icon);
            downBtn.setImageResource(R.drawable.down_icon);
        }

        public void updateTestKit(final String testKitID, final String amount){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE_TEST_KIT,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println("Respose Here ------------> " + response);
                            Toast.makeText(context, "Test Kit Stock Updated", Toast.LENGTH_LONG).show();
                            try{
                                JSONObject jsonObject = new JSONObject(response);
                            }catch (JSONException e){
                                e.printStackTrace();
                                Toast.makeText(context, "Error" + e, Toast.LENGTH_LONG).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("ID", testKitID);
                    params.put("stock", amount);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        }
    }


}
