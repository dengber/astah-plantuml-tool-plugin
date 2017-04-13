package net.astah.plugin.yuml.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.change_vision.jude.api.inf.model.IAttribute;
import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.model.IOperation;
import com.change_vision.jude.api.inf.model.IParameter;
import com.change_vision.jude.api.inf.model.IComment;
import com.change_vision.jude.api.inf.presentation.IPresentation;

public class Clazz {
	protected IPresentation presentation;
	protected IClass clazz;
	
	public Clazz(IPresentation presentation, IClass clazz) {
		this.presentation = presentation;
		this.clazz = clazz;
	}

	public String toYuml() {
		String scope = "";
		String isStatic = "";
		String isAbstract = "";
		String initialValue = "";
		String returnString = ClassUtils.getNameLabel(clazz);
		returnString = "class " + returnString + " {\n";
		IAttribute[] atributes = clazz.getAttributes();

		for (int i = 0; i < atributes.length; i++) {
			if (atributes[i].getName().isEmpty()) {
				break;
			}
			if (atributes[i].isPrivateVisibility()) {
				scope = "-";
			}
			if (atributes[i].isPublicVisibility()) {
				scope = "+";
			}
			if (atributes[i].isStatic()) {
				isStatic = " {static} ";
			}
			else {
				isStatic = "";
			}
			if (atributes[i].getInitialValue() == "") {
				initialValue = "";
			}
			else {
				initialValue = " = " + atributes[i].getInitialValue();
			}
			returnString += scope + isStatic + " " + atributes[i].getName() + " : " + atributes[i].getTypeExpression() + "\n";
		}

		IOperation[] operations = clazz.getOperations();
		for (int i = 0; i < operations.length; i++) {
			if (operations[i].isPrivateVisibility()) {
				scope = "-";
			}
			if (operations[i].isPublicVisibility()) {
				scope = "+";
			}
			if (operations[i].isAbstract()) {
				isAbstract = " {abstract} ";
			}
			else {
				isAbstract = "";
			}
			if (operations[i].isStatic()) {
				isStatic = " {static} ";
			}
			else {
				isStatic = "";
			}
			IParameter[] parameters = operations[i].getParameters();
			String param = "";
			if (parameters.length > 0) {
				for (int j = 0; j < (parameters.length - 1); j++) {
					param += parameters[j].getName() + " : " + parameters[j].getTypeExpression() + ", ";
				}
				param += parameters[parameters.length - 1].getName() + " : " + parameters[parameters.length - 1].getTypeExpression();
				returnString += scope + isStatic + isAbstract + " " + operations[i].getName() + "(" + param + ")" + " : " + operations[i].getReturnTypeExpression() + "\n";
			}
			else {
				returnString += scope + isStatic + isAbstract + operations[i].getReturnTypeExpression() + " " + operations[i].getName() + "()\n";	
			}
		}
		returnString += "}\n";
		
		IComment[] comments = clazz.getComments();
		for (int i = 0; i < comments.length; i++) {
			String commentName = ClassUtils.getNameLabel(clazz) + "_comment_" + i;
			returnString += "note as " + commentName + "\n" + comments[i].getBody() + "\nend note\n";
			returnString += commentName + " --> " + ClassUtils.getNameLabel(clazz) + "\n";
		}
		
		return returnString;
	}
	
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
		
		Clazz rhs = (Clazz) obj;
		return new EqualsBuilder()
					.appendSuper(super.equals(obj))
					.append(presentation, rhs.presentation)
					.append(clazz, rhs.clazz)
					.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
					.appendSuper(super.hashCode())
					.append(presentation)
					.append(clazz)
					.toHashCode();
	}
}
