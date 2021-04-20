package com.elico.catsgame

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*

class Game : AppCompatActivity() {

    var lista = arrayOf<Boolean>(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true)
    var listaData = arrayListOf<Int>(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8)
    var listaTem = arrayListOf<Int>()
    var listaDataBlack = arrayOf<Boolean>(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true)
    var estado: Boolean = false
    var i: Int = 0

    var creditos: Int = 0
    var intentos: Int = 0
    var errores: Int = 0

    private lateinit var t1: TextView
    var p1: Int = 0
    var vistas: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        /*error.setOnClickListener {
            revolver()
        }*/

        game_comenzar.setOnClickListener {
            if (estado == false) {
                estado = true
                ClearAll()
                revolver()
                progressBar.progress = 0
                val thread = Thread(Runnable {
                    i = 0
                    while (i < 100) {
                        progressBar.progress = (progressBar.progress + 1)
                        i++
                        Thread.sleep(300)
                    }
                    estado = false
                })
                thread.start()
            }


        }

        c1.setOnClickListener { Click(c1, 0) }
        c2.setOnClickListener { Click(c2, 1) }
        c3.setOnClickListener { Click(c3, 2) }
        c4.setOnClickListener { Click(c4, 3) }
        c5.setOnClickListener { Click(c5, 4) }
        c6.setOnClickListener { Click(c6, 5) }
        c7.setOnClickListener { Click(c7, 6) }
        c8.setOnClickListener { Click(c8, 7) }
        c9.setOnClickListener { Click(c9, 8) }
        c10.setOnClickListener { Click(c10, 9) }
        c11.setOnClickListener { Click(c11, 10) }
        c12.setOnClickListener { Click(c12, 11) }
        c13.setOnClickListener { Click(c13, 12) }
        c14.setOnClickListener { Click(c14, 13) }
        c15.setOnClickListener { Click(c15, 14) }
        c16.setOnClickListener { Click(c16, 15) }

    }

    private fun Click(caja: TextView?, position: Int) {
        if (estado) {
            if (listaDataBlack[position]) {
                if (vistas == 0) {
                    t1 = caja!!
                    vistas = 1
                    p1 = position
                    mostrar(caja, position)
                } else {
                    if (vistas == 1) {
                        mostrar(caja, position)
                        if (listaData[p1] == listaData[position]) {
                            creditos += 10
                            puntos.text = "puntos: ${creditos}"
                            listaDataBlack[position] = false
                            listaDataBlack[p1] = false
                        } else {
                            ocultar(caja, position)
                            ocultar(t1, p1)
                            errores += 1
                            error.text = "Errores: ${errores}"
                        }
                        vistas = 0
                        intentos += 1
                        intento.text = "Intentos: ${intentos}"
                    } else {
                        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun mostrar(caja: TextView?, position: Int) {
        if (lista[position]) {
            lista[position] = false
            caja?.animate()?.apply {
                duration = 250
                rotationYBy(360f)
                val timer = object : CountDownTimer(180, 1000) {
                    override fun onTick(millisUntilFinished: Long) {}
                    override fun onFinish() {
                        showColor(caja,listaData[position])
                    }
                }
                timer.start()
            }?.start()
        }

    }
    private fun showColor(caja: TextView?, position: Int){
        when(position){
            1 ->{
                caja?.setBackgroundResource(R.drawable.carta01)
            }
            2 ->{
                caja?.setBackgroundResource(R.drawable.carta02)
            }
            3 ->{
                caja?.setBackgroundResource(R.drawable.carta03)
            }
            4 ->{
                caja?.setBackgroundResource(R.drawable.carta04)
            }
            5 ->{
                caja?.setBackgroundResource(R.drawable.carta05)
            }
            6 ->{
                caja?.setBackgroundResource(R.drawable.carta06)
            }
            7 ->{
                caja?.setBackgroundResource(R.drawable.carta07)
            }
            8 ->{
                caja?.setBackgroundResource(R.drawable.carta08)
            }
        }
    }

    private fun ocultar(caja: TextView?, position: Int) {
        val timer2 = object : CountDownTimer(500, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                caja?.animate()?.apply {
                    duration = 250
                    rotationYBy(360f)
                    val timer3 = object : CountDownTimer(180, 1000) {
                        override fun onTick(millisUntilFinished: Long) {}
                        override fun onFinish() {
                            caja?.setBackgroundResource(R.drawable.carta)
                            lista[position] = true
                        }
                    }
                    timer3.start()
                }?.start()
            }
        }
        timer2.start()
    }

    private fun ClearAll() {
        progressBar.progress = 0
        c1.setBackgroundResource(R.drawable.carta)
        c2.setBackgroundResource(R.drawable.carta)
        c3.setBackgroundResource(R.drawable.carta)
        c4.setBackgroundResource(R.drawable.carta)
        c5.setBackgroundResource(R.drawable.carta)
        c6.setBackgroundResource(R.drawable.carta)
        c7.setBackgroundResource(R.drawable.carta)
        c8.setBackgroundResource(R.drawable.carta)
        c9.setBackgroundResource(R.drawable.carta)
        c10.setBackgroundResource(R.drawable.carta)
        c11.setBackgroundResource(R.drawable.carta)
        c12.setBackgroundResource(R.drawable.carta)
        c13.setBackgroundResource(R.drawable.carta)
        c14.setBackgroundResource(R.drawable.carta)
        c15.setBackgroundResource(R.drawable.carta)
        c16.setBackgroundResource(R.drawable.carta)
        puntos.text = "Puntos: 0"
        intento.text = "Intentos: 0"
        error.text = "Errores: 0"
        i = 0
        creditos = 0
        intentos = 0
        errores = 0
        var j: Int = 0
        while (j < lista.size) {
            lista[j] = true
            listaDataBlack[j] = true
            j++
        }

    }

    private fun revolver() {
        listaTem.clear()

        var m: Int = 0
        var aux: Int = 0

        var m1: Int = 0
        var m2: Int = 0
        var m3: Int = 0
        var m4: Int = 0
        var m5: Int = 0
        var m6: Int = 0
        var m7: Int = 0
        var m8: Int = 0

        while (true) {
            if (listaTem.size < 16) {
                aux = (1..8).random()
                if (aux == 1) {
                    if (m1 < 2) {
                        m1++
                        listaTem.add(aux)
                    }
                }
                if (aux == 2){
                    if (m2 < 2) {
                        m2++
                        listaTem.add(aux)
                    }
                }
                if (aux == 3){
                    if (m3 < 2) {
                        m3++
                        listaTem.add(aux)
                    }
                }
                if (aux == 4){
                    if (m4 < 2) {
                        m4++
                        listaTem.add(aux)
                    }
                }
                if (aux == 5){
                    if (m5 < 2) {
                        m5++
                        listaTem.add(aux)
                    }
                }
                if (aux == 6){
                    if (m6 < 2) {
                        m6++
                        listaTem.add(aux)
                    }
                }
                if (aux == 7){
                    if (m7 < 2) {
                        m7++
                        listaTem.add(aux)
                    }
                }
                if (aux == 8){
                    if (m8 < 2) {
                        m8++
                        listaTem.add(aux)
                    }
                }

                m++
            } else {
                break
            }
        }
        var j: Int = 0
        println("-----")
        print("[")
        var salto: Int = 1
        while (j < listaData.size) {
            if (salto == 5){
                salto = 1
                println("")
            }
            salto++
            listaData[j] = listaTem[j]
            print("${listaData[j]},")
            j++
        }
        println("]")
        println("-----")
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}