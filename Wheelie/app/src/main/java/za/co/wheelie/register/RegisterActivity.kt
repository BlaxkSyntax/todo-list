package za.co.wheelie.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import za.co.wheelie.apprunner.R
import za.co.wheelie.map.MapActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBTN: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerBTN = findViewById(R.id.register_control_btn)

        registerBTN.setOnClickListener{
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
    }
}