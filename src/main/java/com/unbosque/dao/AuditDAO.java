package com.unbosque.dao;

import java.util.List;

import com.unbosque.entity.Audit;

public interface AuditDAO {

	public List<Audit> getAudits();
	
	public void update(Audit audit);
	
}
