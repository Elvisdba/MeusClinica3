package br.ucs.easydent.sessionbean;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ucs.easydent.filtro.BaseFilter;
import br.ucs.easydent.session.AgendaSession;
import br.ucs.easydent.util.Util;
import br.ucs.easydental.model.Agenda;
import br.ucs.easydental.model.Consulta;

@Stateless
public class AgendaSessionBean extends BaseSessionBean implements AgendaSession {

	public Agenda buscarPorId(Long id) {
		return em.find(Agenda.class, id);
	}

	public List<Agenda> buscarTodos(QueryParams params) {
		String queryString = "SELECT e FROM Agenda AS e";
		if (params.getOrdenacao() != null) {
			queryString += " ORDER BY e." + params.getOrdenacao();
		}

		Query query = em.createQuery(queryString);
		Util.checkPagination(query, params);

		List<Agenda> agendas = (List<Agenda>) query.getResultList();
		detach(agendas);

		return agendas;
	}

	public Agenda salvar(Agenda entidade) {
		return em.merge(entidade);
	}

	public void excluir(Long id) {
		Agenda agenda = this.buscarPorId(id);
		if (agenda != null) {
			em.remove(agenda);
		}
	}

	public List<Agenda> buscarPorFiltro(BaseFilter<Agenda> filtro) {
		// TODO Criar m�todo buscarPorFiltro em EntityEJB<Agenda>
		return null;
	}

	private void detach(List<Agenda> agendas) {
		for (Agenda agenda : agendas) {
			detach(agenda);
		}
	}

	private void detach(Agenda agenda) {
		agenda.setHorarios(agenda.getHorarios());
		agenda.setHorariosEspeciais(agenda.getHorariosEspeciais());
	}

	@Override
	public List<Consulta> buscarConsultasPorData(Agenda agenda, Calendar inicio, Calendar fim, QueryParams params) {

		Map<String, Object> _params = new HashMap<>();
		StringBuilder queryString = new StringBuilder();
		queryString.append(" SELECT e FROM Consulta AS e ");

		if (agenda == null) {
			throw new RuntimeException("N�o � poss�vel buscar consultas, sem especificar a agenda.");
		}

		queryString.append(" WHERE e.agenda = :agenda ");
		_params.put("agenda", agenda);

		if (inicio != null) {
			queryString.append(" AND e.data >= :inicio ");
			_params.put("inicio", inicio);
		}

		if (fim != null) {
			queryString.append(" AND e.data <= :fim ");
			_params.put("fim", fim);
		}

		Query query = em.createQuery(queryString.toString());
		Util.checkPagination(query, params);
		Util.setParams(query, _params);

		return query.getResultList();
	}

}
