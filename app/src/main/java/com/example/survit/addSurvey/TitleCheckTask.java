package com.example.survit.addSurvey;

import android.icu.text.CaseMap;
import android.os.AsyncTask;

// 중복 확인 테스크
public class TitleCheckTask extends AsyncTask<String, Void, Boolean> {

    private String title;
    private boolean checking;

    public TitleCheckTask(String title){
        this.title = title;
    }

    @Override
    protected Boolean doInBackground(String... strings) {

        // 주제가 너무 짧으면 false
        if (title.length() <= 6) {
            this.checking = false;
        }
        else {
            this.checking = true;
        }


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }

        return true;
    }

    public boolean getChecking() {
        return checking;
    }
}
