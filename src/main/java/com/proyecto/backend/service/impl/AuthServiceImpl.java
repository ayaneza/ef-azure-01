package com.proyecto.backend.service.impl;

import com.proyecto.backend.dto.LoginRequestDTO;
import com.proyecto.backend.service.AutentificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl  implements AutentificacionService {
    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public String[] validarUsuario(LoginRequestDTO loginRequestDTO) throws IOException {



        String[] datosUsuario =null;
        Resource resource = resourceLoader.getResource("classpath:integrantes.txt");

        try(BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            //Implement

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if(     loginRequestDTO.codigo().equals(datos[0]) &&
                        loginRequestDTO.password().equals(datos[1])){

                    datosUsuario = new String[2];

                    datosUsuario[0] = datos[2]; //recupera el nombre que esta en la segunda posicion (empieza de 0)
                    datosUsuario[1] = datos[3];//recupera el apellido que esta en la tercera posicion

                }
            }

        }catch(IOException e){
            datosUsuario =null;
            throw new IOException(e);
        }



        return datosUsuario;
    }

    @Override
    public List<String[]> listarUsuarios() throws IOException {
        List<String[]> listaUsuarios = new ArrayList<>();
        String[] datosUsuario =null;
        Resource resource = resourceLoader.getResource("classpath:integrantes.txt");
        try(BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            //Implement

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");


                    datosUsuario = new String[2];

                    datosUsuario[0] = datos[2]; //recupera el nombre que esta en la segunda posicion (empieza de 0)
                    datosUsuario[1] = datos[3];//recupera el apellido que esta en la tercera posicion

                listaUsuarios.add(datosUsuario); //agregar el nombre y el apellido a la lista


            }
        }catch(IOException e){
            listaUsuarios = null;
            throw new IOException(e);
        }
        return listaUsuarios;
    }
}
