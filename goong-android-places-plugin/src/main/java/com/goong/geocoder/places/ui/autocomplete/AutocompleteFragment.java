package com.goong.geocoder.places.ui.autocomplete;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.goong.geocoder.places.R;
import com.goong.geocoder.places.data.options.PlaceOptions;
import com.goong.geocoder.places.ui.base.BaseViewHolder;
import com.goong.geocoder.places.util.GoongAutocompleteConstants;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AutocompleteFragment extends Fragment implements TextWatcher, BaseViewHolder.OnViewHolderClickListener {

    private View vBack;
    private EditText etSearchBox;
    private RecyclerView rvResults;
    private ProgressBar progressBar;
    private AutocompleteViewModel autocompleteViewModel;
    private PlaceAdapter adapter;
    private OnPlaceSelectedListener onPlaceSelectedListener;

    private static Bundle buildArgs(@NonNull final String apiKey, @Nullable PlaceOptions placeOptions) {
        Bundle bundle = new Bundle();
        bundle.putString(GoongAutocompleteConstants.API_KEY, apiKey);
        bundle.putParcelable(GoongAutocompleteConstants.PLACE_OPTIONS_KEY, placeOptions);
        return bundle;
    }

    public static AutocompleteFragment newInstance(@NonNull final String apiKey) {
        AutocompleteFragment autocompleteFragment = new AutocompleteFragment();
        autocompleteFragment.setArguments(buildArgs(apiKey, null));
        return autocompleteFragment;
    }

    public static AutocompleteFragment newInstance(@NonNull final String apiKey, @Nullable PlaceOptions placeOptions) {
        AutocompleteFragment autocompleteFragment = new AutocompleteFragment();
        autocompleteFragment.setArguments(buildArgs(apiKey, placeOptions));
        return autocompleteFragment;
    }

    public void setOnPlaceSelectedListener(final OnPlaceSelectedListener onPlaceSelectedListener) {
        this.onPlaceSelectedListener = onPlaceSelectedListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_autocomplete, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vBack = view.findViewById(R.id.iv_back);
        etSearchBox = view.findViewById(R.id.et_search_box);
        rvResults = view.findViewById(R.id.rv_results);
        progressBar = view.findViewById(R.id.pb_progress);
        setUpRecyclerView();

        etSearchBox.post(() -> {
            if (getActivity() == null) return;
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        });
        vBack.setOnClickListener(this::onBackButtonClicked);
        etSearchBox.addTextChangedListener(this);

        autocompleteViewModel = new ViewModelProvider(requireActivity()).get(AutocompleteViewModel.class);
        Bundle bundle = getArguments();
        String apiKey = bundle.getString(GoongAutocompleteConstants.API_KEY);
        Objects.requireNonNull(apiKey);
        autocompleteViewModel.init(apiKey, bundle.containsKey(GoongAutocompleteConstants.PLACE_OPTIONS_KEY) ?
                bundle.getParcelable(GoongAutocompleteConstants.PLACE_OPTIONS_KEY) : null);
        observeViewModel();
    }

    private void setUpRecyclerView() {
        adapter = new PlaceAdapter(this);
        rvResults.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvResults.setAdapter(adapter);
    }

    private void observeViewModel() {
        autocompleteViewModel.getAutoCompleteData().observe(this, places -> {
            if (adapter == null) {
                return;
            }
            if (places == null) {
                return;
            }
            adapter.setItems(places);
        });

        autocompleteViewModel.getPlaceDetailsData().observe(this, detail -> {
            if (onPlaceSelectedListener != null) {
                onPlaceSelectedListener.onPlaceSelected(detail);
            }
        });

        autocompleteViewModel.getProgressBarVisibility().observe(this, isVisible -> {
            if (isVisible == null) return;
            progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        });
    }

    private void onBackButtonClicked(View view) {
        requireActivity().finish();
    }

    @Override
    public void beforeTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {
    }

    @Override
    public void onTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {
    }

    @Override
    public void afterTextChanged(final Editable editable) {
        if (autocompleteViewModel == null) {
            // should never go here
            return;
        }
        final String text = editable.toString();
        autocompleteViewModel.onSearchChanged(text);
    }

    @Override
    public void onItemClick(final int position) {
        autocompleteViewModel.getPlaceDetail(adapter.getItem(position).getId());
    }
}
