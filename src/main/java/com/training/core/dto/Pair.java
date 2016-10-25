package com.training.core.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Pair
 * @author zeng yuanjin
 * @date 2012-11-1 上午10:53:22
 * @param <L>
 * @param <R>
 */
public class Pair<L, R> {
	
	private final L left;
	private R right;

	static public<U,V> Pair<U,V> create(U u, V v) {
		return new Pair<U,V>(u,v);
	}
	/**
	 * * Constructor. Accepts two objects and constructs a pair. *Neither can be
	 * null. * * @param left * The left object * @param right * The right object
	 */
	public Pair(L left, R right) {
		//Validate.notNull(left);
		this.left = left;
		//Validate.notNull(right);
		this.right = right;
	}

	/** * Returns the left item of the pair. * * @return the left item */
	public L getLeft() {
		return left;
	}

	/** * Returns the right item of the pair. * * @return the left item */
	public R getRight() {
		return right;
	}

	public void setRight(R right) {
		this.right = right;
	}
	/**
	 * * Returns true iff the the two pairs are equal. * * @param other * some
	 * other pair * @return true if equal
	 */
	@Override
	public boolean equals(Object other) {
		if (other == this)
			return true;
		if (!(other instanceof Pair))
			return false;
		Pair<?, ?> that = (Pair<?, ?>) other;
		return new EqualsBuilder().append(this.left, that.left).append(
				this.right, that.right).isEquals();
	}

	/** * Returns a hash code for this pair * * @return the hash code */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.left).append(this.right)
				.toHashCode();
	}

	/** * Returns the string form of this pair: "(left,right)" */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		sb.append(null==left?"":left.toString());
		sb.append(",");
		sb.append(null==right?"":right.toString());
		sb.append(")");
		return sb.toString();
	}
}