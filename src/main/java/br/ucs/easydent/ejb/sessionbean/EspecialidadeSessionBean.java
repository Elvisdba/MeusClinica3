package br.ucs.easydent.ejb.sessionbean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.lang3.NotImplementedException;

import br.ucs.easydent.app.dto.filtro.BaseFilter;
import br.ucs.easydent.app.util.Util;
import br.ucs.easydent.ejb.session.EspecialidadeSession;
import br.ucs.easydent.model.entity.Especialidade;
import br.ucs.easydent.model.entity.Usuario;

@Stateless
public class EspecialidadeSessionBean extends BaseSessionBean implements EspecialidadeSession {

	public Especialidade buscarPorId(Usuario usuario, Long id) {
		return em.find(Especialidade.class, id);
	}

	public List<Especialidade> buscarTodos(Usuario usuario, Options params) {

		String queryString = "SELECT e FROM Especialidade AS e";
		if (params.getOrdenacao() != null) {
			queryString += " ORDER BY e." + params.getOrdenacao();
		}

		Query query = em.createQuery(queryString);

		Util.checkPagination(query, params);

		return (List<Especialidade>) query.getResultList();
	}

	public Especialidade salvar(Usuario usuario, Especialidade entidade) {
		return em.merge(entidade);
	}

	public void excluir(Usuario usuario, Long id) {
		Especialidade especialidade = em.find(Especialidade.class, id);
		if (especialidade != null) {
			em.remove(especialidade);
		}
	}

	public List<Especialidade> buscarPorFiltro(Usuario usuario, Options options, BaseFilter<Especialidade> filtro) {
		// TODO Criar método buscarPorFiltro em EntityEJB<Especialidade>
		throw new NotImplementedException("EspecialidadeSessionBean/buscarPorFiltro");
	}

}
