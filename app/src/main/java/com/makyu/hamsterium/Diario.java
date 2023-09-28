package com.makyu.hamsterium;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Diario extends AppCompatActivity {
    private Spinner spinnerHamsterType;
    private GridView gridViewHamsters;
    private List<Hamster> hamsterList;
    private List<Hamster> filteredHamsters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario);

        spinnerHamsterType = findViewById(R.id.sp_hamster_type);
        gridViewHamsters = findViewById(R.id.grid_hamsters);

        // Inicializar la lista de hamsters (puedes cargarlos desde tus recursos)
        hamsterList = new ArrayList<>();
        hamsterList.add(new Hamster("Hamster 1", R.drawable.hamster_tipeo, "hamster bueno"));
        hamsterList.add(new Hamster("Hamster 2", R.drawable.hamster_tipeo, "hamster bueno"));
        hamsterList.add(new Hamster("Hamster 3", R.drawable.hamster_tipeo, "hamster malo"));
        hamsterList.add(new Hamster("Hamster 4", R.drawable.hamster_tipeo, "hamster malo"));
        // Agrega más hamsters con diferentes categorías

        // Crear una copia de la lista de hamsters para filtrar
        filteredHamsters = new ArrayList<>(hamsterList);

    // Configurar un adaptador ArrayAdapter para el GridView
        ArrayAdapter<Hamster> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filteredHamsters);
        gridViewHamsters.setAdapter(adapter);



        // Configurar el Spinner con las categorías únicas de los hamsters
        Set<String> uniqueCategories = new HashSet<>();
        for (Hamster hamster : hamsterList) {
            uniqueCategories.add(hamster.getCategory());
        }
        List<String> categoryList = new ArrayList<>(uniqueCategories);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHamsterType.setAdapter(spinnerAdapter);

        // Configurar un listener para el Spinner que filtra los hamsters al seleccionar una categoría
        spinnerHamsterType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Filtrar los hamsters por categoría seleccionada
                String selectedCategory = spinnerHamsterType.getSelectedItem().toString();
                filterHamsters(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Aquí puedes manejar la lógica si no se selecciona nada en el Spinner
            }
        });
    }

    private void filterHamsters(String category) {
        filteredHamsters.clear();
        for (Hamster hamster : hamsterList) {
            if (hamster.getCategory().equals(category)) {
                filteredHamsters.add(hamster);
            }
        }
        // Notificar al adaptador que los datos han cambiado
        ((ArrayAdapter<Hamster>) gridViewHamsters.getAdapter()).notifyDataSetChanged();
    }
}
