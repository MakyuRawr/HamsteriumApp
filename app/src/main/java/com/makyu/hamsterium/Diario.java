package com.makyu.hamsterium;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import com.makyu.hamsterium.HamsterAdapter;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Diario extends AppCompatActivity {
    private Spinner spinnerHamsterType;
    private List<Hamster> hamsterList;
    private List<Hamster> filteredHamsters;
    private RecyclerView recyclerViewHamsters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario);

        spinnerHamsterType = findViewById(R.id.sp_hamster_type);
        recyclerViewHamsters = findViewById(R.id.grid_hamsters);


        //cambiamos las columnas de los hamster
        int columnas = 2;

        GridLayoutManager layoutManager = new GridLayoutManager(this, columnas);
        recyclerViewHamsters.setLayoutManager(layoutManager);


        // Array de las categorias de hamster
        hamsterList = new ArrayList<>();

        //- - - - - - CATEGORIA DE HAMSTERS - - - - - -

        //              Hamster Bueno
        hamsterList.add(new Hamster("Hamster0", R.drawable.hamster_tipeo, "Hamster bueno"));
        hamsterList.add(new Hamster("Hamster1", R.drawable.hamster_tipeo, "Hamster bueno"));
        hamsterList.add(new Hamster("Hamster2", R.drawable.hamster_tipeo, "Hamster bueno"));
        hamsterList.add(new Hamster("Hamster3", R.drawable.hamster_tipeo, "Hamster bueno"));

        //              Hamster Neutral
        hamsterList.add(new Hamster("Hamster4", R.drawable.hamster_tipeo, "Hamster neutral"));
        hamsterList.add(new Hamster("Hamster5", R.drawable.hamster_tipeo, "Hamster neutral"));


        //              Hamster Malo
        hamsterList.add(new Hamster("Hamster6", R.drawable.hamster_tipeo, "Hamster malo"));
        hamsterList.add(new Hamster("Hamster7", R.drawable.hamster_tipeo, "Hamster malo"));
        hamsterList.add(new Hamster("Hamster8", R.drawable.hamster_tipeo, "Hamster malo"));

        // Agrega más hamsters con diferentes categorías

        // Crear una copia de la lista de hamsters para filtrar
        filteredHamsters = new ArrayList<>(hamsterList);

        // configuración de hamster en el gridView
        HamsterAdapter adapter = new HamsterAdapter(filteredHamsters);
        recyclerViewHamsters.setAdapter(adapter);


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
                // Cambiar la lógica del Spinner o añadir según sea necesario
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
        ((HamsterAdapter) recyclerViewHamsters.getAdapter()).notifyDataSetChanged();
    }
}