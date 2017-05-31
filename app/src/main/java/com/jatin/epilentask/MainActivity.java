package com.jatin.epilentask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jatin.epilentask.adapter.SpecialityAdapter;
import com.jatin.epilentask.model.Speciality;
import com.jatin.epilentask.util.RecyclerTouchListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends EpilenBaseActivity {

    List<Speciality> specialityList = new ArrayList<>();
    SpecialityAdapter adapter;
    RecyclerView speciality_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertSpecialityData();

        adapter = new SpecialityAdapter(specialityList);
        speciality_view = (RecyclerView) findViewById(R.id.speciality_view);
        speciality_view.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        speciality_view.setLayoutManager(mLayoutManager);
        speciality_view.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
        speciality_view.setItemAnimator(new DefaultItemAnimator());
        speciality_view.setAdapter(adapter);

        speciality_view.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), speciality_view, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                final Speciality speciality = specialityList.get(position);
                startActivity(new Intent(getApplicationContext(),DoctorsListActivity.class).putExtra("speciality",speciality));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    private void insertSpecialityData() {
        String strJson = "{\n" +
                "\t\"specialities\": [{\n" +
                "\t\t\t\"name\": \"Addiction Medicine\",\n" +
                "\t\t\t\"id\": \"100\",\n" +
                "\t\t\t\"doctors\":[{\n" +
                "\t\t\t\t\"id\": \"1001\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"1002\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"1003\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"1004\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t}]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"ADHD & Autism\",\n" +
                "\t\t\t\"id\": \"101\",\n" +
                "\t\t\t\"doctors\":[{\n" +
                "\t\t\t\t\"id\": \"1\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"2\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"3\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"4\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t}]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Aerospace Medicine\",\n" +
                "\t\t\t\"id\": \"102\",\n" +
                "\t\t\t\"doctors\":[{\n" +
                "\t\t\t\t\"id\": \"1\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"2\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"3\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"4\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t}]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Aesthetic Medicine\",\n" +
                "\t\t\t\"id\": \"103\",\n" +
                "\t\t\t\"doctors\":[{\n" +
                "\t\t\t\t\"id\": \"1\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"2\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"3\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"4\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t}]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Anesthesiology\",\n" +
                "\t\t\t\"id\": \"104\",\n" +
                "\t\t\t\"doctors\":[{\n" +
                "\t\t\t\t\"id\": \"1\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"2\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"3\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"4\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t}]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Anti-Aging Medicine\",\n" +
                "\t\t\t\"id\": \"105\",\n" +
                "\t\t\t\"doctors\":[{\n" +
                "\t\t\t\t\"id\": \"1\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"2\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"3\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"4\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t}]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Bariatrics\",\n" +
                "\t\t\t\"id\": \"106\",\n" +
                "\t\t\t\"doctors\":[{\n" +
                "\t\t\t\t\"id\": \"1\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"2\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"3\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"4\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t}]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Breast Surgery\",\n" +
                "\t\t\t\"id\": \"107\",\n" +
                "\t\t\t\"doctors\":[{\n" +
                "\t\t\t\t\"id\": \"1\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"2\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"3\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"4\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t}]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Cardiologist\",\n" +
                "\t\t\t\"id\": \"108\",\n" +
                "\t\t\t\"doctors\":[{\n" +
                "\t\t\t\t\"id\": \"1\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"2\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"3\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"4\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t}]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Clinical Genetics\",\n" +
                "\t\t\t\"id\": \"109\",\n" +
                "\t\t\t\"doctors\":[{\n" +
                "\t\t\t\t\"id\": \"1\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"2\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"3\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"4\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t}]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Clinical Lipidology\",\n" +
                "\t\t\t\"id\": \"110\",\n" +
                "\t\t\t\"doctors\":[{\n" +
                "\t\t\t\t\"id\": \"1\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"2\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"3\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\": \"4\",\n" +
                "\t\t\t\t\"name\" : \"Dr. Ram\"\n" +
                "\t\t\t}]\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

        try {
            JSONObject object = new JSONObject(strJson);
            String specJSON = object.getString("specialities");
            ObjectMapper mapper = new ObjectMapper();
            specialityList = mapper.readValue(specJSON,new TypeReference<List<Speciality>>(){});
        }catch (Exception e){
            Log.e("SMSError",e.getMessage(),e);
        }

    }
}
