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

import java.util.Map;

import org.thymeleaf.context.ITemplateProcessingContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.engine.IElementStructureHandler;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;

/**
 *
 * @author Daniel Fern&aacute;ndez
 *
 * @since 3.0.0
 *
 */
public abstract class AbstractStandardTargetSelectionTagProcessor extends AbstractStandardAttributeTagProcessor {


    protected AbstractStandardTargetSelectionTagProcessor(final String attrName, final int precedence) {
        super(attrName, precedence);
    }



    protected final void doProcess(
            final ITemplateProcessingContext processingContext,
            final IProcessableElementTag tag,
            final AttributeName attributeName, final String attributeValue,
            final IElementStructureHandler structureHandler) {

        final IStandardExpressionParser expressionParser =
                StandardExpressions.getExpressionParser(processingContext.getConfiguration());

        final IStandardExpression expression = expressionParser.parseExpression(processingContext, attributeValue);

        validateSelectionValue(processingContext, tag, attributeName, attributeValue, expression);

        final Object newSelectionTarget = expression.execute(processingContext);

        final Map<String,Object> additionalLocalVariables =
                computeAdditionalLocalVariables(processingContext, tag, attributeName, attributeValue);
        if (additionalLocalVariables != null && additionalLocalVariables.size() > 0) {
            for (final Map.Entry<String,Object> variableEntry : additionalLocalVariables.entrySet()) {
                structureHandler.setLocalVariable(variableEntry.getKey(), variableEntry.getValue());
            }
        }

        structureHandler.setSelectionTarget(newSelectionTarget);

    }





    protected void validateSelectionValue(
            final ITemplateProcessingContext processingContext,
            final IProcessableElementTag tag,
            final AttributeName attributeName, final String attributeValue,
            final IStandardExpression expression) {
        // Meant for being overridden. Nothing to be done in default implementation.
    }


    protected Map<String,Object> computeAdditionalLocalVariables(
            final ITemplateProcessingContext processingContext,
            final IProcessableElementTag tag,
            final AttributeName attributeName, final String attributeValue) {
        // This method is meant to be overriden. By default, no local variables
        // will be set.
        return null;
    }


}
