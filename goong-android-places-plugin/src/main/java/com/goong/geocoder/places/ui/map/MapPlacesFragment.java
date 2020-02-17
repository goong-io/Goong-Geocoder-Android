package com.goong.geocoder.places.ui.map;//package com.goong.goong_android_places_plugin.ui.map;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ImageView;
//
//import com.goong.goong_android_places_plugin.R;
//import com.goong.goong_android_places_plugin.ui.autocomplete.AutocompleteActivity;
//
//import java.util.Objects;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.core.app.ActivityOptionsCompat;
//import androidx.core.util.Pair;
//import androidx.fragment.app.Fragment;
//import io.goong.goongsdk.Goong;
//import io.goong.goongsdk.annotations.MarkerOptions;
//import io.goong.goongsdk.camera.CameraPosition;
//import io.goong.goongsdk.camera.CameraUpdateFactory;
//import io.goong.goongsdk.geometry.LatLng;
//import io.goong.goongsdk.maps.GoongMap;
//import io.goong.goongsdk.maps.MapView;
//import io.goong.goongsdk.maps.OnMapReadyCallback;
//
//public class MapPlacesFragment extends Fragment implements OnMapReadyCallback {
//
//    static final String KEY_GOONG_MAP_ACCESSTOKEN = "key_goong_map_accesstoken";
//    private GoongMap mMap;
//    private MapView mapView;
//    private ImageView ivSearchIcon;
//    private EditText etSearchBox;
//    private View vSearchContainer;
//
//    public static MapPlacesFragment newInstance(@NonNull final String accessToken) {
//        Bundle bundle = new Bundle();
//        bundle.putString(KEY_GOONG_MAP_ACCESSTOKEN, accessToken);
//
//        final MapPlacesFragment mapPlacesFragment = new MapPlacesFragment();
//        mapPlacesFragment.setArguments(bundle);
//        return mapPlacesFragment;
//    }
//
//    private String extractAccessToken() {
//        Bundle arguments = getArguments();
//        if (arguments == null || !arguments.containsKey(KEY_GOONG_MAP_ACCESSTOKEN)) {
//            return null;
//        }
//        return arguments.getString(KEY_GOONG_MAP_ACCESSTOKEN);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
//                             @Nullable final Bundle savedInstanceState) {
//        final String accessToken = extractAccessToken();
//        Objects.requireNonNull(accessToken);
//        Goong.getInstance(requireContext(), accessToken);
//
//        final View view = inflater.inflate(R.layout.fragment_map_places, container, false);
//        mapView = view.findViewById(R.id.map);
//        mapView.onCreate(savedInstanceState);
//        mapView.getMapAsync(this);
//
//        etSearchBox = view.findViewById(R.id.et_search_box);
//        ivSearchIcon = view.findViewById(R.id.iv_search_icon);
//        etSearchBox.setOnClickListener(this::onSearchBoxClicked);
//        vSearchContainer = view.findViewById(R.id.v_search_container);
//        vSearchContainer.setOnClickListener(this::onSearchBoxClicked);
//        return view;
//    }
//
//    @Override
//    public void onMapReady(@NonNull final GoongMap goongMap) {
//        mMap = goongMap;
//
//        LatLng swordLakeLocation = new LatLng(21.0287747, 105.850176);
//        mMap.addMarker(new MarkerOptions().position(swordLakeLocation).title("Hồ Gươm"));
//
//        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(
//                new CameraPosition.Builder()
//                        .zoom(13.0)
//                        .target(swordLakeLocation)
//                        .build()
//        ));
//    }
//
//    private void onSearchBoxClicked(View view) {
//        Intent intent = new Intent(requireActivity(), AutocompleteActivity.class);
//
//        Pair<View, String> p1 = Pair.create(ivSearchIcon, getString(R.string.transition_name_1));
//        Pair<View, String> p2 = Pair.create(etSearchBox, getString(R.string.transition_name_2));
//        Pair<View, String> p3 = Pair.create(vSearchContainer, getString(R.string.transition_name_3));
//        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(), p1, p2,
//                p3);
//        startActivity(intent, options.toBundle());
//    }
//
//    // Add the mapView lifecycle to the activity's lifecycle methods
//    @Override
//    public void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        mapView.onStart();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        mapView.onStop();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mapView.onPause();
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mapView.onLowMemory();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mapView.onDestroy();
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        mapView.onSaveInstanceState(outState);
//    }
//}
