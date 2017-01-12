package com.rupik.rkm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class DisciplesListActivity extends AppCompatActivity {

    ArrayList<HashMap> dataList;
    static final String MonkName = "MonkName";
    static final String MonkImageName = "MonkImageName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciples_list);

        populateDataSourceForMonasticDisciples();

        ListView disciplesListView = (ListView)findViewById(R.id.disciplesListView);
        DisciplesListAdapter adapter = new DisciplesListAdapter(this, dataList);
        disciplesListView.setAdapter(adapter);

    }


    private void populateDataSourceForMonasticDisciples()
    {
        dataList = new ArrayList<>();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Vivekananda");
        hashMap.put(MonkImageName,"swamiji");

        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Brahmananda");
        hashMap.put(MonkImageName,"swami_brahmananda");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Premananda");
        hashMap.put(MonkImageName,"swami_premananda");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Yogananda");
        hashMap.put(MonkImageName,"swami_yogananda");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Niranjanananda");
        hashMap.put(MonkImageName,"swami_niranjanananda");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Saradananda");
        hashMap.put(MonkImageName,"swami_saradananda");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Shivananda");
        hashMap.put(MonkImageName,"swami_shivananda");

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Ramakrishnananda");
        hashMap.put(MonkImageName,"swami_ramakrishnananda");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Turiyananda");
        hashMap.put(MonkImageName,"swami_turiyananda");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Abhedananda");
        hashMap.put(MonkImageName,"swami_abhedananda_portrait");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Adbhutananda");
        hashMap.put(MonkImageName,"swami_adbhutananda");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Advaitananda");
        hashMap.put(MonkImageName,"swami_advaitananda");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Nirmalananda");
        hashMap.put(MonkImageName,"swami_nirmalananda");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Akhandananda");
        hashMap.put(MonkImageName,"swami_akhandananda");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Trigunatitananda");
        hashMap.put(MonkImageName,"swami_trigunatitananda_portrait");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Subodhananda");
        hashMap.put(MonkImageName,"swami_subodhananda");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Vijnanananda");
        hashMap.put(MonkImageName,"swami_vijnanananda");
        dataList.add(hashMap);
    }

}
