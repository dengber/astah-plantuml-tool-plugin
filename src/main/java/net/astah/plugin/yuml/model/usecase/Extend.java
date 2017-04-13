package net.astah.plugin.yuml.model.usecase;


import com.change_vision.jude.api.inf.model.IUseCase;
import com.change_vision.jude.api.inf.presentation.IPresentation;
import net.astah.plugin.yuml.model.ClassUtils;
import net.astah.plugin.yuml.model.Relation;

public class Extend extends Relation {
    public Extend(IPresentation presentation, IUseCase left, IUseCase right) {
        super(presentation, left, right);
    }

    public String toPlantuml() {
        String left = ClassUtils.getNameLabel(getLeft());
        String right = ClassUtils.getNameLabel(getRight());
        return "(" + left + ")<(" + right + ")";
    }
}
