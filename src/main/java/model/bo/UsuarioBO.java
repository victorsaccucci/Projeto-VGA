package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.ExceptionVGA;
import model.dao.UsuarioDAO;
import model.seletor.SeletorUsuario;
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
	
	public UsuarioVO cadastrarUsuarioBO(UsuarioVO usuarioVO) throws ExceptionVGA {
		if(usuarioDAO.verificarExistenciaCpfeEmail(usuarioVO.getCpf(), usuarioVO.getEmail())) {
			throw new ExceptionVGA("CPF e E-mail já cadastrado");
		}
		if(usuarioDAO.verificarExistenciaCpf(usuarioVO.getCpf())) {
			throw new ExceptionVGA("CPF já cadastrado");
			} 
		if(usuarioDAO.verificarExistenciaEmail(usuarioVO.getEmail())) {
			throw new ExceptionVGA("Email já cadastrado");
		}else {
			usuarioVO = usuarioDAO.cadastrarUsuarioDAO(usuarioVO);
		}
		
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
		return usuarioDAO.atualizar(usuarioVO);
	}

	public boolean excluirUsuarioBO(int id) throws ExceptionVGA {
		return usuarioDAO.excluir(id);
	}

	public List<UsuarioVO> consultarComFiltros(SeletorUsuario seletor) {
		return usuarioDAO.consultarComFiltros(seletor);
	}
}
