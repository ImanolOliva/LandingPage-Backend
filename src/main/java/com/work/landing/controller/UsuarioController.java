package com.work.landing.controller;


import com.work.landing.model.Usuario;
import com.work.landing.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
@RestController
@RequestMapping(value = "/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    private final JavaMailSender javaMailSender;
    public UsuarioController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    @PostMapping("/user")
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){
        Usuario user = this.usuarioService.guardarUsuario(usuario);
       if(user == null){
           return  new ResponseEntity("Nombre formato incorrecto",HttpStatusCode.valueOf(404));
       }
        this.enviarCorreo(usuario);
        return new ResponseEntity<>(usuario, HttpStatusCode.valueOf(200));
    }

   public String  enviarCorreo(Usuario usuario){
        String contenidoCorreo = "Nombre " + usuario.getName() + " \n Email: " + usuario.getEmail() + "\n    Telefono: " + usuario.getTel();

       SimpleMailMessage message = new SimpleMailMessage();

       message.setTo("imanoleduardooliva@gmail.com");
       message.setSubject("Mensaje Landing Page Co-housing");
       message.setText(contenidoCorreo);

       javaMailSender.send(message);

       return "Correo electrónico enviado con éxito.";

   }


}
