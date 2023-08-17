package com.work.landing.service;


import com.work.landing.model.Usuario;
import com.work.landing.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository userRepository;




    public Usuario guardarUsuario(Usuario usuario){
        if(this.isValid(usuario.getName())){
            return null;
        }
       return  this.userRepository.save(usuario);
    }




    //"^[a-zA-Z]*$", el * después de [a-zA-Z] indica que se permiten cero o más ocurrencias de letras
    public boolean isValid(String value) {
        return value != null && value.matches("^[0-9]*$");
    }






}
