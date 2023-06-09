package com.unbosque.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.unbosque.dao.impl.AuditDAOImpl;
import com.unbosque.entity.Audit;

@ManagedBean
@SessionScoped
public class AuditBean {

	public List<Audit> getAudits() {
		AuditDAOImpl auditDAO = new AuditDAOImpl();
		List<Audit> audits = auditDAO.getAudits();
		return audits;

	}
}
