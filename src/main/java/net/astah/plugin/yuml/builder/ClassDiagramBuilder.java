package net.astah.plugin.yuml.builder;

import com.change_vision.jude.api.inf.model.*;
import com.change_vision.jude.api.inf.presentation.ILinkPresentation;
import com.change_vision.jude.api.inf.presentation.IPresentation;
import net.astah.plugin.yuml.draw.Direction;
import net.astah.plugin.yuml.draw.DrawType;
import net.astah.plugin.yuml.draw.Size;
import net.astah.plugin.yuml.draw.UrlType;
import net.astah.plugin.yuml.model.Clazz;
import net.astah.plugin.yuml.model.Relation;
import net.astah.plugin.yuml.model.clazz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ClassDiagramBuilder extends DiagramBuilderBase {
    private static final Logger logger = LoggerFactory.getLogger(ClassDiagramBuilder.class);
    private static final String DIAGRAM_TYPE = "/class/";

    public ClassDiagramBuilder(IClassDiagram diagram) {
        this(diagram, UrlType.PNG, DrawType.PLAIN, Direction.TOP_DOWN, Size.SMALL);
    }

    public ClassDiagramBuilder(IClassDiagram diagram, UrlType urlType) {
        this(diagram, urlType, DrawType.PLAIN, Direction.TOP_DOWN, Size.SMALL);
    }

    public ClassDiagramBuilder(IClassDiagram diagram, UrlType urlType, DrawType drawType) {
        this(diagram, urlType, drawType, Direction.TOP_DOWN, Size.SMALL);
    }

    public ClassDiagramBuilder(IClassDiagram diagram, UrlType urlType, DrawType drawType, Direction direction) {
        this(diagram, urlType, drawType, direction, Size.SMALL);
    }

    public ClassDiagramBuilder(IClassDiagram diagram, UrlType urlType, DrawType drawType, Direction direction, Size size) {
        super(diagram, urlType, drawType, direction, size);
    }

    public String toPlantuml() {
        StringBuilder yumlBuilder = new StringBuilder();
        yumlBuilder.append("@startuml\n");


        List<Clazz> classes = extractClassesOn(diagram);
        List<Relation> relations = extractRelationsOn(diagram);

        for (int i = 0; i < relations.size(); i++) {
            Relation relation = relations.get(i);
            classes.remove(relation.getLeft());
            classes.remove(relation.getRight());
            yumlBuilder.append(relation.toPlantuml());
            yumlBuilder.append("\n");

            logger.debug(relation.toPlantuml());
        }

        for (int i = 0; i < classes.size(); i++) {
            Clazz clazz = classes.get(i);
            yumlBuilder.append(clazz.toYuml());

            logger.debug(clazz.toYuml());
        }

        yumlBuilder.append("@enduml");
        return yumlBuilder.toString();
    }

    private List<Clazz> extractClassesOn(IDiagram diagram) {
        IPresentation[] presentations = getPresentations(diagram);

        List<Clazz> classes = new ArrayList<Clazz>();
        for (IPresentation presentation : presentations) {
            IElement presentationModel = presentation.getModel();
            if (presentationModel instanceof IClass) {
                classes.add(new Clazz(presentation, (IClass) presentationModel));
            }
        }
        return classes;
    }

    private List<Relation> extractRelationsOn(IDiagram diagram) {
        IPresentation[] presentations = getPresentations(diagram);

        List<Relation> relations = new ArrayList<Relation>();
        for (IPresentation presentation : presentations) {
            IElement presentationModel = presentation.getModel();
            if (presentationModel instanceof IAssociation) {
                IPresentation sourceEnd = ((ILinkPresentation) presentation).getSourceEnd();
                IPresentation targetEnd = ((ILinkPresentation) presentation).getTargetEnd();
                IElement source = sourceEnd.getModel();
                IElement target = targetEnd.getModel();
                if (source instanceof IClass && target instanceof IClass) {
                    relations.add(new Association(presentation, (IClass) source, (IClass) target));
                }
            } else if (presentationModel instanceof IGeneralization) {
                IClass superType = ((IGeneralization) presentationModel).getSuperType();
                IClass subType = ((IGeneralization) presentationModel).getSubType();
                relations.add(new Generalization(presentation, (IClass) superType, (IClass) subType));
            } else if (presentationModel instanceof IDependency) {
                INamedElement client = ((IDependency) presentationModel).getClient();
                INamedElement supplier = ((IDependency) presentationModel).getSupplier();
                if (client instanceof IClass && supplier instanceof IClass) {
                    relations.add(new Dependency(presentation, (IClass) client, (IClass) supplier));
                }
            } else if (presentationModel instanceof IUsage) {
                INamedElement client = ((IUsage) presentationModel).getClient();
                INamedElement supplier = ((IUsage) presentationModel).getSupplier();
                if (client instanceof IClass && supplier instanceof IClass) {
                    relations.add(new Usage(presentation, (IClass) client, (IClass) supplier));
                }
            } else if (presentationModel instanceof IRealization) {
                INamedElement client = ((IRealization) presentationModel).getClient();
                INamedElement supplier = ((IRealization) presentationModel).getSupplier();
                if (client instanceof IClass && supplier instanceof IClass) {
                    relations.add(new Realization(presentation, (IClass) client, (IClass) supplier));
                }
            } else if (presentationModel instanceof ITemplateBinding) {
                IClass boundElement = ((ITemplateBinding) presentationModel).getBoundElement();
                IClass template = ((ITemplateBinding) presentationModel).getTemplate();
                if (boundElement instanceof IClass && template instanceof IClass) {
                    relations.add(new TemplateBinding(presentation, (IClass) boundElement, (IClass) template));
                }
            }
        }
        return relations;
    }
}
