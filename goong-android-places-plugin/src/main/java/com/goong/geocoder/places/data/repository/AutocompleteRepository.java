package com.goong.geocoder.places.data.repository;

import com.goong.geocoder.places.data.remote.SearchApi;
import com.goong.geocoder.places.data.remote.ServiceGenerator;
import com.goong.geocoder.places.data.remote.entity.PlaceDetails;
import com.goong.geocoder.places.data.remote.entity.PredictionPlace;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import io.reactivex.Single;

public final class AutocompleteRepository {

    private SearchApi searchApi = ServiceGenerator.getSearchApi();

    public Single<List<PredictionPlace>> getAutocomplete(@NonNull String apiKey,
                                                         @NonNull String searchKeyword,
                                                         double latitude,
                                                         double longitude,
                                                         int limit,
                                                         int radius) {
        String locationString = "";
        if (latitude != 0 && longitude != 0) {
            locationString = String.format(Locale.US, "%1$f,%2$f", latitude, longitude);
        }
        return searchApi.getAutoComplete(apiKey, searchKeyword, locationString, limit, radius)
                .map(autoCompleteResponse -> autoCompleteResponse.predictionPlaces);
    }

    public Single<PlaceDetails> getPlaceDetails(@NonNull String apiKey, @NonNull String placeId) {
        return searchApi.getPlaceDetails(apiKey, placeId)
                .map(placeDetailsResponse -> placeDetailsResponse.placeDetails);
    }

}
