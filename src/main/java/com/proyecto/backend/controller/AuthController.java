package com.proyecto.backend.controller;

import com.proyecto.backend.dto.LoginRequestDTO;
import com.proyecto.backend.dto.LoginResponseDTO;
import com.proyecto.backend.service.AutentificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/autenticar")
@CrossOrigin(origins = "https://proyectofrontend.azurewebsites.net/login/inicio") // Permitir solicitudes desde esta URL
public class AuthController {
    @Autowired
    AutentificacionService autentificacionService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            Thread.sleep(Duration.ofSeconds(1)); // Tiempo en que va a devolver la respuesta
            String[] datosUsuario = autentificacionService.validarUsuario(loginRequestDTO);
            System.out.println("Respuesta backend: " + Arrays.toString(datosUsuario));

            if (datosUsuario == null) {
                return new LoginResponseDTO("01", "Error: Problemas en la autenticación", "", "");
            } else {
                return new LoginResponseDTO("00", "", datosUsuario[0], datosUsuario[1]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new LoginResponseDTO("99", "Error: Ocurrió un problema"+e.getMessage(), "", "");
        }
    }

    @GetMapping("/get-integrantes")
    public ResponseEntity<List<String[]>> listarUsuarios() {
        try {
            List<String[]> usuarios = autentificacionService.listarUsuarios();
            return ResponseEntity.ok(usuarios); // Devuelve la lista de usuarios como respuesta HTTP 200
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Devuelve error 500 si hay un problema
        }
    }
}








