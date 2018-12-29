
package com.syr.app_config;

/**
 * @author apple
 *
 */
public abstract class AbstractPersistentObject implements PersistentObject {

	private String bId = IdGenerator.createId();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.drtjar.PersistentObject#getBId()
	 */
	@Override
	public String getBId() {
		// TODO Auto-generated method stub
		return bId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.drtjar.PersistentObject#setBId(java.lang.String)
	 */
	@Override
	public void setBId(String id) {
		this.bId = id;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof PersistentObject)) {

			return false;
		}

		PersistentObject other = (PersistentObject) o;

		// if the id is missing, return false
		if (bId == null)
			return false;

		// equivalence by id
		return bId.equals(other.getBId());
	}
}
