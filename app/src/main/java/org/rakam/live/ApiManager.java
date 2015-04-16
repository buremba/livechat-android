package org.rakam.live;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by buremba on 15/04/15.
 */
public class ApiManager {

    public interface RakamAPIService {
        @GET("/551af14459cfa7a70f6e5b96")
        Observable<List<Project>> getProjects();

        @GET("/552dc3b149f6abb201a35803")
        Observable<List<ChatMessage>> getMessages(@Query("userId") String userId);

        @GET("/552db60949f6abb001a35801")
        Observable<List<Visitor>> getOnlineUsers();

        @POST("/551aea2459cfa7f50e6e5b8f")
        AuthResponse login(@Body AuthRequest password);
    }

    private static final String API_URL = "http://www.mocky.io/v2/";

    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setEndpoint(API_URL)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build();

    private static final RakamAPIService RAKAM_SERVICE = REST_ADAPTER.create(RakamAPIService.class);
    static {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .registerTypeAdapter(Date.class, new DateSerializer())
                .create();
    }

    public static RakamAPIService getService() {
        return RAKAM_SERVICE;
    }
}
