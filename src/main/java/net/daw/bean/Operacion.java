package net.daw.bean;

public class Operacion {

	private Double num1, num2;
	
	private String op, res;
	
	

	public Operacion(Double num1, Double num2, String res, String op) {

		this.num1 = num1;
		this.num2 = num2;
		this.res = res;
		this.op = op;
	}

	public Double getNum1() {
		return num1;
	}

	public void setNum1(Double num1) {
		this.num1 = num1;
	}

	public Double getNum2() {
		return num2;
	}

	public void setNum2(Double num2) {
		this.num2 = num2;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}
	
	
	
}
