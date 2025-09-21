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

import static io.sarl.tests.api.tools.TestAssertions.assertTypeReferenceIdentifier;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.common.types.util.TypeReferences;
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
import io.sarl.extensions.bspl.lang.bspl.BsplProtocol;
import io.sarl.extensions.bspl.lang.bspl.BsplProtocolRole;
import io.sarl.extensions.bspl.lang.compiler.DefaultProtocolNames;

/** Test for {@link DefaultProtocolNames}.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 * @since 1.0
 */
@SuppressWarnings("all")
@DisplayName("DefaultProtocolNames")
public class DefaultProtocolNamesTest {

	@DisplayName("Get types")
	@Nested
	public class GetTypesTest {

		private DefaultProtocolNames test;
	
		@BeforeEach
		public void setUp() {
			this.test = new DefaultProtocolNames();
		}
	
		@Test
		@DisplayName("getProtocolRoleGenericInterface")
		public void getProtocolRoleGenericInterface() {
			assertEquals(ProtocolRole.class, this.test.getProtocolRoleGenericInterface());
		}
	
		@Test
		@DisplayName("getProtocolCapacityGenericInterface")
		public void getProtocolCapacityGenericInterface() {
			assertEquals(ProtocolCapacity.class, this.test.getProtocolCapacityGenericInterface());
		}
	
		@Test
		@DisplayName("getProtocolSkillGenericInterface")
		public void getProtocolSkillGenericInterface() {
			assertEquals(ProtocolSkill.class, this.test.getProtocolSkillGenericInterface());
		}
	
		@Test
		@DisplayName("getProtocolEventGenericInterface")
		public void getProtocolEventGenericInterface() {
			assertEquals(ProtocolEvent.class, this.test.getProtocolEventGenericInterface());
		}
	
		@Test
		@DisplayName("getProtocolMessageGenericInterface")
		public void getProtocolMessageGenericInterface() {
			assertEquals(ProtocolMessage.class, this.test.getProtocolMessageGenericInterface());
		}
	
		@Test
		@DisplayName("getProtocolSpaceGenericInterface")
		public void getProtocolSpaceGenericInterface() {
			assertEquals(ProtocolSpace.class, this.test.getProtocolSpaceGenericInterface());
		}
	
		@Test
		@DisplayName("getProtocolSpaceSpecificationGenericInterface")
		public void getProtocolSpaceSpecificationGenericInterface() {
			assertEquals(AbstractProtocolSpaceSpecification.class, this.test.getProtocolSpaceSpecificationGenericInterface());
		}
	
		@Test
		@DisplayName("getProtocolBehaviorGenericInterface")
		public void getProtocolBehaviorGenericInterface() {
			assertEquals(ProtocolBehavior.class, this.test.getProtocolBehaviorGenericInterface());
		}
	
		@Test
		@DisplayName("getKnowledgeNameGenericInterface")
		public void getKnowledgeNameGenericInterface() {
			assertEquals(ScopedDataName.class, this.test.getKnowledgeNameGenericInterface());
		}
	
		@Test
		@DisplayName("getAgentWorkingMemoryGenericInterface")
		public void getAgentWorkingMemoryGenericInterface() {
			assertEquals(WorkingMemory.class, this.test.getAgentWorkingMemoryGenericInterface());
		}

	}
	
	@DisplayName("getProtocolRoleEnumerationName")
	@Nested
	public class GetProtocolRoleEnumerationNameTest {

		private DefaultProtocolNames test;
	
		@BeforeEach
		public void setUp() {
			this.test = new DefaultProtocolNames();
		}
	
		@Test
		@DisplayName("(null)")
		public void getProtocolRoleEnumerationName_null() {
			assertEquals("$Anonymous$Role", this.test.getProtocolRoleEnumerationName(null));
		}
	
		@Test
		@DisplayName("(\"\")")
		public void getProtocolRoleEnumerationName_empty() {
			assertEquals("$Anonymous$Role", this.test.getProtocolRoleEnumerationName(""));
		}
	
		@Test
		@DisplayName("(\"  \")")
		public void getProtocolRoleEnumerationName_space() {
			assertEquals("$Anonymous$Role", this.test.getProtocolRoleEnumerationName(""));
		}
	
		@Test
		@DisplayName("(\"abc\")")
		public void getProtocolRoleEnumerationName_abc() {
			assertEquals("abcRole", this.test.getProtocolRoleEnumerationName("abc"));
		}
	
		@Test
		@DisplayName("(\"Abc\")")
		public void getProtocolRoleEnumerationName_Abc() {
			assertEquals("AbcRole", this.test.getProtocolRoleEnumerationName("Abc"));
		}

	}
	
	@DisplayName("getProtocolRoleEnumeration")
	@Nested
	public class GetProtocolRoleEnumerationTest {

		private TypeReferences typeReferences;

		private CommonTypeComputationServices typeServices;

		private DefaultProtocolNames test;
	
		@BeforeEach
		public void setUp() {
			this.typeReferences = mock(TypeReferences.class);

			this.typeServices = mock(CommonTypeComputationServices.class);
			when(this.typeServices.getTypeReferences()).thenAnswer(it -> this.typeReferences);

			this.test = new DefaultProtocolNames();
			this.test.setCommonTypeComputationServices(this.typeServices);
		}

		private void declare(String qualifiedName) {
			when(this.typeReferences.findDeclaredType(anyString(), any())).thenAnswer(it -> {
				if (qualifiedName.equals(it.getArgument(0))) {
					var type = mock(JvmType.class);
					when(type.getQualifiedName()).thenReturn(qualifiedName);
					when(type.getIdentifier()).thenReturn(qualifiedName);
					when(type.eClass()).thenReturn(TypesPackage.Literals.JVM_ENUMERATION_TYPE);
					return type;
				}
				return null;
			});
		}

		private BsplProtocol createProtocol() {
			var resource = mock(Resource.class);
			
			var protocol = mock(BsplProtocol.class);
			when(protocol.getName()).thenReturn("TestProto");
			when(protocol.eResource()).thenReturn(resource);

			return protocol;
		}
		
		private void doTest(String qualifiedName, String packageName, BsplProtocol protocol) {
			declare(qualifiedName);
			var actual = this.test.getProtocolRoleEnumeration(packageName, protocol);
			assertNotNull(actual);
			assertEquals(actual.getIdentifier(), qualifiedName);
		}
	
