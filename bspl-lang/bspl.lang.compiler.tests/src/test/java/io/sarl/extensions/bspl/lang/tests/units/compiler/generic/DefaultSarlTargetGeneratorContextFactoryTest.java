/*
 * $Id$
 *
 * SARL is an general-purpose agent programming language.
 * More details on http://www.sarl.io
 *
 * Copyright (C) 2014-2025 SARL.io, the Original Authors and Main Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.sarl.extensions.bspl.lang.tests.units.compiler.generic;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.sarl.extensions.bspl.lang.compiler.generic.DefaultSarlTargetGeneratorContextFactory;

/** Test for {@link DefaultSarlTargetGeneratorContextFactory}.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 * @since 1.0
 */
@SuppressWarnings("all")
@DisplayName("DefaultSarlTargetGeneratorContextFactory")
public class DefaultSarlTargetGeneratorContextFactoryTest {

	private DefaultSarlTargetGeneratorContextFactory test;

	@BeforeEach
	public void setUp() {
		this.test = new DefaultSarlTargetGeneratorContextFactory();
	}

	@Test
	@DisplayName("createContext(fsa, rs)")
	public void createContext_fsa_rs() {
		var context = this.test.createContext(mock(IFileSystemAccess.class), mock(ResourceSet.class));
		assertNotNull(context);
	}

	@Test
	@DisplayName("createContext(fsa, gc, rs)")
	public void createContext_fsa_gc_rs() {
		var context = this.test.createContext(mock(IFileSystemAccess2.class), mock(IGeneratorContext.class), mock(ResourceSet.class));
		assertNotNull(context);
	}

}
