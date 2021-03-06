package net.astah.plugin.yuml.model.clazz;


import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.presentation.IPresentation;
import net.astah.plugin.yuml.model.ClassUtils;
import net.astah.plugin.yuml.model.Relation;

public class Dependency extends Relation {
    public Dependency(IPresentation presentation, IClass left, IClass right) {
        super(presentation, left, right);
    }

    public String toPlantuml() {
        String left = ClassUtils.getNameLabel(getLeft());
        String right = ClassUtils.getNameLabel(getRight());
        return left + "..>" + right;
    }
}
