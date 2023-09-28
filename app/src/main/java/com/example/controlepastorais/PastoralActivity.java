package com.example.controlepastorais;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.persistence.PastoralDatabase;

public class PastoralActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText editTextName;
    private EditText editTextCoordinator;

    private RadioButton radioButtonYes;
    private RadioButton radioButtonNo;

    private CheckBox checkBoxVisits;
    private CheckBox checkBoxSpiritualityMoments;
    private CheckBox checkBoxTraining;
    private CheckBox checkBoxLiturgy;
    private CheckBox checkBoxMeetings;
    private CheckBox checkBoxGetTogethers;
    private CheckBox checkBoxSocialServices;
    private CheckBox checkBoxFundraisingEvents;
    private CheckBox checkBoxTrips;
    private CheckBox checkBoxOthers;

    private Spinner spinnerPatronSaints;

    private Pastoral pastoral = new Pastoral();
    private String interestActivities = "";
    private boolean isPatronSaintSelected = false;
    private PastoralListActivity pastoralListActivity = new PastoralListActivity();

    private int mode;
    private static int selectedPosition = -1;

    public static int FORM_FILLED = 0;
    public static final int NEW = 1;
    public static final int EDIT = 2;
    public static final String MODE = "MODE";
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String COORDINATOR = "COORDINATOR";
    public static final String PATRON_SAINT = "PATRON_SAINT";
    public static final String INTEREST_ACTIVITIES = "INTEREST_ACTIVITIES";
    public static final String IS_MOVEMENT = "IS_MOVEMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastoral);
        findComponents();
        populateSpinnerPatronSaints();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){
            mode = bundle.getInt(MODE, NEW);

            if (mode == NEW){
                setTitle(getString(R.string.new_pastoral));
                pastoral = new Pastoral();

            }else{
                setTitle(getString(R.string.edited_pastoral));

                long id = bundle.getLong(ID);
                PastoralDatabase database = PastoralDatabase.getDatabase(this);
                pastoral = database.pastoralDao().queryForId(id);
                populateFieldsForEdition(pastoral);
            }
        }
    }

    private void populateFieldsForEdition(Pastoral pastoral){
        if(pastoral != null) {
            editTextName.setText(pastoral.getName());
            editTextCoordinator.setText(pastoral.getCoordinator());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register_options, menu);
        return true;
    }

    public static void newPastoral(AppCompatActivity appCompatActivity){
        Intent intent = new Intent(appCompatActivity, PastoralActivity.class);
        intent.putExtra(MODE, NEW);
        appCompatActivity.startActivityForResult(intent, NEW);
    }

    public static void editPastoral(AppCompatActivity appCompatActivity, Pastoral pastoral){
        Intent intent = new Intent(appCompatActivity, PastoralActivity.class);

        intent.putExtra(MODE, EDIT);
        intent.putExtra(ID, pastoral.getId());
        intent.putExtra(NAME, pastoral.getName());
        intent.putExtra(COORDINATOR, pastoral.getCoordinator());
        intent.putExtra(PATRON_SAINT, pastoral.getPatronSaint());
        intent.putExtra(INTEREST_ACTIVITIES, pastoral.getInterestActivities());
        intent.putExtra(IS_MOVEMENT, pastoral.isMovement());

        appCompatActivity.startActivityForResult(intent, EDIT);
    }

    private void findComponents(){
        editTextName = findViewById(R.id.editTextName);
        editTextCoordinator = findViewById(R.id.editTextCoordinator);

        radioButtonYes = findViewById(R.id.radioButtonYes);
        radioButtonNo = findViewById(R.id.radioButtonNo);

        checkBoxVisits = findViewById(R.id.checkBoxVisits);
        checkBoxTraining = findViewById(R.id.checkBoxTraining);
        checkBoxFundraisingEvents = findViewById(R.id.checkBoxFundraisingEvents);
        checkBoxOthers = findViewById(R.id.checkBoxOthers);
        checkBoxSocialServices = findViewById(R.id.checkBoxSocialServices);
        checkBoxSpiritualityMoments = findViewById(R.id.checkBoxSpiritualityMoments);
        checkBoxMeetings = findViewById(R.id.checkBoxMeetings);
        checkBoxTrips = findViewById(R.id.checkBoxTrips);
        checkBoxGetTogethers = findViewById(R.id.checkBoxGetTogethers);
        checkBoxLiturgy = findViewById(R.id.checkBoxLiturgy);

        spinnerPatronSaints = (Spinner) findViewById(R.id.spinnerPatronSaints);
        spinnerPatronSaints.setOnItemSelectedListener(this);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radioButtonYes:
                if (checked) {
                    pastoral.setMovement(true);
                }
                    break;
            case R.id.radioButtonNo:
                if (checked) {
                    pastoral.setMovement(false);
                }
                    break;
        }
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.checkBoxVisits:
                if (checked) {
                    interestActivities.concat("; " + checkBoxVisits.getText().toString());
                }
                break;
            case R.id.checkBoxSpiritualityMoments:
                if (checked) {
                    interestActivities.concat("; " + checkBoxSpiritualityMoments.getText().toString());
                }
                break;
            case R.id.checkBoxTraining:
                if (checked) {
                    interestActivities.concat("; " + checkBoxTraining.getText().toString());
                }
                break;

            case R.id.checkBoxLiturgy:
                if (checked) {
                    interestActivities.concat("; " + checkBoxLiturgy.getText().toString());
                }
                break;

            case R.id.checkBoxMeetings:
                if (checked) {
                    interestActivities.concat("; " + checkBoxMeetings.getText().toString());
                }
                break;

            case R.id.checkBoxGetTogethers:
                if (checked) {
                    interestActivities.concat("; " + checkBoxGetTogethers.getText().toString());
                }
                break;

            case R.id.checkBoxSocialServices:
                if (checked) {
                    interestActivities.concat("; " + checkBoxSocialServices.getText().toString());
                }
                break;

            case R.id.checkBoxFundraisingEvents:
                if (checked) {
                    interestActivities.concat("; " + checkBoxFundraisingEvents.getText().toString());
                }
                break;

            case R.id.checkBoxTrips:
                if (checked) {
                    interestActivities.concat("; " + checkBoxTrips.getText().toString());
                }
                break;

            default:
                interestActivities.concat("; " + checkBoxOthers.getText().toString());
                break;
        }
    }

    public void populateSpinnerPatronSaints(){
        Spinner spinnerTags = (Spinner) findViewById(R.id.spinnerPatronSaints);
        ArrayAdapter<CharSequence> spinnerTagsAdapter = ArrayAdapter
                .createFromResource(
                        this,
                        R.array.patron_saints,
                        androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
        spinnerTagsAdapter.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
        spinnerTags.setAdapter(spinnerTagsAdapter);
    }

    public void clearData(){
        clearPastoralFields();
        sendToastToTheView(getString(R.string.all_fields_are_cleaned));
     }

    public void submit(){
        PastoralDatabase database = PastoralDatabase.getDatabase(this);

        if(!isValidData()){
            sendToastToTheView(getString(R.string.fill_all_fields));
        }

        if(isValidData() && mode == NEW) {
            startActivityForResult(setDataPastoralNew(), FORM_FILLED);
            database.pastoralDao().insert(setDataPastoral());
            clearPastoralFields();
            sendToastToTheView(getString(R.string.data_sent_success));
        }

        if(isValidData() && mode == EDIT){
            startActivityForResult(setDataPastoralEdit(), FORM_FILLED);
            database.pastoralDao().update(pastoral);
            clearPastoralFields();
            sendToastToTheView(getString(R.string.data_sent_success));
        }
    }

    private Intent setDataPastoralIntent(Pastoral pastoral){
        Intent intent = new Intent(this, PastoralListActivity.class);

        pastoral.setName(editTextName.getText().toString());
        pastoral.setCoordinator(editTextCoordinator.getText().toString());
        pastoral.setInterestActivities(interestActivities);

        intent.putExtra(PastoralListActivity.NAME, editTextName.getText().toString());
        intent.putExtra(PastoralListActivity.COORDINATOR, editTextCoordinator.getText().toString());

        FORM_FILLED = 1;

        return intent;
    }

    private void clearPastoralFields(){
        editTextName.setText(null);
        editTextCoordinator.setText(null);

        radioButtonYes.setChecked(false);
        radioButtonNo.setChecked(false);

        checkBoxTraining.setChecked(false);
        checkBoxSocialServices.setChecked(false);
        checkBoxFundraisingEvents.setChecked(false);
        checkBoxTrips.setChecked(false);
        checkBoxOthers.setChecked(false);
        checkBoxSpiritualityMoments.setChecked(false);
        checkBoxGetTogethers.setChecked(false);
        checkBoxMeetings.setChecked(false);
        checkBoxLiturgy.setChecked(false);
        checkBoxVisits.setChecked(false);

        spinnerPatronSaints.setSelected(false);
    }

    private void sendToastToTheView(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private boolean isValidData(){
        if(editTextName.getText() == null || editTextCoordinator.getText() == null) {
            return false;
        }

        if(!radioButtonNo.isChecked() && !radioButtonYes.isChecked()){
            return false;
        }

        if(!checkBoxOthers.isChecked() &&
                !checkBoxTrips.isChecked() &&
                !checkBoxFundraisingEvents.isChecked() &&
                !checkBoxSocialServices.isChecked() &&
                !checkBoxTraining.isChecked() &&
                !checkBoxSpiritualityMoments.isChecked() &&
                !checkBoxGetTogethers.isChecked() &&
                !checkBoxMeetings.isChecked() &&
                !checkBoxLiturgy.isChecked() &&
                !checkBoxVisits .isChecked()){
            return false;
        }

        if(!isPatronSaintSelected){
            return false;
        }

        return true;
    }

    private Pastoral setDataPastoral(){
        pastoral.setName(editTextName.getText().toString());
        pastoral.setCoordinator(editTextCoordinator.getText().toString());
        pastoral.setInterestActivities(interestActivities);

        return pastoral;
    }

    private Intent setDataPastoralNew(){
        Pastoral pastoral = setDataPastoral();
        pastoralListActivity.getPastoralsNames().add(pastoral.getName());

        Intent intent = setDataPastoralIntent(pastoral);
        return intent;
    }

    private Intent setDataPastoralEdit(){
        Intent intent = setDataPastoralIntent(setDataPastoral());
        intent.putExtra(String.valueOf(PastoralListActivity.POSITION), selectedPosition);

        return intent;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        pastoral.setPatronSaint(adapterView.getItemAtPosition(position).toString());
        isPatronSaintSelected = true;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        isPatronSaintSelected = false;
        sendToastToTheView(getString(R.string.fill_all_fields));
    }

    @Override
    public void onBackPressed() {
        cancel();
    }

    private void cancel(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.send_data:
                submit();
                return true;

            case android.R.id.home:
                cancel();
                return true;

            case R.id.clear_data:
                clearData();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
