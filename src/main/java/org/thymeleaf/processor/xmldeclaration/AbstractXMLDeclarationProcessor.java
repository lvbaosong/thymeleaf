/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2014, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.thymeleaf.processor.xmldeclaration;

import org.thymeleaf.context.ITemplateProcessingContext;
import org.thymeleaf.engine.IXMLDeclarationStructureHandler;
import org.thymeleaf.exceptions.TemplateProcessingException;
import org.thymeleaf.model.IXMLDeclaration;
import org.thymeleaf.processor.AbstractProcessor;
import org.thymeleaf.templatemode.TemplateMode;

/**
 *
 * @author Daniel Fern&aacute;ndez
 *
 * @since 3.0.0
 *
 */
public abstract class AbstractXMLDeclarationProcessor
        extends AbstractProcessor implements IXMLDeclarationProcessor {



    public AbstractXMLDeclarationProcessor(final TemplateMode templateMode, final int precedence) {
        super(templateMode, precedence);
    }


    public final void process(final ITemplateProcessingContext processingContext, final IXMLDeclaration xmlDeclaration,
                        final IXMLDeclarationStructureHandler structureHandler) {

        try {

            doProcess(processingContext, xmlDeclaration, structureHandler);

        } catch (final TemplateProcessingException e) {
            if (!e.hasTemplateName()) {
                e.setTemplateName(xmlDeclaration.getTemplateName());
            }
            if (!e.hasLineAndCol()) {
                e.setLineAndCol(xmlDeclaration.getLine(), xmlDeclaration.getCol());
            }
            throw e;
        } catch (final Exception e) {
            throw new TemplateProcessingException(
                    "Error during execution of processor '" + this.getClass().getName() + "'",
                    xmlDeclaration.getTemplateName(), xmlDeclaration.getLine(), xmlDeclaration.getCol(), e);
        }

    }


    protected abstract void doProcess(final ITemplateProcessingContext processingContext, final IXMLDeclaration xmlDeclaration,
                        final IXMLDeclarationStructureHandler structureHandler);


}
