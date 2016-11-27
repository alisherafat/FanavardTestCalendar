package com.fanavard.alisherafat.fanavardtestcalendar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.edtYear) EditText edtYear;
    @BindView(R.id.edtMonth) EditText edtMonth;
    @BindView(R.id.edtDay) EditText edtDay;
    @BindView(R.id.txtPersianDate) TextView txtPersianDate;
    @BindView(R.id.txtPersianEvents) TextView txtPersianEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 5545) {
            txtPersianDate.setText(data.getDataString());
            txtPersianEvents.setText(data.getStringExtra(Intent.EXTRA_TEXT));
        } else {
            Toast.makeText(this, "در دریافت اطلاعات خطایی به وجود آمد", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btnGetInfo)
    public void onClick() {
        String year = "0" + edtYear.getText().toString().trim();
        String month = "0" + edtMonth.getText().toString().trim();
        String day = "0" + edtDay.getText().toString().trim();
        String warning = "مقدار نامعتبر است";
        if (year.isEmpty() || Integer.parseInt(year) < 1000) {
            edtYear.setError(warning);
            return;
        }

        int intMonth = Integer.parseInt(month);
        if (month.isEmpty() || intMonth < 1 || intMonth > 12) {
            edtMonth.setError(warning);
            return;
        }

        int intDay = Integer.parseInt(day);
        if (day.isEmpty() || intDay < 1 || intDay > 31) {
            edtDay.setError(warning);
            return;
        }

        String strDate = year + "-" + month + "-" + day;

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(strDate), "text/plain");
        startActivityForResult(intent, 5545);

    }
}
