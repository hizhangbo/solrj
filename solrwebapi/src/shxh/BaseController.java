package shxh;

import java.util.List;

import main.base.solr.ZqSolrClient;

public abstract class BaseController<T extends ZqSolrClient<E>, E> {
	
	public T service;
	
	public BaseController(){
		Init();
	}
	
	public abstract void Init();
	
	public abstract E get();
	
	public abstract E getById(Long id);
	
	public abstract List<E> getLst();
	
	public abstract List<E> getPage(int pagenum, int pagesize);
	
	public abstract List<E> getLstByParas(int pagenum, int pagesize, String parameters);
	
	public abstract void update(E model);
	
	public abstract void update(Long id, String fields);
	
	public abstract void delete(E model);
	
	public abstract void add(E model);
}
