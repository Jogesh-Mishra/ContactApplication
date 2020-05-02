package com.example.contactapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class NewActivity extends AppCompatActivity {

    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    EditText etName, etMail,etNumber;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        etName=findViewById(R.id.etName);
        etMail=findViewById(R.id.etMail);
        etNumber=findViewById(R.id.etNumber);
        btnSubmit=findViewById(R.id.btnSubmit);

      btnSubmit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(etMail.getText().toString().isEmpty()||etName.getText().toString().isEmpty()||etNumber.getText().toString().isEmpty())
                  Toast.makeText(NewActivity.this, "Please Enter Required Details", Toast.LENGTH_SHORT).show();
              else{
                  String contactName= etName.getText().toString().trim();
                  String contactPhone = etNumber.getText().toString().trim();
                  String contactEmail= etMail.getText().toString().trim();

                  Contact_Table contact =new Contact_Table();
                  contact.setName(contactName);
                  contact.setMail(contactEmail);
                  contact.setNumber(contactPhone);
                  contact.setUserEmail(Test_Application.user.getEmail());

                  showProgress(true);
                  tvLoad.setText("Creating New Conttact...");

                  Backendless.Persistence.save(contact, new AsyncCallback<Contact_Table>() {
                      @Override
                      public void handleResponse(Contact_Table response) {
                          Toast.makeText(NewActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                          showProgress(false);
                          NewActivity.this.finish();
                      }

                      @Override
                      public void handleFault(BackendlessFault fault) {

                          Toast.makeText(NewActivity.this, "Error: "+fault.getMessage(), Toast.LENGTH_SHORT).show();
                          showProgress(false);
                      }
                  });

              }

          }
      });

    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


}
