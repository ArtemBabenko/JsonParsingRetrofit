package json_parsing_retrofit.jsonparsingretrofit.api;

import json_parsing_retrofit.jsonparsingretrofit.models.ContactList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/bins/7tghr")
    Call<ContactList> getMyJSON();
}
