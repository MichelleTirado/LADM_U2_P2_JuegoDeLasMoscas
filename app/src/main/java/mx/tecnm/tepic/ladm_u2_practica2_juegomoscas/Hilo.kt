package mx.tecnm.tepic.ladm_u2_practica2_juegomoscas


class Hilo(p: MainActivity): Thread(){
    var puntero = p
    override fun run(){
        super.run()

        while(true){
            sleep(70)
            puntero.runOnUiThread {
                puntero.lienzo!!.animarMosca()
            }
        }
    }
}