package controller;

import java.util.ArrayList;

import model.ExceptionVGA;
import model.bo.UsuarioBO;
import model.vo.UsuarioVO;

public class UsuarioController {
	UsuarioBO usuarioBO = new UsuarioBO();

	public UsuarioVO realizarLoginController(String email, String senha) throws ExceptionVGA {

		String validacao = "";

		if (email == null || email.trim().isEmpty()) {
			validacao += "Informe um email.";
		}
		if (senha == null || senha.trim().isEmpty()) {
			validacao += "Informe uma senha.";
		}
		if (!validacao.isEmpty()) {
			throw new ExceptionVGA(validacao);
		}

		return usuarioBO.realizarLoginBO(email, senha);

	}

	public UsuarioVO cadastrarUsuarioController(UsuarioVO usuarioVO) throws ExceptionVGA {	
		this.validarCamposObrigatorios(usuarioVO);
		return usuarioBO.cadastrarUsuarioBO(usuarioVO);
	}

	public ArrayList<UsuarioVO> consultarTodosUsuariosController() {
		return usuarioBO.consultarTodosUsuarioBO();
	}

	public UsuarioVO consultarUsuarioPorIdController(UsuarioVO usuarioVO) {
		return usuarioBO.consultarClientePorIdBO(usuarioVO);
	}

	public boolean atualizarUsuarioController(UsuarioVO usuarioVO) {
		return usuarioBO.atualizarUsuarioBO(usuarioVO);
	}

	public boolean excluirUsuarioController(int id) throws ExceptionVGA {
		return usuarioBO.excluirUsuarioBO(id);
	}

	private void validarCamposObrigatorios(UsuarioVO usuario) throws ExceptionVGA {
		String validacao = "";
		
		if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
			validacao += "Informe um email.";
		}
		if (usuario.getCpf() == null || usuario.getCpf().trim().isEmpty()) {
			validacao += "\nInforme um cpf.";
		}
		if (usuario.getNome() == null ||usuario.getNome().trim().isEmpty()) {
			validacao += "\nInforme um nome.";
		}
		if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
			validacao += "\nInforme uma senha.";
		}
		if (!validacao.isEmpty()) {
			throw new ExceptionVGA(validacao);
		}
	}

//	private String validarCpf(UsuarioVO usuario) throws ExceptionVGA {
//		String validacao = "";
//
//		if (usuario.getCpf() == null) {
//			validacao += "Informe um CPF! \n";
//		} else {
//			String cpfSemMascara = usuario.getCpf().replace(".", "");
//			cpfSemMascara = usuario.getCpf().replace("-", "");
//			usuario.setCpf(cpfSemMascara);
//			if (usuario.getCpf().length() != 11) {
//				validacao += "CPF deve possuir 11 digitos! \n";
//			}
//		}
//		return validacao;
//	}
}