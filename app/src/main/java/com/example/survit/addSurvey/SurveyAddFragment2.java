package com.example.survit.addSurvey;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.survit.R;

import java.util.ArrayList;
import java.util.List;

public class SurveyAddFragment2 extends Fragment  {

    private Button addQuestionBtn;

    private ListView addQuestionList;

    private ArrayAdapter<AddItem> adapter;

    public SurveyAddFragment2() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.survey_add_fragment_second, container, false);

        addQuestionBtn = (Button) rootview.findViewById(R.id.addQuestionButton);
        addQuestionList = (ListView) rootview.findViewById(R.id.addQuestionListView);

        ArrayList<AddItem> items = new ArrayList<>();
        adapter = new ArrayAdapter<>(rootview.getContext(), R.layout.add_question_list_item, items);
        addQuestionList.setAdapter(adapter);

        addQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootview;
    }

}
