package com.proyecto.devAlejandro.proyecto.controllers;

import com.proyecto.devAlejandro.proyecto.dao.UsuarioDao;
import com.proyecto.devAlejandro.proyecto.models.Usuario;
import com.proyecto.devAlejandro.proyecto.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {
    //ATRIBUTO
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;
    @RequestMapping (value = "api/usuario/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Luqwecas");
        usuario.setApellido("11111111111");
        usuario.setTelefono("44231717");
        usuario.setEmail("luqwecasMoy44@gmail.com");
        return usuario;
    }

    @RequestMapping (value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value= "Authorization")String token) {

       // String usuarioId = jwtUtil.getKey(token);
       // if (usuarioId == null) {
        //    return new ArrayList<>();
      //  }
        if (!validadToken(token)) {
             return null;
        }
        return usuarioDao.getUsuario();


       // List<Usuario> usuarios = new ArrayList<>();
//
       // Usuario usuario1 = new Usuario();
       // usuario1.setId(1001L);
       // usuario1.setNombre("21321321");
       // usuario1.setApellido("assadada");
       // usuario1.setTelefono("44231717");
       // usuario1.setEmail("luqwecasMoy44@gmail.com");
//
       // Usuario usuario2 = new Usuario();
       // usuario2.setId(1002L);
       // usuario2.setNombre("123123123123");
       // usuario2.setApellido("calsdasdas");
       // usuario2.setTelefono("44231717");
       // usuario2.setEmail("luqwecasMoy44@gmail.com");
//
       // usuarios.add(usuario1);
       // usuarios.add(usuario2);

        //return usuarios;
    }

    private boolean validadToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping (value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario)  { //estaria convirtiendo el json que recibe a un usuario automaticamente

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1,usuario.getPassword());
        usuario.setPassword(hash);



         usuarioDao.registrar(usuario);
    }

        @RequestMapping (value = "usuario2")
    public Usuario modificar() {
        Usuario usuario = new Usuario();
        usuario.setNombre("2222222222222222222");
        usuario.setApellido("Moy");
        usuario.setTelefono("44231717");
        usuario.setEmail("luqwecasMoy44@gmail.com");
        return usuario;
    }
    @RequestMapping (value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value= "Authorization")String token, @PathVariable Long id) {
       if (!validadToken(token)){return;}
       usuarioDao.eliminarUsuario(id);
    }
    @RequestMapping (value = "usuario4")
    public Usuario buscar() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Luqwecas");
        usuario.setApellido("Moy");
        usuario.setTelefono("44231717");
        usuario.setEmail("luqwecasMoy44@gmail.com");
        return usuario;
    }

}
