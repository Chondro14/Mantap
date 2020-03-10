package com.market_tradis.appsmovie;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import com.market_tradis.appsmovie.Notification.DailyReminder;
import com.market_tradis.appsmovie.Notification.ReleaseReminder;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settingse, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {
        SwitchPreference switchPreferencedaily, switchPreferencerelease;
        DailyReminder dailyReminder;
        ReleaseReminder releaseReminder;
        Preference preferenceLanguage, preferenceContact, preferenceAbout;
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            switchPreferencedaily = findPreference(getString(R.string.key_daily));
            assert switchPreferencedaily != null;
            switchPreferencedaily.setOnPreferenceChangeListener( this);
            switchPreferencerelease = findPreference(getResources().getString(R.string.key_release));
            assert switchPreferencerelease != null;
            switchPreferencerelease.setOnPreferenceChangeListener( this);

            dailyReminder = new DailyReminder();
            releaseReminder = new ReleaseReminder();

            preferenceLanguage = findPreference(getString(R.string.key_language));
            assert preferenceLanguage != null;
            preferenceLanguage.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
                    return true;
                }
            });


        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String getKey = preference.getKey();
            boolean value = (boolean) newValue;
            if (getKey.equals(getString(R.string.key_daily))) {
                if (value) {
                    dailyReminder.setRepeatingAlarm(getContext(), getResources().getString(R.string.daily), "07:00", getResources().getString(R.string.Sumarry_DAILY) );
                }
                else {
                    dailyReminder.cancelAlarm(getContext(),DailyReminder.TYPE_REPEATING );
                }
            }
            else {
                if (value) {
                    releaseReminder.setAlarm(getContext(),getResources().getString(R.string.releaase), "08:00", getResources().getString(R.string.summary_release));
                }
                else {
                    releaseReminder.cancelAlarm(getActivity());
                }
            }
            return true;
        }

        }
    }
