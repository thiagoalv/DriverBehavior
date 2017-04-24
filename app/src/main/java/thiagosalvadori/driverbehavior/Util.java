package thiagosalvadori.driverbehavior;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by thiagosalvadori on 02/01/16.
 */
public class Util {
    /**
     * Display a pop up message
     * @param text
     */
    public void showAlert(Context context, String text) {
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void saveManeuver(Context context, ArrayList<Float> data, String type) {

        String timeStamp = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss").format(Calendar.getInstance().getTime());
        File file = new File(context.getFilesDir(), type + "_" + timeStamp + ".txt");

        try {
            FileOutputStream os = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(os);


            for (int i = 0; i < data.size(); i++) {
                pw.print(data.get(i) + "\n");
            }

            pw.close();
            os.close();

            // Tell the media scanner about the new file so that it is
            // immediately available to the user.
            MediaScannerConnection.scanFile(context,
                    new String[]{file.toString()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("ExternalStorage", "Scanned " + path + ":");
                            Log.i("ExternalStorage", "-> uri=" + uri);
                        }
                    });

        } catch (IOException e) {
            // Unable to create file, likely because external storage is
            // not currently mounted.
            //Log.w("ExternalStorage", "Error writing ", e);
        }
    }

    /**
     * Saves a window of data in a .txt file
     * @param latData
     * @param longData
     */
    public void record(Context context, ArrayList<Float> latData, ArrayList<Float> longData, File xFile, File yFile) {

        try {
            FileOutputStream osX = new FileOutputStream(xFile);
            FileOutputStream osY = new FileOutputStream(yFile);
            PrintWriter pwX = new PrintWriter(osX);
            PrintWriter pwY = new PrintWriter(osY);

            for (int i = 0; i < latData.size(); i++) {
                pwX.print(latData.get(i) + ", ");
                pwY.print(longData.get(i) + ", ");
            }

            pwX.close();
            pwY.close();
            osX.close();
            osY.close();

            // Tell the media scanner about the new file so that it is
            // immediately available to the user.
            MediaScannerConnection.scanFile(context,
                    new String[]{xFile.toString()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("ExternalStorage", "Scanned " + path + ":");
                            Log.i("ExternalStorage", "-> uri=" + uri);
                        }
                    });

            MediaScannerConnection.scanFile(context,
                    new String[]{yFile.toString()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("ExternalStorage", "Scanned " + path + ":");
                            Log.i("ExternalStorage", "-> uri=" + uri);
                        }
                    });

        } catch (IOException e) {
            // Unable to create file, likely because external storage is
            // not currently mounted.
            //Log.w("ExternalStorage", "Error writing ", e);
        }
    }
}
