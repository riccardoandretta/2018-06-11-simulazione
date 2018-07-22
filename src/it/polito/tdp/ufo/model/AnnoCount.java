package it.polito.tdp.ufo.model;

public class AnnoCount {

	
	private int anno;
	private int numeroAvvistamenti;
	public AnnoCount(int anno, int numeroAvvistamenti) {
		super();
		this.anno = anno;
		this.numeroAvvistamenti = numeroAvvistamenti;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public int getNumeroAvvistamenti() {
		return numeroAvvistamenti;
	}
	public void setNumeroAvvistamenti(int numeroAvvistamenti) {
		this.numeroAvvistamenti = numeroAvvistamenti;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anno;
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
		AnnoCount other = (AnnoCount) obj;
		if (anno != other.anno)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return String.format("%s (%s)", anno, numeroAvvistamenti);
	}
	
	
}
