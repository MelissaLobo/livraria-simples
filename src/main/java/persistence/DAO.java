package persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import util.EntityLocal;

public class DAO<T, I extends Serializable> {
	// T é o tipo, a classe. E I é o tipo do id, int ou long

	protected final EntityManager entityManager;
	protected final Class<T> clazz;

	protected DAO() {
		this.entityManager = EntityLocal.currentEntityManager.get();

		@SuppressWarnings("unchecked")
		Class<T> classe = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

		this.clazz = classe;
	}

	public void create(T entity) {
		try {
			entityManager.persist(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public T update(T entity) {
		T entityUpdated = null;
		try {
			entityUpdated = entityManager.merge(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entityUpdated;

	}

	public void destroy(T entity) {
		try {
			entityManager.remove(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public T find(I id) {

		T object = null;

		try {
			object = entityManager.find(clazz, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {

		List<T> resultList = null;

		try {
			Query query = entityManager.createQuery("from " + clazz.getName());
			resultList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public T buscaPorId(Integer id) {
		T instancia = entityManager.find(clazz, id);
		return instancia;
	}

	public int contaTodos() {
		long result = (Long) entityManager.createQuery(
				"select count(n) from livro n").getSingleResult();
		return (int) result;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		CriteriaQuery<T> query = entityManager.getCriteriaBuilder()
				.createQuery(clazz);
		query.select(query.from(clazz));

		List<T> lista = entityManager.createQuery(query)
				.setFirstResult(firstResult).setMaxResults(maxResults)
				.getResultList();

		return lista;
	}
	
	public List<T> listaTodos() {
		CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(clazz);
		query.select(query.from(clazz));

		List<T> lista = entityManager.createQuery(query).getResultList();
		return lista;
	}
}
