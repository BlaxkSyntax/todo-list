package za.co.wheelie.map

import android.annotation.SuppressLint
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.mapbox.android.core.location.LocationEngine
import com.mapbox.android.core.location.LocationEngineListener
import com.mapbox.android.core.location.LocationEnginePriority
import com.mapbox.android.core.location.LocationEngineProvider
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerPlugin
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.CameraMode
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.RenderMode
import za.co.wheelie.apprunner.R

class MapActivity : AppCompatActivity(), PermissionsListener,LocationEngineListener{

    private lateinit var mapView: MapView
    private lateinit var mapMap: MapboxMap
    private lateinit var permissionsMan: PermissionsManager
    private lateinit var originlocation: Location
    private lateinit var request: Button
    private lateinit var options: Button

    private lateinit var locationEngine: LocationEngine
    private lateinit var locationListener: LocationLayerPlugin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        Mapbox.getInstance(applicationContext,getString(R.string.API_KEY))
        request = findViewById(R.id.request_options)
        options = findViewById(R.id.options_manu_btn)
        mapView = findViewById(R.id.mapview)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(){
                mapboxMap -> mapMap = mapboxMap
            enableLocation()
        }
        request.setOnClickListener(){

        }
    }

    private fun enableLocation(){
        if(PermissionsManager.areLocationPermissionsGranted(this)){
            initlocationEngine()
            initlocationLayer()
        }else{
            permissionsMan = PermissionsManager(this)
            permissionsMan.requestLocationPermissions(this)
        }
    }

    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
        TODO("Not yet implemented")
    }

    override fun onPermissionResult(granted: Boolean) {
        if(granted){
            enableLocation()
        }
    }

    @SuppressWarnings("MissingPermission")
    private fun initlocationEngine(){
        locationEngine = LocationEngineProvider(this).obtainBestLocationEngineAvailable()
        locationEngine?.priority = LocationEnginePriority.HIGH_ACCURACY
        locationEngine.activate()

        var lastLocation = locationEngine?.lastLocation
        if(lastLocation != null){
            originlocation = lastLocation
            setCameraPosition(lastLocation)
        }else{
            locationEngine?.addLocationEngineListener(this)
        }
    }
    @SuppressLint("MissingPermission")
    private fun initlocationLayer(){
        locationListener = LocationLayerPlugin(mapView,mapMap,locationEngine)
        locationListener?.setLocationLayerEnabled(true)
        locationListener?.cameraMode = CameraMode.TRACKING
        locationListener?.renderMode = RenderMode.NORMAL
    }

    private fun setCameraPosition(location: Location){
        mapMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
            LatLng(location.latitude,location.longitude),15.0))
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        permissionsMan.onRequestPermissionsResult(requestCode,permissions,grantResults)
    }

    @SuppressLint("MissingPermission")
    override fun onConnected() {
        locationEngine?.requestLocationUpdates()
    }

    override fun onLocationChanged(location: Location?) {
        location?.let {
            originlocation = location
            setCameraPosition(location)
        }
    }
}