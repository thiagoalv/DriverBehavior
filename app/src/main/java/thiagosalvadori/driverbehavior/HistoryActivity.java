package thiagosalvadori.driverbehavior;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class HistoryActivity extends AppCompatActivity{
    private String[] listOfFiles;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private Util util = new Util();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        if (getActionBar() != null){
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        listView = (ListView) findViewById(R.id.list);

        listOfFiles = getFilesDir().list();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfFiles);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(HistoryActivity.this, ManeuverChartActivity.class);
                String fileName = parent.getItemAtPosition(position).toString();
                intent.putExtra("fileName", fileName);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                for (int i = 0; i < listOfFiles.length; i++){
                    deleteFile(listOfFiles[i]);
                }
                listOfFiles = getFilesDir().list();
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfFiles);
                listView.setAdapter(adapter);
                util.showAlert(this, "Files deleted");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
