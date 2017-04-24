package thiagosalvadori.driverbehavior;

import android.app.Activity;
import android.os.Bundle;

public class SettingsActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        // Display fragment as the main content.
        getFragmentManager().beginTransaction().replace(android.R.id.content, new PrefsFragment()).commit();
    }
}
