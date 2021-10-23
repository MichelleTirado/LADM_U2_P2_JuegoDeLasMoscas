package mx.tecnm.tepic.ladm_u2_practica2_juegomoscas


import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

class Mosca(punteroLienzo: Lienzo) {
    var x = (0..850).random().toFloat()
    var y = (0..1700).random().toFloat()
    var incX = 12
    var incY = 12
    var imagen = BitmapFactory.decodeResource(punteroLienzo.resources, R.drawable.mosca)
    var estaviva = true
    fun pintar(c: Canvas, p:Paint){
        c.drawBitmap(imagen,x,y, p)
    }
    fun limites(ancho:Int, alto:Int){
        x+=incX
        y+=incY
        if(x<= 0||x>=ancho-100){
            incX*=-1
        }
        if(y<=0||y>=alto-100){
            incY*=-1
        }
    }
    fun estaEnArea(toqueX:Float,toqueY:Float): Boolean {
        var finx = x + imagen.width
        var finy = y + imagen.height
        if(toqueX >= x && toqueX<= finx){
            if(toqueY >= y && toqueY <= finy){
                return true
            }
        }
        return false
    }
}