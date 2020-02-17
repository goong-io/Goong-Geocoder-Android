package com.goong.geocoder.places.data.remote;

import com.goong.geocoder.places.data.remote.entity.AutoCompleteResponse;
import com.goong.geocoder.places.data.remote.entity.PlaceDetailsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface SearchApi {

    @GET(ServerConstants.HOST_URL + ServerConstants.AUTO_COMPLETE_PATH)
    @Headers({"Content-Type:Application/Json", "Accept:Application/Json"})
    Single<AutoCompleteResponse> getAutoComplete(@Query("api_key") String apiKey,
                                                 @Query("input") String keyword,
                                                 @Query("location") String latLongLocation,
                                                 @Query("limit") int limit,
                                                 @Query("radius") int radius);

    @GET(ServerConstants.HOST_URL + ServerConstants.PLACE_DETAIL_PATH)
    @Headers({"Content-Type:Application/Json", "Accept:Application/Json"})
    Single<PlaceDetailsResponse> getPlaceDetails(@Query("api_key") String apiKey,
                                                 @Query("placeid") String placeId);

}
