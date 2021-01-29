package com.protocoloApp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Protocolo")
@Table(name = "TB_PROTOCOLO_PRO", schema = "protocolo")
public class Protocolo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_PROTOCOLO_PRO")
	@SequenceGenerator(name = "SEQ_PROTOCOLO_PRO", sequenceName = "protocolo.seq_protocolo")
	@Column(name = "ID_PRO", columnDefinition = "numeric", nullable = false)
	private Integer id;
	
	@Column(name = "DATA_INCLUSAO_PRO")
	private Date dataInclusao;

	@Column(name = "NUMERO_PRO")
	private Integer numero;

	@Column(name = "ANO_PRO")
	private Integer ano;

	@Column(name = "DESCRICAO_PROTOCOLO_PRO")
	private String descricaoProtocolo;
	
	@Column(name = "NOME_SOLICITANTE_PRO")
	private String nomeSolicitante;

//	@ManyToOne
//	@JoinColumn(name = "ID_USU_PRO", referencedColumnName = "ID_USU")
//	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getDescricaoProtocolo() {
		return descricaoProtocolo;
	}

	public void setDescricaoProtocolo(String descricaoProtocolo) {
		this.descricaoProtocolo = descricaoProtocolo;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}
/**
 * IMPLEMENTAR USUARIO AUTENTICADO QUE CADASTROU O PROTOCOLO
 */
//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result + ((descricaoProtocolo == null) ? 0 : descricaoProtocolo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomeSolicitante == null) ? 0 : nomeSolicitante.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
//		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Protocolo other = (Protocolo) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (dataInclusao == null) {
			if (other.dataInclusao != null)
				return false;
		} else if (!dataInclusao.equals(other.dataInclusao))
			return false;
		if (descricaoProtocolo == null) {
			if (other.descricaoProtocolo != null)
				return false;
		} else if (!descricaoProtocolo.equals(other.descricaoProtocolo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeSolicitante == null) {
			if (other.nomeSolicitante != null)
				return false;
		} else if (!nomeSolicitante.equals(other.nomeSolicitante))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
//		if (usuario == null) {
//			if (other.usuario != null)
//				return false;
//		} else if (!usuario.equals(other.usuario))
//			return false;
		return true;
	}

	
}
