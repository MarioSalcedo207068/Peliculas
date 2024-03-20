package salcedo.mario.peliculas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RecuperacionActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth
    lateinit var btn_recuperar:Button
    lateinit var et_correo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperacion)
        btn_recuperar = findViewById(R.id.btn_recuperar_contra)
        et_correo = findViewById(R.id.et_contra_recuperacion)

        auth = Firebase.auth

        btn_recuperar.setOnClickListener{
            var correo: String = et_correo.text.toString()

            if (!correo.isNullOrEmpty()){
                auth.sendPasswordResetEmail(correo)
                    .addOnCompleteListener{ task ->
                        if (task.isSuccessful){
                            Toast.makeText(this, "se ha enviado un correo electronico", Toast.LENGTH_SHORT).show()
                            finish()
                        }else{
                            Toast.makeText(this, "No se pudo enviar el correo", Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(this, "Ingresar correo", Toast.LENGTH_SHORT).show()
            }
        }
    }
}