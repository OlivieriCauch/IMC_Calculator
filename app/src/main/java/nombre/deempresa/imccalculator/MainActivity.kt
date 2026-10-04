package nombre.deempresa.imccalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private fun SeekBar.setOnSeekBarChangeListener(
    l: SeekBar.OnSeekBarChangeListener,
    function: () -> Unit
) {
}

class MainActivity : AppCompatActivity() {

    private var nombre: String = ""
    private var kilogramos: Int = 80
    private var altura: Int = 170

    private var Imc: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var btPlus: Button
        var btMinus: Button
        var tvKilogramos : TextView
        var sbAltura : SeekBar = findViewById(R.id.sb_altura)
        var tvAltura: TextView = findViewById(R.id.tv_altura)
        var btCalcularImc: Button = findViewById(R.id.bt_calcularImc)
        var tvResultImc: TextView = findViewById(R.id.tv_resultImc)
        val cardResult: CardView = findViewById(R.id.cardResult)
        val etNombre: EditText = findViewById(R.id.et_nombre)
        val tvNameResult: TextView = findViewById(R.id.tv_name_result)

        btPlus = findViewById(R.id.bt_plus)
        btMinus = findViewById(R.id.bt_minus)
        tvKilogramos = findViewById(R.id.tv_kilogramos)

        btPlus.setOnClickListener (){
            kilogramos++
            tvKilogramos.text = kilogramos.toString()
            true
        }

        btMinus.setOnClickListener (){
            kilogramos--
            tvKilogramos.text = kilogramos.toString()
            true
        }
        
        sbAltura.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                altura = progress
                tvAltura.text = altura.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        btCalcularImc.setOnClickListener {

                val alturaMetros = altura.toFloat() / 100f
                val imc = kilogramos / (alturaMetros * alturaMetros)
                tvResultImc.text = String.format("%.2f", imc)

                val nombreIngresado = etNombre.text.toString().trim()
                if (nombreIngresado.isNotEmpty()) {
                    tvNameResult.text = "$nombreIngresado, tu IMC es:"
                } else {
                    tvNameResult.text = "Tu IMC es:"
                }

                cardResult.visibility = View.VISIBLE
            }



        }
    }



