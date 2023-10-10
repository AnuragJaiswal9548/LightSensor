package com.example.lightsensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lightsensor.ui.theme.LightSensorTheme

class MainActivity : ComponentActivity(),SensorEventListener {
    var sensorManager:SensorManager?=null
    var sensor:Sensor?=null
    var image:ImageView?=null
    var bulb:Boolean=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        image=findViewById(R.id.imageView)
        sensorManager=getSystemService(Context.SENSOR_SERVICE)as SensorManager
        sensor=sensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)

    }

override fun onResume(){
    super.onResume()
    sensorManager?.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
}

    override fun onPause() {
        super.onPause()
        sensorManager?.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event!!.values[0]>10){
            if(bulb){
                image?.setImageResource(R.drawable.poweroff)
                bulb=false
            }
            else{
                return
            }

        }
        else{
            bulb=true
            image?.setImageResource(R.drawable.poweron)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }


}





