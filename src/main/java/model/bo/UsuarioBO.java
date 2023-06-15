package model.bo;

import java.util.ArrayList;

import model.ExceptionVGA;
import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

public class UsuarioBO {
	UsuarioDAO usuarioDAO = new UsuarioDAO();

	public UsuarioVO realizarLoginBO(String email, String senha) throws ExceptionVGA {
		UsuarioVO usuarioAutenticado = usuarioDAO.realizarLogin(email, senha);
		
		if(usuarioAutenticado == null) {
			throw new ExceptionVGA("Usuário não encontrado!");
		}		
		return usuarioAutenticado;
	}
	
	public UsuarioVO cadastrarUsuarioBO(UsuarioVO usuarioVO) {
		
		usuarioVO = usuarioDAO.cadastrarUsuarioDAO(usuarioVO);
		
		return usuarioVO;
	}
	
	public ArrayList<UsuarioVO> consultarTodosUsuarioBO() {
		ArrayList<UsuarioVO> listaUsuariosVO = usuarioDAO.consultarTodos();
		if(listaUsuariosVO.isEmpty()){
			System.out.println("\nNenhum usário cadastrado!");
		}
		return listaUsuariosVO;
	}
	
	public UsuarioVO consultarClientePorIdBO(UsuarioVO usuarioVO) {
		return usuarioDAO.consultarClientePorId(usuarioVO.getId());
	}

	public boolean atualizarUsuarioBO(UsuarioVO usuarioVO) {
		return false;
	}

	public boolean excluirUsuarioBO(int id) throws ExceptionVGA {
		return usuarioDAO.excluir(id);
	}
}
