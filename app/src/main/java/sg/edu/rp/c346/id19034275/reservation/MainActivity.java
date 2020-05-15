package sg.edu.rp.c346.id19034275.reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    EditText etName, etPhone, etPax;
    DatePicker dtPicker;
    TimePicker tmPicker;
    CheckBox cbSomke;
    TextView tvText;
    Button btnReserve, btnReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etPhone = findViewById(R.id.editTextHP);
        etPax = findViewById(R.id.editTextPax);
        dtPicker = findViewById(R.id.datePicker);
        tmPicker = findViewById(R.id.timePicker);
        cbSomke = findViewById(R.id.checkBoxsmoke);
        tvText = findViewById(R.id.textViewText);
        btnReserve = findViewById(R.id.buttonReserve);
        btnReset = findViewById(R.id.buttonReset);

        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String pax = etPax.getText().toString();
                String phoneNo = etPhone.getText().toString();
                if (name.isEmpty() || pax.isEmpty() || phoneNo.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter your name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Restrict the reservation after today Chanllenge 2

                Calendar now = Calendar.getInstance();
                Calendar reservation = Calendar.getInstance();
                reservation.set(dtPicker.getYear(), dtPicker.getMonth(), dtPicker.getDayOfMonth(), tmPicker.getCurrentHour(),
                        tmPicker.getCurrentMinute());
                if (now.after(reservation)) {
                    Toast.makeText(MainActivity.this, "Please select a date and time after today.", Toast.LENGTH_LONG).show();
                    return;
                }

                String isSmoke = "";
                if (cbSomke.isChecked()) {
                    isSmoke = "smoking";
                }  else {
                    Toast.makeText(MainActivity.this, "Please tick smoking or non-smoking area!", Toast.LENGTH_LONG).show();
                }

                Toast.makeText(MainActivity.this, "Hi, " + name + ",you have booked a " + pax + " person(s)"
                        + isSmoke + " table on " + dtPicker.getYear() + "/" + (dtPicker.getMonth() + 1) + "/" + dtPicker.getDayOfMonth() +
                        " at " + tmPicker.getCurrentHour() + ":" + tmPicker.getCurrentMinute() + ". Your phone number is"
                        + phoneNo + ".", Toast.LENGTH_LONG).show();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etPax.setText(null);
                etName.setText(null);
                etPhone.setText(null);
                cbSomke.setChecked(false);
                dtPicker.updateDate(2020, 5, 1);
                tmPicker.setCurrentHour(20);
                tmPicker.setCurrentMinute(30);
            }
        });
         //Changllenge 2
        tmPicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(hourOfDay<8){
                    Toast.makeText(MainActivity.this,"We open at 8AM",Toast.LENGTH_LONG).show();
                    tmPicker.setCurrentHour(8);
                }else if(hourOfDay>=21){
                    Toast.makeText(MainActivity.this,"We close at 9PM",Toast.LENGTH_LONG).show();
                    tmPicker.setCurrentHour(20);
                }
            }
        });


    }

    ;

};





