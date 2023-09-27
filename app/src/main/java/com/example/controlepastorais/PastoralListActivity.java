package com.example.controlepastorais;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PastoralListActivity extends AppCompatActivity {
    private ListView listViewPastorais;
    private final ArrayList<Pastoral> pastorais = new ArrayList<>();
    private final ArrayList<String> names = new ArrayList<>();
    private ArrayAdapter<String> pastoralArrayAdapter;

    public static final String NAME = "NAME";
    public static final String COORDINATOR = "COORDINATOR";
    public static final String PATRON_SAINT = "PATRON_SAINT";
    public static final String INTEREST_ACTIVITIES = "INTEREST_ACTIVITIES";
    public static final boolean IS_MOVEMENT = false;
    public static final int POSITION = -1;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastoral_list);

        findComponentsFromView();
        onItemSelected();
        registerForContextMenu(listViewPastorais);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info;
        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch(item.getItemId()){
            case R.id.delete_menu_item:
                delete(info.position);
                return true;

            case R.id.edit_menu_item:
                edit(info.position);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    public void delete(int position){
        names.remove(position);
        pastoralArrayAdapter.notifyDataSetChanged();
    }

    public void edit(int position){
        PastoralActivity.editPastoral(this, pastorais.get(position), position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_options, menu);
        return true;
    }

    public void callAboutAppAuthorshipActivity(View view){
        Intent intent = new Intent(this, AppAuthorshipActivity.class);
        startActivity(intent);
    }

    public void callPastoralActivity(View view){
        PastoralActivity.newPastoral(this);
    }

    private void findComponentsFromView(){
        listViewPastorais = findViewById(R.id.listViewPastorais);
        setAdapterOnListView();
    }

    private void setAdapterOnListView(){
        populateAdapter();
        listViewPastorais.setAdapter(pastoralArrayAdapter);
    }

    private void populateAdapter(){
        populatePastoraisList();
        populatePastoraisListWithDataFromView();

        for (Pastoral pastoral : pastorais){
            names.add(pastoral.getName());
        }

        pastoralArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        PastoralActivity.FORM_FILLED = 0;
        pastoralArrayAdapter.notifyDataSetChanged();
    }

    private void populatePastoraisListWithDataFromView(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Pastoral pastoral = new Pastoral();

        if(bundle != null){
            pastoral.setName(bundle.getString(NAME, ""));
            pastoral.setCoordinator(bundle.getString(COORDINATOR, ""));
            pastoral.setPatronSaint(bundle.getString(PATRON_SAINT, ""));
            pastoral.setMovement(false);
            pastoral.setInterestActivities(bundle.getString(INTEREST_ACTIVITIES));

            pastorais.add(pastoral);
            setResult(Activity.RESULT_OK, intent);
        }
    }

    private void populatePastoraisList(){
        Pastoral pastoral;
        String interestActivities = "";

        pastoral = new Pastoral();
        pastoral.setName("Pastoral do Batismo");
        pastoral.setCoordinator("José Carlos Cardoso");
        pastoral.setPatronSaint("Espírito Santo");
        pastoral.setMovement(false);
        interestActivities = getResources().getString(R.string.training);
        pastoral.setInterestActivities(interestActivities);
        pastorais.add(pastoral);

        pastoral = new Pastoral();
        pastoral.setName("Pastoral Social");
        pastoral.setCoordinator("Emanoel da Silva");
        pastoral.setPatronSaint("Santa Terezinha");
        pastoral.setMovement(true);
        interestActivities = getResources().getString(R.string.fundraising_events) + "; " + getResources().getString(R.string.social_services);
        pastoral.setInterestActivities(interestActivities);
        pastorais.add(pastoral);

        pastoral = new Pastoral();
        pastoral.setName("Treinamento de Liderança Cristã (TLC)");
        pastoral.setCoordinator("Aline Gonçalves");
        pastoral.setPatronSaint("Nossa Senhora de Guadalupe");
        pastoral.setMovement(true);
        interestActivities = getResources().getString(R.string.training) + "; " + getResources().getString(R.string.spirituality_moments) + "; " + getResources().getString(R.string.get_togethers);
        pastoral.setInterestActivities(interestActivities);
        pastorais.add(pastoral);

        pastoral = new Pastoral();
        pastoral.setName("Pastoral da Comunicação (PASCOM)");
        pastoral.setCoordinator("Leonardo de Paula");
        pastoral.setPatronSaint("Santa Clara");
        pastoral.setMovement(false);
        interestActivities = getResources().getString(R.string.others) + "; " + getResources().getString(R.string.get_togethers) + "; " + getResources().getString(R.string.meetings);
        pastoral.setInterestActivities(interestActivities);
        pastorais.add(pastoral);

        pastoral = new Pastoral();
        pastoral.setName("Movimento Mãe Rainha");
        pastoral.setCoordinator("Rosa Toneti");
        pastoral.setPatronSaint("Mãe Rainha");
        pastoral.setMovement(true);
        interestActivities = getResources().getString(R.string.spirituality_moments) + "; " + getResources().getString(R.string.liturgy) + "; " + getResources().getString(R.string.meetings);
        pastoral.setInterestActivities(interestActivities);
        pastorais.add(pastoral);

        pastoral = new Pastoral();
        pastoral.setName("Pastoral da Liturgia");
        pastoral.setCoordinator("Angela Toneti");
        pastoral.setPatronSaint("Nossa Senhora das Graças");
        pastoral.setMovement(false);
        interestActivities = getResources().getString(R.string.liturgy) + "; " + getResources().getString(R.string.training);
        pastoral.setInterestActivities(interestActivities);
        pastorais.add(pastoral);

        pastoral = new Pastoral();
        pastoral.setName("Terço dos Homens");
        pastoral.setCoordinator("Lauro Sarachi");
        pastoral.setPatronSaint("Mãe Rainha");
        pastoral.setMovement(true);
        interestActivities = getResources().getString(R.string.spirituality_moments) + "; " + getResources().getString(R.string.liturgy);
        pastoral.setInterestActivities(interestActivities);
        pastorais.add(pastoral);

        pastoral = new Pastoral();
        pastoral.setName("Grupo de Jovens");
        pastoral.setCoordinator("Cindy Navarini");
        pastoral.setPatronSaint("Nossa Senhora do Perpétuo Socorro");
        pastoral.setMovement(false);
        interestActivities = getResources().getString(R.string.trips) + "; " + getResources().getString(R.string.others);
        pastoral.setInterestActivities(interestActivities);
        pastorais.add(pastoral);

        pastoral = new Pastoral();
        pastoral.setName("Apostolado da Oração");
        pastoral.setCoordinator("Neuza Natal");
        pastoral.setPatronSaint("Sagrado Coração de Jesus");
        pastoral.setMovement(true);
        interestActivities = getResources().getString(R.string.spirituality_moments) + "; " + getResources().getString(R.string.liturgy) + "; " + getResources().getString(R.string.trips) + "; " + getResources().getString(R.string.meetings);
        pastoral.setInterestActivities(interestActivities);
        pastorais.add(pastoral);

        pastoral = new Pastoral();
        pastoral.setName("Coroinhas");
        pastoral.setCoordinator("Leticia Batistel");
        pastoral.setPatronSaint("São Tarcisio");
        pastoral.setMovement(false);
        interestActivities = getResources().getString(R.string.training) + "; " + getResources().getString(R.string.liturgy);
        pastoral.setInterestActivities(interestActivities);
        pastorais.add(pastoral);
    }

    private void sendToastMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void onItemSelected() {
        listViewPastorais.setOnItemClickListener((adapterView, view, position, id) -> sendToastMessage(getString(R.string.fill_all_fields) + listViewPastorais.getItemAtPosition(position).toString()));
    }

    @Override
    public void onBackPressed() {
        cancel();
    }

    public void cancel(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    public ArrayList<Pastoral> getPastorais() {
        return pastorais;
    }
}
