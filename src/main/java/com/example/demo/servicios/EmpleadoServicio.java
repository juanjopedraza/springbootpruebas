package com.example.demo.servicios;

import com.example.demo.modelo.Empleado;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmpleadoServicio {
    private List<Empleado> repositorio = new ArrayList<>();

    public Empleado add(Empleado e){
        repositorio.add(e);
        return e;
    }

    public List<Empleado> findAll() {
        return repositorio;
    }

    @PostConstruct
    public void init() {
        repositorio.addAll(
                Arrays.asList(new Empleado(1,"Juan","juan@juan.com","1111111111"),
                        new Empleado(2,"Perico","perico@perico.com","2222222222"),
                        new Empleado(3,"Andrés","andres@andres.com","3333333333"))
        );
    }

}
