package com.proyecto.backend.service;

import com.proyecto.backend.dto.LoginRequestDTO;

import java.io.IOException;
import java.util.List;

public interface AutentificacionService {

    String[] validarUsuario(LoginRequestDTO loginRequestDTO) throws IOException;
    List<String[]> listarUsuarios() throws IOException;
}
