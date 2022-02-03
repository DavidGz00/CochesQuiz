package es.android.coches.servicio.implementacion;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import es.android.coches.entidad.Pregunta;
import es.android.coches.repositorio.implementacion.RepositorioSQLitelmpl;
import es.android.coches.repositorio.interfaz.Repositorio;
import es.android.coches.servicio.interfaz.ServicioPreguntas;

public class ServicioPreguntasSQLiteImpl implements ServicioPreguntas {

    @Override
    public List<Pregunta> generarPreguntas(String recurso) throws Exception {
        todasLasPreguntas = repositorio.getAll();
        return todasLasPreguntas;
    }

    @Override
    public List<String> generarRespuestasPosibles(String respuestaCorrecta, int numRespuestas) {
        List<String> respuestasPosibles = new ArrayList<>();
        respuestasPosibles.add(respuestaCorrecta);
        List<String> respuestasIncorrectas = new ArrayList<>(
                this.todasLasPreguntas.stream().map(p ->
                        p.getRespuestaCorrecta()).collect(Collectors.toList()));
        respuestasIncorrectas.remove(respuestaCorrecta);
        for(int i=0; i<numRespuestas-1; i++) {
            int indiceRespuesta = new
                    Random().nextInt(respuestasIncorrectas.size());
            respuestasPosibles.add(respuestasIncorrectas.remove(indiceRespuesta));
        }
        Collections.shuffle(respuestasPosibles);
        return respuestasPosibles;
    }
    private Repositorio<Pregunta> repositorio;
    private List<Pregunta> todasLasPreguntas;
    public ServicioPreguntasSQLiteImpl(Context context) {
        repositorio = new RepositorioSQLitelmpl(context);
    }
}
