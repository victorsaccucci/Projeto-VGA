package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import model.vo.ItemVO;
import model.vo.UsuarioVO;
import model.vo.VendaVO;

public class GeradorPlanilha {

	
	public String gerarPlanilhaProdutos(List<ItemVO> itens, String caminho) {
		HSSFWorkbook arquivoExcel = new HSSFWorkbook();
		HSSFSheet abaPlanilha = arquivoExcel.createSheet("Clientes");
		
		HSSFRow linhaCabecalho = abaPlanilha.createRow(0);
		linhaCabecalho.createCell(0).setCellValue("Modelo");
		linhaCabecalho.createCell(1).setCellValue("Marca");
		linhaCabecalho.createCell(2).setCellValue("Cor");
		linhaCabecalho.createCell(3).setCellValue("Tamanho");
		linhaCabecalho.createCell(4).setCellValue("Quantidade");
		linhaCabecalho.createCell(5).setCellValue("Preço?");
		
		int contadorLinhas = 1;
		for(ItemVO item: itens) {
			HSSFRow novaLinha = abaPlanilha.createRow(contadorLinhas);
			novaLinha.createCell(0).setCellValue(item.getProduto().getModelo());
			novaLinha.createCell(1).setCellValue(item.getProduto().getMarca());
			novaLinha.createCell(2).setCellValue(item.getCor());
			novaLinha.createCell(3).setCellValue(item.getTamanho());
			novaLinha.createCell(4).setCellValue(item.getQuantidade());
			novaLinha.createCell(5).setCellValue(item.getPrecoUnitario());
			contadorLinhas++;
		}
		
		return salvarNoDisco(arquivoExcel, caminho);
	}
	
	private String salvarNoDisco(HSSFWorkbook planilha, String caminhoArquivo) {
		String mensagem = "";
		FileOutputStream saida = null;
		String extensao = ".xls";

		try {
			saida = new FileOutputStream(new File(caminhoArquivo + extensao));
			planilha.write(saida);
			mensagem = "Planilha gerada com sucesso!";
		} catch (FileNotFoundException e) {
			mensagem = "Erro ao tentar salvar planilha (sem acesso): " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} catch (IOException e) {
			mensagem = "Erro de I/O ao tentar salvar planilha em: " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} finally {
			if (saida != null) {
				try {
					saida.close();
					planilha.close();
				} catch (IOException e) {
					mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}

		return mensagem;
	}

	public String gerarPlanilhaUsuarios(List<UsuarioVO> usuarios, String caminhoEscolhido) {
		HSSFWorkbook arquivoExcel = new HSSFWorkbook();
		HSSFSheet abaPlanilha = arquivoExcel.createSheet("Clientes");
		
		HSSFRow linhaCabecalho = abaPlanilha.createRow(0);
		linhaCabecalho.createCell(0).setCellValue("Nome");
		linhaCabecalho.createCell(1).setCellValue("E-mail");
		linhaCabecalho.createCell(2).setCellValue("CPF");
		linhaCabecalho.createCell(3).setCellValue("Senha");
		linhaCabecalho.createCell(4).setCellValue("ADM?");
		
		int contadorLinhas = 1;
		for(UsuarioVO usuario: usuarios) {
			HSSFRow novaLinha = abaPlanilha.createRow(contadorLinhas);
			novaLinha.createCell(0).setCellValue(usuario.getNome());
			novaLinha.createCell(1).setCellValue(usuario.getEmail());
			novaLinha.createCell(2).setCellValue(usuario.getCpf());
			novaLinha.createCell(3).setCellValue(usuario.getSenha());
			novaLinha.createCell(4).setCellValue(usuario.isAdm() ? "Sim" : "Não");
			contadorLinhas++;
		}
		
		return salvarNoDiscoUsuarios(arquivoExcel, caminhoEscolhido);
	}
	
	private String salvarNoDiscoUsuarios(HSSFWorkbook planilha, String caminhoArquivo) {
		String mensagem = "";
		FileOutputStream saida = null;
		String extensao = ".xls";

		try {
			saida = new FileOutputStream(new File(caminhoArquivo + extensao));
			planilha.write(saida);
			mensagem = "Planilha gerada com sucesso!";
		} catch (FileNotFoundException e) {
			mensagem = "Erro ao tentar salvar planilha (sem acesso): " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} catch (IOException e) {
			mensagem = "Erro de I/O ao tentar salvar planilha em: " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} finally {
			if (saida != null) {
				try {
					saida.close();
					planilha.close();
				} catch (IOException e) {
					mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}

		return mensagem;
	}

	public String gerarPlanilhaVendas(List<VendaVO> vendas, String caminhoEscolhido) {
		HSSFWorkbook arquivoExcel = new HSSFWorkbook();
		HSSFSheet abaPlanilha = arquivoExcel.createSheet("Clientes");
		
		HSSFRow linhaCabecalho = abaPlanilha.createRow(0);
		linhaCabecalho.createCell(0).setCellValue("Data venda");
		linhaCabecalho.createCell(1).setCellValue("ID Usuario");
		linhaCabecalho.createCell(2).setCellValue("ID Item");
		
		
		int contadorLinhas = 1;
		for(VendaVO venda: vendas) {
			HSSFRow novaLinha = abaPlanilha.createRow(contadorLinhas);
			novaLinha.createCell(0).setCellValue(venda.getDataVenda());
			novaLinha.createCell(1).setCellValue(venda.getIdUsuario());
			novaLinha.createCell(2).setCellValue(venda.getIdItem());
			contadorLinhas++;
		}
		
		return salvarNoDiscoVendas(arquivoExcel, caminhoEscolhido);
	}

	private String salvarNoDiscoVendas(HSSFWorkbook planilha, String caminhoArquivo) {
		String mensagem = "";
		FileOutputStream saida = null;
		String extensao = ".xls";

		try {
			saida = new FileOutputStream(new File(caminhoArquivo + extensao));
			planilha.write(saida);
			mensagem = "Planilha gerada com sucesso!";
		} catch (FileNotFoundException e) {
			mensagem = "Erro ao tentar salvar planilha (sem acesso): " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} catch (IOException e) {
			mensagem = "Erro de I/O ao tentar salvar planilha em: " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} finally {
			if (saida != null) {
				try {
					saida.close();
					planilha.close();
				} catch (IOException e) {
					mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}

		return mensagem;
	}
	
}
