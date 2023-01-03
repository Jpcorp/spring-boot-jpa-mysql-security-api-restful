package cl.espacio.ws.api.service;

import cl.espacio.ws.api.repository.UsuariosRepository;
import cl.espacio.ws.api.security.UpdatableBCrypt;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import cl.espacio.ws.api.repository.entity.Usuarios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Value("${spring.security.user.name}")
	private String secret;

	@Value("${spring.security.user.password}")
	private String secretPassword;

	@Autowired
	UsuariosRepository usuarioRepository;
	private static final UpdatableBCrypt bcrypt = new UpdatableBCrypt(11);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails credential;

		Usuarios user = usuarioRepository.findByUsername(username);
		if (user == null) {

			if (secret.equals(username)) {
				credential =  new User(secret, secretPassword, new ArrayList<>());
			} else {
				throw new UsernameNotFoundException("User not found with username: " + username);
			}

		} else {
			String passwdBcrypt = bcrypt.hash(user.getPassword());
			user.setLastLogin(new Date());
			usuarioRepository.findById(user.getID());
			credential = new User(user.getUsername(), passwdBcrypt, new ArrayList<>());
		}
		return credential;
	}

	public UserDetails loadUserByUsername(String paramUser, String paramPasswd) {
		UserDetails credential;

		Usuarios user = findByUsername(paramUser, paramPasswd);

		if (user == null) {

			if (secret.equals(paramUser)) {
				credential =  new User(paramUser, paramPasswd, new ArrayList<>());
			} else {
				throw new UsernameNotFoundException("User not found with username: " + paramUser);
			}
		} else {
			String passwdBcrypt = bcrypt.hash(user.getPassword());
			user.setLastLogin(new Date());
			credential = new User(user.getUsername(), passwdBcrypt, new ArrayList<>());
		}

		return credential;

	}

	private Usuarios findByUsername(String paramUser, String paramPasswd) {
		Usuarios usuario = null;

		if (secret.equals(paramUser) && secretPassword.equals(paramPasswd) ) {
			usuario = new Usuarios(paramUser, paramPasswd);
		}
		return usuario;
	}
}