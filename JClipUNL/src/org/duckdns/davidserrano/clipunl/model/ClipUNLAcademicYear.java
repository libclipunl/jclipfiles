package org.duckdns.davidserrano.clipunl.model;

import java.io.Serializable;
import java.util.List;

public interface ClipUNLAcademicYear extends Serializable {
	abstract public String getYear();
	
	abstract public String getURL();
	
	abstract public String getDescription();

	abstract public List<ClipUNLCurricularUnit> getCurricularUnits();
}