		@Test
		@DisplayName("(null, null)")
		public void getProtocolRoleEnumeration_null_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$Role", null, null);
			});
		}

		@Test
		@DisplayName("(null, p)")
		public void getProtocolRoleEnumeration_null_p() {
			doTest("TestProtoRole", null, createProtocol());
		}
		
		@Test
		@DisplayName("(\"\", null)")
		public void getProtocolRoleEnumeration_empty_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$Role", "", null);
			});
		}

		@Test
		@DisplayName("(\"\", p)")
		public void getProtocolRoleEnumeration_empty_p() {
			doTest("TestProtoRole", "", createProtocol());
		}

		@Test
		@DisplayName("(\"  \", null)")
		public void getProtocolRoleEnumeration_stage_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$.$Anonymous$Role", "  ", null);
			});
		}

		@Test
		@DisplayName("(\"  \", p)")
		public void getProtocolRoleEnumeration_stage_p() {
			doTest("TestProtoRole", "  ", createProtocol());
		}

		@Test
		@DisplayName("(\"abc\", null)")
		public void getProtocolRoleEnumeration_abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("abc.$Anonymous$Role", "abc", null);
			});
		}

		@Test
		@DisplayName("(\"abc\", p)")
		public void getProtocolRoleEnumeration_abc_p() {
			doTest("abc.TestProtoRole", "abc", createProtocol());
		}

		@Test
		@DisplayName("(\"Abc\", null)")
		public void getProtocolRoleEnumeration_Abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("Abc.$Anonymous$Role", "Abc", null);
			});
		}

		@Test
		@DisplayName("(\"Abc\", p)")
		public void getProtocolRoleEnumeration_Abc_p() {
			doTest("Abc.TestProtoRole", "Abc", createProtocol());
		}

	}
	
	@DisplayName("getProtocolSpaceSpecificationName")
	@Nested
	public class GetProtocolSpaceSpecificationNameTest {

		private DefaultProtocolNames test;
	
		@BeforeEach
		public void setUp() {
			this.test = new DefaultProtocolNames();
		}
	
		@Test
		@DisplayName("(null)")
		public void getProtocolSpaceSpecificationName_null() {
			assertEquals("$Anonymous$SpaceSpecification", this.test.getProtocolSpaceSpecificationName(null));
		}
	
		@Test
		@DisplayName("(\"\")")
		public void getProtocolSpaceSpecificationName_empty() {
			assertEquals("$Anonymous$SpaceSpecification", this.test.getProtocolSpaceSpecificationName(""));
		}
	
		@Test
		@DisplayName("(\"  \")")
		public void getProtocolSpaceSpecificationName_space() {
			assertEquals("$Anonymous$SpaceSpecification", this.test.getProtocolSpaceSpecificationName(""));
		}
	
		@Test
		@DisplayName("(\"abc\")")
		public void getProtocolSpaceSpecificationName_abc() {
			assertEquals("abcSpaceSpecification", this.test.getProtocolSpaceSpecificationName("abc"));
		}
	
		@Test
		@DisplayName("(\"Abc\")")
		public void getProtocolSpaceSpecificationName_Abc() {
			assertEquals("AbcSpaceSpecification", this.test.getProtocolSpaceSpecificationName("Abc"));
		}

	}
	
	@DisplayName("getProtocolCapacityName")
	@Nested
	public class GetProtocolCapacityNameTest {

		private BsplProtocolRole role;

		private DefaultProtocolNames test;
	
		@BeforeEach
		public void setUp() {
			this.role = mock(BsplProtocolRole.class);
			when(this.role.getName()).thenReturn("ROLE1");
			this.test = new DefaultProtocolNames();
		}

		@Test
		@DisplayName("(null, null, null)")
		public void getProtocolCapacityName_null_null_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName(null, null, null));
		}
	
		@Test
		@DisplayName("(null, \"\", null)")
		public void getProtocolCapacityName_null_empty_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName(null, "", null));
		}
	
		@Test
		@DisplayName("(null, \"  \", null)")
		public void getProtocolCapacityName_null_space_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName(null, "  ", null));
		}
	
		@Test
		@DisplayName("(null, \"abc\", null)")
		public void getProtocolCapacityName_null_abc_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName(null, "abc", null));
		}
	
		@Test
		@DisplayName("(null, \"Abc\", null)")
		public void getProtocolCapacityName_null_Abc_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName(null, "Abc", null));
		}
	
		@Test
		@DisplayName("(null, null, r)")
		public void getProtocolCapacityName_null_null_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName(null, null, this.role));
		}
	
		@Test
		@DisplayName("(null, \"\", r)")
		public void getProtocolCapacityName_null_empty_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName(null, "", this.role));
		}
	
		@Test
		@DisplayName("(null, \"  \", r)")
		public void getProtocolCapacityName_null_space_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName(null, "  ", this.role));
		}
	
		@Test
		@DisplayName("(null, \"abc\", r)")
		public void getProtocolCapacityName_null_abc_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName(null, "abc", this.role));
		}
	
		@Test
		@DisplayName("(null, \"Abc\", r)")
		public void getProtocolCapacityName_null_Abc_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName(null, "Abc", this.role));
		}
		
		@Test
		@DisplayName("(\"\", null, null)")
		public void getProtocolCapacityName_empty_null_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName("", null, null));
		}
	
		@Test
		@DisplayName("(\"\", \"\", null)")
		public void getProtocolCapacityName_empty_empty_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName("", "", null));
		}
	
		@Test
		@DisplayName("(\"\", \"  \", null)")
		public void getProtocolCapacityName_empty_space_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName("", "  ", null));
		}
	
		@Test
		@DisplayName("(\"\", \"abc\", null)")
		public void getProtocolCapacityName_empty_abc_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName("", "abc", null));
		}
	
		@Test
		@DisplayName("(\"\", \"Abc\", null)")
		public void getProtocolCapacityName_empty_Abc_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName("", "Abc", null));
		}
	
		@Test
		@DisplayName("(\"\", null, r)")
		public void getProtocolCapacityName_empty_null_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName("", null, this.role));
		}
	
		@Test
		@DisplayName("(\"\", \"\", r)")
		public void getProtocolCapacityName_empty_empty_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName("", "", this.role));
		}
	
		@Test
		@DisplayName("(\"\", \"  \", r)")
		public void getProtocolCapacityName_empty_space_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName("", "  ", this.role));
		}
	
		@Test
		@DisplayName("(\"\", \"abc\", r)")
		public void getProtocolCapacityName_empty_abc_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName("", "abc", this.role));
		}
	
		@Test
		@DisplayName("(\"\", \"Abc\", r)")
		public void getProtocolCapacityName_empty_Abc_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName("", "Abc", this.role));
		}
	
		@Test
		@DisplayName("(\"  \", null, null)")
		public void getProtocolCapacityName_space_null_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName("  ", null, null));
		}
	
		@Test
		@DisplayName("(\"  \", \"\", null)")
		public void getProtocolCapacityName_space_empty_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName("  ", "", null));
		}
	
		@Test
		@DisplayName("(\"  \", \"  \", null)")
		public void getProtocolCapacityName_space_space_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName("  ", "  ", null));
		}
	
		@Test
		@DisplayName("(\"  \", \"abc\", null)")
		public void getProtocolCapacityName_space_abc_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName("  ", "abc", null));
		}
	
		@Test
		@DisplayName("(\"  \", \"Abc\", null)")
		public void getProtocolCapacityName_space_Abc_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName("  ", "Abc", null));
		}
	
		@Test
		@DisplayName("(\"  \", null, r)")
		public void getProtocolCapacityName_space_null_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName("  ", null, this.role));
		}
	
		@Test
		@DisplayName("(\"  \", \"\", r)")
		public void getProtocolCapacityName_space_empty_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName("  ", "", this.role));
		}
	
		@Test
		@DisplayName("(\"  \", \"  \", r)")
		public void getProtocolCapacityName_space_space_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName("  ", "  ", this.role));
		}
	
		@Test
		@DisplayName("(\"  \", \"abc\", r)")
		public void getProtocolCapacityName_space_abc_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName("  ", "abc", this.role));
		}
	
		@Test
		@DisplayName("(\"  \", \"Abc\", r)")
		public void getProtocolCapacityName_space_Abc_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName("  ", "Abc", this.role));
		}
		
		@Test
		@DisplayName("(\"abc\", null, null)")
		public void getProtocolCapacityName_abc_null_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName("abc", null, null));
		}
	
		@Test
		@DisplayName("(\"abc\", \"\", null)")
		public void getProtocolCapacityName_abc_empty_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName("abc", "", null));
		}
	
		@Test
		@DisplayName("(\"abc\", \"  \", null)")
		public void getProtocolCapacityName_abc_space_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName("abc", "  ", null));
		}
	
		@Test
		@DisplayName("(\"abc\", \"abc\", null)")
		public void getProtocolCapacityName_abc_abc_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName("abc", "abc", null));
		}
	
		@Test
		@DisplayName("(\"abc\", \"Abc\", null)")
		public void getProtocolCapacityName_abc_Abc_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName("abc", "Abc", null));
		}
	
		@Test
		@DisplayName("(\"abc\", null, r)")
		public void getProtocolCapacityName_abc_null_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName("abc", null, this.role));
		}
	
		@Test
		@DisplayName("(\"abc\", \"\", r)")
		public void getProtocolCapacityName_abc_empty_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName("abc", "", this.role));
		}
	
		@Test
		@DisplayName("(\"abc\", \"  \", r)")
		public void getProtocolCapacityName_abc_space_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName("abc", "  ", this.role));
		}
	
		@Test
		@DisplayName("(\"abc\", \"abc\", r)")
		public void getProtocolCapacityName_abc_abc_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName("abc", "abc", this.role));
		}
	
		@Test
		@DisplayName("(\"abc\", \"Abc\", r)")
		public void getProtocolCapacityName_abc_Abc_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName("abc", "Abc", this.role));
		}
	
		@Test
		@DisplayName("(\"Abc\", null, null)")
		public void getProtocolCapacityName_Abc_null_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName("Abc", null, null));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"\", null)")
		public void getProtocolCapacityName_Abc_empty_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName("Abc", "", null));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"  \", null)")
		public void getProtocolCapacityName_Abc_space_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName("Abc", "  ", null));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"abc\", null)")
		public void getProtocolCapacityName_Abc_abc_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName("Abc", "abc", null));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"Abc\", null)")
		public void getProtocolCapacityName_Abc_Abc_null() {
			assertEquals("$Anonymous$ProtocolCapacity", this.test.getProtocolCapacityName("Abc", "Abc", null));
		}
	
		@Test
		@DisplayName("(\"Abc\", null, r)")
		public void getProtocolCapacityName_Abc_null_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName("Abc", null, this.role));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"\", r)")
		public void getProtocolCapacityName_Abc_empty_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName("Abc", "", this.role));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"  \", r)")
		public void getProtocolCapacityName_Abc_space_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName("Abc", "  ", this.role));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"abc\", r)")
		public void getProtocolCapacityName_Abc_abc_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName("Abc", "abc", this.role));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"Abc\", r)")
		public void getProtocolCapacityName_Abc_Abc_r() {
			assertEquals("ROLE1ProtocolCapacity", this.test.getProtocolCapacityName("Abc", "Abc", this.role));
		}

	}
	
	@DisplayName("getProtocolCapacity")
	@Nested
	public class GetProtocolCapacityTest {

		private TypeReferences typeReferences;

		private CommonTypeComputationServices typeServices;

		private BsplProtocolRole role;

		private DefaultProtocolNames test;
	
		@BeforeEach
		public void setUp() {
			this.role = mock(BsplProtocolRole.class);
			when(this.role.getName()).thenReturn("ROLE1");
			when(this.role.eResource()).thenReturn(mock(Resource.class));

			this.typeReferences = mock(TypeReferences.class);

			this.typeServices = mock(CommonTypeComputationServices.class);
			when(this.typeServices.getTypeReferences()).thenAnswer(it -> this.typeReferences);

			this.test = new DefaultProtocolNames();
			this.test.setCommonTypeComputationServices(this.typeServices);
		}

		private void declare(String qualifiedName) {
			when(this.typeReferences.findDeclaredType(anyString(), any())).thenAnswer(it -> {
				if (qualifiedName.equals(it.getArgument(0))) {
					var type = mock(JvmType.class);
					when(type.getQualifiedName()).thenReturn(qualifiedName);
					when(type.getIdentifier()).thenReturn(qualifiedName);
					when(type.eClass()).thenReturn(TypesPackage.Literals.JVM_ENUMERATION_TYPE);
					return type;
				}
				return null;
			});
		}
		
		private void doTest(String qualifiedName, String packageName, String protocolName, BsplProtocolRole role) {
			declare(qualifiedName);
			var actual = this.test.getProtocolCapacity(packageName, protocolName, role);
			assertNotNull(actual);
			assertEquals(actual.getIdentifier(), qualifiedName);
		}

		@Test
		@DisplayName("(null, null, null)")
		public void getProtocolCapacity_null_null_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolCapacity", null, null, null);
			});
		}

		@Test
		@DisplayName("(null, null, r)")
		public void getProtocolCapacity_null_null_r() {
			doTest("ROLE1ProtocolCapacity", null, null, this.role);
		}

		@Test
		@DisplayName("(null, \"\", null)")
		public void getProtocolCapacity_null_empty_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolCapacity", null, "", null);
			});
		}

		@Test
		@DisplayName("(null, \"\", r)")
		public void getProtocolCapacity_null_empty_r() {
			doTest("ROLE1ProtocolCapacity", null, "", this.role);
		}

		@Test
		@DisplayName("(null, \"  \", null)")
		public void getProtocolCapacity_null_space_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolCapacity", null, "  ", null);
			});
		}

		@Test
		@DisplayName("(null, \"  \", r)")
		public void getProtocolCapacity_null_space_r() {
			doTest("ROLE1ProtocolCapacity", null, "  ", this.role);
		}

		@Test
		@DisplayName("(null, \"abc\", null)")
		public void getProtocolCapacity_null_abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolCapacity", null, "abc", null);
			});
		}

		@Test
		@DisplayName("(null, \"abc\", r)")
		public void getProtocolCapacity_null_abc_r() {
			doTest("ROLE1ProtocolCapacity", null, "abc", this.role);
		}

		@Test
		@DisplayName("(null, \"Abc\", null)")
		public void getProtocolCapacity_null_Abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolCapacity", null, "Abc", null);
			});
		}

		@Test
		@DisplayName("(null, \"Abc\", r)")
		public void getProtocolCapacity_null_Abc_r() {
			doTest("ROLE1ProtocolCapacity", null, "Abc", this.role);
		}

		@Test
		@DisplayName("(\"\", null, null)")
		public void getProtocolCapacity_empty_null_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolCapacity", "", null, null);
			});
		}

		@Test
		@DisplayName("(\"\", null, r)")
		public void getProtocolCapacity_empty_null_r() {
			doTest("ROLE1ProtocolCapacity", "", null, this.role);
		}

		@Test
		@DisplayName("(\"\", \"\", null)")
		public void getProtocolCapacity_empty_empty_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolCapacity", "", "", null);
			});
		}

		@Test
		@DisplayName("(\"\", \"\", r)")
		public void getProtocolCapacity_empty_empty_r() {
			doTest("ROLE1ProtocolCapacity", "", "", this.role);
		}

		@Test
		@DisplayName("(\"\", \"  \", null)")
		public void getProtocolCapacity_empty_space_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolCapacity", "", "  ", null);
			});
		}

		@Test
		@DisplayName("(\"\", \"  \", r)")
		public void getProtocolCapacity_empty_space_r() {
			doTest("ROLE1ProtocolCapacity", "", "  ", this.role);
		}

		@Test
		@DisplayName("(\"\", \"abc\", null)")
		public void getProtocolCapacity_empty_abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolCapacity", "", "abc", null);
			});
		}

		@Test
		@DisplayName("(\"\", \"abc\", r)")
		public void getProtocolCapacity_empty_abc_r() {
			doTest("ROLE1ProtocolCapacity", "", "abc", this.role);
		}

		@Test
		@DisplayName("(\"\", \"Abc\", null)")
		public void getProtocolCapacity_empty_Abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolCapacity", "", "Abc", null);
			});
		}

		@Test
		@DisplayName("(\"\", \"Abc\", r)")
		public void getProtocolCapacity_empty_Abc_r() {
			doTest("ROLE1ProtocolCapacity", "", "Abc", this.role);
		}

		@Test
		@DisplayName("(\"  \", null, null)")
		public void getProtocolCapacity_space_null_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolCapacity", "  ", null, null);
			});
		}

		@Test
		@DisplayName("(\"  \", null, r)")
		public void getProtocolCapacity_space_null_r() {
			doTest("ROLE1ProtocolCapacity", "  ", null, this.role);
		}

		@Test
		@DisplayName("(\"  \", \"\", null)")
		public void getProtocolCapacity_space_empty_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolCapacity", "  ", "", null);
			});
		}

		@Test
		@DisplayName("(\"  \", \"\", r)")
		public void getProtocolCapacity_space_empty_r() {
			doTest("ROLE1ProtocolCapacity", "  ", "", this.role);
		}

		@Test
		@DisplayName("(\"  \", \"  \", null)")
		public void getProtocolCapacity_space_space_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolCapacity", "  ", "  ", null);
			});
		}

		@Test
		@DisplayName("(\"  \", \"  \", r)")
		public void getProtocolCapacity_space_space_r() {
			doTest("ROLE1ProtocolCapacity", "  ", "  ", this.role);
		}

		@Test
		@DisplayName("(\"  \", \"abc\", null)")
		public void getProtocolCapacity_space_abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolCapacity", "  ", "abc", null);
			});
		}

		@Test
		@DisplayName("(\"  \", \"abc\", r)")
		public void getProtocolCapacity_space_abc_r() {
			doTest("ROLE1ProtocolCapacity", "  ", "abc", this.role);
		}

		@Test
		@DisplayName("(\"  \", \"Abc\", null)")
		public void getProtocolCapacity_space_Abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolCapacity", "  ", "Abc", null);
			});
		}

		@Test
		@DisplayName("(\"  \", \"Abc\", r)")
		public void getProtocolCapacity_space_Abc_r() {
			doTest("ROLE1ProtocolCapacity", "  ", "Abc", this.role);
		}

		@Test
		@DisplayName("(\"abc\", null, null)")
		public void getProtocolCapacity_abc_null_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("abc.$Anonymous$ProtocolCapacity", "abc", null, null);
			});
		}

		@Test
		@DisplayName("(\"abc\", null, r)")
		public void getProtocolCapacity_abc_null_r() {
			doTest("abc.ROLE1ProtocolCapacity", "abc", null, this.role);
		}

		@Test
		@DisplayName("(\"abc\", \"\", null)")
		public void getProtocolCapacity_abc_empty_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("abc.$Anonymous$ProtocolCapacity", "abc", "", null);
			});
		}

		@Test
		@DisplayName("(\"abc\", \"\", r)")
		public void getProtocolCapacity_abc_empty_r() {
			doTest("abc.ROLE1ProtocolCapacity", "abc", "", this.role);
		}

		@Test
		@DisplayName("(\"abc\", \"  \", null)")
		public void getProtocolCapacity_abc_space_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("abc.$Anonymous$ProtocolCapacity", "abc", "  ", null);
			});
		}

		@Test
		@DisplayName("(\"abc\", \"  \", r)")
		public void getProtocolCapacity_abc_space_r() {
			doTest("abc.ROLE1ProtocolCapacity", "abc", "  ", this.role);
		}

		@Test
		@DisplayName("(\"abc\", \"abc\", null)")
		public void getProtocolCapacity_abc_abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("abc.$Anonymous$ProtocolCapacity", "abc", "abc", null);
			});
		}

		@Test
		@DisplayName("(\"abc\", \"abc\", r)")
		public void getProtocolCapacity_abc_abc_r() {
			doTest("abc.ROLE1ProtocolCapacity", "abc", "abc", this.role);
		}

		@Test
		@DisplayName("(\"abc\", \"Abc\", null)")
		public void getProtocolCapacity_abc_Abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("abc.$Anonymous$ProtocolCapacity", "abc", "Abc", null);
			});
		}

		@Test
		@DisplayName("(\"abc\", \"Abc\", r)")
		public void getProtocolCapacity_abc_Abc_r() {
			doTest("abc.ROLE1ProtocolCapacity", "abc", "Abc", this.role);
		}

		@Test
		@DisplayName("(\"Abc\", null, null)")
		public void getProtocolCapacity_Abc_null_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("Abc.$Anonymous$ProtocolCapacity", "Abc", null, null);
			});
		}

		@Test
		@DisplayName("(\"Abc\", null, r)")
		public void getProtocolCapacity_Abc_null_r() {
			doTest("Abc.ROLE1ProtocolCapacity", "Abc", null, this.role);
		}

		@Test
		@DisplayName("(\"Abc\", \"\", null)")
		public void getProtocolCapacity_Abc_empty_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("Abc.$Anonymous$ProtocolCapacity", "Abc", "", null);
			});
		}

		@Test
		@DisplayName("(\"Abc\", \"\", r)")
		public void getProtocolCapacity_Abc_empty_r() {
			doTest("Abc.ROLE1ProtocolCapacity", "Abc", "", this.role);
		}

		@Test
		@DisplayName("(\"Abc\", \"  \", null)")
		public void getProtocolCapacity_Abc_space_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolCapacity", "Abc", "  ", null);
			});
		}

		@Test
		@DisplayName("(\"Abc\", \"  \", r)")
		public void getProtocolCapacity_Abc_space_r() {
			doTest("Abc.ROLE1ProtocolCapacity", "Abc", "  ", this.role);
		}

		@Test
		@DisplayName("(\"Abc\", \"abc\", null)")
		public void getProtocolCapacity_Abc_abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("Abc.$Anonymous$ProtocolCapacity", "Abc", "abc", null);
			});
		}

		@Test
		@DisplayName("(\"Abc\", \"abc\", r)")
		public void getProtocolCapacity_Abc_abc_r() {
			doTest("Abc.ROLE1ProtocolCapacity", "Abc", "abc", this.role);
		}

		@Test
		@DisplayName("(\"Abc\", \"Abc\", null)")
		public void getProtocolCapacity_Abc_Abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("Abc.$Anonymous$ProtocolCapacity", "Abc", "Abc", null);
			});
		}

		@Test
		@DisplayName("(\"Abc\", \"Abc\", r)")
		public void getProtocolCapacity_Abc_Abc_r() {
			doTest("Abc.ROLE1ProtocolCapacity", "Abc", "Abc", this.role);
		}

	}
	
	@DisplayName("getProtocolSkillName")
	@Nested
	public class GetProtocolSkillNameTest {

		private BsplProtocolRole role;

		private DefaultProtocolNames test;
	
		@BeforeEach
		public void setUp() {
			this.role = mock(BsplProtocolRole.class);
			when(this.role.getName()).thenReturn("ROLE1");
			this.test = new DefaultProtocolNames();
		}

		@Test
		@DisplayName("(null, null, null)")
		public void getProtocolSkillName_null_null_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName(null, null, null));
		}
	
		@Test
		@DisplayName("(null, \"\", null)")
		public void getProtocolSkillName_null_empty_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName(null, "", null));
		}
	
		@Test
		@DisplayName("(null, \"  \", null)")
		public void getProtocolSkillName_null_space_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName(null, "  ", null));
		}
	
		@Test
		@DisplayName("(null, \"abc\", null)")
		public void getProtocolSkillName_null_abc_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName(null, "abc", null));
		}
	
		@Test
		@DisplayName("(null, \"Abc\", null)")
		public void getProtocolSkillName_null_Abc_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName(null, "Abc", null));
		}
	
		@Test
		@DisplayName("(null, null, r)")
		public void getProtocolSkillName_null_null_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName(null, null, this.role));
		}
	
		@Test
		@DisplayName("(null, \"\", r)")
		public void getProtocolSkillName_null_empty_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName(null, "", this.role));
		}
	
		@Test
		@DisplayName("(null, \"  \", r)")
		public void getProtocolSkillName_null_space_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName(null, "  ", this.role));
		}
	
		@Test
		@DisplayName("(null, \"abc\", r)")
		public void getProtocolSkillName_null_abc_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName(null, "abc", this.role));
		}
	
		@Test
		@DisplayName("(null, \"Abc\", r)")
		public void getProtocolSkillName_null_Abc_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName(null, "Abc", this.role));
		}
		
		@Test
		@DisplayName("(\"\", null, null)")
		public void getProtocolSkillName_empty_null_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName("", null, null));
		}
	
		@Test
		@DisplayName("(\"\", \"\", null)")
		public void getProtocolSkillName_empty_empty_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName("", "", null));
		}
	
		@Test
		@DisplayName("(\"\", \"  \", null)")
		public void getProtocolSkillName_empty_space_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName("", "  ", null));
		}
	
		@Test
		@DisplayName("(\"\", \"abc\", null)")
		public void getProtocolSkillName_empty_abc_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName("", "abc", null));
		}
	
		@Test
		@DisplayName("(\"\", \"Abc\", null)")
		public void getProtocolSkillName_empty_Abc_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName("", "Abc", null));
		}
	
		@Test
		@DisplayName("(\"\", null, r)")
		public void getProtocolSkillName_empty_null_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName("", null, this.role));
		}
	
		@Test
		@DisplayName("(\"\", \"\", r)")
		public void getProtocolSkillName_empty_empty_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName("", "", this.role));
		}
	
		@Test
		@DisplayName("(\"\", \"  \", r)")
		public void getProtocolSkillName_empty_space_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName("", "  ", this.role));
		}
	
		@Test
		@DisplayName("(\"\", \"abc\", r)")
		public void getProtocolSkillName_empty_abc_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName("", "abc", this.role));
		}
	
		@Test
		@DisplayName("(\"\", \"Abc\", r)")
		public void getProtocolSkillName_empty_Abc_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName("", "Abc", this.role));
		}
	
		@Test
		@DisplayName("(\"  \", null, null)")
		public void getProtocolSkillName_space_null_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName("  ", null, null));
		}
	
		@Test
		@DisplayName("(\"  \", \"\", null)")
		public void getProtocolSkillName_space_empty_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName("  ", "", null));
		}
	
		@Test
		@DisplayName("(\"  \", \"  \", null)")
		public void getProtocolSkillName_space_space_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName("  ", "  ", null));
		}
	
		@Test
		@DisplayName("(\"  \", \"abc\", null)")
		public void getProtocolSkillName_space_abc_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName("  ", "abc", null));
		}
	
		@Test
		@DisplayName("(\"  \", \"Abc\", null)")
		public void getProtocolSkillName_space_Abc_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName("  ", "Abc", null));
		}
	
		@Test
		@DisplayName("(\"  \", null, r)")
		public void getProtocolSkillName_space_null_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName("  ", null, this.role));
		}
	
		@Test
		@DisplayName("(\"  \", \"\", r)")
		public void getProtocolSkillName_space_empty_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName("  ", "", this.role));
		}
	
		@Test
		@DisplayName("(\"  \", \"  \", r)")
		public void getProtocolSkillName_space_space_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName("  ", "  ", this.role));
		}
	
		@Test
		@DisplayName("(\"  \", \"abc\", r)")
		public void getProtocolSkillName_space_abc_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName("  ", "abc", this.role));
		}
	
		@Test
		@DisplayName("(\"  \", \"Abc\", r)")
		public void getProtocolSkillName_space_Abc_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName("  ", "Abc", this.role));
		}
		
		@Test
		@DisplayName("(\"abc\", null, null)")
		public void getProtocolSkillName_abc_null_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName("abc", null, null));
		}
	
		@Test
		@DisplayName("(\"abc\", \"\", null)")
		public void getProtocolSkillName_abc_empty_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName("abc", "", null));
		}
	
		@Test
		@DisplayName("(\"abc\", \"  \", null)")
		public void getProtocolSkillName_abc_space_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName("abc", "  ", null));
		}
	
		@Test
		@DisplayName("(\"abc\", \"abc\", null)")
		public void getProtocolSkillName_abc_abc_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName("abc", "abc", null));
		}
	
		@Test
		@DisplayName("(\"abc\", \"Abc\", null)")
		public void getProtocolSkillName_abc_Abc_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName("abc", "Abc", null));
		}
	
		@Test
		@DisplayName("(\"abc\", null, r)")
		public void getProtocolSkillName_abc_null_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName("abc", null, this.role));
		}
	
		@Test
		@DisplayName("(\"abc\", \"\", r)")
		public void getProtocolSkillName_abc_empty_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName("abc", "", this.role));
		}
	
		@Test
		@DisplayName("(\"abc\", \"  \", r)")
		public void getProtocolSkillName_abc_space_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName("abc", "  ", this.role));
		}
	
		@Test
		@DisplayName("(\"abc\", \"abc\", r)")
		public void getProtocolSkillName_abc_abc_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName("abc", "abc", this.role));
		}
	
		@Test
		@DisplayName("(\"abc\", \"Abc\", r)")
		public void getProtocolSkillName_abc_Abc_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName("abc", "Abc", this.role));
		}
	
		@Test
		@DisplayName("(\"Abc\", null, null)")
		public void getProtocolSkillName_Abc_null_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName("Abc", null, null));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"\", null)")
		public void getProtocolSkillName_Abc_empty_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName("Abc", "", null));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"  \", null)")
		public void getProtocolSkillName_Abc_space_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName("Abc", "  ", null));
		}
	
		@Test
		@DisplayName("getProtocolSkillName(\"Abc\", \"abc\", null)")
		public void getProtocolSkillName_Abc_abc_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName("Abc", "abc", null));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"Abc\", null)")
		public void getProtocolSkillName_Abc_Abc_null() {
			assertEquals("$Anonymous$ProtocolSkill", this.test.getProtocolSkillName("Abc", "Abc", null));
		}
	
		@Test
		@DisplayName("(\"Abc\", null, r)")
		public void getProtocolSkillName_Abc_null_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName("Abc", null, this.role));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"\", r)")
		public void getProtocolSkillName_Abc_empty_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName("Abc", "", this.role));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"  \", r)")
		public void getProtocolSkillName_Abc_space_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName("Abc", "  ", this.role));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"abc\", r)")
		public void getProtocolSkillName_Abc_abc_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName("Abc", "abc", this.role));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"Abc\", r)")
		public void getProtocolSkillName_Abc_Abc_r() {
			assertEquals("ROLE1ProtocolSkill", this.test.getProtocolSkillName("Abc", "Abc", this.role));
		}

	}
	
	@DisplayName("getProtocolSkill")
	@Nested
	public class GetProtocolSkillTest {

		private TypeReferences typeReferences;

		private CommonTypeComputationServices typeServices;

		private BsplProtocolRole role;

		private DefaultProtocolNames test;
	
		@BeforeEach
		public void setUp() {
			this.role = mock(BsplProtocolRole.class);
			when(this.role.getName()).thenReturn("ROLE1");
			when(this.role.eResource()).thenReturn(mock(Resource.class));

			this.typeReferences = mock(TypeReferences.class);

			this.typeServices = mock(CommonTypeComputationServices.class);
			when(this.typeServices.getTypeReferences()).thenAnswer(it -> this.typeReferences);

			this.test = new DefaultProtocolNames();
			this.test.setCommonTypeComputationServices(this.typeServices);
		}

		private void declare(String qualifiedName) {
			when(this.typeReferences.findDeclaredType(anyString(), any())).thenAnswer(it -> {
				if (qualifiedName.equals(it.getArgument(0))) {
					var type = mock(JvmType.class);
					when(type.getQualifiedName()).thenReturn(qualifiedName);
					when(type.getIdentifier()).thenReturn(qualifiedName);
					when(type.eClass()).thenReturn(TypesPackage.Literals.JVM_ENUMERATION_TYPE);
					return type;
				}
				return null;
			});
		}
		
		private void doTest(String qualifiedName, String packageName, String protocolName, BsplProtocolRole role) {
			declare(qualifiedName);
			var actual = this.test.getProtocolSkill(packageName, protocolName, role);
			assertNotNull(actual);
			assertEquals(actual.getIdentifier(), qualifiedName);
		}

		@Test
		@DisplayName("(null, null, null)")
		public void getProtocolSkill_null_null_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolSkill", null, null, null);
			});
		}

		@Test
		@DisplayName("(null, null, r)")
		public void getProtocolSkill_null_null_r() {
			doTest("ROLE1ProtocolSkill", null, null, this.role);
		}

		@Test
		@DisplayName("(null, \"\", null)")
		public void getProtocolSkill_null_empty_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolSkill", null, "", null);
			});
		}

		@Test
		@DisplayName("(null, \"\", r)")
		public void getProtocolSkill_null_empty_r() {
			doTest("ROLE1ProtocolSkill", null, "", this.role);
		}

		@Test
		@DisplayName("(null, \"  \", null)")
		public void getProtocolSkill_null_space_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolSkill", null, "  ", null);
			});
		}

		@Test
		@DisplayName("(null, \"  \", r)")
		public void getProtocolSkill_null_space_r() {
			doTest("ROLE1ProtocolSkill", null, "  ", this.role);
		}

		@Test
		@DisplayName("(null, \"abc\", null)")
		public void getProtocolSkill_null_abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolSkill", null, "abc", null);
			});
		}

		@Test
		@DisplayName("(null, \"abc\", r)")
		public void getProtocolSkill_null_abc_r() {
			doTest("ROLE1ProtocolSkill", null, "abc", this.role);
		}

		@Test
		@DisplayName("(null, \"Abc\", null)")
		public void getProtocolSkill_null_Abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolSkill", null, "Abc", null);
			});
		}

		@Test
		@DisplayName("(null, \"Abc\", r)")
		public void getProtocolSkill_null_Abc_r() {
			doTest("ROLE1ProtocolSkill", null, "Abc", this.role);
		}

		@Test
		@DisplayName("(\"\", null, null)")
		public void getProtocolSkill_empty_null_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolSkill", "", null, null);
			});
		}

		@Test
		@DisplayName("(\"\", null, r)")
		public void getProtocolSkill_empty_null_r() {
			doTest("ROLE1ProtocolSkill", "", null, this.role);
		}

		@Test
		@DisplayName("(\"\", \"\", null)")
		public void getProtocolSkill_empty_empty_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolSkill", "", "", null);
			});
		}

		@Test
		@DisplayName("(\"\", \"\", r)")
		public void getProtocolSkill_empty_empty_r() {
			doTest("ROLE1ProtocolSkill", "", "", this.role);
		}

		@Test
		@DisplayName("(\"\", \"  \", null)")
		public void getProtocolSkill_empty_space_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolSkill", "", "  ", null);
			});
		}

		@Test
		@DisplayName("(\"\", \"  \", r)")
		public void getProtocolSkill_empty_space_r() {
			doTest("ROLE1ProtocolSkill", "", "  ", this.role);
		}

		@Test
		@DisplayName("(\"\", \"abc\", null)")
		public void getProtocolSkill_empty_abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolSkill", "", "abc", null);
			});
		}

		@Test
		@DisplayName("(\"\", \"abc\", r)")
		public void getProtocolSkill_empty_abc_r() {
			doTest("ROLE1ProtocolSkill", "", "abc", this.role);
		}

		@Test
		@DisplayName("(\"\", \"Abc\", null)")
		public void getProtocolSkill_empty_Abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolSkill", "", "Abc", null);
			});
		}

		@Test
		@DisplayName("(\"\", \"Abc\", r)")
		public void getProtocolSkill_empty_Abc_r() {
			doTest("ROLE1ProtocolSkill", "", "Abc", this.role);
		}

		@Test
		@DisplayName("(\"  \", null, null)")
		public void getProtocolSkill_space_null_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolSkill", "  ", null, null);
			});
		}

		@Test
		@DisplayName("(\"  \", null, r)")
		public void getProtocolSkill_space_null_r() {
			doTest("ROLE1ProtocolSkill", "  ", null, this.role);
		}

		@Test
		@DisplayName("(\"  \", \"\", null)")
		public void getProtocolSkill_space_empty_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolSkill", "  ", "", null);
			});
		}

		@Test
		@DisplayName("(\"  \", \"\", r)")
		public void getProtocolSkill_space_empty_r() {
			doTest("ROLE1ProtocolSkill", "  ", "", this.role);
		}

		@Test
		@DisplayName("(\"  \", \"  \", null)")
		public void getProtocolSkill_space_space_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolSkill", "  ", "  ", null);
			});
		}

		@Test
		@DisplayName("(\"  \", \"  \", r)")
		public void getProtocolSkill_space_space_r() {
			doTest("ROLE1ProtocolSkill", "  ", "  ", this.role);
		}

		@Test
		@DisplayName("(\"  \", \"abc\", null)")
		public void getProtocolSkill_space_abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolSkill", "  ", "abc", null);
			});
		}

		@Test
		@DisplayName("(\"  \", \"abc\", r)")
		public void getProtocolSkill_space_abc_r() {
			doTest("ROLE1ProtocolSkill", "  ", "abc", this.role);
		}

		@Test
		@DisplayName("(\"  \", \"Abc\", null)")
		public void getProtocolSkill_space_Abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolSkill", "  ", "Abc", null);
			});
		}

		@Test
		@DisplayName("(\"  \", \"Abc\", r)")
		public void getProtocolSkill_space_Abc_r() {
			doTest("ROLE1ProtocolSkill", "  ", "Abc", this.role);
		}

		@Test
		@DisplayName("(\"abc\", null, null)")
		public void getProtocolSkill_abc_null_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("abc.$Anonymous$ProtocolSkill", "abc", null, null);
			});
		}

		@Test
		@DisplayName("(\"abc\", null, r)")
		public void getProtocolSkill_abc_null_r() {
			doTest("abc.ROLE1ProtocolSkill", "abc", null, this.role);
		}

		@Test
		@DisplayName("(\"abc\", \"\", null)")
		public void getProtocolSkill_abc_empty_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("abc.$Anonymous$ProtocolSkill", "abc", "", null);
			});
		}

		@Test
		@DisplayName("(\"abc\", \"\", r)")
		public void getProtocolSkill_abc_empty_r() {
			doTest("abc.ROLE1ProtocolSkill", "abc", "", this.role);
		}

		@Test
		@DisplayName("(\"abc\", \"  \", null)")
		public void getProtocolSkill_abc_space_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("abc.$Anonymous$ProtocolSkill", "abc", "  ", null);
			});
		}

		@Test
		@DisplayName("(\"abc\", \"  \", r)")
		public void getProtocolSkill_abc_space_r() {
			doTest("abc.ROLE1ProtocolSkill", "abc", "  ", this.role);
		}

		@Test
		@DisplayName("(\"abc\", \"abc\", null)")
		public void getProtocolSkill_abc_abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("abc.$Anonymous$ProtocolSkill", "abc", "abc", null);
			});
		}

		@Test
		@DisplayName("(\"abc\", \"abc\", r)")
		public void getProtocolSkill_abc_abc_r() {
			doTest("abc.ROLE1ProtocolSkill", "abc", "abc", this.role);
		}

		@Test
		@DisplayName("(\"abc\", \"Abc\", null)")
		public void getProtocolSkill_abc_Abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("abc.$Anonymous$ProtocolSkill", "abc", "Abc", null);
			});
		}

		@Test
		@DisplayName("(\"abc\", \"Abc\", r)")
		public void getProtocolSkill_abc_Abc_r() {
			doTest("abc.ROLE1ProtocolSkill", "abc", "Abc", this.role);
		}

		@Test
		@DisplayName("(\"Abc\", null, null)")
		public void getProtocolSkill_Abc_null_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("Abc.$Anonymous$ProtocolSkill", "Abc", null, null);
			});
		}

		@Test
		@DisplayName("(\"Abc\", null, r)")
		public void getProtocolSkill_Abc_null_r() {
			doTest("Abc.ROLE1ProtocolSkill", "Abc", null, this.role);
		}

		@Test
		@DisplayName("(\"Abc\", \"\", null)")
		public void getProtocolSkill_Abc_empty_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("Abc.$Anonymous$ProtocolSkill", "Abc", "", null);
			});
		}

		@Test
		@DisplayName("(\"Abc\", \"\", r)")
		public void getProtocolSkill_Abc_empty_r() {
			doTest("Abc.ROLE1ProtocolSkill", "Abc", "", this.role);
		}

		@Test
		@DisplayName("(\"Abc\", \"  \", null)")
		public void getProtocolSkill_Abc_space_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolSkill", "Abc", "  ", null);
			});
		}

		@Test
		@DisplayName("(\"Abc\", \"  \", r)")
		public void getProtocolSkill_Abc_space_r() {
			doTest("Abc.ROLE1ProtocolSkill", "Abc", "  ", this.role);
		}

		@Test
		@DisplayName("(\"Abc\", \"abc\", null)")
		public void getProtocolSkill_Abc_abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("Abc.$Anonymous$ProtocolSkill", "Abc", "abc", null);
			});
		}

		@Test
		@DisplayName("(\"Abc\", \"abc\", r)")
		public void getProtocolSkill_Abc_abc_r() {
			doTest("Abc.ROLE1ProtocolSkill", "Abc", "abc", this.role);
		}

		@Test
		@DisplayName("(\"Abc\", \"Abc\", null)")
		public void getProtocolSkill_Abc_Abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("Abc.$Anonymous$ProtocolSkill", "Abc", "Abc", null);
			});
		}

		@Test
		@DisplayName("(\"Abc\", \"Abc\", r)")
		public void getProtocolSkill_Abc_Abc_r() {
			doTest("Abc.ROLE1ProtocolSkill", "Abc", "Abc", this.role);
		}

	}
	
	@DisplayName("getProtocolBehaviorName")
	@Nested
	public class GetProtocolBehaviorNameTest {

		private BsplProtocolRole role;

		private DefaultProtocolNames test;
	
		@BeforeEach
		public void setUp() {
			this.role = mock(BsplProtocolRole.class);
			when(this.role.getName()).thenReturn("ROLE1");
			this.test = new DefaultProtocolNames();
		}

		@Test
		@DisplayName("(null, null, null)")
		public void getProtocolBehaviorName_null_null_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName(null, null, null));
		}
	
		@Test
		@DisplayName("(null, \"\", null)")
		public void getProtocolBehaviorName_null_empty_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName(null, "", null));
		}
	
		@Test
		@DisplayName("(null, \"  \", null)")
		public void getProtocolBehaviorName_null_space_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName(null, "  ", null));
		}
	
		@Test
		@DisplayName("(null, \"abc\", null)")
		public void getProtocolBehaviorName_null_abc_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName(null, "abc", null));
		}
	
		@Test
		@DisplayName("(null, \"Abc\", null)")
		public void getProtocolBehaviorName_null_Abc_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName(null, "Abc", null));
		}
	
		@Test
		@DisplayName("(null, null, r)")
		public void getProtocolBehaviorName_null_null_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName(null, null, this.role));
		}
	
		@Test
		@DisplayName("(null, \"\", r)")
		public void getProtocolBehaviorName_null_empty_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName(null, "", this.role));
		}
	
		@Test
		@DisplayName("(null, \"  \", r)")
		public void getProtocolBehaviorName_null_space_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName(null, "  ", this.role));
		}
	
		@Test
		@DisplayName("(null, \"abc\", r)")
		public void getProtocolBehaviorName_null_abc_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName(null, "abc", this.role));
		}
	
		@Test
		@DisplayName("(null, \"Abc\", r)")
		public void getProtocolBehaviorName_null_Abc_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName(null, "Abc", this.role));
		}
		
		@Test
		@DisplayName("(\"\", null, null)")
		public void getProtocolBehaviorName_empty_null_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("", null, null));
		}
	
		@Test
		@DisplayName("(\"\", \"\", null)")
		public void getProtocolBehaviorName_empty_empty_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("", "", null));
		}
	
		@Test
		@DisplayName("(\"\", \"  \", null)")
		public void getProtocolBehaviorName_empty_space_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("", "  ", null));
		}
	
		@Test
		@DisplayName("(\"\", \"abc\", null)")
		public void getProtocolBehaviorName_empty_abc_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("", "abc", null));
		}
	
		@Test
		@DisplayName("(\"\", \"Abc\", null)")
		public void getProtocolBehaviorName_empty_Abc_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("", "Abc", null));
		}
	
		@Test
		@DisplayName("(\"\", null, r)")
		public void getProtocolBehaviorName_empty_null_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("", null, this.role));
		}
	
		@Test
		@DisplayName("(\"\", \"\", r)")
		public void getProtocolBehaviorName_empty_empty_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("", "", this.role));
		}
	
		@Test
		@DisplayName("(\"\", \"  \", r)")
		public void getProtocolBehaviorName_empty_space_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("", "  ", this.role));
		}
	
		@Test
		@DisplayName("(\"\", \"abc\", r)")
		public void getProtocolBehaviorName_empty_abc_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("", "abc", this.role));
		}
	
		@Test
		@DisplayName("(\"\", \"Abc\", r)")
		public void getProtocolBehaviorName_empty_Abc_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("", "Abc", this.role));
		}
	
		@Test
		@DisplayName("(\"  \", null, null)")
		public void getProtocolBehaviorName_space_null_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("  ", null, null));
		}
	
		@Test
		@DisplayName("(\"  \", \"\", null)")
		public void getProtocolBehaviorName_space_empty_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("  ", "", null));
		}
	
		@Test
		@DisplayName("(\"  \", \"  \", null)")
		public void getProtocolBehaviorName_space_space_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("  ", "  ", null));
		}
	
		@Test
		@DisplayName("(\"  \", \"abc\", null)")
		public void getProtocolBehaviorName_space_abc_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("  ", "abc", null));
		}
	
		@Test
		@DisplayName("(\"  \", \"Abc\", null)")
		public void getProtocolBehaviorName_space_Abc_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("  ", "Abc", null));
		}
	
		@Test
		@DisplayName("(\"  \", null, r)")
		public void getProtocolBehaviorName_space_null_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("  ", null, this.role));
		}
	
		@Test
		@DisplayName("(\"  \", \"\", r)")
		public void getProtocolBehaviorName_space_empty_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("  ", "", this.role));
		}
	
		@Test
		@DisplayName("(\"  \", \"  \", r)")
		public void getProtocolBehaviorName_space_space_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("  ", "  ", this.role));
		}
	
		@Test
		@DisplayName("(\"  \", \"abc\", r)")
		public void getProtocolBehaviorName_space_abc_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("  ", "abc", this.role));
		}
	
		@Test
		@DisplayName("(\"  \", \"Abc\", r)")
		public void getProtocolBehaviorName_space_Abc_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("  ", "Abc", this.role));
		}
		
		@Test
		@DisplayName("(\"abc\", null, null)")
		public void getProtocolBehaviorName_abc_null_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("abc", null, null));
		}
	
		@Test
		@DisplayName("(\"abc\", \"\", null)")
		public void getProtocolBehaviorName_abc_empty_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("abc", "", null));
		}
	
		@Test
		@DisplayName("(\"abc\", \"  \", null)")
		public void getProtocolBehaviorName_abc_space_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("abc", "  ", null));
		}
	
		@Test
		@DisplayName("(\"abc\", \"abc\", null)")
		public void getProtocolBehaviorName_abc_abc_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("abc", "abc", null));
		}
	
		@Test
		@DisplayName("(\"abc\", \"Abc\", null)")
		public void getProtocolBehaviorName_abc_Abc_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("abc", "Abc", null));
		}
	
		@Test
		@DisplayName("(\"abc\", null, r)")
		public void getProtocolBehaviorName_abc_null_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("abc", null, this.role));
		}
	
		@Test
		@DisplayName("(\"abc\", \"\", r)")
		public void getProtocolBehaviorName_abc_empty_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("abc", "", this.role));
		}
	
		@Test
		@DisplayName("(\"abc\", \"  \", r)")
		public void getProtocolBehaviorName_abc_space_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("abc", "  ", this.role));
		}
	
		@Test
		@DisplayName("(\"abc\", \"abc\", r)")
		public void getProtocolBehaviorName_abc_abc_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("abc", "abc", this.role));
		}
	
		@Test
		@DisplayName("(\"abc\", \"Abc\", r)")
		public void getProtocolBehaviorName_abc_Abc_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("abc", "Abc", this.role));
		}
	
		@Test
		@DisplayName("(\"Abc\", null, null)")
		public void getProtocolBehaviorName_Abc_null_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("Abc", null, null));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"\", null)")
		public void getProtocolBehaviorName_Abc_empty_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("Abc", "", null));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"  \", null)")
		public void getProtocolBehaviorName_Abc_space_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("Abc", "  ", null));
		}
	
		@Test
		@DisplayName("getProtocolBehaviorName(\"Abc\", \"abc\", null)")
		public void getProtocolBehaviorName_Abc_abc_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("Abc", "abc", null));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"Abc\", null)")
		public void getProtocolBehaviorName_Abc_Abc_null() {
			assertEquals("$Anonymous$ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("Abc", "Abc", null));
		}
	
		@Test
		@DisplayName("(\"Abc\", null, r)")
		public void getProtocolBehaviorName_Abc_null_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("Abc", null, this.role));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"\", r)")
		public void getProtocolBehaviorName_Abc_empty_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("Abc", "", this.role));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"  \", r)")
		public void getProtocolBehaviorName_Abc_space_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("Abc", "  ", this.role));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"abc\", r)")
		public void getProtocolBehaviorName_Abc_abc_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("Abc", "abc", this.role));
		}
	
		@Test
		@DisplayName("(\"Abc\", \"Abc\", r)")
		public void getProtocolBehaviorName_Abc_Abc_r() {
			assertEquals("ROLE1ProtocolReactiveBehavior", this.test.getProtocolBehaviorName("Abc", "Abc", this.role));
		}

	}
	
	@DisplayName("getProtocolBehavior")
	@Nested
	public class GetProtocolBehaviorTest {

		private TypeReferences typeReferences;

		private CommonTypeComputationServices typeServices;

		private BsplProtocolRole role;

		private DefaultProtocolNames test;
	
		@BeforeEach
		public void setUp() {
			this.role = mock(BsplProtocolRole.class);
			when(this.role.getName()).thenReturn("ROLE1");
			when(this.role.eResource()).thenReturn(mock(Resource.class));

			this.typeReferences = mock(TypeReferences.class);

			this.typeServices = mock(CommonTypeComputationServices.class);
			when(this.typeServices.getTypeReferences()).thenAnswer(it -> this.typeReferences);

			this.test = new DefaultProtocolNames();
			this.test.setCommonTypeComputationServices(this.typeServices);
		}

		private void declare(String qualifiedName) {
			when(this.typeReferences.findDeclaredType(anyString(), any())).thenAnswer(it -> {
				if (qualifiedName.equals(it.getArgument(0))) {
					var type = mock(JvmType.class);
					when(type.getQualifiedName()).thenReturn(qualifiedName);
					when(type.getIdentifier()).thenReturn(qualifiedName);
					when(type.eClass()).thenReturn(TypesPackage.Literals.JVM_ENUMERATION_TYPE);
					return type;
				}
				return null;
			});
		}
		
		private void doTest(String qualifiedName, String packageName, String protocolName, BsplProtocolRole role) {
			declare(qualifiedName);
			var actual = this.test.getProtocolBehavior(packageName, protocolName, role);
			assertNotNull(actual);
			assertEquals(actual.getIdentifier(), qualifiedName);
		}

		@Test
		@DisplayName("(null, null, null)")
		public void getProtocolBehavior_null_null_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolReactiveBehavior", null, null, null);
			});
		}

		@Test
		@DisplayName("(null, null, r)")
		public void getProtocolBehavior_null_null_r() {
			doTest("ROLE1ProtocolReactiveBehavior", null, null, this.role);
		}

		@Test
		@DisplayName("(null, \"\", null)")
		public void getProtocolBehavior_null_empty_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolReactiveBehavior", null, "", null);
			});
		}

		@Test
		@DisplayName("(null, \"\", r)")
		public void getProtocolBehavior_null_empty_r() {
			doTest("ROLE1ProtocolReactiveBehavior", null, "", this.role);
		}

		@Test
		@DisplayName("(null, \"  \", null)")
		public void getProtocolBehavior_null_space_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolReactiveBehavior", null, "  ", null);
			});
		}

		@Test
		@DisplayName("(null, \"  \", r)")
		public void getProtocolBehavior_null_space_r() {
			doTest("ROLE1ProtocolReactiveBehavior", null, "  ", this.role);
		}

		@Test
		@DisplayName("(null, \"abc\", null)")
		public void getProtocolBehavior_null_abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolReactiveReactiveBehavior", null, "abc", null);
			});
		}

		@Test
		@DisplayName("(null, \"abc\", r)")
		public void getProtocolBehavior_null_abc_r() {
			doTest("ROLE1ProtocolReactiveBehavior", null, "abc", this.role);
		}

		@Test
		@DisplayName("(null, \"Abc\", null)")
		public void getProtocolBehavior_null_Abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolReactiveBehavior", null, "Abc", null);
			});
		}

		@Test
		@DisplayName("(null, \"Abc\", r)")
		public void getProtocolBehavior_null_Abc_r() {
			doTest("ROLE1ProtocolReactiveBehavior", null, "Abc", this.role);
		}

		@Test
		@DisplayName("(\"\", null, null)")
		public void getProtocolBehavior_empty_null_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolReactiveBehavior", "", null, null);
			});
		}

		@Test
		@DisplayName("(\"\", null, r)")
		public void getProtocolBehavior_empty_null_r() {
			doTest("ROLE1ProtocolReactiveBehavior", "", null, this.role);
		}

		@Test
		@DisplayName("(\"\", \"\", null)")
		public void getProtocolBehavior_empty_empty_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolReactiveBehavior", "", "", null);
			});
		}

		@Test
		@DisplayName("(\"\", \"\", r)")
		public void getProtocolBehavior_empty_empty_r() {
			doTest("ROLE1ProtocolReactiveBehavior", "", "", this.role);
		}

		@Test
		@DisplayName("(\"\", \"  \", null)")
		public void getProtocolBehavior_empty_space_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolReactiveBehavior", "", "  ", null);
			});
		}

		@Test
		@DisplayName("(\"\", \"  \", r)")
		public void getProtocolBehavior_empty_space_r() {
			doTest("ROLE1ProtocolReactiveBehavior", "", "  ", this.role);
		}

		@Test
		@DisplayName("(\"\", \"abc\", null)")
		public void getProtocolBehavior_empty_abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolReactiveBehavior", "", "abc", null);
			});
		}

		@Test
		@DisplayName("(\"\", \"abc\", r)")
		public void getProtocolBehavior_empty_abc_r() {
			doTest("ROLE1ProtocolReactiveBehavior", "", "abc", this.role);
		}

		@Test
		@DisplayName("(\"\", \"Abc\", null)")
		public void getProtocolBehavior_empty_Abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolReactiveBehavior", "", "Abc", null);
			});
		}

		@Test
		@DisplayName("(\"\", \"Abc\", r)")
		public void getProtocolBehavior_empty_Abc_r() {
			doTest("ROLE1ProtocolReactiveBehavior", "", "Abc", this.role);
		}

		@Test
		@DisplayName("(\"  \", null, null)")
		public void getProtocolBehavior_space_null_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolReactiveBehavior", "  ", null, null);
			});
		}

		@Test
		@DisplayName("(\"  \", null, r)")
		public void getProtocolBehavior_space_null_r() {
			doTest("ROLE1ProtocolReactiveBehavior", "  ", null, this.role);
		}

		@Test
		@DisplayName("(\"  \", \"\", null)")
		public void getProtocolBehavior_space_empty_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolReactiveBehavior", "  ", "", null);
			});
		}

		@Test
		@DisplayName("(\"  \", \"\", r)")
		public void getProtocolBehavior_space_empty_r() {
			doTest("ROLE1ProtocolReactiveBehavior", "  ", "", this.role);
		}

		@Test
		@DisplayName("(\"  \", \"  \", null)")
		public void getProtocolBehavior_space_space_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolReactiveBehavior", "  ", "  ", null);
			});
		}

		@Test
		@DisplayName("(\"  \", \"  \", r)")
		public void getProtocolBehavior_space_space_r() {
			doTest("ROLE1ProtocolReactiveBehavior", "  ", "  ", this.role);
		}

		@Test
		@DisplayName("(\"  \", \"abc\", null)")
		public void getProtocolBehavior_space_abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolReactiveBehavior", "  ", "abc", null);
			});
		}

		@Test
		@DisplayName("(\"  \", \"abc\", r)")
		public void getProtocolBehavior_space_abc_r() {
			doTest("ROLE1ProtocolReactiveBehavior", "  ", "abc", this.role);
		}

		@Test
		@DisplayName("(\"  \", \"Abc\", null)")
		public void getProtocolBehavior_space_Abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolReactiveBehavior", "  ", "Abc", null);
			});
		}

		@Test
		@DisplayName("(\"  \", \"Abc\", r)")
		public void getProtocolBehavior_space_Abc_r() {
			doTest("ROLE1ProtocolReactiveBehavior", "  ", "Abc", this.role);
		}

		@Test
		@DisplayName("(\"abc\", null, null)")
		public void getProtocolBehavior_abc_null_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("abc.$Anonymous$ProtocolReactiveBehavior", "abc", null, null);
			});
		}

		@Test
		@DisplayName("(\"abc\", null, r)")
		public void getProtocolBehavior_abc_null_r() {
			doTest("abc.ROLE1ProtocolReactiveBehavior", "abc", null, this.role);
		}

		@Test
		@DisplayName("(\"abc\", \"\", null)")
		public void getProtocolBehavior_abc_empty_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("abc.$Anonymous$ProtocolReactiveBehavior", "abc", "", null);
			});
		}

		@Test
		@DisplayName("(\"abc\", \"\", r)")
		public void getProtocolBehavior_abc_empty_r() {
			doTest("abc.ROLE1ProtocolReactiveBehavior", "abc", "", this.role);
		}

		@Test
		@DisplayName("(\"abc\", \"  \", null)")
		public void getProtocolBehavior_abc_space_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("abc.$Anonymous$ProtocolReactiveBehavior", "abc", "  ", null);
			});
		}

		@Test
		@DisplayName("(\"abc\", \"  \", r)")
		public void getProtocolBehavior_abc_space_r() {
			doTest("abc.ROLE1ProtocolReactiveBehavior", "abc", "  ", this.role);
		}

		@Test
		@DisplayName("(\"abc\", \"abc\", null)")
		public void getProtocolBehavior_abc_abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("abc.$Anonymous$ProtocolReactiveBehavior", "abc", "abc", null);
			});
		}

		@Test
		@DisplayName("(\"abc\", \"abc\", r)")
		public void getProtocolBehavior_abc_abc_r() {
			doTest("abc.ROLE1ProtocolReactiveBehavior", "abc", "abc", this.role);
		}

		@Test
		@DisplayName("(\"abc\", \"Abc\", null)")
		public void getProtocolBehavior_abc_Abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("abc.$Anonymous$ProtocolReactiveBehavior", "abc", "Abc", null);
			});
		}

		@Test
		@DisplayName("(\"abc\", \"Abc\", r)")
		public void getProtocolBehavior_abc_Abc_r() {
			doTest("abc.ROLE1ProtocolReactiveBehavior", "abc", "Abc", this.role);
		}

		@Test
		@DisplayName("(\"Abc\", null, null)")
		public void getProtocolBehavior_Abc_null_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("Abc.$Anonymous$ProtocolReactiveBehavior", "Abc", null, null);
			});
		}

		@Test
		@DisplayName("(\"Abc\", null, r)")
		public void getProtocolBehavior_Abc_null_r() {
			doTest("Abc.ROLE1ProtocolReactiveBehavior", "Abc", null, this.role);
		}

		@Test
		@DisplayName("(\"Abc\", \"\", null)")
		public void getProtocolBehavior_Abc_empty_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("Abc.$Anonymous$ProtocolReactiveBehavior", "Abc", "", null);
			});
		}

		@Test
		@DisplayName("(\"Abc\", \"\", r)")
		public void getProtocolBehavior_Abc_empty_r() {
			doTest("Abc.ROLE1ProtocolReactiveBehavior", "Abc", "", this.role);
		}

		@Test
		@DisplayName("(\"Abc\", \"  \", null)")
		public void getProtocolBehavior_Abc_space_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("$Anonymous$ProtocolReactiveBehavior", "Abc", "  ", null);
			});
		}

		@Test
		@DisplayName("(\"Abc\", \"  \", r)")
		public void getProtocolBehavior_Abc_space_r() {
			doTest("Abc.ROLE1ProtocolReactiveBehavior", "Abc", "  ", this.role);
		}

		@Test
		@DisplayName("(\"Abc\", \"abc\", null)")
		public void getProtocolBehavior_Abc_abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("Abc.$Anonymous$ProtocolReactiveBehavior", "Abc", "abc", null);
			});
		}

		@Test
		@DisplayName("(\"Abc\", \"abc\", r)")
		public void getProtocolBehavior_Abc_abc_r() {
			doTest("Abc.ROLE1ProtocolReactiveBehavior", "Abc", "abc", this.role);
		}

		@Test
		@DisplayName("(\"Abc\", \"Abc\", null)")
		public void getProtocolBehavior_Abc_Abc_null() {
			assertThrows(NullPointerException.class, () -> {
				doTest("Abc.$Anonymous$ProtocolReactiveBehavior", "Abc", "Abc", null);
			});
		}

		@Test
		@DisplayName("(\"Abc\", \"Abc\", r)")
		public void getProtocolBehavior_Abc_Abc_r() {
			doTest("Abc.ROLE1ProtocolReactiveBehavior", "Abc", "Abc", this.role);
		}

	}
	
	@DisplayName("getEnabledMessageListFunctionName")
	@Nested
	public class GetEnabledMessageListFunctionNameTest {

		private DefaultProtocolNames test;
	
		@BeforeEach
		public void setUp() {
			this.test = new DefaultProtocolNames();
		}

		@Test
		@DisplayName("(null)")
		public void getEnabledMessageListFunctionName_null() {
			assertEquals("getEnabled$Anonymous$Messages", this.test.getEnabledMessageListFunctionName(null));
		}

		@Test
		@DisplayName("(\"\")")
		public void getEnabledMessageListFunctionName_empty() {
			assertEquals("getEnabled$Anonymous$Messages", this.test.getEnabledMessageListFunctionName(""));
		}

		@Test
		@DisplayName("(\"  \")")
		public void getEnabledMessageListFunctionName_space() {
			assertEquals("getEnabled$Anonymous$Messages", this.test.getEnabledMessageListFunctionName("  "));
		}

		@Test
		@DisplayName("(\"abc\")")
		public void getEnabledMessageListFunctionName_abc() {
			assertEquals("getEnabledabcMessages", this.test.getEnabledMessageListFunctionName("abc"));
		}

		@Test
		@DisplayName("(\"Abc\")")
		public void getEnabledMessageListFunctionName_Abc() {
			assertEquals("getEnabledAbcMessages", this.test.getEnabledMessageListFunctionName("Abc"));
		}

	}
	
	@DisplayName("getProtocolMessageQualifiedName")
	@Nested
	public class GetProtocolMessageQualifiedNameTest {

		private DefaultProtocolNames test;
	
		@BeforeEach
		public void setUp() {
			this.test = new DefaultProtocolNames();
		}

		@Test
		@DisplayName("(null, null, null)")
		public void getProtocolMessageQualifiedName_null_null_null() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName(null, null, null));
		}

		@Test
		@DisplayName("(null, null, \"\")")
		public void getProtocolMessageQualifiedName_null_null_empty() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName(null, null, ""));
		}

		@Test
		@DisplayName("(null, null, \"  \")")
		public void getProtocolMessageQualifiedName_null_null_space() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName(null, null, "  "));
		}

		@Test
		@DisplayName("(null, null, \"abc\")")
		public void getProtocolMessageQualifiedName_null_null_abc() {
			assertEquals("$Anonymous$.messages.abc", this.test.getProtocolMessageQualifiedName(null, null, "abc"));
		}

		@Test
		@DisplayName("(null, null, \"Abc\")")
		public void getProtocolMessageQualifiedName_null_null_Abc() {
			assertEquals("$Anonymous$.messages.Abc", this.test.getProtocolMessageQualifiedName(null, null, "Abc"));
		}

		@Test
		@DisplayName("(null, \"\", null)")
		public void getProtocolMessageQualifiedName_null_empty_null() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName(null, "", null));
		}

		@Test
		@DisplayName("(null, \"\", \"\")")
		public void getProtocolMessageQualifiedName_null_empty_empty() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName(null, "", ""));
		}

		@Test
		@DisplayName("(null, \"\", \"  \")")
		public void getProtocolMessageQualifiedName_null_empty_space() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName(null, "", "  "));
		}

		@Test
		@DisplayName("(null, \"\", \"abc\")")
		public void getProtocolMessageQualifiedName_null_empty_abc() {
			assertEquals("$Anonymous$.messages.abc", this.test.getProtocolMessageQualifiedName(null, "", "abc"));
		}

		@Test
		@DisplayName("(null, \"\", \"Abc\")")
		public void getProtocolMessageQualifiedName_null_empty_Abc() {
			assertEquals("$Anonymous$.messages.Abc", this.test.getProtocolMessageQualifiedName(null, "", "Abc"));
		}

		@Test
		@DisplayName("(null, \"  \", null)")
		public void getProtocolMessageQualifiedName_null_space_null() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName(null, "  ", null));
		}

		@Test
		@DisplayName("(null, \"  \", \"\")")
		public void getProtocolMessageQualifiedName_null_space_empty() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName(null, "  ", ""));
		}

		@Test
		@DisplayName("(null, \"  \", \"  \")")
		public void getProtocolMessageQualifiedName_null_space_space() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName(null, "  ", "  "));
		}

		@Test
		@DisplayName("(null, \"  \", \"abc\")")
		public void getProtocolMessageQualifiedName_null_space_abc() {
			assertEquals("$Anonymous$.messages.abc", this.test.getProtocolMessageQualifiedName(null, "  ", "abc"));
		}

		@Test
		@DisplayName("(null, \"  \", \"Abc\")")
		public void getProtocolMessageQualifiedName_null_space_Abc() {
			assertEquals("$Anonymous$.messages.Abc", this.test.getProtocolMessageQualifiedName(null, "  ", "Abc"));
		}

		@Test
		@DisplayName("(null, \"abc\", null)")
		public void getProtocolMessageQualifiedName_null_abc_null() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName(null, "abc", null));
		}

		@Test
		@DisplayName("(null, \"abc\", \"\")")
		public void getProtocolMessageQualifiedName_null_abc_empty() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName(null, "abc", ""));
		}

		@Test
		@DisplayName("(null, \"abc\", \"  \")")
		public void getProtocolMessageQualifiedName_null_abc_space() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName(null, "abc", "  "));
		}

		@Test
		@DisplayName("(null, \"abc\", \"abc\")")
		public void getProtocolMessageQualifiedName_null_abc_abc() {
			assertEquals("$Anonymous$.messages.abc", this.test.getProtocolMessageQualifiedName(null, "abc", "abc"));
		}

		@Test
		@DisplayName("(null, \"abc\", \"Abc\")")
		public void getProtocolMessageQualifiedName_null_abc_Abc() {
			assertEquals("$Anonymous$.messages.Abc", this.test.getProtocolMessageQualifiedName(null, "abc", "Abc"));
		}

		@Test
		@DisplayName("(null, \"Abc\", null)")
		public void getProtocolMessageQualifiedName_null_Abc_null() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName(null, "Abc", null));
		}

		@Test
		@DisplayName("(null, \"Abc\", \"\")")
		public void getProtocolMessageQualifiedName_null_Abc_empty() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName(null, "Abc", ""));
		}

		@Test
		@DisplayName("(null, \"Abc\", \"  \")")
		public void getProtocolMessageQualifiedName_null_Abc_space() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName(null, "Abc", "  "));
		}

		@Test
		@DisplayName("(null, \"Abc\", \"abc\")")
		public void getProtocolMessageQualifiedName_null_Abc_abc() {
			assertEquals("$Anonymous$.messages.abc", this.test.getProtocolMessageQualifiedName(null, "Abc", "abc"));
		}

		@Test
		@DisplayName("(null, \"Abc\", \"Abc\")")
		public void getProtocolMessageQualifiedName_null_Abc_Abc() {
			assertEquals("$Anonymous$.messages.Abc", this.test.getProtocolMessageQualifiedName(null, "Abc", "Abc"));
		}

		@Test
		@DisplayName("(\"\", null, null)")
		public void getProtocolMessageQualifiedName_empty_null_null() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("", null, null));
		}

		@Test
		@DisplayName("(\"\", null, \"\")")
		public void getProtocolMessageQualifiedName_empty_null_empty() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("", null, ""));
		}

		@Test
		@DisplayName("(\"\", null, \"  \")")
		public void getProtocolMessageQualifiedName_empty_null_space() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("", null, "  "));
		}

		@Test
		@DisplayName("(\"\", null, \"abc\")")
		public void getProtocolMessageQualifiedName_empty_null_abc() {
			assertEquals("$Anonymous$.messages.abc", this.test.getProtocolMessageQualifiedName("", null, "abc"));
		}

		@Test
		@DisplayName("(\"\", null, \"Abc\")")
		public void getProtocolMessageQualifiedName_empty_null_Abc() {
			assertEquals("$Anonymous$.messages.Abc", this.test.getProtocolMessageQualifiedName("", null, "Abc"));
		}

		@Test
		@DisplayName("(\"\", \"\", null)")
		public void getProtocolMessageQualifiedName_empty_empty_null() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("", "", null));
		}

		@Test
		@DisplayName("(\"\", \"\", \"\")")
		public void getProtocolMessageQualifiedName_empty_empty_empty() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("", "", ""));
		}

		@Test
		@DisplayName("(\"\", \"\", \"  \")")
		public void getProtocolMessageQualifiedName_empty_empty_space() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("", "", "  "));
		}

		@Test
		@DisplayName("(\"\", \"\", \"abc\")")
		public void getProtocolMessageQualifiedName_empty_empty_abc() {
			assertEquals("$Anonymous$.messages.abc", this.test.getProtocolMessageQualifiedName("", "", "abc"));
		}

		@Test
		@DisplayName("(\"\", \"\", \"Abc\")")
		public void getProtocolMessageQualifiedName_empty_empty_Abc() {
			assertEquals("$Anonymous$.messages.Abc", this.test.getProtocolMessageQualifiedName("", "", "Abc"));
		}

		@Test
		@DisplayName("(\"\", \"  \", null)")
		public void getProtocolMessageQualifiedName_empty_space_null() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("", "  ", null));
		}

		@Test
		@DisplayName("(\"\", \"  \", \"\")")
		public void getProtocolMessageQualifiedName_empty_space_empty() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("", "  ", ""));
		}

		@Test
		@DisplayName("(\"\", \"  \", \"  \")")
		public void getProtocolMessageQualifiedName_empty_space_space() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("", "  ", "  "));
		}

		@Test
		@DisplayName("(\"\", \"  \", \"abc\")")
		public void getProtocolMessageQualifiedName_empty_space_abc() {
			assertEquals("$Anonymous$.messages.abc", this.test.getProtocolMessageQualifiedName("", "  ", "abc"));
		}

		@Test
		@DisplayName("(\"\", \"  \", \"Abc\")")
		public void getProtocolMessageQualifiedName_empty_space_Abc() {
			assertEquals("$Anonymous$.messages.Abc", this.test.getProtocolMessageQualifiedName("", "  ", "Abc"));
		}

		@Test
		@DisplayName("(\"\", \"abc\", null)")
		public void getProtocolMessageQualifiedName_empty_abc_null() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("", "abc", null));
		}

		@Test
		@DisplayName("(\"\", \"abc\", \"\")")
		public void getProtocolMessageQualifiedName_empty_abc_empty() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("", "abc", ""));
		}

		@Test
		@DisplayName("(\"\", \"abc\", \"  \")")
		public void getProtocolMessageQualifiedName_empty_abc_space() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("", "abc", "  "));
		}

		@Test
		@DisplayName("(\"\", \"abc\", \"abc\")")
		public void getProtocolMessageQualifiedName_empty_abc_abc() {
			assertEquals("$Anonymous$.messages.abc", this.test.getProtocolMessageQualifiedName("", "abc", "abc"));
		}

		@Test
		@DisplayName("(\"\", \"abc\", \"Abc\")")
		public void getProtocolMessageQualifiedName_empty_abc_Abc() {
			assertEquals("$Anonymous$.messages.Abc", this.test.getProtocolMessageQualifiedName("", "abc", "Abc"));
		}

		@Test
		@DisplayName("(\"\", \"Abc\", null)")
		public void getProtocolMessageQualifiedName_empty_Abc_null() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("", "Abc", null));
		}

		@Test
		@DisplayName("(\"\", \"Abc\", \"\")")
		public void getProtocolMessageQualifiedName_empty_Abc_empty() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("", "Abc", ""));
		}

		@Test
		@DisplayName("(\"\", \"Abc\", \"  \")")
		public void getProtocolMessageQualifiedName_empty_Abc_space() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("", "Abc", "  "));
		}

		@Test
		@DisplayName("(\"\", \"Abc\", \"abc\")")
		public void getProtocolMessageQualifiedName_empty_Abc_abc() {
			assertEquals("$Anonymous$.messages.abc", this.test.getProtocolMessageQualifiedName("", "Abc", "abc"));
		}

		@Test
		@DisplayName("(\"\", \"Abc\", \"Abc\")")
		public void getProtocolMessageQualifiedName_empty_Abc_Abc() {
			assertEquals("$Anonymous$.messages.Abc", this.test.getProtocolMessageQualifiedName("", "Abc", "Abc"));
		}

		@Test
		@DisplayName("(\"  \", null, null)")
		public void getProtocolMessageQualifiedName_space_null_null() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("  ", null, null));
		}

		@Test
		@DisplayName("(\"  \", null, \"\")")
		public void getProtocolMessageQualifiedName_space_null_empty() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("  ", null, ""));
		}

		@Test
		@DisplayName("(\"  \", null, \"  \")")
		public void getProtocolMessageQualifiedName_space_null_space() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("  ", null, "  "));
		}

		@Test
		@DisplayName("(\"  \", null, \"abc\")")
		public void getProtocolMessageQualifiedName_space_null_abc() {
			assertEquals("$Anonymous$.messages.abc", this.test.getProtocolMessageQualifiedName("  ", null, "abc"));
		}

		@Test
		@DisplayName("(\"  \", null, \"Abc\")")
		public void getProtocolMessageQualifiedName_space_null_Abc() {
			assertEquals("$Anonymous$.messages.Abc", this.test.getProtocolMessageQualifiedName("  ", null, "Abc"));
		}

		@Test
		@DisplayName("(\"  \", \"\", null)")
		public void getProtocolMessageQualifiedName_space_empty_null() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("  ", "", null));
		}

		@Test
		@DisplayName("(\"  \", \"\", \"\")")
		public void getProtocolMessageQualifiedName_space_empty_empty() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("  ", "", ""));
		}

		@Test
		@DisplayName("(\"  \", \"\", \"  \")")
		public void getProtocolMessageQualifiedName_space_empty_space() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("  ", "", "  "));
		}

		@Test
		@DisplayName("(\"  \", \"\", \"abc\")")
		public void getProtocolMessageQualifiedName_space_empty_abc() {
			assertEquals("$Anonymous$.messages.abc", this.test.getProtocolMessageQualifiedName("  ", "", "abc"));
		}

		@Test
		@DisplayName("(\"  \", \"\", \"Abc\")")
		public void getProtocolMessageQualifiedName_space_empty_Abc() {
			assertEquals("$Anonymous$.messages.Abc", this.test.getProtocolMessageQualifiedName("  ", "", "Abc"));
		}

		@Test
		@DisplayName("(\"  \", \"  \", null)")
		public void getProtocolMessageQualifiedName_space_space_null() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("  ", "  ", null));
		}

		@Test
		@DisplayName("(\"  \", \"  \", \"\")")
		public void getProtocolMessageQualifiedName_space_space_empty() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("  ", "  ", ""));
		}

		@Test
		@DisplayName("(\"  \", \"  \", \"  \")")
		public void getProtocolMessageQualifiedName_space_space_space() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("  ", "  ", "  "));
		}

		@Test
		@DisplayName("(\"  \", \"  \", \"abc\")")
		public void getProtocolMessageQualifiedName_space_space_abc() {
			assertEquals("$Anonymous$.messages.abc", this.test.getProtocolMessageQualifiedName("  ", "  ", "abc"));
		}

		@Test
		@DisplayName("(\"  \", \"  \", \"Abc\")")
		public void getProtocolMessageQualifiedName_space_space_Abc() {
			assertEquals("$Anonymous$.messages.Abc", this.test.getProtocolMessageQualifiedName("  ", "  ", "Abc"));
		}

		@Test
		@DisplayName("(\"  \", \"abc\", null)")
		public void getProtocolMessageQualifiedName_space_abc_null() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("  ", "abc", null));
		}

		@Test
		@DisplayName("(\"  \", \"abc\", \"\")")
		public void getProtocolMessageQualifiedName_space_abc_empty() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("  ", "abc", ""));
		}

		@Test
		@DisplayName("(\"  \", \"abc\", \"  \")")
		public void getProtocolMessageQualifiedName_space_abc_space() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("  ", "abc", "  "));
		}

		@Test
		@DisplayName("(\"  \", \"abc\", \"abc\")")
		public void getProtocolMessageQualifiedName_space_abc_abc() {
			assertEquals("$Anonymous$.messages.abc", this.test.getProtocolMessageQualifiedName("  ", "abc", "abc"));
		}

		@Test
		@DisplayName("(\"  \", \"abc\", \"Abc\")")
		public void getProtocolMessageQualifiedName_space_abc_Abc() {
			assertEquals("$Anonymous$.messages.Abc", this.test.getProtocolMessageQualifiedName("  ", "abc", "Abc"));
		}

		@Test
		@DisplayName("(\"  \", \"Abc\", null)")
		public void getProtocolMessageQualifiedName_space_Abc_null() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("  ", "Abc", null));
		}

		@Test
		@DisplayName("(\"  \", \"Abc\", \"\")")
		public void getProtocolMessageQualifiedName_space_Abc_empty() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("  ", "Abc", ""));
		}

		@Test
		@DisplayName("(\"  \", \"Abc\", \"  \")")
		public void getProtocolMessageQualifiedName_space_Abc_space() {
			assertEquals("$Anonymous$.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("  ", "Abc", "  "));
		}

		@Test
		@DisplayName("(\"  \", \"Abc\", \"abc\")")
		public void getProtocolMessageQualifiedName_space_Abc_abc() {
			assertEquals("$Anonymous$.messages.abc", this.test.getProtocolMessageQualifiedName("  ", "Abc", "abc"));
		}

		@Test
		@DisplayName("(\"  \", \"Abc\", \"Abc\")")
		public void getProtocolMessageQualifiedName_space_Abc_Abc() {
			assertEquals("$Anonymous$.messages.Abc", this.test.getProtocolMessageQualifiedName("  ", "Abc", "Abc"));
		}

		@Test
		@DisplayName("(\"abc\", null, null)")
		public void getProtocolMessageQualifiedName_abc_null_null() {
			assertEquals("abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("abc", null, null));
		}

		@Test
		@DisplayName("(\"abc\", null, \"\")")
		public void getProtocolMessageQualifiedName_abc_null_empty() {
			assertEquals("abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("abc", null, ""));
		}

		@Test
		@DisplayName("(\"abc\", null, \"  \")")
		public void getProtocolMessageQualifiedName_abc_null_space() {
			assertEquals("abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("abc", null, "  "));
		}

		@Test
		@DisplayName("(\"abc\", null, \"abc\")")
		public void getProtocolMessageQualifiedName_abc_null_abc() {
			assertEquals("abc.messages.abc", this.test.getProtocolMessageQualifiedName("abc", null, "abc"));
		}

		@Test
		@DisplayName("(\"abc\", null, \"Abc\")")
		public void getProtocolMessageQualifiedName_abc_null_Abc() {
			assertEquals("abc.messages.Abc", this.test.getProtocolMessageQualifiedName("abc", null, "Abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"\", null)")
		public void getProtocolMessageQualifiedName_abc_empty_null() {
			assertEquals("abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("abc", "", null));
		}

		@Test
		@DisplayName("(\"abc\", \"\", \"\")")
		public void getProtocolMessageQualifiedName_abc_empty_empty() {
			assertEquals("abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("abc", "", ""));
		}

		@Test
		@DisplayName("(\"abc\", \"\", \"  \")")
		public void getProtocolMessageQualifiedName_abc_empty_space() {
			assertEquals("abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("abc", "", "  "));
		}

		@Test
		@DisplayName("(\"abc\", \"\", \"abc\")")
		public void getProtocolMessageQualifiedName_abc_empty_abc() {
			assertEquals("abc.messages.abc", this.test.getProtocolMessageQualifiedName("abc", "", "abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"\", \"Abc\")")
		public void getProtocolMessageQualifiedName_abc_empty_Abc() {
			assertEquals("abc.messages.Abc", this.test.getProtocolMessageQualifiedName("abc", "", "Abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"  \", null)")
		public void getProtocolMessageQualifiedName_abc_space_null() {
			assertEquals("abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("abc", "  ", null));
		}

		@Test
		@DisplayName("(\"abc\", \"  \", \"\")")
		public void getProtocolMessageQualifiedName_abc_space_empty() {
			assertEquals("abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("abc", "  ", ""));
		}

		@Test
		@DisplayName("(\"abc\", \"  \", \"  \")")
		public void getProtocolMessageQualifiedName_abc_space_space() {
			assertEquals("abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("abc", "  ", "  "));
		}

		@Test
		@DisplayName("(\"abc\", \"  \", \"abc\")")
		public void getProtocolMessageQualifiedName_abc_space_abc() {
			assertEquals("abc.messages.abc", this.test.getProtocolMessageQualifiedName("abc", "  ", "abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"  \", \"Abc\")")
		public void getProtocolMessageQualifiedName_abc_space_Abc() {
			assertEquals("abc.messages.Abc", this.test.getProtocolMessageQualifiedName("abc", "  ", "Abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"abc\", null)")
		public void getProtocolMessageQualifiedName_abc_abc_null() {
			assertEquals("abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("abc", "abc", null));
		}

		@Test
		@DisplayName("(\"abc\", \"abc\", \"\")")
		public void getProtocolMessageQualifiedName_abc_abc_empty() {
			assertEquals("abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("abc", "abc", ""));
		}

		@Test
		@DisplayName("(\"abc\", \"abc\", \"  \")")
		public void getProtocolMessageQualifiedName_abc_abc_space() {
			assertEquals("abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("abc", "abc", "  "));
		}

		@Test
		@DisplayName("(\"abc\", \"abc\", \"abc\")")
		public void getProtocolMessageQualifiedName_abc_abc_abc() {
			assertEquals("abc.messages.abc", this.test.getProtocolMessageQualifiedName("abc", "abc", "abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"abc\", \"Abc\")")
		public void getProtocolMessageQualifiedName_abc_abc_Abc() {
			assertEquals("abc.messages.Abc", this.test.getProtocolMessageQualifiedName("abc", "abc", "Abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"Abc\", null)")
		public void getProtocolMessageQualifiedName_abc_Abc_null() {
			assertEquals("abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("abc", "Abc", null));
		}

		@Test
		@DisplayName("(\"abc\", \"Abc\", \"\")")
		public void getProtocolMessageQualifiedName_abc_Abc_empty() {
			assertEquals("abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("abc", "Abc", ""));
		}

		@Test
		@DisplayName("(\"abc\", \"Abc\", \"  \")")
		public void getProtocolMessageQualifiedName_abc_Abc_space() {
			assertEquals("abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("abc", "Abc", "  "));
		}

		@Test
		@DisplayName("(\"abc\", \"Abc\", \"abc\")")
		public void getProtocolMessageQualifiedName_abc_Abc_abc() {
			assertEquals("abc.messages.abc", this.test.getProtocolMessageQualifiedName("abc", "Abc", "abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"Abc\", \"Abc\")")
		public void getProtocolMessageQualifiedName_abc_Abc_Abc() {
			assertEquals("abc.messages.Abc", this.test.getProtocolMessageQualifiedName("abc", "Abc", "Abc"));
		}

		@Test
		@DisplayName("(\"Abc\", null, null)")
		public void getProtocolMessageQualifiedName_Abc_null_null() {
			assertEquals("Abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("Abc", null, null));
		}

		@Test
		@DisplayName("(\"Abc\", null, \"\")")
		public void getProtocolMessageQualifiedName_Abc_null_empty() {
			assertEquals("Abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("Abc", null, ""));
		}

		@Test
		@DisplayName("(\"Abc\", null, \"  \")")
		public void getProtocolMessageQualifiedName_Abc_null_space() {
			assertEquals("Abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("Abc", null, "  "));
		}

		@Test
		@DisplayName("(\"Abc\", null, \"abc\")")
		public void getProtocolMessageQualifiedName_Abc_null_abc() {
			assertEquals("Abc.messages.abc", this.test.getProtocolMessageQualifiedName("Abc", null, "abc"));
		}

		@Test
		@DisplayName("(\"Abc\", null, \"Abc\")")
		public void getProtocolMessageQualifiedName_Abc_null_Abc() {
			assertEquals("Abc.messages.Abc", this.test.getProtocolMessageQualifiedName("Abc", null, "Abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"\", null)")
		public void getProtocolMessageQualifiedName_Abc_empty_null() {
			assertEquals("Abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("Abc", "", null));
		}

		@Test
		@DisplayName("(\"Abc\", \"\", \"\")")
		public void getProtocolMessageQualifiedName_Abc_empty_empty() {
			assertEquals("Abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("Abc", "", ""));
		}

		@Test
		@DisplayName("(\"Abc\", \"\", \"  \")")
		public void getProtocolMessageQualifiedName_Abc_empty_space() {
			assertEquals("Abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("Abc", "", "  "));
		}

		@Test
		@DisplayName("(\"Abc\", \"\", \"abc\")")
		public void getProtocolMessageQualifiedName_Abc_empty_abc() {
			assertEquals("Abc.messages.abc", this.test.getProtocolMessageQualifiedName("Abc", "", "abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"\", \"Abc\")")
		public void getProtocolMessageQualifiedName_Abc_empty_Abc() {
			assertEquals("Abc.messages.Abc", this.test.getProtocolMessageQualifiedName("Abc", "", "Abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"  \", null)")
		public void getProtocolMessageQualifiedName_Abc_space_null() {
			assertEquals("Abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("Abc", "  ", null));
		}

		@Test
		@DisplayName("(\"Abc\", \"  \", \"\")")
		public void getProtocolMessageQualifiedName_Abc_space_empty() {
			assertEquals("Abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("Abc", "  ", ""));
		}

		@Test
		@DisplayName("(\"Abc\", \"  \", \"  \")")
		public void getProtocolMessageQualifiedName_Abc_space_space() {
			assertEquals("Abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("Abc", "  ", "  "));
		}

		@Test
		@DisplayName("(\"Abc\", \"  \", \"abc\")")
		public void getProtocolMessageQualifiedName_Abc_space_abc() {
			assertEquals("Abc.messages.abc", this.test.getProtocolMessageQualifiedName("Abc", "  ", "abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"  \", \"Abc\")")
		public void getProtocolMessageQualifiedName_Abc_space_Abc() {
			assertEquals("Abc.messages.Abc", this.test.getProtocolMessageQualifiedName("Abc", "  ", "Abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"abc\", null)")
		public void getProtocolMessageQualifiedName_Abc_abc_null() {
			assertEquals("Abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("Abc", "abc", null));
		}

		@Test
		@DisplayName("(\"Abc\", \"abc\", \"\")")
		public void getProtocolMessageQualifiedName_Abc_abc_empty() {
			assertEquals("Abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("Abc", "abc", ""));
		}

		@Test
		@DisplayName("(\"Abc\", \"abc\", \"  \")")
		public void getProtocolMessageQualifiedName_Abc_abc_space() {
			assertEquals("Abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("Abc", "abc", "  "));
		}

		@Test
		@DisplayName("(\"Abc\", \"abc\", \"abc\")")
		public void getProtocolMessageQualifiedName_Abc_abc_abc() {
			assertEquals("Abc.messages.abc", this.test.getProtocolMessageQualifiedName("Abc", "abc", "abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"abc\", \"Abc\")")
		public void getProtocolMessageQualifiedName_Abc_abc_Abc() {
			assertEquals("Abc.messages.Abc", this.test.getProtocolMessageQualifiedName("Abc", "abc", "Abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"Abc\", null)")
		public void getProtocolMessageQualifiedName_Abc_Abc_null() {
			assertEquals("Abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("Abc", "Abc", null));
		}

		@Test
		@DisplayName("(\"Abc\", \"Abc\", \"\")")
		public void getProtocolMessageQualifiedName_Abc_Abc_empty() {
			assertEquals("Abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("Abc", "Abc", ""));
		}

		@Test
		@DisplayName("(\"Abc\", \"Abc\", \"  \")")
		public void getProtocolMessageQualifiedName_Abc_Abc_space() {
			assertEquals("Abc.messages.$Anonymous$", this.test.getProtocolMessageQualifiedName("Abc", "Abc", "  "));
		}

		@Test
		@DisplayName("(\"Abc\", \"Abc\", \"abc\")")
		public void getProtocolMessageQualifiedName_Abc_Abc_abc() {
			assertEquals("Abc.messages.abc", this.test.getProtocolMessageQualifiedName("Abc", "Abc", "abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"Abc\", \"Abc\")")
		public void getProtocolMessageQualifiedName_Abc_Abc_Abc() {
			assertEquals("Abc.messages.Abc", this.test.getProtocolMessageQualifiedName("Abc", "Abc", "Abc"));
		}

	}
	
	@DisplayName("getProtocolMessagePackageName")
	@Nested
	public class GetProtocolMessagePackageNameTest {

		private DefaultProtocolNames test;
	
		@BeforeEach
		public void setUp() {
			this.test = new DefaultProtocolNames();
		}

		@Test
		@DisplayName("(null, null, null)")
		public void getProtocolMessageName_null_null_null() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, null, null));
		}

		@Test
		@DisplayName("(null, null, \"\")")
		public void getProtocolMessageName_null_null_empty() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, null, ""));
		}

		@Test
		@DisplayName("(null, null, \"  \")")
		public void getProtocolMessageName_null_null_space() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, null, "  "));
		}

		@Test
		@DisplayName("(null, null, \"abc\")")
		public void getProtocolMessageName_null_null_abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, null, "abc"));
		}

		@Test
		@DisplayName("(null, null, \"Abc\")")
		public void getProtocolMessageName_null_null_Abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, null, "Abc"));
		}

		@Test
		@DisplayName("(null, \"\", null)")
		public void getProtocolMessageName_null_empty_null() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, "", null));
		}

		@Test
		@DisplayName("(null, \"\", \"\")")
		public void getProtocolMessageName_null_empty_empty() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, "", ""));
		}

		@Test
		@DisplayName("(null, \"\", \"  \")")
		public void getProtocolMessageName_null_empty_space() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, "", "  "));
		}

		@Test
		@DisplayName("(null, \"\", \"abc\")")
		public void getProtocolMessageName_null_empty_abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, "", "abc"));
		}

		@Test
		@DisplayName("(null, \"\", \"Abc\")")
		public void getProtocolMessageName_null_empty_Abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, "", "Abc"));
		}

		@Test
		@DisplayName("(null, \"  \", null)")
		public void getProtocolMessageName_null_space_null() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, "  ", null));
		}

		@Test
		@DisplayName("(null, \"  \", \"\")")
		public void getProtocolMessageName_null_space_empty() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, "  ", ""));
		}

		@Test
		@DisplayName("(null, \"  \", \"  \")")
		public void getProtocolMessageName_null_space_space() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, "  ", "  "));
		}

		@Test
		@DisplayName("(null, \"  \", \"abc\")")
		public void getProtocolMessageName_null_space_abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, "  ", "abc"));
		}

		@Test
		@DisplayName("(null, \"  \", \"Abc\")")
		public void getProtocolMessageName_null_space_Abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, "  ", "Abc"));
		}

		@Test
		@DisplayName("(null, \"abc\", null)")
		public void getProtocolMessageName_null_abc_null() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, "abc", null));
		}

		@Test
		@DisplayName("(null, \"abc\", \"\")")
		public void getProtocolMessageName_null_abc_empty() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, "abc", ""));
		}

		@Test
		@DisplayName("(null, \"abc\", \"  \")")
		public void getProtocolMessageName_null_abc_space() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, "abc", "  "));
		}

		@Test
		@DisplayName("(null, \"abc\", \"abc\")")
		public void getProtocolMessageName_null_abc_abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, "abc", "abc"));
		}

		@Test
		@DisplayName("(null, \"abc\", \"Abc\")")
		public void getProtocolMessageName_null_abc_Abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, "abc", "Abc"));
		}

		@Test
		@DisplayName("(null, \"Abc\", null)")
		public void getProtocolMessageName_null_Abc_null() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, "Abc", null));
		}

		@Test
		@DisplayName("(null, \"Abc\", \"\")")
		public void getProtocolMessageName_null_Abc_empty() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, "Abc", ""));
		}

		@Test
		@DisplayName("(null, \"Abc\", \"  \")")
		public void getProtocolMessageName_null_Abc_space() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, "Abc", "  "));
		}

		@Test
		@DisplayName("(null, \"Abc\", \"abc\")")
		public void getProtocolMessageName_null_Abc_abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, "Abc", "abc"));
		}

		@Test
		@DisplayName("(null, \"Abc\", \"Abc\")")
		public void getProtocolMessageName_null_Abc_Abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName(null, "Abc", "Abc"));
		}

		@Test
		@DisplayName("(\"\", null, null)")
		public void getProtocolMessageName_empty_null_null() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", null, null));
		}

		@Test
		@DisplayName("(\"\", null, \"\")")
		public void getProtocolMessageName_empty_null_empty() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", null, ""));
		}

		@Test
		@DisplayName("(\"\", null, \"  \")")
		public void getProtocolMessageName_empty_null_space() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", null, "  "));
		}

		@Test
		@DisplayName("(\"\", null, \"abc\")")
		public void getProtocolMessageName_empty_null_abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", null, "abc"));
		}

		@Test
		@DisplayName("(\"\", null, \"Abc\")")
		public void getProtocolMessageName_empty_null_Abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", null, "Abc"));
		}

		@Test
		@DisplayName("(\"\", \"\", null)")
		public void getProtocolMessageName_empty_empty_null() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", "", null));
		}

		@Test
		@DisplayName("(\"\", \"\", \"\")")
		public void getProtocolMessageName_empty_empty_empty() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", "", ""));
		}

		@Test
		@DisplayName("(\"\", \"\", \"  \")")
		public void getProtocolMessageName_empty_empty_space() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", "", "  "));
		}

		@Test
		@DisplayName("(\"\", \"\", \"abc\")")
		public void getProtocolMessageName_empty_empty_abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", "", "abc"));
		}

		@Test
		@DisplayName("(\"\", \"\", \"Abc\")")
		public void getProtocolMessageName_empty_empty_Abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", "", "Abc"));
		}

		@Test
		@DisplayName("(\"\", \"  \", null)")
		public void getProtocolMessageName_empty_space_null() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", "  ", null));
		}

		@Test
		@DisplayName("(\"\", \"  \", \"\")")
		public void getProtocolMessageName_empty_space_empty() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", "  ", ""));
		}

		@Test
		@DisplayName("(\"\", \"  \", \"  \")")
		public void getProtocolMessageName_empty_space_space() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", "  ", "  "));
		}

		@Test
		@DisplayName("(\"\", \"  \", \"abc\")")
		public void getProtocolMessageName_empty_space_abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", "  ", "abc"));
		}

		@Test
		@DisplayName("(\"\", \"  \", \"Abc\")")
		public void getProtocolMessageName_empty_space_Abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", "  ", "Abc"));
		}

		@Test
		@DisplayName("(\"\", \"abc\", null)")
		public void getProtocolMessageName_empty_abc_null() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", "abc", null));
		}

		@Test
		@DisplayName("(\"\", \"abc\", \"\")")
		public void getProtocolMessageName_empty_abc_empty() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", "abc", ""));
		}

		@Test
		@DisplayName("(\"\", \"abc\", \"  \")")
		public void getProtocolMessageName_empty_abc_space() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", "abc", "  "));
		}

		@Test
		@DisplayName("(\"\", \"abc\", \"abc\")")
		public void getProtocolMessageName_empty_abc_abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", "abc", "abc"));
		}

		@Test
		@DisplayName("(\"\", \"abc\", \"Abc\")")
		public void getProtocolMessageName_empty_abc_Abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", "abc", "Abc"));
		}

		@Test
		@DisplayName("(\"\", \"Abc\", null)")
		public void getProtocolMessageName_empty_Abc_null() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", "Abc", null));
		}

		@Test
		@DisplayName("(\"\", \"Abc\", \"\")")
		public void getProtocolMessageName_empty_Abc_empty() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", "Abc", ""));
		}

		@Test
		@DisplayName("(\"\", \"Abc\", \"  \")")
		public void getProtocolMessageName_empty_Abc_space() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", "Abc", "  "));
		}

		@Test
		@DisplayName("(\"\", \"Abc\", \"abc\")")
		public void getProtocolMessageName_empty_Abc_abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", "Abc", "abc"));
		}

		@Test
		@DisplayName("(\"\", \"Abc\", \"Abc\")")
		public void getProtocolMessageName_empty_Abc_Abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("", "Abc", "Abc"));
		}

		@Test
		@DisplayName("(\"  \", null, null)")
		public void getProtocolMessageName_space_null_null() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", null, null));
		}

		@Test
		@DisplayName("(\"  \", null, \"\")")
		public void getProtocolMessageName_space_null_empty() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", null, ""));
		}

		@Test
		@DisplayName("(\"  \", null, \"  \")")
		public void getProtocolMessageName_space_null_space() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", null, "  "));
		}

		@Test
		@DisplayName("(\"  \", null, \"abc\")")
		public void getProtocolMessageName_space_null_abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", null, "abc"));
		}

		@Test
		@DisplayName("(\"  \", null, \"Abc\")")
		public void getProtocolMessageName_space_null_Abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", null, "Abc"));
		}

		@Test
		@DisplayName("(\"  \", \"\", null)")
		public void getProtocolMessageName_space_empty_null() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", "", null));
		}

		@Test
		@DisplayName("(\"  \", \"\", \"\")")
		public void getProtocolMessageName_space_empty_empty() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", "", ""));
		}

		@Test
		@DisplayName("(\"  \", \"\", \"  \")")
		public void getProtocolMessageName_space_empty_space() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", "", "  "));
		}

		@Test
		@DisplayName("(\"  \", \"\", \"abc\")")
		public void getProtocolMessageName_space_empty_abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", "", "abc"));
		}

		@Test
		@DisplayName("(\"  \", \"\", \"Abc\")")
		public void getProtocolMessageName_space_empty_Abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", "", "Abc"));
		}

		@Test
		@DisplayName("(\"  \", \"  \", null)")
		public void getProtocolMessageName_space_space_null() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", "  ", null));
		}

		@Test
		@DisplayName("(\"  \", \"  \", \"\")")
		public void getProtocolMessageName_space_space_empty() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", "  ", ""));
		}

		@Test
		@DisplayName("(\"  \", \"  \", \"  \")")
		public void getProtocolMessageName_space_space_space() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", "  ", "  "));
		}

		@Test
		@DisplayName("(\"  \", \"  \", \"abc\")")
		public void getProtocolMessageName_space_space_abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", "  ", "abc"));
		}

		@Test
		@DisplayName("(\"  \", \"  \", \"Abc\")")
		public void getProtocolMessageName_space_space_Abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", "  ", "Abc"));
		}

		@Test
		@DisplayName("(\"  \", \"abc\", null)")
		public void getProtocolMessageName_space_abc_null() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", "abc", null));
		}

		@Test
		@DisplayName("(\"  \", \"abc\", \"\")")
		public void getProtocolMessageName_space_abc_empty() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", "abc", ""));
		}

		@Test
		@DisplayName("(\"  \", \"abc\", \"  \")")
		public void getProtocolMessageName_space_abc_space() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", "abc", "  "));
		}

		@Test
		@DisplayName("(\"  \", \"abc\", \"abc\")")
		public void getProtocolMessageName_space_abc_abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", "abc", "abc"));
		}

		@Test
		@DisplayName("(\"  \", \"abc\", \"Abc\")")
		public void getProtocolMessageName_space_abc_Abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", "abc", "Abc"));
		}

		@Test
		@DisplayName("(\"  \", \"Abc\", null)")
		public void getProtocolMessageName_space_Abc_null() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", "Abc", null));
		}

		@Test
		@DisplayName("(\"  \", \"Abc\", \"\")")
		public void getProtocolMessageName_space_Abc_empty() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", "Abc", ""));
		}

		@Test
		@DisplayName("(\"  \", \"Abc\", \"  \")")
		public void getProtocolMessageName_space_Abc_space() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", "Abc", "  "));
		}

		@Test
		@DisplayName("(\"  \", \"Abc\", \"abc\")")
		public void getProtocolMessageName_space_Abc_abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", "Abc", "abc"));
		}

		@Test
		@DisplayName("(\"  \", \"Abc\", \"Abc\")")
		public void getProtocolMessageName_space_Abc_Abc() {
			assertEquals("$Anonymous$.messages", this.test.getProtocolMessagePackageName("  ", "Abc", "Abc"));
		}

		@Test
		@DisplayName("(\"abc\", null, null)")
		public void getProtocolMessageName_abc_null_null() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", null, null));
		}

		@Test
		@DisplayName("(\"abc\", null, \"\")")
		public void getProtocolMessageName_abc_null_empty() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", null, ""));
		}

		@Test
		@DisplayName("(\"abc\", null, \"  \")")
		public void getProtocolMessageName_abc_null_space() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", null, "  "));
		}

		@Test
		@DisplayName("(\"abc\", null, \"abc\")")
		public void getProtocolMessageName_abc_null_abc() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", null, "abc"));
		}

		@Test
		@DisplayName("(\"abc\", null, \"Abc\")")
		public void getProtocolMessageName_abc_null_Abc() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", null, "Abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"\", null)")
		public void getProtocolMessageName_abc_empty_null() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", "", null));
		}

		@Test
		@DisplayName("(\"abc\", \"\", \"\")")
		public void getProtocolMessageName_abc_empty_empty() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", "", ""));
		}

		@Test
		@DisplayName("(\"abc\", \"\", \"  \")")
		public void getProtocolMessageName_abc_empty_space() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", "", "  "));
		}

		@Test
		@DisplayName("(\"abc\", \"\", \"abc\")")
		public void getProtocolMessageName_abc_empty_abc() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", "", "abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"\", \"Abc\")")
		public void getProtocolMessageName_abc_empty_Abc() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", "", "Abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"  \", null)")
		public void getProtocolMessageName_abc_space_null() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", "  ", null));
		}

		@Test
		@DisplayName("(\"abc\", \"  \", \"\")")
		public void getProtocolMessageName_abc_space_empty() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", "  ", ""));
		}

		@Test
		@DisplayName("(\"abc\", \"  \", \"  \")")
		public void getProtocolMessageName_abc_space_space() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", "  ", "  "));
		}

		@Test
		@DisplayName("(\"abc\", \"  \", \"abc\")")
		public void getProtocolMessageName_abc_space_abc() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", "  ", "abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"  \", \"Abc\")")
		public void getProtocolMessageName_abc_space_Abc() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", "  ", "Abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"abc\", null)")
		public void getProtocolMessageName_abc_abc_null() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", "abc", null));
		}

		@Test
		@DisplayName("(\"abc\", \"abc\", \"\")")
		public void getProtocolMessageName_abc_abc_empty() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", "abc", ""));
		}

		@Test
		@DisplayName("(\"abc\", \"abc\", \"  \")")
		public void getProtocolMessageName_abc_abc_space() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", "abc", "  "));
		}

		@Test
		@DisplayName("(\"abc\", \"abc\", \"abc\")")
		public void getProtocolMessageName_abc_abc_abc() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", "abc", "abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"abc\", \"Abc\")")
		public void getProtocolMessageName_abc_abc_Abc() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", "abc", "Abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"Abc\", null)")
		public void getProtocolMessageName_abc_Abc_null() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", "Abc", null));
		}

		@Test
		@DisplayName("(\"abc\", \"Abc\", \"\")")
		public void getProtocolMessageName_abc_Abc_empty() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", "Abc", ""));
		}

		@Test
		@DisplayName("(\"abc\", \"Abc\", \"  \")")
		public void getProtocolMessageName_abc_Abc_space() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", "Abc", "  "));
		}

		@Test
		@DisplayName("(\"abc\", \"Abc\", \"abc\")")
		public void getProtocolMessageName_abc_Abc_abc() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", "Abc", "abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"Abc\", \"Abc\")")
		public void getProtocolMessageName_abc_Abc_Abc() {
			assertEquals("abc.messages", this.test.getProtocolMessagePackageName("abc", "Abc", "Abc"));
		}

		@Test
		@DisplayName("(\"Abc\", null, null)")
		public void getProtocolMessageName_Abc_null_null() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", null, null));
		}

		@Test
		@DisplayName("(\"Abc\", null, \"\")")
		public void getProtocolMessageName_Abc_null_empty() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", null, ""));
		}

		@Test
		@DisplayName("(\"Abc\", null, \"  \")")
		public void getProtocolMessageName_Abc_null_space() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", null, "  "));
		}

		@Test
		@DisplayName("(\"Abc\", null, \"abc\")")
		public void getProtocolMessageName_Abc_null_abc() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", null, "abc"));
		}

		@Test
		@DisplayName("(\"Abc\", null, \"Abc\")")
		public void getProtocolMessageName_Abc_null_Abc() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", null, "Abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"\", null)")
		public void getProtocolMessageName_Abc_empty_null() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", "", null));
		}

		@Test
		@DisplayName("(\"Abc\", \"\", \"\")")
		public void getProtocolMessageName_Abc_empty_empty() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", "", ""));
		}

		@Test
		@DisplayName("(\"Abc\", \"\", \"  \")")
		public void getProtocolMessageName_Abc_empty_space() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", "", "  "));
		}

		@Test
		@DisplayName("(\"Abc\", \"\", \"abc\")")
		public void getProtocolMessageName_Abc_empty_abc() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", "", "abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"\", \"Abc\")")
		public void getProtocolMessageName_Abc_empty_Abc() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", "", "Abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"  \", null)")
		public void getProtocolMessageName_Abc_space_null() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", "  ", null));
		}

		@Test
		@DisplayName("(\"Abc\", \"  \", \"\")")
		public void getProtocolMessageName_Abc_space_empty() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", "  ", ""));
		}

		@Test
		@DisplayName("(\"Abc\", \"  \", \"  \")")
		public void getProtocolMessageName_Abc_space_space() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", "  ", "  "));
		}

		@Test
		@DisplayName("(\"Abc\", \"  \", \"abc\")")
		public void getProtocolMessageName_Abc_space_abc() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", "  ", "abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"  \", \"Abc\")")
		public void getProtocolMessageName_Abc_space_Abc() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", "  ", "Abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"abc\", null)")
		public void getProtocolMessageName_Abc_abc_null() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", "abc", null));
		}

		@Test
		@DisplayName("(\"Abc\", \"abc\", \"\")")
		public void getProtocolMessageName_Abc_abc_empty() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", "abc", ""));
		}

		@Test
		@DisplayName("(\"Abc\", \"abc\", \"  \")")
		public void getProtocolMessageName_Abc_abc_space() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", "abc", "  "));
		}

		@Test
		@DisplayName("(\"Abc\", \"abc\", \"abc\")")
		public void getProtocolMessageName_Abc_abc_abc() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", "abc", "abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"abc\", \"Abc\")")
		public void getProtocolMessageName_Abc_abc_Abc() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", "abc", "Abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"Abc\", null)")
		public void getProtocolMessageName_Abc_Abc_null() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", "Abc", null));
		}

		@Test
		@DisplayName("(\"Abc\", \"Abc\", \"\")")
		public void getProtocolMessageName_Abc_Abc_empty() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", "Abc", ""));
		}

		@Test
		@DisplayName("(\"Abc\", \"Abc\", \"  \")")
		public void getProtocolMessageName_Abc_Abc_space() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", "Abc", "  "));
		}

		@Test
		@DisplayName("(\"Abc\", \"Abc\", \"abc\")")
		public void getProtocolMessageName_Abc_Abc_abc() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", "Abc", "abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"Abc\", \"Abc\")")
		public void getProtocolMessageName_Abc_Abc_Abc() {
			assertEquals("Abc.messages", this.test.getProtocolMessagePackageName("Abc", "Abc", "Abc"));
		}

	}
	
	@DisplayName("getProtocolMessageName")
	@Nested
	public class GetProtocolMessageNameTest {

		private DefaultProtocolNames test;
	
		@BeforeEach
		public void setUp() {
			this.test = new DefaultProtocolNames();
		}

		@Test
		@DisplayName("(null, null, null)")
		public void getProtocolMessageName_null_null_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName(null, null, null));
		}

		@Test
		@DisplayName("(null, null, \"\")")
		public void getProtocolMessageName_null_null_empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName(null, null, ""));
		}

		@Test
		@DisplayName("(null, null, \"  \")")
		public void getProtocolMessageName_null_null_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName(null, null, "  "));
		}

		@Test
		@DisplayName("(null, null, \"abc\")")
		public void getProtocolMessageName_null_null_abc() {
			assertEquals("abc", this.test.getProtocolMessageName(null, null, "abc"));
		}

		@Test
		@DisplayName("(null, null, \"Abc\")")
		public void getProtocolMessageName_null_null_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName(null, null, "Abc"));
		}

		@Test
		@DisplayName("(null, \"\", null)")
		public void getProtocolMessageName_null_empty_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName(null, "", null));
		}

		@Test
		@DisplayName("(null, \"\", \"\")")
		public void getProtocolMessageName_null_empty_empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName(null, "", ""));
		}

		@Test
		@DisplayName("(null, \"\", \"  \")")
		public void getProtocolMessageName_null_empty_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName(null, "", "  "));
		}

		@Test
		@DisplayName("(null, \"\", \"abc\")")
		public void getProtocolMessageName_null_empty_abc() {
			assertEquals("abc", this.test.getProtocolMessageName(null, "", "abc"));
		}

		@Test
		@DisplayName("(null, \"\", \"Abc\")")
		public void getProtocolMessageName_null_empty_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName(null, "", "Abc"));
		}

		@Test
		@DisplayName("(null, \"  \", null)")
		public void getProtocolMessageName_null_space_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName(null, "  ", null));
		}

		@Test
		@DisplayName("(null, \"  \", \"\")")
		public void getProtocolMessageName_null_space__empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName(null, "  ", ""));
		}

		@Test
		@DisplayName("(null, \"  \", \"  \")")
		public void getProtocolMessageName_null_space_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName(null, "  ", "  "));
		}

		@Test
		@DisplayName("(null, \"  \", \"abc\")")
		public void getProtocolMessageName_null_space_abc() {
			assertEquals("abc", this.test.getProtocolMessageName(null, "  ", "abc"));
		}

		@Test
		@DisplayName("(null, \"  \", \"Abc\")")
		public void getProtocolMessageName_null_space_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName(null, "  ", "Abc"));
		}

		@Test
		@DisplayName("(null, \"abc\", null)")
		public void getProtocolMessageName_null_abc_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName(null, "abc", null));
		}

		@Test
		@DisplayName("(null, \"abc\", \"\")")
		public void getProtocolMessageName_null_abc_empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName(null, "abc", ""));
		}

		@Test
		@DisplayName("(null, \"abc\", \"  \")")
		public void getProtocolMessageName_null_abc_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName(null, "abc", "  "));
		}

		@Test
		@DisplayName("(null, \"abc\", \"abc\")")
		public void getProtocolMessageName_null_abc_abc() {
			assertEquals("abc", this.test.getProtocolMessageName(null, "abc", "abc"));
		}

		@Test
		@DisplayName("(null, \"abc\", \"Abc\")")
		public void getProtocolMessageName_null_abc_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName(null, "abc", "Abc"));
		}

		@Test
		@DisplayName("(null, \"Abc\", null)")
		public void getProtocolMessageName_null_Abc_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName(null, "Abc", null));
		}

		@Test
		@DisplayName("(null, \"Abc\", \"\")")
		public void getProtocolMessageName_null_Abc_empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName(null, "Abc", ""));
		}

		@Test
		@DisplayName("(null, \"Abc\", \"  \")")
		public void getProtocolMessageName_null_Abc_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName(null, "Abc", "  "));
		}

		@Test
		@DisplayName("(null, \"Abc\", \"abc\")")
		public void getProtocolMessageName_null_Abc_abc() {
			assertEquals("abc", this.test.getProtocolMessageName(null, "Abc", "abc"));
		}

		@Test
		@DisplayName("(null, \"Abc\", \"Abc\")")
		public void getProtocolMessageName_null_Abc_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName(null, "Abc", "Abc"));
		}
		
		@Test
		@DisplayName("(\"\", null, null)")
		public void getProtocolMessageName_empty_null_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("", null, null));
		}

		@Test
		@DisplayName("(\"\", null, \"\")")
		public void getProtocolMessageName_empty_null_empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("", null, ""));
		}

		@Test
		@DisplayName("(\"\", null, \"  \")")
		public void getProtocolMessageName_empty_null_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("", null, "  "));
		}

		@Test
		@DisplayName("(\"\", null, \"abc\")")
		public void getProtocolMessageName_empty_null_abc() {
			assertEquals("abc", this.test.getProtocolMessageName("", null, "abc"));
		}

		@Test
		@DisplayName("(\"\", null, \"Abc\")")
		public void getProtocolMessageName_empty_null_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName("", null, "Abc"));
		}

		@Test
		@DisplayName("(\"\", \"\", null)")
		public void getProtocolMessageName_empty_empty_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("", "", null));
		}

		@Test
		@DisplayName("(\"\", \"\", \"\")")
		public void getProtocolMessageName_empty_empty_empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("", "", ""));
		}

		@Test
		@DisplayName("(\"\", \"\", \"  \")")
		public void getProtocolMessageName_empty_empty_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("", "", "  "));
		}

		@Test
		@DisplayName("(\"\", \"\", \"abc\")")
		public void getProtocolMessageName_empty_empty_abc() {
			assertEquals("abc", this.test.getProtocolMessageName("", "", "abc"));
		}

		@Test
		@DisplayName("(\"\", \"\", \"Abc\")")
		public void getProtocolMessageName_empty_empty_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName("", "", "Abc"));
		}

		@Test
		@DisplayName("(\"\", \"  \", null)")
		public void getProtocolMessageName_empty_space_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("", "  ", null));
		}

		@Test
		@DisplayName("(\"\", \"  \", \"\")")
		public void getProtocolMessageName_empty_space__empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("", "  ", ""));
		}

		@Test
		@DisplayName("(\"\", \"  \", \"  \")")
		public void getProtocolMessageName_empty_space_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("", "  ", "  "));
		}

		@Test
		@DisplayName("(\"\", \"  \", \"abc\")")
		public void getProtocolMessageName_empty_space_abc() {
			assertEquals("abc", this.test.getProtocolMessageName("", "  ", "abc"));
		}

		@Test
		@DisplayName("(\"\", \"  \", \"Abc\")")
		public void getProtocolMessageName_empty_space_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName("", "  ", "Abc"));
		}

		@Test
		@DisplayName("(\"\", \"abc\", null)")
		public void getProtocolMessageName_empty_abc_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("", "abc", null));
		}

		@Test
		@DisplayName("(\"\", \"abc\", \"\")")
		public void getProtocolMessageName_empty_abc_empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("", "abc", ""));
		}

		@Test
		@DisplayName("(\"\", \"abc\", \"  \")")
		public void getProtocolMessageName_empty_abc_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("", "abc", "  "));
		}

		@Test
		@DisplayName("(\"\", \"abc\", \"abc\")")
		public void getProtocolMessageName_empty_abc_abc() {
			assertEquals("abc", this.test.getProtocolMessageName("", "abc", "abc"));
		}

		@Test
		@DisplayName("(\"\", \"abc\", \"Abc\")")
		public void getProtocolMessageName_empty_abc_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName("", "abc", "Abc"));
		}

		@Test
		@DisplayName("(\"\", \"Abc\", null)")
		public void getProtocolMessageName_empty_Abc_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("", "Abc", null));
		}

		@Test
		@DisplayName("(\"\", \"Abc\", \"\")")
		public void getProtocolMessageName_empty_Abc_empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("", "Abc", ""));
		}

		@Test
		@DisplayName("(\"\", \"Abc\", \"  \")")
		public void getProtocolMessageName_empty_Abc_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("", "Abc", "  "));
		}

		@Test
		@DisplayName("(\"\", \"Abc\", \"abc\")")
		public void getProtocolMessageName_empty_Abc_abc() {
			assertEquals("abc", this.test.getProtocolMessageName("", "Abc", "abc"));
		}

		@Test
		@DisplayName("(\"\", \"Abc\", \"Abc\")")
		public void getProtocolMessageName_empty_Abc_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName("", "Abc", "Abc"));
		}
		
		@Test
		@DisplayName("(\"  \", null, null)")
		public void getProtocolMessageName_space_null_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("  ", null, null));
		}

		@Test
		@DisplayName("(\"  \", null, \"\")")
		public void getProtocolMessageName_space_null_empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("  ", null, ""));
		}

		@Test
		@DisplayName("(\"  \", null, \"  \")")
		public void getProtocolMessageName_space_null_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("  ", null, "  "));
		}

		@Test
		@DisplayName("(\"  \", null, \"abc\")")
		public void getProtocolMessageName_space_null_abc() {
			assertEquals("abc", this.test.getProtocolMessageName("  ", null, "abc"));
		}

		@Test
		@DisplayName("(\"  \", null, \"Abc\")")
		public void getProtocolMessageName_space_null_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName("  ", null, "Abc"));
		}

		@Test
		@DisplayName("(\"  \", \"\", null)")
		public void getProtocolMessageName_space_empty_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("  ", "", null));
		}

		@Test
		@DisplayName("(\"  \", \"\", \"\")")
		public void getProtocolMessageName_space_empty_empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("  ", "", ""));
		}

		@Test
		@DisplayName("(\"  \", \"\", \"  \")")
		public void getProtocolMessageName_space_empty_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("  ", "", "  "));
		}

		@Test
		@DisplayName("(\"  \", \"\", \"abc\")")
		public void getProtocolMessageName_space_empty_abc() {
			assertEquals("abc", this.test.getProtocolMessageName("  ", "", "abc"));
		}

		@Test
		@DisplayName("(\"  \", \"\", \"Abc\")")
		public void getProtocolMessageName_space_empty_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName("  ", "", "Abc"));
		}

		@Test
		@DisplayName("(\"  \", \"  \", null)")
		public void getProtocolMessageName_space_space_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("  ", "  ", null));
		}

		@Test
		@DisplayName("(\"  \", \"  \", \"\")")
		public void getProtocolMessageName_space_space__empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("  ", "  ", ""));
		}

		@Test
		@DisplayName("(\"  \", \"  \", \"  \")")
		public void getProtocolMessageName_space_space_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("  ", "  ", "  "));
		}

		@Test
		@DisplayName("(\"  \", \"  \", \"abc\")")
		public void getProtocolMessageName_space_space_abc() {
			assertEquals("abc", this.test.getProtocolMessageName("  ", "  ", "abc"));
		}

		@Test
		@DisplayName("(\"  \", \"  \", \"Abc\")")
		public void getProtocolMessageName_space_space_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName("  ", "  ", "Abc"));
		}

		@Test
		@DisplayName("(\"  \", \"abc\", null)")
		public void getProtocolMessageName_space_abc_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("  ", "abc", null));
		}

		@Test
		@DisplayName("(\"  \", \"abc\", \"\")")
		public void getProtocolMessageName_space_abc_empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("  ", "abc", ""));
		}

		@Test
		@DisplayName("(\"  \", \"abc\", \"  \")")
		public void getProtocolMessageName_space_abc_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("  ", "abc", "  "));
		}

		@Test
		@DisplayName("(\"  \", \"abc\", \"abc\")")
		public void getProtocolMessageName_space_abc_abc() {
			assertEquals("abc", this.test.getProtocolMessageName("  ", "abc", "abc"));
		}

		@Test
		@DisplayName("(\"  \", \"abc\", \"Abc\")")
		public void getProtocolMessageName_space_abc_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName("  ", "abc", "Abc"));
		}

		@Test
		@DisplayName("(\"  \", \"Abc\", null)")
		public void getProtocolMessageName_space_Abc_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("  ", "Abc", null));
		}

		@Test
		@DisplayName("(\"  \", \"Abc\", \"\")")
		public void getProtocolMessageName_space_Abc_empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("  ", "Abc", ""));
		}

		@Test
		@DisplayName("(\"  \", \"Abc\", \"  \")")
		public void getProtocolMessageName_space_Abc_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("  ", "Abc", "  "));
		}

		@Test
		@DisplayName("(\"  \", \"Abc\", \"abc\")")
		public void getProtocolMessageName_space_Abc_abc() {
			assertEquals("abc", this.test.getProtocolMessageName("  ", "Abc", "abc"));
		}

		@Test
		@DisplayName("(\"  \", \"Abc\", \"Abc\")")
		public void getProtocolMessageName_space_Abc_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName("  ", "Abc", "Abc"));
		}

		@Test
		@DisplayName("(\"abc\", null, null)")
		public void getProtocolMessageName_abc_null_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("abc", null, null));
		}

		@Test
		@DisplayName("(\"abc\", null, \"\")")
		public void getProtocolMessageName_abc_null_empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("Abc", null, ""));
		}

		@Test
		@DisplayName("(\"abc\", null, \"  \")")
		public void getProtocolMessageName_abc_null_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("abc", null, "  "));
		}

		@Test
		@DisplayName("(\"abc\", null, \"abc\")")
		public void getProtocolMessageName_abc_null_abc() {
			assertEquals("abc", this.test.getProtocolMessageName("abc", null, "abc"));
		}

		@Test
		@DisplayName("(\"abc\", null, \"Abc\")")
		public void getProtocolMessageName_abc_null_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName("abc", null, "Abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"\", null)")
		public void getProtocolMessageName_abc_empty_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("abc", "", null));
		}

		@Test
		@DisplayName("(\"abc\", \"\", \"\")")
		public void getProtocolMessageName_abc_empty_empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("abc", "", ""));
		}

		@Test
		@DisplayName("(\"abc\", \"\", \"  \")")
		public void getProtocolMessageName_abc_empty_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("abc", "", "  "));
		}

		@Test
		@DisplayName("(\"abc\", \"\", \"abc\")")
		public void getProtocolMessageName_abc_empty_abc() {
			assertEquals("abc", this.test.getProtocolMessageName("abc", "", "abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"\", \"Abc\")")
		public void getProtocolMessageName_abc_empty_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName("abc", "", "Abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"  \", null)")
		public void getProtocolMessageName_abc_space_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("abc", "  ", null));
		}

		@Test
		@DisplayName("(\"abc\", \"  \", \"\")")
		public void getProtocolMessageName_abc_space__empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("abc", "  ", ""));
		}

		@Test
		@DisplayName("(\"abc\", \"  \", \"  \")")
		public void getProtocolMessageName_abc_space_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("abc", "  ", "  "));
		}

		@Test
		@DisplayName("(\"abc\", \"  \", \"abc\")")
		public void getProtocolMessageName_abc_space_abc() {
			assertEquals("abc", this.test.getProtocolMessageName("abc", "  ", "abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"  \", \"Abc\")")
		public void getProtocolMessageName_abc_space_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName("abc", "  ", "Abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"abc\", null)")
		public void getProtocolMessageName_abc_abc_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("abc", "abc", null));
		}

		@Test
		@DisplayName("(\"abc\", \"abc\", \"\")")
		public void getProtocolMessageName_abc_abc_empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("abc", "abc", ""));
		}

		@Test
		@DisplayName("(\"abc\", \"abc\", \"  \")")
		public void getProtocolMessageName_abc_abc_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("abc", "abc", "  "));
		}

		@Test
		@DisplayName("(\"abc\", \"abc\", \"abc\")")
		public void getProtocolMessageName_abc_abc_abc() {
			assertEquals("abc", this.test.getProtocolMessageName("abc", "abc", "abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"abc\", \"Abc\")")
		public void getProtocolMessageName_abc_abc_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName("abc", "abc", "Abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"Abc\", null)")
		public void getProtocolMessageName_abc_Abc_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("abc", "Abc", null));
		}

		@Test
		@DisplayName("(\"abc\", \"Abc\", \"\")")
		public void getProtocolMessageName_abc_Abc_empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("abc", "Abc", ""));
		}

		@Test
		@DisplayName("(\"abc\", \"Abc\", \"  \")")
		public void getProtocolMessageName_abc_Abc_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("abc", "Abc", "  "));
		}

		@Test
		@DisplayName("(\"abc\", \"Abc\", \"abc\")")
		public void getProtocolMessageName_abc_Abc_abc() {
			assertEquals("abc", this.test.getProtocolMessageName("abc", "Abc", "abc"));
		}

		@Test
		@DisplayName("(\"abc\", \"Abc\", \"Abc\")")
		public void getProtocolMessageName_abc_Abc_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName("abc", "Abc", "Abc"));
		}

		@Test
		@DisplayName("(\"Abc\", null, null)")
		public void getProtocolMessageName_Abc_null_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("Abc", null, null));
		}

		@Test
		@DisplayName("(\"Abc\", null, \"\")")
		public void getProtocolMessageName_Abc_null_empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("Abc", null, ""));
		}

		@Test
		@DisplayName("(\"Abc\", null, \"  \")")
		public void getProtocolMessageName_Abc_null_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("Abc", null, "  "));
		}

		@Test
		@DisplayName("(\"Abc\", null, \"abc\")")
		public void getProtocolMessageName_Abc_null_abc() {
			assertEquals("abc", this.test.getProtocolMessageName("Abc", null, "abc"));
		}

		@Test
		@DisplayName("(\"Abc\", null, \"Abc\")")
		public void getProtocolMessageName_Abc_null_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName("Abc", null, "Abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"\", null)")
		public void getProtocolMessageName_Abc_empty_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("Abc", "", null));
		}

		@Test
		@DisplayName("(\"Abc\", \"\", \"\")")
		public void getProtocolMessageName_Abc_empty_empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("Abc", "", ""));
		}

		@Test
		@DisplayName("(\"Abc\", \"\", \"  \")")
		public void getProtocolMessageName_Abc_empty_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("Abc", "", "  "));
		}

		@Test
		@DisplayName("(\"Abc\", \"\", \"abc\")")
		public void getProtocolMessageName_Abc_empty_abc() {
			assertEquals("abc", this.test.getProtocolMessageName("Abc", "", "abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"\", \"Abc\")")
		public void getProtocolMessageName_Abc_empty_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName("Abc", "", "Abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"  \", null)")
		public void getProtocolMessageName_Abc_space_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("Abc", "  ", null));
		}

		@Test
		@DisplayName("(\"Abc\", \"  \", \"\")")
		public void getProtocolMessageName_Abc_space__empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("Abc", "  ", ""));
		}

		@Test
		@DisplayName("(\"Abc\", \"  \", \"  \")")
		public void getProtocolMessageName_Abc_space_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("Abc", "  ", "  "));
		}

		@Test
		@DisplayName("(\"Abc\", \"  \", \"abc\")")
		public void getProtocolMessageName_Abc_space_abc() {
			assertEquals("abc", this.test.getProtocolMessageName("Abc", "  ", "abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"  \", \"Abc\")")
		public void getProtocolMessageName_Abc_space_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName("Abc", "  ", "Abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"abc\", null)")
		public void getProtocolMessageName_Abc_abc_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("Abc", "abc", null));
		}

		@Test
		@DisplayName("(\"Abc\", \"abc\", \"\")")
		public void getProtocolMessageName_Abc_abc_empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("Abc", "abc", ""));
		}

		@Test
		@DisplayName("(\"Abc\", \"abc\", \"  \")")
		public void getProtocolMessageName_Abc_abc_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("Abc", "abc", "  "));
		}

		@Test
		@DisplayName("(\"Abc\", \"abc\", \"abc\")")
		public void getProtocolMessageName_Abc_abc_abc() {
			assertEquals("abc", this.test.getProtocolMessageName("Abc", "abc", "abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"abc\", \"Abc\")")
		public void getProtocolMessageName_Abc_abc_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName("Abc", "abc", "Abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"Abc\", null)")
		public void getProtocolMessageName_Abc_Abc_null() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("Abc", "Abc", null));
		}

		@Test
		@DisplayName("(\"Abc\", \"Abc\", \"\")")
		public void getProtocolMessageName_Abc_Abc_empty() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("Abc", "Abc", ""));
		}

		@Test
		@DisplayName("(\"Abc\", \"Abc\", \"  \")")
		public void getProtocolMessageName_Abc_Abc_space() {
			assertEquals("$Anonymous$", this.test.getProtocolMessageName("Abc", "Abc", "  "));
		}

		@Test
		@DisplayName("(\"Abc\", \"Abc\", \"abc\")")
		public void getProtocolMessageName_Abc_Abc_abc() {
			assertEquals("abc", this.test.getProtocolMessageName("Abc", "Abc", "abc"));
		}

		@Test
		@DisplayName("(\"Abc\", \"Abc\", \"Abc\")")
		public void getProtocolMessageName_Abc_Abc_Abc() {
			assertEquals("Abc", this.test.getProtocolMessageName("Abc", "Abc", "Abc"));
		}

	}
	
	@DisplayName("getGetEnabledMessagesFunctionName")
	@Nested
	public class GetGetEnabledMessagesFunctionNameTest {

		private DefaultProtocolNames test;
	
		@BeforeEach
		public void setUp() {
			this.test = new DefaultProtocolNames();
		}

		@Test
		@DisplayName("(null)")
		public void getGetEnabledMessagesFunctionName_null() {
			assertEquals("getEnabled$Anonymous$Messages", this.test.getGetEnabledMessagesFunctionName(null));
		}

		@Test
		@DisplayName("(\"\")")
		public void getGetEnabledMessagesFunctionName_empty() {
			assertEquals("getEnabled$Anonymous$Messages", this.test.getGetEnabledMessagesFunctionName(""));
		}

		@Test
		@DisplayName("(\"  \")")
		public void getGetEnabledMessagesFunctionName_space() {
			assertEquals("getEnabled$Anonymous$Messages", this.test.getGetEnabledMessagesFunctionName("  "));
		}

		@Test
		@DisplayName("(\"abc\")")
		public void getGetEnabledMessagesFunctionName_abc() {
			assertEquals("getEnabledabcMessages", this.test.getGetEnabledMessagesFunctionName("abc"));
		}

		@Test
		@DisplayName("(\"Abc\")")
		public void getGetEnabledMessagesFunctionName_Abc() {
			assertEquals("getEnabledAbcMessages", this.test.getGetEnabledMessagesFunctionName("Abc"));
		}

	}
	
	@DisplayName("getSendMessageFunctionName")
	@Nested
	public class GetSendMessageFunctionNameTest {

		private DefaultProtocolNames test;
	
		@BeforeEach
		public void setUp() {
			this.test = new DefaultProtocolNames();
		}

		@Test
		@DisplayName("(null)")
		public void getSendMessageFunctionName_null() {
			assertEquals("send$Anonymous$Message", this.test.getSendMessageFunctionName(null));
		}

		@Test
		@DisplayName("(\"\")")
		public void getSendMessageFunctionName_empty() {
			assertEquals("send$Anonymous$Message", this.test.getSendMessageFunctionName(""));
		}

		@Test
		@DisplayName("(\"  \")")
		public void getSendMessageFunctionName_space() {
			assertEquals("send$Anonymous$Message", this.test.getSendMessageFunctionName("  "));
		}

		@Test
		@DisplayName("(\"abc\")")
		public void getSendMessageFunctionName_abc() {
			assertEquals("sendabcMessage", this.test.getSendMessageFunctionName("abc"));
		}

		@Test
		@DisplayName("(\"Abc\")")
		public void getSendMessageFunctionName_Abc() {
			assertEquals("sendAbcMessage", this.test.getSendMessageFunctionName("Abc"));
		}

	}
	
	@DisplayName("getProtocolAdapterPackageName")
	@Nested
	public class GetProtocolAdapterPackageNameTest {

		private DefaultProtocolNames test;
	
		@BeforeEach
		public void setUp() {
			this.test = new DefaultProtocolNames();
		}

		@Test
		@DisplayName("(null, null)")
		public void getProtocolAdapterPackageName_null_null() {
			assertEquals("", this.test.getProtocolAdapterPackageName(null, null));
		}

		@Test
		@DisplayName("(null, \"\")")
		public void getProtocolAdapterPackageName_null_empty() {
			assertEquals("", this.test.getProtocolAdapterPackageName(null, ""));
		}

		@Test
		@DisplayName("(null, \"   \")")
		public void getProtocolAdapterPackageName_null_space() {
			assertEquals("", this.test.getProtocolAdapterPackageName(null, "  "));
		}

		@Test
		@DisplayName("(null, \"abc\")")
		public void getProtocolAdapterPackageName_null_abc() {
			assertEquals("", this.test.getProtocolAdapterPackageName(null, "abc"));
		}

		@Test
		@DisplayName("(null, \"Abc\")")
		public void getProtocolAdapterPackageName_null_Abc() {
			assertEquals("", this.test.getProtocolAdapterPackageName(null, "Abc"));
		}

		@Test
		@DisplayName("(\"\", null)")
		public void getProtocolAdapterPackageName_empty_null() {
			assertEquals("", this.test.getProtocolAdapterPackageName("", null));
		}

		@Test
		@DisplayName("(\"\", \"\")")
		public void getProtocolAdapterPackageName_empty_empty() {
			assertEquals("", this.test.getProtocolAdapterPackageName("", ""));
		}

		@Test
		@DisplayName("(\"\", \"   \")")
		public void getProtocolAdapterPackageName_empty_space() {
			assertEquals("", this.test.getProtocolAdapterPackageName("", "  "));
		}

		@Test
		@DisplayName("(\"\", \"abc\")")
		public void getProtocolAdapterPackageName_empty_abc() {
			assertEquals("", this.test.getProtocolAdapterPackageName("", "abc"));
		}

		@Test
		@DisplayName("(\"\", \"Abc\")")
		public void getProtocolAdapterPackageName_empty_Abc() {
			assertEquals("", this.test.getProtocolAdapterPackageName("", "Abc"));
		}

		@Test
		@DisplayName("(\"  \", null)")
		public void getProtocolAdapterPackageName_space_null() {
			assertEquals("", this.test.getProtocolAdapterPackageName("  ", null));
		}

		@Test
		@DisplayName("(\"  \", \"\")")
		public void getProtocolAdapterPackageName_space_empty() {
			assertEquals("", this.test.getProtocolAdapterPackageName("  ", ""));
		}

		@Test
		@DisplayName("(\"  \", \"   \")")
		public void getProtocolAdapterPackageName_space_space() {
			assertEquals("", this.test.getProtocolAdapterPackageName("  ", "  "));
		}

		@Test
		@DisplayName("(\"  \", \"abc\")")
		public void getProtocolAdapterPackageName_space_abc() {
			assertEquals("", this.test.getProtocolAdapterPackageName("  ", "abc"));
		}

		@Test
		@DisplayName("(\"  \", \"Abc\")")
		public void getProtocolAdapterPackageName_space_Abc() {
			assertEquals("", this.test.getProtocolAdapterPackageName("  ", "Abc"));
		}

		@Test
		@DisplayName("(\"abc\", null)")
		public void getProtocolAdapterPackageName_abc_null() {
			assertEquals("abc", this.test.getProtocolAdapterPackageName("abc", null));
		}

		@Test
		@DisplayName("(\"abc\", \"\")")
		public void getProtocolAdapterPackageName_abc_empty() {
			assertEquals("abc", this.test.getProtocolAdapterPackageName("abc", ""));
		}

		@Test
		@DisplayName("(\"abc\", \"   \")")
		public void getProtocolAdapterPackageName_abc_space() {
			assertEquals("abc", this.test.getProtocolAdapterPackageName("abc", "  "));
		}

		@Test
		@DisplayName("(\"xyz\", \"abc\")")
		public void getProtocolAdapterPackageName_abc_abc() {
			assertEquals("xyz", this.test.getProtocolAdapterPackageName("xyz", "abc"));
		}

		@Test
		@DisplayName("(\"xyz\", \"Abc\")")
		public void getProtocolAdapterPackageName_abc_Abc() {
			assertEquals("xyz", this.test.getProtocolAdapterPackageName("xyz", "Abc"));
		}

		@Test
		@DisplayName("(\"Abc\", null)")
		public void getProtocolAdapterPackageName_Abc_null() {
			assertEquals("Abc", this.test.getProtocolAdapterPackageName("Abc", null));
		}

		@Test
		@DisplayName("(\"Abc\", \"\")")
		public void getProtocolAdapterPackageName_Abc_empty() {
			assertEquals("Abc", this.test.getProtocolAdapterPackageName("Abc", ""));
		}

		@Test
		@DisplayName("(\"Abc\", \"   \")")
		public void getProtocolAdapterPackageName_Abc_space() {
			assertEquals("Abc", this.test.getProtocolAdapterPackageName("Abc", "  "));
		}

		@Test
		@DisplayName("(\"Xyz\", \"abc\")")
		public void getProtocolAdapterPackageName_Abc_abc() {
			assertEquals("Xyz", this.test.getProtocolAdapterPackageName("Xyz", "abc"));
		}

		@Test
		@DisplayName("(\"Xyz\", \"Abc\")")
		public void getProtocolAdapterPackageName_Abc_Abc() {
			assertEquals("Xyz", this.test.getProtocolAdapterPackageName("Xyz", "Abc"));
		}

	}
	
}
