package za.co.wheelie.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import za.co.wheelie.apprunner.R
import za.co.wheelie.map.MapActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var appSignIn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        appSignIn = findViewById(R.id.app_sign)

        appSignIn.setOnClickListener{
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
    }
}