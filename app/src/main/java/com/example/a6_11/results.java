package com.example.a6_11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class results extends AppCompatActivity implements AdapterView.OnItemLongClickListener , View.OnCreateContextMenuListener {
    /**
    * @author		Noa Shetrit
    * @version	2
    * @since		27/11/2021
    * the second activity
    **/


    TextView tvX1, tvD, tvN_SN;
    ListView lv;
    Button returni;
    Intent gi;
    String[] series = new String[20];
    String[] seriesSum = new String[20];
    double x1;
    double d;
    int type;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        tvD = (TextView) findViewById(R.id.tvD);
        tvX1 = (TextView) findViewById(R.id.tvX1);
        tvN_SN = (TextView) findViewById(R.id.tvN_SN);
        lv = (ListView) findViewById(R.id.lv);
        returni = (Button) findViewById(R.id.returni);

        lv.setOnItemLongClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, series);
        lv.setAdapter(adapter);
        lv.setOnCreateContextMenuListener(this);

        gi = getIntent();
        x1 = gi.getDoubleExtra("x1", -1000);
        d = gi.getDoubleExtra("d", -1001);
        type = gi.getIntExtra("type", -1002);

        if (type == 0) heshbonit(x1, d, series, seriesSum);
        else handasit(x1, d, series, seriesSum);
        tvD.setText("d = " + d);
        tvX1.setText("X1 = " + x1);

    }



    public static void heshbonit(double x1, double d, String[] series, String[] seriesSum) {
        /**
         * the function calculate the engineering series end put the values in string array
         * @param   x1 - the first element of series
         * @param   d - the difference
         * @param   series - the elements of the series in array
         * @param   seriesSum - the sum of elements of the series in array
         * @return	none.
         **/
        series[0] = x1 + "";
        double sum = x1;
        seriesSum[0] = x1 + "";
        double nextN = x1;
        for (int i = 1; i < 20; i++) {
            nextN += d;
            sum += nextN;
            series[i] = nextN + "";
            seriesSum[i] = sum + "";

        }
    }

    public static void handasit(double x1, double d, String[] series, String[] seriesSum) {
        /**
         * the function calculate the arithmetic series end put the values in string array
         * @param   x1 - the first element of series
         * @param   d - the difference
         * @param   series - the elements of the series in array
         * @param   seriesSum - the sum of elements of the series in array
         * @return	none.
         **/
        series[0] = x1 + "";
        double sum = x1;
        seriesSum[0] = x1 + "";
        double nextN = x1;
        for (int i = 1; i < 20; i++) {
            nextN *= d;
            sum += nextN;
            series[i] = nextN + "";
            seriesSum[i] = sum + "";

        }
    }

    public void onClick(View view) {
        tvX1.setText("");
        tvD.setText("");
        tvN_SN.setText("");

        Intent wi = getIntent();
        wi.putExtra("X1", x1);
        wi.putExtra("d", d);

        setResult(RESULT_OK, wi);
        finish();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        pos = position;
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Please choose:");
        menu.add("Show sum of series until N");
        menu.add("Show N");

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String oper=item.getTitle().toString();
        if (oper.equals("Show N")) {
            tvN_SN.setText("N = " + (pos+1));
        }
        else tvN_SN.setText("Sn = " +seriesSum[pos]);
        return true;

    }
}