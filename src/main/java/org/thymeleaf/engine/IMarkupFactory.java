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
package org.thymeleaf.engine;

import org.thymeleaf.model.IAutoCloseElementTag;
import org.thymeleaf.model.IAutoOpenElementTag;
import org.thymeleaf.model.ICDATASection;
import org.thymeleaf.model.ICloseElementTag;
import org.thymeleaf.model.IComment;
import org.thymeleaf.model.IDocType;
import org.thymeleaf.model.IOpenElementTag;
import org.thymeleaf.model.IProcessingInstruction;
import org.thymeleaf.model.IStandaloneElementTag;
import org.thymeleaf.model.IText;
import org.thymeleaf.model.IUnmatchedCloseElementTag;
import org.thymeleaf.model.IXMLDeclaration;

/**
 *
 * @author Daniel Fern&aacute;ndez
 * @since 3.0.0
 * 
 */
public interface IMarkupFactory {


    public Markup createMarkup();

    public IMarkup createMarkup(final String markup);

    public ICDATASection createCDATASection(final String content);

    public IComment createComment(final String content);

    public IDocType createHTML5DocType();
    public IDocType createDocType(final String publicId, final String systemId);
    public IDocType createDocType(
            final String keyword,
            final String elementName,
            final String type,
            final String publicId,
            final String systemId,
            final String internalSubset);

    public IProcessingInstruction createProcessingInstruction(final String target, final String content);

    public IText createText(final String text);

    public IXMLDeclaration createXMLDeclaration(final String version, final String encoding, final String standalone);


    public IStandaloneElementTag createStandaloneElementTag(final String elementName, final boolean minimized);
    public IOpenElementTag createOpenElementTag(final String elementName);
    public IAutoOpenElementTag createAutoOpenElementTag(final String elementName);
    public ICloseElementTag createCloseElementTag(final String elementName);
    public IAutoCloseElementTag createAutoCloseElementTag(final String elementName);
    public IUnmatchedCloseElementTag createUnmatchedCloseElementTag(final String elementName);


}
