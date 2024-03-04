package com.example.survit.addSurvey;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.survit.CategoryDialogListener;
import com.example.survit.R;

import java.util.ArrayList;
import java.util.List;

public class SurveyAddFragment1 extends Fragment implements CategoryDialogListener {
    public static final String PREFS_NAME = "CategoryPrefs";

    private ProgressBar progressBar;
    private ImageView doneIcon;
    private Button titleCheckBtn;

    private EditText titleEditText;

    private Button setCategoryButton;

    private LinearLayout selectedCategoryLayout;
    private TextView selectedCategoryText;

    private setCategoryDialog categoryDialog;

    public SurveyAddFragment1() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryDialog = new setCategoryDialog(requireContext(), this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.survey_add_fragment_first, container, false);

        titleCheckBtn = (Button) rootview.findViewById(R.id.duplicateCheckBtn);
        progressBar = (ProgressBar) rootview.findViewById(R.id.progressBar);
        doneIcon = (ImageView) rootview.findViewById(R.id.done_ImageView);
        titleEditText = (EditText) rootview.findViewById(R.id.addTitleEditText);

        // 텍스트 감지
        titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력하기 전에 조치
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력란에 변화가 있을 시 조치
                if (doneIcon.getVisibility() == View.VISIBLE){
                    titleCheckBtn.setEnabled(true);
                    doneIcon.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 입력이 끝났을 때 조치
            }
        });

        // 주제 확인 버튼 이벤트
        titleCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("StaticFieldLeak")
                TitleCheckTask titleCheckTask = new TitleCheckTask(titleEditText.getText().toString()) {
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        doneIcon.setVisibility(View.GONE);

                        progressBar.setVisibility(View.VISIBLE);


                    }

                    @Override
                    protected void onPostExecute(Boolean aBoolean) {
                        super.onPostExecute(aBoolean);

                        if (this.getChecking()) {

                            titleCheckBtn.setEnabled(false);

                            progressBar.setVisibility(View.GONE);

                            doneIcon.setVisibility(View.VISIBLE);

                            // 검사가 완료되면 변수에 질문 저장
                            SurveyAddActivity activity = (SurveyAddActivity) getActivity();
                            activity.addItem.setQuestion(titleEditText.getText().toString());
                        }
                        else if (!this.getChecking()) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "주제가 너무 짧아요 !", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getContext(), "통신 오류", Toast.LENGTH_SHORT).show();
                        }
                    }
                };

                titleCheckTask.execute();
            }


        });

        selectedCategoryLayout = (LinearLayout) rootview.findViewById(R.id.selectedCategoryLayout);

        setCategoryButton = (Button) rootview.findViewById(R.id.setCategoryBtn);

        selectedCategoryText = (TextView) rootview.findViewById(R.id.selectedCategoryText);

        setCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryDialog.show();
            }
        });

        return rootview;
    }


    @Override
    public void onDialogResult(String result) {
        selectedCategoryLayout.setVisibility(View.VISIBLE);
        selectedCategoryText.setText(result);

        SurveyAddActivity activity = (SurveyAddActivity) getActivity();
        activity.addItem.setType(result);
    }
}


