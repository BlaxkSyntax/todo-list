package za.co.wheelie.apprunner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import za.co.wheelie.signin.SignInActivity

class MainActivity : AppCompatActivity() {

    private lateinit var signBTN: Button
    private lateinit var registerBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signBTN = findViewById(R.id.Sign_in_main)
        registerBTN = findViewById(R.id.Register_main)

        signBTN.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        registerBTN.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}