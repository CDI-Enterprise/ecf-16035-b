package fr.cdiEnterprise.dao;

import java.util.List;

public interface IRechercheAvancee <T> {
	
	boolean create(T o);
	boolean delete(T o);
	boolean update(T o);
	List<T> findAll();
	T findById(int id);
}


