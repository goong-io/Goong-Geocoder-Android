package com.goong.geocoder.places.ui.autocomplete;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.goong.geocoder.places.R;
import com.goong.geocoder.places.data.options.PlaceOptions;
import com.goong.geocoder.places.data.remote.entity.PlaceDetails;
import com.goong.geocoder.places.util.GoongAutocompleteConstants;

import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AutocompleteActivity extends AppCompatActivity implements OnPlaceSelectedListener {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete);

        final String apiKey = getIntent().getStringExtra(GoongAutocompleteConstants.API_KEY);
        final PlaceOptions options = getIntent().getParcelableExtra(GoongAutocompleteConstants.PLACE_OPTIONS_KEY);
        Objects.requireNonNull(apiKey);


        final String tag = "AutocompleteFragment";
        AutocompleteFragment autocompleteFragment =
                (AutocompleteFragment) getSupportFragmentManager().findFragmentByTag(tag);

        if (autocompleteFragment == null) {
            autocompleteFragment = AutocompleteFragment.newInstance(apiKey, options);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, autocompleteFragment, tag)
                    .commit();
        }
        autocompleteFragment.setOnPlaceSelectedListener(this);
    }

    @Override
    public void onPlaceSelected(final PlaceDetails placeDetails) {
        if (placeDetails == null) return;
        Intent data = new Intent();
        data.putExtra(GoongAutocompleteConstants.PLACE_DETAIL_KEY, placeDetails);
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}
