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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.TypesFactory;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.resource.IResourceFactory;
import org.eclipse.xtext.xbase.compiler.ImportManager;
import org.eclipse.xtext.xbase.typesystem.util.CommonTypeComputationServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.inject.Injector;

import io.sarl.extensions.bspl.lang.compiler.generic.DefaultSarlTargetGeneratorContext;

/** Test for {@link DefaultSarlTargetGeneratorContext}.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 * @since 1.0
 */
@SuppressWarnings("all")
@DisplayName("DefaultSarlTargetGeneratorContext")
public class DefaultSarlTargetGeneratorContextTest {

	private IGeneratorContext delegate;

	private Injector injector;

	private IFileSystemAccess fileSystemAccess;

	private EObject source;

	private ResourceSet resourceSet;

	private IResourceFactory resourceFactory;

	private CommonTypeComputationServices typeServices;

	private DefaultSarlTargetGeneratorContext<Object> test;

	@BeforeEach
	public void setUp() {
		this.delegate = mock(IGeneratorContext.class);
		this.injector = mock(Injector.class);
		this.fileSystemAccess = mock(IFileSystemAccess.class);
		this.source = mock(EObject.class);
		this.resourceSet = mock(ResourceSet.class);
		this.resourceFactory = mock(IResourceFactory.class);
		
		var typeFactory = mock(TypesFactory.class);
		when(typeFactory.createJvmGenericType()).thenAnswer(it -> {
			var type = mock(JvmGenericType.class);
			when(type.getMembers()).thenReturn(new BasicEList<>());
			when(type.getSuperTypes()).thenReturn(new BasicEList<>());
			return type;
		});
		
		this.typeServices = mock(CommonTypeComputationServices.class);
		when(this.typeServices.getTypesFactory()).thenReturn(typeFactory);

		this.test = new DefaultSarlTargetGeneratorContext<>(
				this.delegate, this.injector,
				this.fileSystemAccess, this.source, this.resourceSet,
				this.resourceFactory, this.typeServices);
	}

	@Test
	@DisplayName("getInjector")
	public void getInjector() {
		assertSame(this.injector, this.test.getInjector());
	}

	@Test
	@DisplayName("isPreStage")
	public void isPreStage() {
		assertFalse(this.test.isPreStage());
	}

	@Test
	@DisplayName("getPackage")
	public void getPackage() {
		assertNull(this.test.getPackage());
	}

	@Test
	@DisplayName("forPreStage")
	public void forPreStage() {
		assertTrue(this.test.forPreStage().isPreStage());
	}

	@Test
	@DisplayName("withSource")
	public void withSource() {
		var src = mock(EObject.class);
		assertSame(src, this.test.withSource(src).getSource());
	}

	@Test
	@DisplayName("withNameProvider")
	public void withNameProvider() {
		var provider = mock(Object.class);
		assertSame(provider, this.test.withNameProvider(provider).getNameProvider());
	}

	@Test
	@DisplayName("withPackage")
	public void withPackage() {
		assertEquals("my.pkg", this.test.withPackage("my.pkg").getPackage());
	}

	@Test
	@DisplayName("withTypeName")
	public void withTypeName() {
		assertEquals("my.pkg.type", this.test.withTypeName("my.pkg.type").getEnclosingTypeName());
	}

	@Test
	@DisplayName("withPackageVisibility(true)")
	public void withPackageVisibility_true() {
		assertTrue(this.test.withPackageVisibility(true).isPackageVisibility());
	}

	@Test
	@DisplayName("withPackageVisibility(false)")
	public void withPackageVisibility_false() {
		assertFalse(this.test.withPackageVisibility(false).isPackageVisibility());
	}

	@Test
	@DisplayName("newImportManager(p, b)")
	public void newImportManager_p_b() {
		var manager = this.test.newImportManager("my.pkg", "basename");
		assertNotNull(manager);
	}

	@Test
	@DisplayName("newImportManager(p, null)")
	public void newImportManager_p_null() {
		var manager = this.test.newImportManager("my.pkg", null);
		assertNotNull(manager);
	}

	@Test
	@DisplayName("newImportManager(null, b)")
	public void newImportManager_null_b() {
		var manager = this.test.newImportManager(null, "basename");
		assertNotNull(manager);
	}

	@Test
	@DisplayName("newImportManager(null, null)")
	public void newImportManager_null_null() {
		var manager = this.test.newImportManager(null, null);
		assertNotNull(manager);
	}

	@Test
	@DisplayName("newAppendableContent(null)")
	public void newAppendableContent_null() {
		var content = this.test.newAppendableContent(null);
		assertNotNull(content);
	}

	@Test
	@DisplayName("newAppendableContent(i)")
	public void newAppendableContent_i() {
		var content = this.test.newAppendableContent(mock(ImportManager.class));
		assertNotNull(content);
	}

	@Test
	@DisplayName("getNameProvider")
	public void getNameProvider() {
		assertNull(this.test.getNameProvider());
	}

	@Test
	@DisplayName("getEnclosingTypeName")
	public void getEnclosingTypeName() {
		assertNull(this.test.getEnclosingTypeName());
	}

	@Test
	@DisplayName("getSource")
	public void getSource() {
		assertSame(this.source, this.test.getSource());
	}

	@Test
	@DisplayName("isPackageVisibility")
	public void isPackageVisibility() {
		assertFalse(this.test.isPackageVisibility());
	}

	@Test
	@DisplayName("getResourceSet")
	public void getResourceSet() {
		assertSame(this.resourceSet, this.test.getResourceSet());
	}

}
