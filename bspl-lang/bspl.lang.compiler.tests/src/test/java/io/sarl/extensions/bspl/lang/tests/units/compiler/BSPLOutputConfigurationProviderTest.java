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

package io.sarl.extensions.bspl.lang.tests.units.compiler;

import static io.sarl.tests.api.tools.TestAssertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.common.types.util.TypeReferences;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.xbase.typesystem.util.CommonTypeComputationServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import io.sarl.api.naming.name.ScopedDataName;
import io.sarl.api.workingmemory.WorkingMemory;
import io.sarl.extensions.bspl.api.protocol.events.ProtocolEvent;
import io.sarl.extensions.bspl.api.protocol.impl.AbstractProtocolSpaceSpecification;
import io.sarl.extensions.bspl.api.protocol.impl.ProtocolBehavior;
import io.sarl.extensions.bspl.api.protocol.impl.ProtocolCapacity;
import io.sarl.extensions.bspl.api.protocol.impl.ProtocolMessage;
import io.sarl.extensions.bspl.api.protocol.impl.ProtocolRole;
import io.sarl.extensions.bspl.api.protocol.impl.ProtocolSkill;
import io.sarl.extensions.bspl.api.protocol.impl.ProtocolSpace;
import io.sarl.extensions.bspl.lang.BSPLConfig;
import io.sarl.extensions.bspl.lang.bspl.BsplProtocol;
import io.sarl.extensions.bspl.lang.bspl.BsplProtocolRole;
import io.sarl.extensions.bspl.lang.compiler.BSPLOutputConfigurationProvider;
import io.sarl.extensions.bspl.lang.compiler.DefaultProtocolNames;

/** Test for {@link BSPLOutputConfigurationProvider}.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 * @since 1.0
 */
@SuppressWarnings("all")
@DisplayName("BSPLOutputConfigurationProvider")
public class BSPLOutputConfigurationProviderTest {

	private BSPLOutputConfigurationProvider test;

	@BeforeEach
	public void setUp() {
		this.test = new BSPLOutputConfigurationProvider();
	}

	@Test
	@DisplayName("getOutputConfigurations")
	public void getOutputConfigurations() {
		var actual = this.test.getOutputConfigurations();
		
		assertNotNull(actual);
		assertEquals(2, actual.size());

		var tab = new OutputConfiguration[2];
		actual.toArray(tab);
		
		final int standard;
		final int test;
		if (IFileSystemAccess.DEFAULT_OUTPUT.equals(tab[0].getName())) {
			standard = 0;
			test = 1;
		} else {
			standard = 1;
			test = 0;
		}

		var config0 = tab[standard];
		assertEquals(IFileSystemAccess.DEFAULT_OUTPUT, config0.getName());
		assertEquals(BSPLConfig.FOLDER_SOURCE_GENERATED, config0.getOutputDirectory());
		assertTrue(config0.isOverrideExistingResources());
		assertTrue(config0.isCreateOutputDirectory());
		assertFalse(config0.isCanClearOutputDirectory());
		assertTrue(config0.isCleanUpDerivedResources());
		assertTrue(config0.isSetDerivedProperty());
		assertFalse(config0.isKeepLocalHistory());

		var config1 = tab[test];
		assertEquals(BSPLConfig.TEST_OUTPUT_CONFIGURATION, config1.getName());
		assertEquals(BSPLConfig.FOLDER_TEST_SOURCE_GENERATED, config1.getOutputDirectory());
		assertTrue(config1.isOverrideExistingResources());
		assertTrue(config1.isCreateOutputDirectory());
		assertFalse(config1.isCanClearOutputDirectory());
		assertTrue(config1.isCleanUpDerivedResources());
		assertTrue(config1.isSetDerivedProperty());
		assertFalse(config1.isKeepLocalHistory());
	}

}
