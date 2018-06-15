package jp.co.axiz.web.entity;

public class SelectForm {
	private Integer id;

	private String name;

	private String tel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer _id) {
		this.id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String _name) {
		this.name = _name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String _tel) {
		this.tel = _tel;
	}

	public boolean isEmpty() {
		return id == null
				&& (name == null || name.isEmpty())
				&& (tel == null || tel.isEmpty());
	}
}
