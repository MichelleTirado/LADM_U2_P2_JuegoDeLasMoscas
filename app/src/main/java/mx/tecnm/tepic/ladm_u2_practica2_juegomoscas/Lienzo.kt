package mx.tecnm.tepic.ladm_u2_practica2_juegomoscas
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AlertDialog
import java.lang.String
import java.util.*
class Lienzo(p: MainActivity): View(p){
    var aplastador = 0
    var txtTimer =""
    var moscas : Array<Mosca> = Array(100, { Mosca(this) })
    var kmosca = 0
    override fun onDraw(c: Canvas){
        super.onDraw(c)
        var p = Paint()
        //Cielo
        c.drawColor(Color.parseColor("#A86707"))
        p.setColor(Color.parseColor("#3C2400"))
        c.drawRect(0f,0f,900f,300f,p)
        p.setColor(Color.WHITE)
        p.textSize = 40f
        //mosca.pintar(c,p)
        (0..99).forEach {
            moscas[it].pintar(c, p)
        }
        c.drawText("Moscas pisadas: " +aplastador.toString(), 100f, 100f, p)
        c.drawText("Tiempo restante: "+ txtTimer, 100f, 200f, p)
    }

    fun animarMosca(){
        (0..99).forEach {
            if(moscas[it].estaviva)
            moscas[it].limites(width, height)
        }
        invalidate()
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                var puntero = 0
                while (puntero <= 99) {
                    if (moscas[puntero].estaEnArea(event.x, event.y)) {
                        if (moscas[puntero].estaviva) {
                            aplastador++
                            kmosca = puntero
                            var moscapresionada = BitmapFactory.decodeResource(
                                this.resources,
                                R.drawable.rip
                            )
                            moscas[puntero].imagen = moscapresionada
                            moscas[puntero].estaviva = false
                        }
                    }
                    puntero++
                }
            }
        }
        invalidate()
        return true
    }

    var countDownTimer: CountDownTimer? = object : CountDownTimer(60000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            txtTimer= (
                    String.format(
                        Locale.getDefault(),
                        "%d segundos",
                        millisUntilFinished / 1000L
                    )
                    )
            if(aplastador>=100){
                AlertDialog.Builder(context)
                    .setTitle("FELICIDADES")
                    .setMessage("ELIMINASTE A TODAS LAS MOSCAS ")
                    .setPositiveButton("OK"){p, i ->}
                    .show()
                this.cancel()
            }
            if(millisUntilFinished/1000L==0.toLong()&&aplastador!=100){
                AlertDialog.Builder(context)
                    .setTitle("TREMENDA F:(")
                    .setMessage("Se acabo el tiempo, reinicia la app y vuelve a intentarlo")
                    .setPositiveButton("OK"){p, i ->}
                    .show()
            }
        }
        override fun onFinish() {
        }
    }.start()
}