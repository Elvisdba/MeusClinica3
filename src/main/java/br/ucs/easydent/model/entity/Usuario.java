package br.ucs.easydent.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.ucs.easydent.model.enums.TipoUsuarioEnum;
import br.ucs.easydent.model.intf.EntidadeComEstabelecimento;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Usuario implements EntidadeComEstabelecimento {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "usuario", sequenceName = "usuario_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario")
	private Long id;

	@Column
	private String email;

	@Column(nullable = true, unique = true)
	private String login;

	@Column(nullable = false)
	private String senha;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private Integer fgTipoUsuario;

	@OneToOne(mappedBy = "usuario")
	private Paciente paciente;

	@OneToOne(mappedBy = "usuario")
	private Dentista dentista;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Estabelecimento estabelecimento;

	public Usuario() {
		// TODO Auto-gene rated constructor stub
	}

	public TipoUsuarioEnum getTipoUsuarioEnum() {
		return TipoUsuarioEnum.getById(fgTipoUsuario);
	}

	public void setTipoUsuarioEnum(TipoUsuarioEnum tipoUsuarioEnum) {
		fgTipoUsuario = tipoUsuarioEnum != null ? tipoUsuarioEnum.getId() : null;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getFgTipoUsuario() {
		return fgTipoUsuario;
	}

	public void setFgTipoUsuario(Integer fgTipoUsuario) {
		this.fgTipoUsuario = fgTipoUsuario;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Dentista getDentista() {
		return dentista;
	}

	public void setDentista(Dentista dentista) {
		this.dentista = dentista;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
