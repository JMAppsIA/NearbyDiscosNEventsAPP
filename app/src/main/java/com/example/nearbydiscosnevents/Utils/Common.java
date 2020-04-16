package com.example.nearbydiscosnevents.Utils;

import com.example.nearbydiscosnevents.Models.User;
import com.example.nearbydiscosnevents.Retrofit.IAPI;
import com.example.nearbydiscosnevents.Retrofit.RetrofitClient;

public class Common {
    //En emulador localhost es 10.0.2.2
    //public static final String URL_BASE = "http://jorge.hospedandoperu.com/upndocentes/";
    // public static final String URL_BASE = "http://upndocentes.jorge.hospedandoperu.com/upndocentes/";
    public static final String URL_BASE = "http://192.168.1.62:3000/";
    public static final int API_KEY = 1234;

    public static User usuarioActual = null;
    /*
    public static Evento eventoActual = null;
    public static Docente docenteActual = null;
    //public static Docente docenteActual = null;
    public static Solicitudes solicitudActual = null;
    //public static Amigos amigoActual = null;
    public static FSolicitudes fSolicitudActual = null;
    public static Horario horarioActual = null;
    public static Chat chatActual = null;
    public static Dia diaActual = null;
    */

    public static IAPI getAPI(){

        return RetrofitClient.getCliente(URL_BASE).create(IAPI.class);

    }
}
