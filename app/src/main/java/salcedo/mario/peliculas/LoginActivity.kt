package salcedo.mario.peliculas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    lateinit var btn_ingresar:Button
    lateinit var btn_registro:Button
    lateinit var tv_recuperacion:TextView

    lateinit var et_correo: EditText
    lateinit var et_contra: EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_ingresar = findViewById(R.id.btn_ingresar)
        btn_ingresar = findViewById(R.id.btn_registro)
        tv_recuperacion = findViewById(R.id.tv_recuperacion)
        et_contra = findViewById(R.id.et_contra_login)
        et_correo = findViewById(R.id.et_correo_login)

        auth = Firebase.auth
        btn_registro.setOnClickListener{
            var intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        btn_ingresar.setOnClickListener{

            var correo: String = et_correo.text.toString()
            var contra: String = et_contra.text.toString()

            if (!correo.isNullOrEmpty() && !contra.isNullOrEmpty()){
                Toast.makeText(this, "Entro", Toast.LENGTH_SHORT).show()
                auth.createUserWithEmailAndPassword(correo, contra)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser

                            var intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)

                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                this,
                                "Datos incorrectos.",
                                Toast.LENGTH_SHORT,
                            ).show()
                            //updateUI(null)
                        }
                    }

            }else{
                Toast.makeText(this, "Ingresar datos", Toast.LENGTH_SHORT).show()
            }


        }

        tv_recuperacion.setOnClickListener {
            var intent = Intent(this, RecuperacionActivity::class.java)
            startActivity(intent)
        }
    }
}