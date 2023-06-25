package model.vo;

import java.time.LocalDate;

public class VendaVO {

	private int id;
	private int idItem;
	private int idUsuario;
	private LocalDate dataVenda;
	
	public VendaVO() {
		super();
	}

	public VendaVO(int idItem) {
		super();
		this.idItem = idItem;
	}

	public VendaVO(int id, int idItem, int idUsuario, LocalDate dataVenda) {
		super();
		this.id = id;
		this.idItem = idItem;
		this.idUsuario = idUsuario;
		this.dataVenda = dataVenda;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	@Override
	public String toString() {
		return id + " " + idItem + " " + idUsuario + " " + dataVenda;
	}
	
	
	
}
