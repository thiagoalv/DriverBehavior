package thiagosalvadori.driverbehavior;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ManeuverChartActivity extends Activity {
    // Chart Layout
    private LinearLayout chartLayout;
    private Chart chart;
    private Util util =  new Util();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maneuver_chart);
        Bundle extras = getIntent().getExtras();
        String fileName = extras.getString("fileName");

        chartLayout = (LinearLayout) findViewById(R.id.chart);
        chart = new Chart(this);

        RelativeLayout.LayoutParams chartParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );

        chart.getChart().setLayoutParams(chartParams);
        chartLayout.addView(chart.getChart());

        String filePath = this.getFilesDir() + "/" + fileName;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null)
            {
                chart.addEntry(Float.valueOf(line));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            util.showAlert(this, "File not found");
        } catch (IOException e) {
            util.showAlert(this, "IO Exception");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
