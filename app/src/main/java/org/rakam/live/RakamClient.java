package org.rakam.live;


import java.util.List;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by buremba on 31/03/15.
 */
public interface RakamClient {
    @GET("/551af14459cfa7a70f6e5b96")
    Observable<List<Project>> getProjects();

    @GET("/")
    Observable<List<Project>> getMessages(int userId);

    @POST("/551aea2459cfa7f50e6e5b8f")
    AuthResponse login(@Body AuthRequest password);
}
