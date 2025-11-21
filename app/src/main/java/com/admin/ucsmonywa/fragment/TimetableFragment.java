package com.admin.ucsmonywa.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.admin.ucsmonywa.R;
import com.admin.ucsmonywa.activity.TimetableActivity;
import com.admin.ucsmonywa.constants.AppConstants;
import com.admin.ucsmonywa.utils.PreferenceManager;

import java.util.Arrays;

/**
 * Fragment for timetable year and section selection
 */
public class TimetableFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    
    private Spinner yearSpinner, sectionSpinner;
    private Button submitBtn;
    private String section, year;
    private PreferenceManager preferenceManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timetable_fragment, container, false);
        
        initPreferences();
        initViews(view);
        setupSpinners();
        setupSubmitButton();
        
        return view;
    }
    
    private void initPreferences() {
        if (getContext() != null) {
            preferenceManager = PreferenceManager.getInstance(getContext());
        }
    }
    
    private void initViews(View view) {
        yearSpinner = view.findViewById(R.id.spinner_year);
        sectionSpinner = view.findViewById(R.id.spinner_section);
        submitBtn = view.findViewById(R.id.submit_button);
    }
    
    private void setupSpinners() {
        // Setup year spinner
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            Arrays.asList(AppConstants.Academic.YEARS)
        );
        yearSpinner.setAdapter(yearAdapter);
        yearSpinner.setOnItemSelectedListener(this);
    }
    
    private void setupSubmitButton() {
        submitBtn.setOnClickListener(v -> {
            if (year == null || section == null) {
                year = String.valueOf(yearSpinner.getSelectedItem());
                section = String.valueOf(sectionSpinner.getSelectedItem());
            }
            Intent intent = new Intent(getActivity(), TimetableActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedYear = String.valueOf(yearSpinner.getSelectedItem());
        
        if ("First Year".equals(selectedYear)) {
            ArrayAdapter<String> sectionAdapter = new ArrayAdapter<>(
                requireActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                Arrays.asList(AppConstants.Academic.FIRST_YEAR_SECTIONS)
            );
            sectionSpinner.setAdapter(sectionAdapter);
        } else {
            ArrayAdapter<String> sectionAdapter = new ArrayAdapter<>(
                requireActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                Arrays.asList(AppConstants.Academic.OTHER_YEAR_SECTIONS)
            );
            sectionSpinner.setAdapter(sectionAdapter);
        }
        
        year = selectedYear;
        
        if (view != null) {
            ((TextView) view).setTextColor(ContextCompat.getColor(requireContext(), R.color.colorAccent));
        }
        
        sectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                section = String.valueOf(sectionSpinner.getSelectedItem());
                if (view != null) {
                    ((TextView) view).setTextColor(ContextCompat.getColor(requireContext(), R.color.colorAccent));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // No action needed
            }
        });
    }
    
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // No action needed
    }

    @Override
    public void onPause() {
        super.onPause();
        
        if (preferenceManager != null && year != null && section != null) {
            preferenceManager.saveYear(year);
            preferenceManager.saveSection(section);
        }
    }
}
