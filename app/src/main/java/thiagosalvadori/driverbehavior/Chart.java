package thiagosalvadori.driverbehavior;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

/**
 * Created by thiagosalvadori on 01/01/16.
 */
public class Chart {
    private LineChart lineChart;
    private String selectedAxis = "xAxis";
    private static final float MAX_VISIBLE_RANGE = 100;

    public Chart(Context context){
        lineChart = new LineChart(context);
        // customize line chart
        configChart(lineChart);
    }

    public void setSelectedAxis(String selectedAxis) {
        this.selectedAxis = selectedAxis;
    }

    public String getSelectedAxis() {
        return selectedAxis;
    }

    public LineChart getChart() {
        return lineChart;
    }

    /**
     * Add a value to the Chart
     * @param entry
     */
    public void addEntry(float entry){
        LineData data = lineChart.getData();

        if (data != null){
            LineDataSet set = data.getDataSetByIndex(0);

            if (set == null){
                set = createSet();
                data.addDataSet(set);
            }

            // change line color
            if (selectedAxis.equals("xAxis")){
                set.setColor(ColorTemplate.getHoloBlue());
            } else if (selectedAxis.equals("yAxis")){
                set.setColor(Color.RED);
            } else if (selectedAxis.equals("zAxis")){
                set.setColor(Color.GREEN);
            }

            // add entry
            data.addXValue("");
            data.addEntry(new Entry(entry, set.getEntryCount()), 0);

            // notify chart change
            lineChart.notifyDataSetChanged();

            // limit number o values visible
            lineChart.setVisibleXRangeMaximum(MAX_VISIBLE_RANGE);

            // move to last entry
            lineChart.moveViewToX(data.getXValCount() - (MAX_VISIBLE_RANGE+1));
        }
    }

    /**
     * Chart's line configuration
     * @return
     */
    private LineDataSet createSet() {
        LineDataSet set = new LineDataSet(null, "Dynamic Data");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        //set.setCircleColor(Color.WHITE);
        set.setLineWidth(2f);
        set.setCircleSize(0f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244, 117, 117));
        set.setValueTextColor(Color.WHITE);
        set.setValueTextSize(9f);
        set.setDrawValues(false);
        return set;
    }

    /**
     * Initial configuration of the chart
     * @param chart
     */
    public void configChart(LineChart chart){
        chart.setDescription("");
        chart.setNoDataTextDescription("No data");

        // enable value highlighting
        chart.setHighlightPerTapEnabled(true);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setDrawGridBackground(false);

        // enable pitch zoom to avoid scaling x and y axis separately
        chart.setPinchZoom(true);

        // alternative background color
        chart.setBackgroundColor(Color.LTGRAY);

        // data
        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);

        // add data to line chart
        chart.setData(data);

        // get legend object
        Legend l = chart.getLegend();

        // customize legend
        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(Color.WHITE);

        XAxis xl = chart.getXAxis();
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);
        xl.setEnabled(true);

        YAxis yl = chart.getAxisLeft();
        yl.setTextColor(Color.WHITE);
        yl.setAxisMaxValue(10f);
        yl.setAxisMinValue(-10f);
        yl.setStartAtZero(false);
        yl.setDrawGridLines(true);

        YAxis yl2 = chart.getAxisRight();
        yl2.setEnabled(false);
    }
}
