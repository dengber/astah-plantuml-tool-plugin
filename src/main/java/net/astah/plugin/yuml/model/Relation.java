package net.astah.plugin.yuml.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.presentation.IPresentation;

public abstract class Relation {
	private IPresentation presentation;
	private IClass left;
	private IClass right;
	
	public Relation(IPresentation presentation, IClass left, IClass right) {
		this.presentation = presentation;
		this.left = left;
		this.right = right;
	}
	
	public IPresentation getPresentation() {
		return presentation;
	}
	
	public IClass getLeft() {
		return left;
	}

	public IClass getRight() {
		return right;
	}
	
	public abstract String toPlantuml();
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		
		Relation rhs = (Relation) obj;
		return new EqualsBuilder()
					.appendSuper(super.equals(obj))
					.append(presentation, rhs.presentation)
					.append(left, rhs.left)
					.append(right, rhs.right)
					.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
					.appendSuper(super.hashCode())
					.append(presentation)
					.append(left)
					.append(right)
					.toHashCode();
	}
}
