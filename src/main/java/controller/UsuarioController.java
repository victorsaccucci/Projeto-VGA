package controller;

import java.util.ArrayList;

import model.ExceptionVGA;
import model.bo.UsuarioBO;
import model.vo.UsuarioVO;

public class UsuarioController {

	public UsuarioVO realizarLoginController(UsuarioVO usuarioVO) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.realizarLoginBO(usuarioVO);

	}	
	public UsuarioVO cadastrarUsuarioController(UsuarioVO usuarioVO) throws ExceptionVGA {
		UsuarioBO usuarioBO = new UsuarioBO();
		this.validarCamposObrigatorios(usuarioVO);
		return usuarioBO.cadastrarUsuarioBO(usuarioVO);
	}
	public ArrayList<UsuarioVO> consultarTodosUsuariosController() {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.consultarTodosUsuarioBO();
	}
	public UsuarioVO consultarUsuarioPorIdController(UsuarioVO usuarioVO) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.consultarClientePorIdBO(usuarioVO);
	}

	public boolean atualizarUsuarioController(UsuarioVO usuarioVO) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.atualizarUsuarioBO(usuarioVO);
	}

	public boolean excluirUsuarioController(UsuarioVO usuarioVO) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.excluirUsuarioBO(usuarioVO);
	}
	
	private void validarCamposObrigatorios(UsuarioVO usuario) throws ExceptionVGA {
		String validacao = "";
		
		if(usuario.getNome() == null || usuario.getNome().trim().length() < 2) {
			validacao += "Nome inválido \n";
		}
		
		validacao += validarCpf(usuario);
		
		if(usuario.getEmail() == null) {
			validacao += "Informe um endereço \n";
		}
		
		if(usuario.getSenha() == null) {
			validacao += "Informe uma senha! \n";
		}
		
		if(!validacao.isEmpty()) {
			throw new ExceptionVGA(validacao);
		}
	}
	
	private String validarCpf(UsuarioVO usuario) throws ExceptionVGA{
		String validacao = "";
		
		if(usuario.getCpf() == null) {
			validacao += "Informe um CPF! \n";
		}else {
			String cpfSemMascara = usuario.getCpf().replace(".", "");
			cpfSemMascara = usuario.getCpf().replace("-", "");
			usuario.setCpf(cpfSemMascara);
			if(usuario.getCpf().length() != 11) {
				validacao += "CPF deve possuir 11 digitos! \n";
			}
		}
		return validacao;
	}
}