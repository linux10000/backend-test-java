package br.com.fcamara.provajava.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fcamara.provajava.pojo.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Integer> {

	List<Usuario> findByUsocloginAndUsocsenha(String usoclogin, String usocsenha);
}
