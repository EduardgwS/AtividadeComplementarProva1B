package com.example.atividadecomplementarprova1B

import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val edLarguraA = findViewById<EditText>(R.id.edLarguraA)
        val edLarguraB = findViewById<EditText>(R.id.edLarguraB)
        val edAlturaA = findViewById<EditText>(R.id.edAlturaA)
        val edAlturaB = findViewById<EditText>(R.id.edAlturaB)
        val tvPerimetro = findViewById<TextView>(R.id.tvPerimetro)
        val tvArea = findViewById<TextView>(R.id.tvArea)
        val btCalcularFormas = findViewById<Button>(R.id.btCalcularFormas)

        val edTempo = findViewById<EditText>(R.id.edTempo)
        val edVelocidade = findViewById<EditText>(R.id.edVelocidade)
        val edMedia = findViewById<EditText>(R.id.edMedia)
        val tvCombustivel = findViewById<TextView>(R.id.tvCombustivel)
        val btCalcularCombustivel = findViewById<Button>(R.id.btCalcularCombustivel)

        btCalcularFormas.setOnClickListener {
            val lA = edLarguraA.text.toString().toDoubleOrNull() ?: 0.0
            val aA = edAlturaA.text.toString().toDoubleOrNull() ?: 0.0
            val lB = edLarguraB.text.toString().toDoubleOrNull() ?: 0.0
            val aB = edAlturaB.text.toString().toDoubleOrNull() ?: 0.0

            if (lA + aA + lB + aB == 0.0) {
                Toast.makeText(this, "Informe valores válidos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val (areaA, perA) = calcularForma(lA, aA)
            val (areaB, perB) = calcularForma(lB, aB)

            tvArea.text = "%.1f".format(areaA + areaB)
            tvPerimetro.text = "%.1f".format(perA + perB)
        }

        btCalcularCombustivel.setOnClickListener {
            val tempo = edTempo.text.toString().toDoubleOrNull() ?: 0.0
            val vel = edVelocidade.text.toString().toDoubleOrNull() ?: 0.0
            val med = edMedia.text.toString().toDoubleOrNull() ?: 0.0

            if (med <= 0) {
                Toast.makeText(this, "Média inválida", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val litros = (tempo * vel) / med
            tvCombustivel.text = "%.2f".format(litros)
        }
    }


    private fun calcularForma(largura: Double, altura: Double): Pair<Double, Double> {
        return if (largura > 0 && altura > 0) {
            Pair(largura * altura, 2 * (largura + altura))
        } else {
            val lado = if (largura > 0) largura else altura
            Pair(lado * lado, 4 * lado)
        }
    }
}