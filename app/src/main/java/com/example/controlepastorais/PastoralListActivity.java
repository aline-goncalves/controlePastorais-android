package com.example.controlepastorais;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class PastoralListActivity extends AppCompatActivity {
    private ListView listViewPastorais;
    private final ArrayList<Pastoral> pastorais = new ArrayList<>();
    private final ArrayList<String> names = new ArrayList<>();
    private ArrayAdapter<String> pastoralArrayAdapter;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastoral_list);

        findComponentsFromView();
        onItemSelected();
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
        populateBooksList();

        for (Pastoral pastoral : pastorais){
            names.add(pastoral.getName());
        }

        pastoralArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
    }

    private void populateBooksList(){
        Pastoral pastoral;
        ArrayList<String> interestActivities = new ArrayList<>();

        pastoral = new Pastoral();
        pastoral.setName("Pastoral do Batismo");
        pastoral.setCoordinator("José Carlos Cardoso");
        pastoral.setPatronSaint("Espírito Santo");
        pastoral.setMovement(false);
        interestActivities.add(getResources().getString(R.string.training));
        pastoral.setInterestActivities(interestActivities);
        pastorais.add(pastoral);

        pastoral = new Pastoral();
        pastoral.setName("Pastoral Social");
        pastoral.setCoordinator("Emanoel da Silva");
        pastoral.setPatronSaint("Santa Terezinha");
        pastoral.setMovement(true);
        interestActivities.add(getResources().getString(R.string.fundraising_events));
        interestActivities.add(getResources().getString(R.string.social_services));
        pastoral.setInterestActivities(interestActivities);
        pastorais.add(pastoral);

        pastoral = new Pastoral();
        pastoral.setName("Treinamento de Liderança Cristã (TLC)");
        pastoral.setCoordinator("Aline Gonçalves");
        pastoral.setPatronSaint("Nossa Senhora de Guadalupe");
        pastoral.setMovement(true);
        interestActivities.add(getResources().getString(R.string.training));
        interestActivities.add(getResources().getString(R.string.spirituality_moments));
        interestActivities.add(getResources().getString(R.string.get_togethers));
        pastoral.setInterestActivities(interestActivities);
        pastorais.add(pastoral);

        pastoral = new Pastoral();
        pastoral.setName("Pastoral da Comunicação (PASCOM)");
        pastoral.setCoordinator("Leonardo de Paula");
        pastoral.setPatronSaint("Santa Clara");
        pastoral.setMovement(false);
        interestActivities.add(getResources().getString(R.string.others));
        interestActivities.add(getResources().getString(R.string.get_togethers));
        interestActivities.add(getResources().getString(R.string.meetings));
        pastoral.setInterestActivities(interestActivities);
        pastorais.add(pastoral);

        pastoral = new Pastoral();
        pastoral.setName("Movimento Mãe Rainha");
        pastoral.setCoordinator("Rosa Toneti");
        pastoral.setPatronSaint("Mãe Rainha");
        pastoral.setMovement(true);
        interestActivities.add(getResources().getString(R.string.spirituality_moments));
        interestActivities.add(getResources().getString(R.string.liturgy));
        interestActivities.add(getResources().getString(R.string.meetings));
        pastoral.setInterestActivities(interestActivities);
        pastorais.add(pastoral);

        pastoral = new Pastoral();
        pastoral.setName("Pastoral da Liturgia");
        pastoral.setCoordinator("Angela Toneti");
        pastoral.setPatronSaint("Nossa Senhora das Graças");
        pastoral.setMovement(false);
        interestActivities.add(getResources().getString(R.string.liturgy));
        interestActivities.add(getResources().getString(R.string.training));
        pastoral.setInterestActivities(interestActivities);
        pastorais.add(pastoral);

        pastoral = new Pastoral();
        pastoral.setName("Terço dos Homens");
        pastoral.setCoordinator("Lauro Sarachi");
        pastoral.setPatronSaint("Mãe Rainha");
        pastoral.setMovement(true);
        interestActivities.add(getResources().getString(R.string.spirituality_moments));
        interestActivities.add(getResources().getString(R.string.liturgy));
        pastoral.setInterestActivities(interestActivities);
        pastorais.add(pastoral);

        pastoral = new Pastoral();
        pastoral.setName("Grupo de Jovens");
        pastoral.setCoordinator("Cindy Navarini");
        pastoral.setPatronSaint("Nossa Senhora do Perpétuo Socorro");
        pastoral.setMovement(false);
        interestActivities.add(getResources().getString(R.string.trips));
        interestActivities.add(getResources().getString(R.string.others));
        pastoral.setInterestActivities(interestActivities);
        pastorais.add(pastoral);

        pastoral = new Pastoral();
        pastoral.setName("Apostolado da Oração");
        pastoral.setCoordinator("Neuza Natal");
        pastoral.setPatronSaint("Sagrado Coração de Jesus");
        pastoral.setMovement(true);
        interestActivities.add(getResources().getString(R.string.spirituality_moments));
        interestActivities.add(getResources().getString(R.string.liturgy));
        interestActivities.add(getResources().getString(R.string.trips));
        interestActivities.add(getResources().getString(R.string.meetings));
        pastoral.setInterestActivities(interestActivities);
        pastorais.add(pastoral);

        pastoral = new Pastoral();
        pastoral.setName("Coroinhas");
        pastoral.setCoordinator("Leticia Batistel");
        pastoral.setPatronSaint("São Tarcisio");
        pastoral.setMovement(false);
        interestActivities.add(getResources().getString(R.string.training));
        interestActivities.add(getResources().getString(R.string.liturgy));
        pastoral.setInterestActivities(interestActivities);
        pastorais.add(pastoral);
    }

    private void sendToastMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void onItemSelected() {
        listViewPastorais.setOnItemClickListener((adapterView, view, position, id) -> sendToastMessage("Você selecionou a pastoral: " + listViewPastorais.getItemAtPosition(position).toString()));
    }
}