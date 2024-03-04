package com.example.survit.addSurvey;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.survit.CategoryDialogListener;
import com.example.survit.R;

public class setCategoryDialog extends Dialog {

    private CategoryDialogListener dialogListener;

    private Button btn1, btn2, btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11, btn12,btn13,btn14,btn15 ;
    private String selectedCategory;

    LinearLayout selectedCategoryLayout;

    TextView selectedCategoryText;

    public setCategoryDialog(@NonNull Context context, CategoryDialogListener listener) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
        this.dialogListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_category_dialog_layout); // 사용자 정의 다이얼로그 레이아웃 파일을 지정하세요.

        btn1 = (Button) findViewById(R.id.category_item_culture);
        btn2 = (Button) findViewById(R.id.category_item_social);
        btn3 = (Button) findViewById(R.id.category_item_society);
        btn4 = (Button) findViewById(R.id.category_item_sports);
        btn5 = (Button) findViewById(R.id.category_item_economy);
        btn6 = (Button) findViewById(R.id.category_item_education);
        btn7 = (Button) findViewById(R.id.category_item_future);
        btn8 = (Button) findViewById(R.id.category_item_health);
        btn9 = (Button) findViewById(R.id.category_item_lifestyle);
        btn10 = (Button) findViewById(R.id.category_item_politic);
        btn11 = (Button) findViewById(R.id.category_item_leisure);
        btn12 = (Button) findViewById(R.id.category_item_law);
        btn13 = (Button) findViewById(R.id.category_item_environment);
        btn14 = (Button) findViewById(R.id.category_item_media);
        btn15 = (Button) findViewById(R.id.category_item_trip);

        View.OnClickListener categoryClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int buttonId = v.getId();
                Button checkedBtn = v.findViewById(buttonId);
                selectedCategory = checkedBtn.getText().toString();

                dialogListener.onDialogResult(selectedCategory);

                dismiss();
            }
        };

        btn1.setOnClickListener(categoryClickListener);
        btn2.setOnClickListener(categoryClickListener);
        btn3.setOnClickListener(categoryClickListener);
        btn4.setOnClickListener(categoryClickListener);
        btn5.setOnClickListener(categoryClickListener);
        btn6.setOnClickListener(categoryClickListener);
        btn7.setOnClickListener(categoryClickListener);
        btn8.setOnClickListener(categoryClickListener);
        btn9.setOnClickListener(categoryClickListener);
        btn10.setOnClickListener(categoryClickListener);
        btn11.setOnClickListener(categoryClickListener);
        btn12.setOnClickListener(categoryClickListener);
        btn13.setOnClickListener(categoryClickListener);
        btn14.setOnClickListener(categoryClickListener);
        btn15.setOnClickListener(categoryClickListener);


    }


}
