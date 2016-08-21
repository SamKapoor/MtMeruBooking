package in.infinium.mtmerubooking.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import in.infinium.mtmerubooking.R;
import in.infinium.mtmerubooking.adapter.CountryAdapter;
import in.infinium.mtmerubooking.client.MyLoopJGet;
import in.infinium.mtmerubooking.client.MyLoopJPost;
import in.infinium.mtmerubooking.client.NetworkUrls;
import in.infinium.mtmerubooking.model.Country;
import in.infinium.mtmerubooking.utils.Common;


/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {

    // UI references.
    private EditText edtName;
    private EditText edtAddress;
    private EditText edtMobile;
    private EditText edtUsername;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnRegister;
    private Spinner spinnerCountry;
    private Spinner spinnerCity;
    private ArrayList<Country> countryArrayList;
    private CountryAdapter countryAdapter;
    private ArrayList<Country> cityArrayList;
    private CountryAdapter cityAdapter;
    private long countryID;
    private long cityID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Set up the login form.
        edtName = (EditText) findViewById(R.id.name);
        edtAddress = (EditText) findViewById(R.id.address);
        edtMobile = (EditText) findViewById(R.id.mobile);
        edtUsername = (EditText) findViewById(R.id.username);
        edtEmail = (EditText) findViewById(R.id.email);
        edtPassword = (EditText) findViewById(R.id.password);
        spinnerCountry = (Spinner) findViewById(R.id.spinnerCountry);
        spinnerCity = (Spinner) findViewById(R.id.spinnerCity);

        getCountry();

        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countryID = parent.getItemIdAtPosition(position);
                getCity(countryID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cityID = parent.getItemIdAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnRegister = (Button) findViewById(R.id.email_sign_in_button);
        btnRegister.setOnClickListener(this);
    }

    private void getCountry() {
        if (Common.isNetworkAvailable(this)) {
            MyLoopJGet myLoopJGet = new MyLoopJGet(this, "Please wait", onLoopJCountryCallComplete, NetworkUrls.countryUrl);
        } else
            Common.showNETWORDDisabledAlert(this);

    }

    MyLoopJGet.OnLoopJGetCallComplete onLoopJCountryCallComplete = new MyLoopJGet.OnLoopJGetCallComplete() {
        @Override
        public void response(String result) {
            try {
                JSONObject resultJson = new JSONObject(result);
                JSONArray dataObject = resultJson.optJSONArray("dataModel");
                if (dataObject != null && dataObject.length() > 0) {
                    countryArrayList = new ArrayList<>();
                    for (int i = 0; i < dataObject.length(); i++) {
                        JSONObject countryJson = dataObject.getJSONObject(i);
                        Country country = new Country();
                        country.setId(countryJson.getString("CountryId"));
                        country.setName(countryJson.getString("CountryName"));
                        countryArrayList.add(country);
                    }

                    countryAdapter = new CountryAdapter(RegisterActivity.this, countryArrayList);
                    spinnerCountry.setAdapter(countryAdapter);
                } else {
                    Common.showAlertDialog(RegisterActivity.this, "", "No Countries found", true, null);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

    private void getCity(long countryID) {
        if (Common.isNetworkAvailable(this)) {
            MyLoopJGet myLoopJGet = new MyLoopJGet(this, "Please wait", onLoopJCityCallComplete, NetworkUrls.cityUrl + countryID);
        } else
            Common.showNETWORDDisabledAlert(this);

    }

    MyLoopJGet.OnLoopJGetCallComplete onLoopJCityCallComplete = new MyLoopJGet.OnLoopJGetCallComplete() {
        @Override
        public void response(String result) {
            try {
                JSONObject resultJson = new JSONObject(result);
                JSONArray dataObject = resultJson.optJSONArray("dataModel");
                if (dataObject != null && dataObject.length() > 0) {
                    cityArrayList = new ArrayList<>();
                    for (int i = 0; i < dataObject.length(); i++) {
                        JSONObject countryJson = dataObject.getJSONObject(i);
                        Country country = new Country();
                        country.setId(countryJson.getString("CityId"));
                        country.setName(countryJson.getString("CityName"));
                        cityArrayList.add(country);
                    }

                    cityAdapter = new CountryAdapter(RegisterActivity.this, cityArrayList);
                    spinnerCity.setAdapter(cityAdapter);
                } else {
                    Common.showAlertDialog(RegisterActivity.this, "", "No Cities found", true, null);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


    public void attemptRegister() {
        edtEmail.setError(null);
        edtPassword.setError(null);
        // Store values at the time of the login attempt.
        String name = edtName.getText().toString();
        String address = edtAddress.getText().toString();
        String mobile = edtMobile.getText().toString();
        String username = edtUsername.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password)) {
            edtPassword.setError("Password is required");
            focusView = edtPassword;
            cancel = true;
        }
        if (TextUtils.isEmpty(username)) {
            edtUsername.setError("Username is required");
            focusView = edtUsername;
            cancel = true;
        }
        if (TextUtils.isEmpty(mobile)) {
            edtMobile.setError("Mobile is required");
            focusView = edtMobile;
            cancel = true;
        }
        if (TextUtils.isEmpty(address)) {
            edtAddress.setError("Address is required");
            focusView = edtAddress;
            cancel = true;
        }
        if (TextUtils.isEmpty(name)) {
            edtName.setError("Name is required");
            focusView = edtName;
            cancel = true;
        }

        if (countryID == 0) {
            cancel = true;
            focusView = spinnerCountry;
            Common.showAlertDialog(this, "", "Please select Country", true, null);
        }
        if (cityID == 0) {
            cancel = true;
            focusView = spinnerCity;
            Common.showAlertDialog(this, "", "Please select City", true, null);
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            if (Common.isNetworkAvailable(this)) {
                RequestParams requestParams = new RequestParams();
                requestParams.put("Name", name);
                requestParams.put("Address", address);
                requestParams.put("MobileNo", mobile);
                requestParams.put("Email", email);
                requestParams.put("UserName", username);
                requestParams.put("Password", password);
                requestParams.put("Status", "P");
                requestParams.put("CityId", cityID);
                requestParams.put("CountryId", countryID);

                MyLoopJPost myLoopJPost = new MyLoopJPost(this, "Please wait", onLoopJRegisterCallComplete, NetworkUrls.registerUrl, requestParams);
            } else
                Common.showNETWORDDisabledAlert(this);
        }
    }

    MyLoopJPost.OnLoopJPostCallComplete onLoopJRegisterCallComplete = new MyLoopJPost.OnLoopJPostCallComplete() {
        @Override
        public void response(String result) {
            try {
                JSONObject resultJson = new JSONObject(result);

                if (resultJson != null) {
                    if (resultJson.getString("result").equals("0")) {
                        Common.showAlertDialog(RegisterActivity.this, "", "Registration failed", true, null);
                    } else {
                        edtName.setText("");
                        edtAddress.setText("");
                        edtMobile.setText("");
                        edtUsername.setText("");
                        edtEmail.setText("");
                        edtPassword.setText("");
                        Common.showAlertDialog(RegisterActivity.this, "", "Registration successful", true, null);
                    }
                }
            } catch (JSONException e) {
                Common.showAlertDialog(RegisterActivity.this, "", "Registration failed", true, null);
                e.printStackTrace();
            }

        }
    };

    @Override
    public void onClick(View v) {
        if (v == btnRegister) {
            attemptRegister();
        }
    }

}

