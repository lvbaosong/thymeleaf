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
package org.thymeleaf.standard.processor;

import org.thymeleaf.context.ITemplateProcessingContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.engine.IElementStructureHandler;
import org.thymeleaf.exceptions.TemplateProcessingException;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeMatchingHTMLElementTagProcessor;
import org.unbescape.html.HtmlEscape;

/**
 *
 * @author Daniel Fern&aacute;ndez
 *
 * @since 3.0.0
 *
 */
public abstract class AbstractStandardAttributeTagProcessor extends AbstractAttributeMatchingHTMLElementTagProcessor {



    protected AbstractStandardAttributeTagProcessor(final String attrName, final int precedence) {
        super(attrName, precedence);
    }



    protected final void doProcess(
            final ITemplateProcessingContext processingContext,
            final IProcessableElementTag tag,
            final IElementStructureHandler structureHandler) {

        AttributeName attributeName = null;
        try {

            attributeName = getMatchingAttributeName().getMatchingAttributeName();
            final String attributeValue = HtmlEscape.unescapeHtml(tag.getAttributes().getValue(attributeName));

            doProcess(processingContext, tag, attributeName, attributeValue, structureHandler);

            tag.getAttributes().removeAttribute(attributeName);

        } catch (final TemplateProcessingException e) {
            // This is a nice moment to check whether the execution raised an error and, if so, add location information
            if (!e.hasTemplateName()) {
                e.setTemplateName(tag.getTemplateName());
            }
            if (!e.hasLineAndCol()) {
                if (attributeName != null) {
                    final int line = tag.getAttributes().getLine(attributeName);
                    final int col = tag.getAttributes().getCol(attributeName);
                    e.setLineAndCol(line, col);
                } else {
                    // We don't have info about the specific attribute provoking the error
                    e.setLineAndCol(tag.getLine(), tag.getCol());
                }
            }
            throw e;
        } catch (final Exception e) {
            throw new TemplateProcessingException(
                    "Error during execution of processor '" + this.getClass().getName() + "'",
                    tag.getTemplateName(), tag.getLine(), tag.getCol(), e);
        }

    }


    protected abstract void doProcess(
            final ITemplateProcessingContext processingContext,
            final IProcessableElementTag tag,
            final AttributeName attributeName,
            final String attributeValue,
            final IElementStructureHandler structureHandler);


}
