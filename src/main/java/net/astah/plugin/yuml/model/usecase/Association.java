package net.astah.plugin.yuml.model.usecase;


import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.model.IUseCase;
import com.change_vision.jude.api.inf.presentation.IPresentation;
import net.astah.plugin.yuml.model.ClassUtils;
import net.astah.plugin.yuml.model.Relation;

public class Association extends Relation {
    public Association(IPresentation presentation, IClass left, IClass right) {
        super(presentation, left, right);
    }

    public String toPlantuml() {
        return getLabel(getLeft()) + "-" + getLabel(getRight());
    }

    private String getLabel(IClass classOrUsecase) {
        String label = "";
        if (classOrUsecase instanceof IUseCase) {
            label = "(" + ClassUtils.getNameLabel(classOrUsecase) + ")";
        } else {
            label = "[" + ClassUtils.getNameLabel(classOrUsecase) + "]";
        }
        return label;
    }
}
