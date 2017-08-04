package json_parsing_retrofit.jsonparsingretrofit.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import json_parsing_retrofit.jsonparsingretrofit.R;
import json_parsing_retrofit.jsonparsingretrofit.adapter.ContactAdapter;
import json_parsing_retrofit.jsonparsingretrofit.api.ApiService;
import json_parsing_retrofit.jsonparsingretrofit.api.RetroClient;
import json_parsing_retrofit.jsonparsingretrofit.models.Contact;
import json_parsing_retrofit.jsonparsingretrofit.models.ContactList;
import json_parsing_retrofit.jsonparsingretrofit.utils.InternetConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ContactAdapter adapter;
    private RecyclerView recycler;
    private List<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createRecycler();
        loaData();
    }

    private void createRecycler() {
        recycler = (RecyclerView) findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutMenager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutMenager);
        recycler.setHasFixedSize(true);
    }

    private void loaData() {

        if (InternetConnection.checkConnection(getApplicationContext())) {
            final ProgressDialog dialog;
            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("Loading");
            dialog.setMessage("Loading data...");
            dialog.show();

            //Creating an object of our api interface
            ApiService api = RetroClient.getApiService();

            /**
             * Calling JSON
             */
            Call<ContactList> call = api.getMyJSON();

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<ContactList>() {
                @Override
                public void onResponse(Call<ContactList> call, Response<ContactList> response) {
                    //Dismiss Dialog
                    dialog.dismiss();

                    if (response.isSuccessful()) {
                        /**
                         * Got Successfully
                         */
                        contactList = response.body().getContacts();

                        /**
                         * Binding that List to Adapter
                         */
                        adapter = new ContactAdapter(contactList, MainActivity.this);
                        recycler.setAdapter(adapter);

                    } else {
                        Toast.makeText(MainActivity.this, "Some think wrong", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ContactList> call, Throwable t) {
                    dialog.dismiss();
                }
            });

        } else {
            Toast.makeText(MainActivity.this, "Internet connection not available", Toast.LENGTH_SHORT).show();
        }
    }

}
