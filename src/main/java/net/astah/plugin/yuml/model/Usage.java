package net.astah.plugin.yuml.model;

import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.presentation.IPresentation;

public class Usage extends Relation {
    public Usage(IPresentation presentation, IClass left, IClass right) {
        super(presentation, left, right);
    }

    public String toPlantuml() {
        String left = ClassUtils.getNameLabel(getLeft());
        String right = ClassUtils.getNameLabel(getRight());
        return left + "..|>" + right;
    }
}
