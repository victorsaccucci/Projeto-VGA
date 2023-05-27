package model.bo;

import java.util.ArrayList;

import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

public class UsuarioBO {

	public UsuarioVO realizarLoginBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.realizarLogin(usuarioVO);
	}
	
	//regras de negócios -> verificar a existência de um usuário por CPF
	
	public UsuarioVO cadastrarUsuarioBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.verificarExistenciaLoginPorCpf(usuarioVO)) {
			System.out.println("\nUsuário já cadastrado!");
		}else {
			usuarioVO = usuarioDAO.cadastrarUsuarioDAO(usuarioVO);
		}
		return usuarioVO;
	}
	
	//outra regra
	
	public ArrayList<UsuarioVO> consultarTodosUsuarioBO() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		ArrayList<UsuarioVO> listaUsuariosVO = usuarioDAO.consultarTodos();
		if(listaUsuariosVO.isEmpty()){
			System.out.println("\nNenhum usário cadastrado!");
		}
		return listaUsuariosVO;
	}

	//verificar paramentro de consultarClientePorId
	
	public UsuarioVO consultarClientePorIdBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.consultarClientePorId(usuarioVO.getId());
	}

	public boolean atualizarUsuarioBO(UsuarioVO usuarioVO) {
		return false;
	}

	public boolean excluirUsuarioBO(UsuarioVO usuarioVO) {
		return false;
	}
}
