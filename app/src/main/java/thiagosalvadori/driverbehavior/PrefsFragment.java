package thiagosalvadori.driverbehavior;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by thiagosalvadori on 03/01/16.
 */
public class PrefsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }


}
