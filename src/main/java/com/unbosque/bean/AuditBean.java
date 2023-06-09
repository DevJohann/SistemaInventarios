package com.unbosque.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.unbosque.dao.AuditDAO;
import com.unbosque.dao.impl.AuditDAOImpl;
import com.unbosque.entity.Audit;

@ManagedBean
@SessionScoped
public class AuditBean {
	
	private DataModel listaAuditorias;
	private Audit audit = new Audit();

	public List<Audit> getAudits() {
		AuditDAOImpl auditDAO = new AuditDAOImpl();
		List<Audit> audit = auditDAO.getAudits();
		return audit;
	}
	
	public DataModel getListarAuditorias() {
		List<Audit> lista = new AuditDAOImpl().getAudits();
		listaAuditorias = new ListDataModel(lista);
		return listaAuditorias;
	}
	
	public String prepararUpdate(){
		audit = (Audit)(listaAuditorias.getRowData());
		return "actualizarAuditoria";
	}
	
	public String realizarUpdate() {
		AuditDAO dao = new AuditDAOImpl();
		dao.update(audit);
		return "listarAuditorias";
	}
	
	public Audit getAudit() {
		return audit;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}
	
}
