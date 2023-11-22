package com.makyu.hamsterium;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.annotations.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.annotations.PointAnnotationOptions;

public class mapaBox extends AppCompatActivity {

    private MapView mapView;
    private static final int REQUEST_LOCATION = 1;
    private PointAnnotationManager pointAnnotationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa_box);

        mapView = findViewById(R.id.mapView);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this::onMapReady);

        obtenerUbicacion();
    }

    private void onMapReady(@NonNull Style style) {
        // Cuando el mapa esté listo, inicializamos el PointAnnotationManager
        pointAnnotationManager = mapView.getRotation().createPointAnnotationManager();
    }

    private void obtenerUbicacion() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null && mapView != null) {
                    Point point = Point.fromLngLat(location.getLongitude(), location.getLatitude());

                    // Centramos la cámara en la ubicación del usuario
                    mapView.getMapboxMap().setCamera(new CameraOptions.Builder()
                            .center(point)
                            .zoom(15.0)
                            .build());

                    // Añadimos el marcador en la ubicación del usuario
                    addMarker(point);
                }
            }

            // ...implementa los otros métodos de LocationListener...
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
    }

    private void addMarker(Point point) {
        PointAnnotationOptions options = new PointAnnotationOptions()
                .withPoint(point)
                .withIconImage("icon-image"); // Asegúrate de que el icono está cargado en el estilo del mapa

        pointAnnotationManager.create(options);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                obtenerUbicacion();
            } else {
                // Manejar el caso en que el permiso no fue concedido
            }
        }
    }
}
