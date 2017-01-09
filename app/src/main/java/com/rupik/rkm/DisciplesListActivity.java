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
        hashMap.put(MonkName,"Swami Vivekananda");
        hashMap.put(MonkImageName,"swamiji");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Vivekananda");
        hashMap.put(MonkImageName,"swamiji");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Vivekananda");
        hashMap.put(MonkImageName,"swamiji");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Vivekananda");
        hashMap.put(MonkImageName,"swamiji");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Vivekananda");
        hashMap.put(MonkImageName,"swamiji");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Vivekananda");
        hashMap.put(MonkImageName,"swamiji");

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Vivekananda");
        hashMap.put(MonkImageName,"swamiji");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Vivekananda");
        hashMap.put(MonkImageName,"swamiji");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Vivekananda");
        hashMap.put(MonkImageName,"swamiji");
        dataList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(MonkName,"Swami Vivekananda");
        hashMap.put(MonkImageName,"swamiji");
        dataList.add(hashMap);
    }

}
