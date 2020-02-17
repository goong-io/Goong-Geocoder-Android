package com.goong.geocoder.places.data.options;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.goong.geocoder.places.data.remote.entity.PlaceDetails;
import com.goong.geocoder.places.ui.autocomplete.AutocompleteActivity;
import com.goong.geocoder.places.util.GoongAutocompleteConstants;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PlaceAutocomplete {

    @Nullable
    public static PlaceDetails getPlaceDetail(@NonNull Intent data) {
        return data.getParcelableExtra(GoongAutocompleteConstants.PLACE_DETAIL_KEY);
    }

    public static class IntentBuilder {
        private String apiKey;
        private PlaceOptions placeOptions;

        public IntentBuilder apiKey(String key) {
            this.apiKey = key;
            return this;
        }

        public IntentBuilder placeOptions(PlaceOptions placeOptions) {
            this.placeOptions = placeOptions;
            return this;
        }

        public Intent build(Context context) {
            boolean isActivityContext = context instanceof Activity;
            Intent intent = new Intent(context, AutocompleteActivity.class);
            if (!isActivityContext) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            intent.putExtra(GoongAutocompleteConstants.API_KEY, apiKey);
            intent.putExtra(GoongAutocompleteConstants.PLACE_OPTIONS_KEY, placeOptions);
            return intent;
        }
    }
}
