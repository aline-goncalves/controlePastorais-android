package com.example.controlepastorais;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

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
    private ArrayList<String> interestActivities = new ArrayList<>();
    private boolean isPatronSaintSelected = false;

    public static int FORM_FILLED = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastoral);
        findComponents();
        populateSpinnerPatronSaints();
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
                    interestActivities.add(checkBoxVisits.getText().toString());
                }
                break;
            case R.id.checkBoxSpiritualityMoments:
                if (checked) {
                    interestActivities.add(checkBoxSpiritualityMoments.getText().toString());
                }
                break;
            case R.id.checkBoxTraining:
                if (checked) {
                    interestActivities.add(checkBoxTraining.getText().toString());
                }
                break;

            case R.id.checkBoxLiturgy:
                if (checked) {
                    interestActivities.add(checkBoxLiturgy.getText().toString());
                }
                break;

            case R.id.checkBoxMeetings:
                if (checked) {
                    interestActivities.add(checkBoxMeetings.getText().toString());
                }
                break;

            case R.id.checkBoxGetTogethers:
                if (checked) {
                    interestActivities.add(checkBoxGetTogethers.getText().toString());
                }
                break;

            case R.id.checkBoxSocialServices:
                if (checked) {
                    interestActivities.add(checkBoxSocialServices.getText().toString());
                }
                break;

            case R.id.checkBoxFundraisingEvents:
                if (checked) {
                    interestActivities.add(checkBoxFundraisingEvents.getText().toString());
                }
                break;

            case R.id.checkBoxTrips:
                if (checked) {
                    interestActivities.add(checkBoxTrips.getText().toString());
                }
                break;

            default:
                interestActivities.add(checkBoxOthers.getText().toString());
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

    public void clearData(View view){
        clearPastoralFields(view);
        sendToastToTheView("Todos os campos foram limpos!");
     }

    public void submit(View view){
        if(isValidData()) {
            startActivityForResult(setDataPastoral(view), FORM_FILLED);
            clearPastoralFields(view);
            sendToastToTheView("Dados enviados com sucesso!");
        }else{
            sendToastToTheView("Preencha todos os campos!");
        }
    }

    private Intent setDataPastoral(View view){
        Intent intent = new Intent(this, PastoralListActivity.class);

        pastoral.setName(editTextName.getText().toString());
        pastoral.setCoordinator(editTextCoordinator.getText().toString());
        pastoral.setInterestActivities(interestActivities);

        intent.putExtra(PastoralListActivity.NAME, editTextName.getText().toString());
        intent.putExtra(PastoralListActivity.COORDINATOR, editTextCoordinator.getText().toString());

        FORM_FILLED = 1;

        return intent;
    }

    private void clearPastoralFields(View view){
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

    private void setData(){
        pastoral.setName(editTextName.getText().toString());
        pastoral.setCoordinator(editTextCoordinator.getText().toString());
        pastoral.setInterestActivities(interestActivities);
    }

    private void getData(){
        System.out.println("Nome: " + pastoral.getName());
        System.out.println("Coordenador: " + pastoral.getCoordinator());
        System.out.println("Santo(a) Padroeiro(a): " + pastoral.getPatronSaint());
        System.out.println("É um movimento? " + (pastoral.isMovement() ? "Sim" : "Não"));
        System.out.println("Atividades de interesse: ");
        for(String activity : pastoral.getInterestActivities()) {
            System.out.println(activity + "\n");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        pastoral.setPatronSaint(adapterView.getItemAtPosition(position).toString());
        isPatronSaintSelected = true;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        isPatronSaintSelected = false;
        sendToastToTheView("Preencha todos os campos!");
    }
}
