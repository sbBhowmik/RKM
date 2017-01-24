package com.rupik.rkm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class DisciplesListActivity extends AppCompatActivity {

    ArrayList<Disciple> dataList;
    static final String MonkName = "MonkName";
    static final String MonkImageName = "MonkImageName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciples_list);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        populateDataSourceForMonasticDisciples();

        ListView disciplesListView = (ListView)findViewById(R.id.disciplesListView);
        DisciplesListAdapter adapter = new DisciplesListAdapter(this, dataList);
        disciplesListView.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }

        return true;
    }

    private void populateDataSourceForMonasticDisciples()
    {
        dataList = new ArrayList<>();
//test
        Disciple disciple = new Disciple("Swami Vivekananda", R.drawable.swamiji);
        dataList.add(disciple);

        disciple = new Disciple("Swami Brahmananda", R.drawable.swami_brahmananda);
        dataList.add(disciple);

        disciple = new Disciple("Swami Premananda", R.drawable.swami_premananda);
        dataList.add(disciple);

        disciple = new Disciple("Swami Yogananda", R.drawable.swami_yogananda);
        dataList.add(disciple);

        disciple = new Disciple("Swami Niranjanananda", R.drawable.swami_niranjanananda);
        dataList.add(disciple);

        disciple = new Disciple("Swami Saradananda", R.drawable.swami_saradananda);
        dataList.add(disciple);

        disciple = new Disciple("Swami Shivananda", R.drawable.swami_shivananda);
        dataList.add(disciple);

        disciple = new Disciple("Swami Ramakrishnananda", R.drawable.swami_ramakrishnananda);
        dataList.add(disciple);

        disciple = new Disciple("Swami Turiyananda", R.drawable.swami_turiyananda);
        dataList.add(disciple);

        disciple = new Disciple("Swami Abhedananda", R.drawable.swami_abhedananda_portrait);
        dataList.add(disciple);

        disciple = new Disciple("Swami Adbhutananda", R.drawable.swami_adbhutananda);
        dataList.add(disciple);

        disciple = new Disciple("Swami Advaitananda", R.drawable.swami_advaitananda);
        dataList.add(disciple);

        disciple = new Disciple("Swami Nirmalananda", R.drawable.swami_nirmalananda);
        dataList.add(disciple);

        disciple = new Disciple("Swami Akhandananda", R.drawable.swami_akhandananda);
        dataList.add(disciple);

        disciple = new Disciple("Swami Trigunatitananda", R.drawable.swami_trigunatitananda_portrait);
        dataList.add(disciple);

        disciple = new Disciple("Swami Vijnanananda", R.drawable.swami_vijnanananda);
        dataList.add(disciple);
    }

}
