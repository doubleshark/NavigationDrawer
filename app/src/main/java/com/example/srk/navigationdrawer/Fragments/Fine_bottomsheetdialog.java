package com.example.srk.navigationdrawer.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.srk.navigationdrawer.Others.Fine_pojo;
import com.example.srk.navigationdrawer.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Fine_bottomsheetdialog extends BottomSheetDialogFragment {

    View view;

    String m_id = "hDjofc47534841725025";
    String customer_id = "123";
    String order_id = UUID.randomUUID().toString().substring(0,28);
    String url = "https://double-shark.000webhostapp.com/Paytm_php/generateChecksum.php";
    String callbackurl = "https://pguat.paytm.com/paytmchecksum/paytmCallback.jsp";

    TextView fine_amount_bs;
    Button payment_btn_BS;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.bottom_sheet_fine,container,false);


        fine_amount_bs = view.findViewById(R.id.fine_amount_bs);
        fine_amount_bs.setText("₹ "+Integer.toString(Fine_pojo.fineamount));

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
        }


        payment_btn_BS = view.findViewById(R.id.payment_btn_BS);
        payment_btn_BS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), ""+Fine_pojo.fineamount, Toast.LENGTH_SHORT).show();
                Paytm_payment();
            }
        });

        return view;
    }

    private void Paytm_payment() {

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.has("CHECKSUMHASH"))
                    {
                        String CHECKSUMHASH = jsonObject.getString("CHECKSUMHASH");
                        PaytmPGService paytmPGService = PaytmPGService.getStagingService();

                        HashMap<String, String> paramMap = new HashMap<String,String>();
                        paramMap.put( "MID" , m_id);
                        paramMap.put( "ORDER_ID" , order_id);
                        paramMap.put( "CUST_ID" , customer_id);
                        paramMap.put( "CHANNEL_ID" , "WAP");
                        paramMap.put( "TXN_AMOUNT" , Integer.toString(Fine_pojo.fineamount));
                        paramMap.put( "WEBSITE" , "WEBSTAGING");
                        paramMap.put( "INDUSTRY_TYPE_ID" , "Retail");
                        paramMap.put( "CALLBACK_URL", callbackurl);
                        paramMap.put( "CHECKSUMHASH", CHECKSUMHASH);

                        PaytmOrder order = new PaytmOrder(paramMap);

                        paytmPGService.initialize(order,null);
                        paytmPGService.startPaymentTransaction(getActivity(), true, true, new PaytmPaymentTransactionCallback() {
                            @Override
                            public void onTransactionResponse(Bundle inResponse) {

                                Toast.makeText(getContext(), "Payment Transaction response " + inResponse.toString(), Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void networkNotAvailable() {

                                Toast.makeText(getContext(), "Network connection error: Check your internet connectivity", Toast.LENGTH_LONG).show();


                            }

                            @Override
                            public void clientAuthenticationFailed(String inErrorMessage) {

                                Toast.makeText(getContext(), "Authentication failed: Server error" + inErrorMessage, Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void someUIErrorOccurred(String inErrorMessage) {

                                Toast.makeText(getContext(), "UI Error " + inErrorMessage , Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {

                                Toast.makeText(getContext(), "Unable to load webpage " + inErrorMessage, Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onBackPressedCancelTransaction() {

                                Toast.makeText(getContext(), "Transaction cancelled" , Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {

                                Toast.makeText(getContext(), "Transaction Cancelled" + inResponse.toString(), Toast.LENGTH_LONG).show();


                            }
                        });


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> paramMap = new HashMap<String,String>();
                paramMap.put( "MID" , m_id);
                paramMap.put( "ORDER_ID" , order_id);
                paramMap.put( "CUST_ID" , customer_id);
                paramMap.put( "CHANNEL_ID" , "WAP");
                paramMap.put( "TXN_AMOUNT" , Integer.toString(Fine_pojo.fineamount));
                paramMap.put( "WEBSITE" , "WEBSTAGING");
                paramMap.put( "INDUSTRY_TYPE_ID" , "Retail");
                paramMap.put( "CALLBACK_URL", callbackurl);

                return paramMap;
            }
        };

        requestQueue.add(stringRequest);


    }
}
