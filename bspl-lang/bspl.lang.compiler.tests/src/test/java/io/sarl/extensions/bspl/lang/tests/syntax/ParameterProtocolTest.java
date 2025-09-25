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
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.sarl.extensions.bspl.lang.tests.syntax;

import static io.sarl.extensions.bspl.lang.validation.IssueCodes.DUPLICATE_PROTOCOL_PARAMETER;
import static io.sarl.extensions.bspl.lang.validation.IssueCodes.INVALID_ARGUMENT_MODIFIER;
import static io.sarl.extensions.bspl.lang.validation.IssueCodes.INVALID_PARAMETER_MODIFIER;
import static io.sarl.extensions.bspl.lang.validation.IssueCodes.MISSED_PROTOCOL_KEY;
import static io.sarl.extensions.bspl.lang.validation.IssueCodes.REQUIRED_OUT_MESSAGE_FOR_PRIVATE_PARAMETER;
import static io.sarl.extensions.bspl.lang.validation.IssueCodes.REQUIRED_OUT_PARAMETER;
import static io.sarl.extensions.bspl.lang.validation.IssueCodes.REQUIRED_OUT_PARAMETER_IN_MESSAGES;
import static io.sarl.extensions.bspl.lang.validation.IssueCodes.UNDEFINED_PROTOCOL_PARAMETER;
import static io.sarl.extensions.bspl.lang.validation.IssueCodes.UNUSED_PROTOCOL_PARAMETER;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.diagnostics.Diagnostic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import io.sarl.extensions.bspl.lang.bspl.BsplPackage;
import io.sarl.extensions.bspl.lang.tests.AbstractBsplTest;

/**
 * @author $Author: sgalland$
 * @version $Name$ $Revision$ $Date$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
@SuppressWarnings("all")
@DisplayName("Protocol parameter")
public class ParameterProtocolTest {

	/**
	 * @author $Author: sgalland$
	 * @version $Name$ $Revision$ $Date$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 */
	@Nested
	@DisplayName("Default public parameter w/o type")
	public class DefaultPublicTest extends AbstractBsplTest {

		@Test
		@DisplayName("Default parameter")
		public void defaultParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertFalse(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("IN parameter")
		public void inParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter in P1",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid default IN parameter")
		public void invalidDefaultInParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 in",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'in'");
		}

		@Test
		@DisplayName("OUT parameter")
		public void outParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter out P1",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid OUT parameter")
		public void invalidOutParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 out",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'out'");
		}

		@Test
		@DisplayName("NIL parameter")
		public void nilParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter nil P1",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertTrue(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid NIL parameter")
		public void invalidNilParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 nil",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'nil'");
		}

		@Test
		@DisplayName("OPT parameter")
		public void optParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter opt P1",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertTrue(param.isOptional());
			assertFalse(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid OPT parameter")
		public void invalidOptParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 opt",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'opt'");
		}

		@Test
		@DisplayName("ANY parameter")
		public void anyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter any P1",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertFalse(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid ANY parameter")
		public void invalidAnyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 any",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'any'");
		}

		@Test
		@DisplayName("Invalid private modifier #1")
		public void invalidPrivateParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 private",
					"  R1 -> R2 : M",
					"}");
			// Two parameters are recognized: P1 and R1
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input '->'");
		}

		@Test
		@DisplayName("Invalid private modifier #2")
		public void invalidPrivateParameter_2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter private P1",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"extraneous input 'private'");
		}

		@Test
		@DisplayName("KEY parameter")
		public void keyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertTrue(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("IN KEY parameter")
		public void inKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter in P1 key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("OUT KEY parameter")
		public void outKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter out P1 key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("NIL KEY parameter")
		public void nilKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter nil P1 key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertTrue(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("OPT KEY parameter")
		public void optKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter opt P1 key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertTrue(param.isOptional());
			assertFalse(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("ANY KEY parameter")
		public void anyKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter any P1 key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertTrue(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid key modifier #1")
		public void invalidKeyParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter key P1",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"extraneous input 'key'");
		}

	}

	/**
	 * @author $Author: sgalland$
	 * @version $Name$ $Revision$ $Date$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 */
	@Nested
	@DisplayName("Default public parameter w/ type")
	public class DefaultPublicTypeTest extends AbstractBsplTest {

		@Test
		@DisplayName("Default parameter")
		public void defaultParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 : double",
					"  R1 -> R2 : M [out P1]",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertFalse(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("IN parameter")
		public void inParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter in P1 : double",
					"  R1 -> R2 : M [out P1]",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid IN parameter #1")
		public void invalidInParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 in : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'in'");
		}

		@Test
		@DisplayName("Invalid IN parameter #2")
		public void invalidInParameter_2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 : double in",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"no viable alternative at input 'in'");
		}

		@Test
		@DisplayName("OUT parameter")
		public void outParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter out P1 : double",
					"  R1 -> R2 : M [out P1]",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid OUT parameter #1")
		public void invalidOutParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 out : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'out'");
		}

		@Test
		@DisplayName("Invalid OUT parameter #2")
		public void invalidOutParameter_2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 : double out",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"no viable alternative at input 'out'");
		}

		@Test
		@DisplayName("NIL parameter")
		public void nilParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter nil P1 : double",
					"  R1 -> R2 : M [nil P1]",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertTrue(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid NIL parameter #1")
		public void invalidNilParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 nil : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'nil'");
		}

		@Test
		@DisplayName("Invalid NIL parameter #2")
		public void invalidNilParameter_2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 : double nil",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"no viable alternative at input 'nil'");
		}

		@Test
		@DisplayName("OPT parameter")
		public void optParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter opt P1 : double",
					"  R1 -> R2 : M  [opt P1]",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertTrue(param.isOptional());
			assertFalse(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid OPT parameter #1")
		public void invalidOptParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 opt : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'opt'");
		}

		@Test
		@DisplayName("Invalid OPT parameter #2")
		public void invalidOptParameter_2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 : double opt",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"no viable alternative at input 'opt'");
		}

		@Test
		@DisplayName("ANY parameter")
		public void anyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter any P1 : double",
					"  R1 -> R2 : M  [any P1]",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertFalse(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid ANY parameter #1")
		public void invalidAnyParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 any : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'any'");
		}

		@Test
		@DisplayName("Invalid ANY parameter #2")
		public void invalidAnyParameter_2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 : double any",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"no viable alternative at input 'any'");
		}

		@Test
		@DisplayName("Invalid private modifier #1")
		public void invalidPrivateParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 private : double",
					"  R1 -> R2 : M",
					"}");
			// Two parameters are recognized: P1 and R1
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"extraneous input ':'");
		}

		@Test
		@DisplayName("Invalid private modifier #2")
		public void invalidPrivateParameter_2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 : double private",
					"  R1 -> R2 : M",
					"}");
			// Two parameters are recognized: P1 and R1
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input '->'");
		}

		@Test
		@DisplayName("KEY parameter")
		public void keyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 : double key",
					"  R1 -> R2 : M  [out P1]",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertTrue(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid KEY parameter")
		public void invalidKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P1 key : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input ':'");
		}

		@Test
		@DisplayName("IN KEY parameter")
		public void inKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter in P1 : double key",
					"  R1 -> R2 : M [in P1]",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid IN KEY parameter")
		public void invalidInKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter in P1 key : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input ':'");
		}

		@Test
		@DisplayName("OUT KEY parameter")
		public void outKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter out P1 : double key",
					"  R1 -> R2 : M [out P1]",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid OUT KEY parameter")
		public void invalidOutKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter out P1 key : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input ':'");
		}

		@Test
		@DisplayName("NIL KEY parameter")
		public void nilKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter nil P1 : double key",
					"  R1 -> R2 : M  [nil P1]",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertTrue(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid NIL KEY parameter")
		public void invalidNilKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter nil P1 key : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input ':'");
		}

		@Test
		@DisplayName("OPT KEY parameter")
		public void optKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter opt P1 : double key",
					"  R1 -> R2 : M  [opt P1]",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertTrue(param.isOptional());
			assertFalse(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid OPT KEY parameter")
		public void invaliOptKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter opt P1 key : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input ':'");
		}

		@Test
		@DisplayName("ANY KEY parameter")
		public void anyKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter any P1 : double key",
					"  R1 -> R2 : M  [any P1]",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertTrue(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertNull(param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid ANY KEY parameter")
		public void invalidAnyKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter any P1 key : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input ':'");
		}

		@Test
		@DisplayName("Invalid key modifier #1")
		public void invalidKeyParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter key P1 : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"extraneous input 'key'");
		}

	}

	/**
	 * @author $Author: sgalland$
	 * @version $Name$ $Revision$ $Date$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 */
	@Nested
	@DisplayName("Public parameter w/o type")
	public class PublicTest extends AbstractBsplTest {

		@Test
		@DisplayName("Public modifier for multiple parameters")
		public void publicModifierMultipleParameters() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1, P2, P3",
					"  parameter P4",
					"  R1 -> R2 : M [out P1]",
					"}");
			var params = bspl.getBsplProtocols().get(0).getParameters();
			assertNotNull(params);
			assertEquals(4, params.size());

			var param0 = params.get(0);
			assertNull(param0.getType());
			assertEquals("P1", param0.getName());
			assertTrue(param0.isInput());
			assertFalse(param0.isKey());
			assertTrue(param0.isAny());
			assertFalse(param0.isNil());
			assertFalse(param0.isOptional());
			assertTrue(param0.isOutput());
			assertSame(JvmVisibility.PUBLIC, param0.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param0.getVisibility());
			assertTrue(param0.isPublicVisibility());
			assertFalse(param0.isProtectedVisibility());
			assertFalse(param0.isPackageVisibility());
			assertFalse(param0.isPrivateVisibility());

			var param1 = params.get(1);
			assertNull(param1.getType());
			assertEquals("P2", param1.getName());
			assertTrue(param1.isInput());
			assertFalse(param1.isKey());
			assertTrue(param1.isAny());
			assertFalse(param1.isNil());
			assertFalse(param1.isOptional());
			assertTrue(param1.isOutput());
			assertSame(JvmVisibility.PUBLIC, param1.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param1.getVisibility());
			assertTrue(param1.isPublicVisibility());
			assertFalse(param1.isProtectedVisibility());
			assertFalse(param1.isPackageVisibility());
			assertFalse(param1.isPrivateVisibility());

			var param2 = params.get(2);
			assertNull(param2.getType());
			assertEquals("P3", param2.getName());
			assertTrue(param2.isInput());
			assertFalse(param2.isKey());
			assertTrue(param2.isAny());
			assertFalse(param2.isNil());
			assertFalse(param2.isOptional());
			assertTrue(param2.isOutput());
			assertSame(JvmVisibility.PUBLIC, param2.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param2.getVisibility());
			assertTrue(param2.isPublicVisibility());
			assertFalse(param2.isProtectedVisibility());
			assertFalse(param2.isPackageVisibility());
			assertFalse(param2.isPrivateVisibility());

			var param3 = params.get(3);
			assertNull(param3.getType());
			assertEquals("P4", param3.getName());
			assertTrue(param3.isInput());
			assertFalse(param3.isKey());
			assertTrue(param3.isAny());
			assertFalse(param3.isNil());
			assertFalse(param3.isOptional());
			assertTrue(param3.isOutput());
			assertNull(param3.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param3.getVisibility());
			assertTrue(param3.isPublicVisibility());
			assertFalse(param3.isProtectedVisibility());
			assertFalse(param3.isPackageVisibility());
			assertFalse(param3.isPrivateVisibility());
		}

		@Test
		@DisplayName("Public parameter modifiers")
		public void publicParameterModifiers() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1",
					"  R1 -> R2 : M [out P1]",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertFalse(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Public modifier")
		public void publicModifier() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public P1",
					"  R1 -> R2 : M  [out P1]",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertFalse(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("IN parameter")
		public void inParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter in P1",
					"  R1 -> R2 : M  [in P1]",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid IN parameter")
		public void invalidInParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 in",
					"  R1 -> R2 : M [in P1]",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'in'");
		}

		@Test
		@DisplayName("OUT parameter")
		public void outParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter out P1",
					"  R1 -> R2 : M  [out P1]",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid OUT parameter")
		public void invalidOutParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 out",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'out'");
		}

		@Test
		@DisplayName("NIL parameter")
		public void nilParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter nil P1",
					"  R1 -> R2 : M  [nil P1]",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertTrue(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid NIL parameter")
		public void invalidNilParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 nil",
					"  R1 -> R2 : M [nil P1]",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'nil'");
		}

		@Test
		@DisplayName("OPT parameter")
		public void optParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter opt P1",
					"  R1 -> R2 : M [opt P1]",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertTrue(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid OPT parameter")
		public void invalidOptParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 opt",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'opt'");
		}

		@Test
		@DisplayName("ANY parameter")
		public void anyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter any P1",
					"  R1 -> R2 : M  [any P1]",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertFalse(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid ANY parameter")
		public void invalidAnyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 any",
					"  R1 -> R2 : M  [any P1]",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'any'");
		}

		@Test
		@DisplayName("Invalid private modifier #1")
		public void invalidPrivateParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 private",
					"  R1 -> R2 : M",
					"}");
			// Two parameters are recognized: P1 and R1
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input '->'");
		}

		@Test
		@DisplayName("Invalid private modifier #2")
		public void invalidPrivateParameter_2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter private P1",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"extraneous input 'private'");
		}

		@Test
		@DisplayName("KEY parameter")
		public void keyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertTrue(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("IN KEY parameter")
		public void inKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter in P1 key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("OUT KEY parameter")
		public void outKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter out P1 key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("NIL KEY parameter")
		public void nilKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter nil P1 key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertTrue(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("OPT KEY parameter")
		public void optKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter opt P1 key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertTrue(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("ANY KEY parameter")
		public void anyKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter any P1 key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertTrue(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid key modifier #1")
		public void invalidKeyParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter key P1",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"extraneous input 'key'");
		}

	}

	/**
	 * @author $Author: sgalland$
	 * @version $Name$ $Revision$ $Date$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 */
	@Nested
	@DisplayName("Public parameter w/ type")
	public class PublicTypeTest extends AbstractBsplTest {

		@Test
		@DisplayName("Public modifier for multiple typed parameters")
		public void publicModifierMultipleTypedParameters() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 : String, P2 : int, P3",
					"  parameter P4 : Object",
					"  R1 -> R2 : M [out P1]",
					"}");
			var params = bspl.getBsplProtocols().get(0).getParameters();
			assertNotNull(params);
			assertEquals(4, params.size());

			var param0 = params.get(0);
			assertEquals(String.class.getName(), param0.getType().getIdentifier());
			assertEquals("P1", param0.getName());
			assertTrue(param0.isInput());
			assertFalse(param0.isKey());
			assertTrue(param0.isAny());
			assertFalse(param0.isNil());
			assertFalse(param0.isOptional());
			assertTrue(param0.isOutput());
			assertSame(JvmVisibility.PUBLIC, param0.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param0.getVisibility());
			assertTrue(param0.isPublicVisibility());
			assertFalse(param0.isProtectedVisibility());
			assertFalse(param0.isPackageVisibility());
			assertFalse(param0.isPrivateVisibility());

			var param1 = params.get(1);
			assertEquals(int.class.getName(), param1.getType().getIdentifier());
			assertEquals("P2", param1.getName());
			assertTrue(param1.isInput());
			assertFalse(param1.isKey());
			assertTrue(param1.isAny());
			assertFalse(param1.isNil());
			assertFalse(param1.isOptional());
			assertTrue(param1.isOutput());
			assertSame(JvmVisibility.PUBLIC, param1.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param1.getVisibility());
			assertTrue(param1.isPublicVisibility());
			assertFalse(param1.isProtectedVisibility());
			assertFalse(param1.isPackageVisibility());
			assertFalse(param1.isPrivateVisibility());

			var param2 = params.get(2);
			assertNull(param2.getType());
			assertEquals("P3", param2.getName());
			assertTrue(param2.isInput());
			assertFalse(param2.isKey());
			assertTrue(param2.isAny());
			assertFalse(param2.isNil());
			assertFalse(param2.isOptional());
			assertTrue(param2.isOutput());
			assertSame(JvmVisibility.PUBLIC, param2.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param2.getVisibility());
			assertTrue(param2.isPublicVisibility());
			assertFalse(param2.isProtectedVisibility());
			assertFalse(param2.isPackageVisibility());
			assertFalse(param2.isPrivateVisibility());

			var param3 = params.get(3);
			assertEquals(Object.class.getName(), param3.getType().getIdentifier());
			assertEquals("P4", param3.getName());
			assertTrue(param3.isInput());
			assertFalse(param3.isKey());
			assertTrue(param3.isAny());
			assertFalse(param3.isNil());
			assertFalse(param3.isOptional());
			assertTrue(param3.isOutput());
			assertNull(param3.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param3.getVisibility());
			assertTrue(param3.isPublicVisibility());
			assertFalse(param3.isProtectedVisibility());
			assertFalse(param3.isPackageVisibility());
			assertFalse(param3.isPrivateVisibility());
		}

		@Test
		@DisplayName("Public parameter modifiers")
		public void publicParameterModifiers() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 : double",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertFalse(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Public modifier")
		public void publicModifier() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public P1 : double",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertFalse(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("IN parameter")
		public void inParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter in P1 : double",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid IN parameter #1")
		public void invalidInParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 in : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'in'");
		}

		@Test
		@DisplayName("Invalid IN parameter #2")
		public void invalidInParameter_2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 : double in",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"no viable alternative at input 'in'");
		}

		@Test
		@DisplayName("OUT parameter")
		public void outParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter out P1 : double",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid OUT parameter #1")
		public void invalidOutParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 out : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'out'");
		}

		@Test
		@DisplayName("Invalid OUT parameter #2")
		public void invalidOutParameter_2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 : double out",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"no viable alternative at input 'out'");
		}

		@Test
		@DisplayName("NIL parameter")
		public void nilParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter nil P1 : double",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertTrue(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid NIL parameter #1")
		public void invalidNilParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 nil : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'nil'");
		}

		@Test
		@DisplayName("Invalid NIL parameter #2")
		public void invalidNilParameter_2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 : double nil",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"no viable alternative at input 'nil'");
		}

		@Test
		@DisplayName("OPT parameter")
		public void optParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter opt P1 : double",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertTrue(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid OPT parameter #1")
		public void invalidOptParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 opt : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'opt'");
		}

		@Test
		@DisplayName("Invalid OPT parameter #2")
		public void invalidOptParameter_2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 : double opt",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"no viable alternative at input 'opt'");
		}

		@Test
		@DisplayName("ANY parameter")
		public void anyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter any P1 : double",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertFalse(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid ANY parameter #1")
		public void invalidAnyParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 any : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'any'");
		}

		@Test
		@DisplayName("Invalid ANY parameter #2")
		public void invalidAnyParameter_2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 : double any",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"no viable alternative at input 'any'");
		}

		@Test
		@DisplayName("Invalid private modifier #1")
		public void invalidPrivateParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 private : double",
					"  R1 -> R2 : M",
					"}");
			// Two parameters are recognized: P1 and R1
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"extraneous input ':'");
		}

		@Test
		@DisplayName("Invalid private modifier #2")
		public void invalidPrivateParameter_2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 : double private",
					"  R1 -> R2 : M",
					"}");
			// Two parameters are recognized: P1 and R1
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input '->'");
		}

		@Test
		@DisplayName("KEY parameter")
		public void keyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 : double key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertTrue(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid KEY parameter")
		public void invalidKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter P1 key : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input ':'");
		}

		@Test
		@DisplayName("IN KEY parameter")
		public void inKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter in P1 : double key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid IN KEY parameter")
		public void invalidInKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter in P1 key : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input ':'");
		}

		@Test
		@DisplayName("OUT KEY parameter")
		public void outKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter out P1 : double key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid OUT KEY parameter")
		public void invalidOutKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter out P1 key : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input ':'");
		}

		@Test
		@DisplayName("NIL KEY parameter")
		public void nilKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter nil P1 : double key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertTrue(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid NIL KEY parameter")
		public void invalidNilKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter nil P1 key : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input ':'");
		}

		@Test
		@DisplayName("OPT KEY parameter")
		public void optKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter opt P1 : double key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertTrue(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid OPT KEY parameter")
		public void invaliOptKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter opt P1 key : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input ':'");
		}

		@Test
		@DisplayName("ANY KEY parameter")
		public void anyKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter any P1 : double key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertTrue(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertSame(JvmVisibility.PUBLIC, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param.getVisibility());
			assertTrue(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertFalse(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid ANY KEY parameter")
		public void invalidAnyKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter any P1 key : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input ':'");
		}

		@Test
		@DisplayName("Invalid key modifier #1")
		public void invalidKeyParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  public parameter key P1 : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"extraneous input 'key'");
		}

	}

	/**
	 * @author $Author: sgalland$
	 * @version $Name$ $Revision$ $Date$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 */
	@Nested
	@DisplayName("Private parameter w/o type")
	public class PrivateTest extends AbstractBsplTest {

		@Test
		@DisplayName("Private modifier for multiple parameters")
		public void privateModifierMultipleParameters() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1, P2, P3",
					"  parameter P4",
					"  R1 -> R2 : M [out P1]",
					"}");
			var params = bspl.getBsplProtocols().get(0).getParameters();
			assertNotNull(params);
			assertEquals(4, params.size());

			var param0 = params.get(0);
			assertNull(param0.getType());
			assertEquals("P1", param0.getName());
			assertTrue(param0.isInput());
			assertFalse(param0.isKey());
			assertTrue(param0.isAny());
			assertFalse(param0.isNil());
			assertFalse(param0.isOptional());
			assertTrue(param0.isOutput());
			assertSame(JvmVisibility.PRIVATE, param0.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param0.getVisibility());
			assertFalse(param0.isPublicVisibility());
			assertFalse(param0.isProtectedVisibility());
			assertFalse(param0.isPackageVisibility());
			assertTrue(param0.isPrivateVisibility());

			var param1 = params.get(1);
			assertNull(param1.getType());
			assertEquals("P2", param1.getName());
			assertTrue(param1.isInput());
			assertFalse(param1.isKey());
			assertTrue(param1.isAny());
			assertFalse(param1.isNil());
			assertFalse(param1.isOptional());
			assertTrue(param1.isOutput());
			assertSame(JvmVisibility.PRIVATE, param1.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param1.getVisibility());
			assertFalse(param1.isPublicVisibility());
			assertFalse(param1.isProtectedVisibility());
			assertFalse(param1.isPackageVisibility());
			assertTrue(param1.isPrivateVisibility());

			var param2 = params.get(2);
			assertNull(param2.getType());
			assertEquals("P3", param2.getName());
			assertTrue(param2.isInput());
			assertFalse(param2.isKey());
			assertTrue(param2.isAny());
			assertFalse(param2.isNil());
			assertFalse(param2.isOptional());
			assertTrue(param2.isOutput());
			assertSame(JvmVisibility.PRIVATE, param2.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param2.getVisibility());
			assertFalse(param2.isPublicVisibility());
			assertFalse(param2.isProtectedVisibility());
			assertFalse(param2.isPackageVisibility());
			assertTrue(param2.isPrivateVisibility());

			var param3 = params.get(3);
			assertNull(param3.getType());
			assertEquals("P4", param3.getName());
			assertTrue(param3.isInput());
			assertFalse(param3.isKey());
			assertTrue(param3.isAny());
			assertFalse(param3.isNil());
			assertFalse(param3.isOptional());
			assertTrue(param3.isOutput());
			assertNull(param3.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param3.getVisibility());
			assertTrue(param3.isPublicVisibility());
			assertFalse(param3.isProtectedVisibility());
			assertFalse(param3.isPackageVisibility());
			assertFalse(param3.isPrivateVisibility());
		}

		@Test
		@DisplayName("Private parameter modifiers")
		public void privateParameterModifiers() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1",
					"  R1 -> R2 : M [out P1]",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertFalse(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertSame(JvmVisibility.PRIVATE, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param.getVisibility());
			assertFalse(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertTrue(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Private modifier")
		public void privateModifier() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private P1",
					"  R1 -> R2 : M",
					"}");
			//
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertFalse(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertSame(JvmVisibility.PRIVATE, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param.getVisibility());
			assertFalse(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertTrue(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("IN parameter")
		public void inParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter in P1",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PRIVATE, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param.getVisibility());
			assertFalse(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertTrue(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid IN parameter")
		public void invalidInParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 in",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'in'");
		}

		@Test
		@DisplayName("OUT parameter")
		public void outParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter out P1",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					INVALID_PARAMETER_MODIFIER,
					"Invalid modifier 'out' or 'any'", "Private parameter cannot have the modifier 'out' or 'any'");
		}

		@Test
		@DisplayName("Invalid OUT parameter")
		public void invalidOutParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 out",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'out'");
		}

		@Test
		@DisplayName("NIL parameter")
		public void nilParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter nil P1",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertTrue(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PRIVATE, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param.getVisibility());
			assertFalse(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertTrue(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid NIL parameter")
		public void invalidNilParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 nil",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'nil'");
		}

		@Test
		@DisplayName("OPT parameter")
		public void optParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter opt P1",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertTrue(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PRIVATE, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param.getVisibility());
			assertFalse(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertTrue(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid OPT parameter")
		public void invalidOptParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 opt",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'opt'");
		}

		@Test
		@DisplayName("ANY parameter")
		public void anyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter any P1",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertWarning(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					INVALID_PARAMETER_MODIFIER,
					"Invalid modifier 'any' for the private parameter P1", "Assume 'in'");
		}

		@Test
		@DisplayName("Invalid ANY parameter")
		public void invalidAnyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 any",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'any'");
		}

		@Test
		@DisplayName("Invalid private modifier #1")
		public void invalidPrivateParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 private",
					"  R1 -> R2 : M",
					"}");
			// Two parameters are recognized: P1 and R1
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input '->'");
		}

		@Test
		@DisplayName("Invalid private modifier #2")
		public void invalidPrivateParameter_2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter private P1",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"extraneous input 'private'");
		}

		@Test
		@DisplayName("KEY parameter")
		public void keyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertTrue(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertSame(JvmVisibility.PRIVATE, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param.getVisibility());
			assertFalse(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertTrue(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("IN KEY parameter")
		public void inKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter in P1 key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PRIVATE, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param.getVisibility());
			assertFalse(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertTrue(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("OUT KEY parameter")
		public void outKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter out P1 key",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					INVALID_PARAMETER_MODIFIER,
					"Invalid modifier 'out' or 'any'", "Private parameter cannot have the modifier 'out' or 'any'");
		}

		@Test
		@DisplayName("NIL KEY parameter")
		public void nilKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter nil P1 key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertTrue(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PRIVATE, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param.getVisibility());
			assertFalse(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertTrue(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("OPT KEY parameter")
		public void optKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter opt P1 key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertNull(param.getType());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertTrue(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PRIVATE, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param.getVisibility());
			assertFalse(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertTrue(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("ANY KEY parameter")
		public void anyKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter any P1 key",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertWarning(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					INVALID_PARAMETER_MODIFIER,
					"Invalid modifier 'any' for the private parameter P1", "Assume 'in'");
		}

		@Test
		@DisplayName("Invalid key modifier #1")
		public void invalidKeyParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter key P1",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"extraneous input 'key'");
		}

		@Test
		@DisplayName("Private parameter cannot be out parameter")
		public void missedPrivateParameterDeclaration() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"PROTO {",
					"  role R1, R2",
					"  private parameter out P1",
					"  R1 -> R2 : M [out P1]",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					INVALID_PARAMETER_MODIFIER,
					"Invalid modifier 'out' or 'any'", "Private parameter cannot have the modifier 'out' or 'any'");
		}

	}

	/**
	 * @author $Author: sgalland$
	 * @version $Name$ $Revision$ $Date$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 */
	@Nested
	@DisplayName("Private parameter w/ type")
	public class PrivateTypeTest extends AbstractBsplTest {

		@Test
		@DisplayName("Private modifier for multiple typed parameters")
		public void privateModifierMultipleTypedParameters() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 : String, P2 : int, P3",
					"  parameter P4 : Object",
					"  R1 -> R2 : M [out P1]",
					"}");
			var params = bspl.getBsplProtocols().get(0).getParameters();
			assertNotNull(params);
			assertEquals(4, params.size());

			var param0 = params.get(0);
			assertEquals(String.class.getName(), param0.getType().getIdentifier());
			assertEquals("P1", param0.getName());
			assertTrue(param0.isInput());
			assertFalse(param0.isKey());
			assertTrue(param0.isAny());
			assertFalse(param0.isNil());
			assertFalse(param0.isOptional());
			assertTrue(param0.isOutput());
			assertSame(JvmVisibility.PRIVATE, param0.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param0.getVisibility());
			assertFalse(param0.isPublicVisibility());
			assertFalse(param0.isProtectedVisibility());
			assertFalse(param0.isPackageVisibility());
			assertTrue(param0.isPrivateVisibility());

			var param1 = params.get(1);
			assertEquals(int.class.getName(), param1.getType().getIdentifier());
			assertEquals("P2", param1.getName());
			assertTrue(param1.isInput());
			assertFalse(param1.isKey());
			assertTrue(param1.isAny());
			assertFalse(param1.isNil());
			assertFalse(param1.isOptional());
			assertTrue(param1.isOutput());
			assertSame(JvmVisibility.PRIVATE, param1.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param1.getVisibility());
			assertFalse(param1.isPublicVisibility());
			assertFalse(param1.isProtectedVisibility());
			assertFalse(param1.isPackageVisibility());
			assertTrue(param1.isPrivateVisibility());

			var param2 = params.get(2);
			assertNull(param2.getType());
			assertEquals("P3", param2.getName());
			assertTrue(param2.isInput());
			assertFalse(param2.isKey());
			assertTrue(param2.isAny());
			assertFalse(param2.isNil());
			assertFalse(param2.isOptional());
			assertTrue(param2.isOutput());
			assertSame(JvmVisibility.PRIVATE, param2.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param2.getVisibility());
			assertFalse(param2.isPublicVisibility());
			assertFalse(param2.isProtectedVisibility());
			assertFalse(param2.isPackageVisibility());
			assertTrue(param2.isPrivateVisibility());

			var param3 = params.get(3);
			assertEquals(Object.class.getName(), param3.getType().getIdentifier());
			assertEquals("P4", param3.getName());
			assertTrue(param3.isInput());
			assertFalse(param3.isKey());
			assertTrue(param3.isAny());
			assertFalse(param3.isNil());
			assertFalse(param3.isOptional());
			assertTrue(param3.isOutput());
			assertNull(param3.getSpecifiedVisibility());
			assertSame(JvmVisibility.PUBLIC, param3.getVisibility());
			assertTrue(param3.isPublicVisibility());
			assertFalse(param3.isProtectedVisibility());
			assertFalse(param3.isPackageVisibility());
			assertFalse(param3.isPrivateVisibility());
		}

		@Test
		@DisplayName("Private parameter modifiers")
		public void privateParameterModifiers() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 : double",
					"  R1 -> R2 : M",
					"}");
			//
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertFalse(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertSame(JvmVisibility.PRIVATE, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param.getVisibility());
			assertFalse(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertTrue(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Private modifier")
		public void privateModifier() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private P1 : double",
					"  R1 -> R2 : M [out P1]",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertFalse(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertSame(JvmVisibility.PRIVATE, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param.getVisibility());
			assertFalse(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertTrue(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("IN parameter")
		public void inParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter in P1 : double",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PRIVATE, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param.getVisibility());
			assertFalse(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertTrue(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid IN parameter #1")
		public void invalidInParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 in : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'in'");
		}

		@Test
		@DisplayName("Invalid IN parameter #2")
		public void invalidInParameter_2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 : double in",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"no viable alternative at input 'in'");
		}

		@Test
		@DisplayName("OUT parameter")
		public void outParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter out P1 : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					INVALID_PARAMETER_MODIFIER,
					"Invalid modifier 'out' or 'any'", "Private parameter cannot have the modifier 'out' or 'any'");
		}

		@Test
		@DisplayName("Invalid OUT parameter #1")
		public void invalidOutParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 out : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'out'");
		}

		@Test
		@DisplayName("Invalid OUT parameter #2")
		public void invalidOutParameter_2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 : double out",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"no viable alternative at input 'out'");
		}

		@Test
		@DisplayName("NIL parameter")
		public void nilParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter nil P1 : double",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertTrue(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PRIVATE, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param.getVisibility());
			assertFalse(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertTrue(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid NIL parameter #1")
		public void invalidNilParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 nil : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'nil'");
		}

		@Test
		@DisplayName("Invalid NIL parameter #2")
		public void invalidNilParameter_2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 : double nil",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"no viable alternative at input 'nil'");
		}

		@Test
		@DisplayName("OPT parameter")
		public void optParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter opt P1 : double",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertFalse(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertTrue(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PRIVATE, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param.getVisibility());
			assertFalse(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertTrue(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid OPT parameter #1")
		public void invalidOptParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 opt : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'opt'");
		}

		@Test
		@DisplayName("Invalid OPT parameter #2")
		public void invalidOptParameter_2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 : double opt",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"no viable alternative at input 'opt'");
		}

		@Test
		@DisplayName("ANY parameter")
		public void anyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter any P1 : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertWarning(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					INVALID_PARAMETER_MODIFIER,
					"Invalid modifier 'any' for the private parameter P1", "Assume 'in'");
		}

		@Test
		@DisplayName("Invalid ANY parameter #1")
		public void invalidAnyParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 any : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input 'any'");
		}

		@Test
		@DisplayName("Invalid ANY parameter #2")
		public void invalidAnyParameter_2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 : double any",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"no viable alternative at input 'any'");
		}

		@Test
		@DisplayName("Invalid private modifier #1")
		public void invalidPrivateParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 private : double",
					"  R1 -> R2 : M",
					"}");
			// Two parameters are recognized: P1 and R1
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"extraneous input ':'");
		}

		@Test
		@DisplayName("Invalid private modifier #2")
		public void invalidPrivateParameter_2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 : double private",
					"  R1 -> R2 : M",
					"}");
			// Two parameters are recognized: P1 and R1
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input '->'");
		}

		@Test
		@DisplayName("KEY parameter")
		public void keyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 : double key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertTrue(param.isKey());
			assertTrue(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertTrue(param.isOutput());
			assertSame(JvmVisibility.PRIVATE, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param.getVisibility());
			assertFalse(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertTrue(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid KEY parameter")
		public void invalidKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter P1 key : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input ':'");
		}

		@Test
		@DisplayName("IN KEY parameter")
		public void inKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter in P1 : double key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertTrue(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PRIVATE, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param.getVisibility());
			assertFalse(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertTrue(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid IN KEY parameter")
		public void invalidInKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter in P1 key : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input ':'");
		}

		@Test
		@DisplayName("OUT KEY parameter")
		public void outKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter out P1 : double key",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					INVALID_PARAMETER_MODIFIER,
					"Invalid modifier 'out' or 'any'", "Private parameter cannot have the modifier 'out' or 'any'");
		}

		@Test
		@DisplayName("Invalid OUT KEY parameter")
		public void invalidOutKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter out P1 key : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input ':'");
		}

		@Test
		@DisplayName("NIL KEY parameter")
		public void nilKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter nil P1 : double key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertTrue(param.isNil());
			assertFalse(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PRIVATE, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param.getVisibility());
			assertFalse(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertTrue(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid NIL KEY parameter")
		public void invalidNilKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter nil P1 key : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input ':'");
		}

		@Test
		@DisplayName("OPT KEY parameter")
		public void optKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter opt P1 : double key",
					"  R1 -> R2 : M",
					"}");
			var param = bspl.getBsplProtocols().get(0).getParameters().get(0);
			assertEquals("double", param.getType().getIdentifier());
			assertEquals("P1", param.getName());
			assertFalse(param.isInput());
			assertTrue(param.isKey());
			assertFalse(param.isAny());
			assertFalse(param.isNil());
			assertTrue(param.isOptional());
			assertFalse(param.isOutput());
			assertSame(JvmVisibility.PRIVATE, param.getSpecifiedVisibility());
			assertSame(JvmVisibility.PRIVATE, param.getVisibility());
			assertFalse(param.isPublicVisibility());
			assertFalse(param.isProtectedVisibility());
			assertFalse(param.isPackageVisibility());
			assertTrue(param.isPrivateVisibility());
		}

		@Test
		@DisplayName("Invalid OPT KEY parameter")
		public void invaliOptKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter opt P1 key : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input ':'");
		}

		@Test
		@DisplayName("ANY KEY parameter")
		public void anyKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter any P1 : double key",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertWarning(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					INVALID_PARAMETER_MODIFIER,
					"Invalid modifier 'any' for the private parameter P1", "Assume 'in'");
		}

		@Test
		@DisplayName("Invalid ANY KEY parameter")
		public void invalidAnyKeyParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter any P1 key : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"mismatched input ':'");
		}

		@Test
		@DisplayName("Invalid key modifier #1")
		public void invalidKeyParameter_1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  private parameter key P1 : double",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					Diagnostic.SYNTAX_DIAGNOSTIC,
					"extraneous input 'key'");
		}

	}

	/**
	 * @author $Author: sgalland$
	 * @version $Name$ $Revision$ $Date$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 */
	@Nested
	@DisplayName("Singh Definition Compliance")
	public class SinghDefinitionComplianceTest extends AbstractBsplTest {

		@Test
		@DisplayName("Unecessary parameter")
		public void unecessaryParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"PROTO {",
					"  role R1, R2",
					"  parameter P1",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertWarning(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					UNUSED_PROTOCOL_PARAMETER,
					"Unused parameter P1 in protocol PROTO");
		}

		@Test
		@DisplayName("Missing parameter")
		public void missingParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"PROTO {",
					"  role R1, R2",
					"  R1 -> R2 : M [in P1]",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolMessage(),
					UNDEFINED_PROTOCOL_PARAMETER,
					"Undefined parameter P1 in the protocol PROTO");
		}

		@Test
		@DisplayName("Duplicate parameter #1")
		public void duplicateParameter1() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"PROTO {",
					"  role R1, R2",
					"  parameter in P1, in P1",
					"  R1 -> R2 : M [in P1]",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					DUPLICATE_PROTOCOL_PARAMETER,
					"Duplicate definition of the parameter P1 in the protocol PROTO");
		}

		@Test
		@DisplayName("Duplicate parameter #2")
		public void duplicateParameter2() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"PROTO {",
					"  role R1, R2",
					"  parameter in P1",
					"  parameter in P1",
					"  R1 -> R2 : M [in P1]",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					DUPLICATE_PROTOCOL_PARAMETER,
					"Duplicate definition of the parameter P1 in the protocol PROTO");
		}

		@Test
		@DisplayName("Missed protocol key")
		public void missedProtocolKey() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"PROTO {",
					"  role R1, R2",
					"  parameter in P1",
					"  parameter in P2",
					"  R1 -> R2 : M [in P1, in P2]",
					"}");
			validate(bspl).assertWarning(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					MISSED_PROTOCOL_KEY,
					"Missed protocol key for protocol PROTO");
		}

		@Test
		@DisplayName("Invalid key declaration in message")
		public void invalidKeyDeclarationInMessage() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"PROTO {",
					"  role R1, R2",
					"  parameter in P1",
					"  R1 -> R2 : M [in P1 key]",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolMessage(),
					INVALID_ARGUMENT_MODIFIER,
					"Parameter P1 is not declared as a key of the protocol PROTO");
		}

		@Test
		@DisplayName("Missing out parameter")
		public void missingOutParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"PROTO {",
					"  role R1, R2",
					"  parameter in P1",
					"  R1 -> R2 : M [in P1]",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					REQUIRED_OUT_PARAMETER,
					"Out parameter must be declared for protocol PROTO");
		}

		@Test
		@DisplayName("No out parameter in messages")
		public void noOutArgument() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"PROTO {",
					"  role R1, R2",
					"  parameter out P1",
					"  R1 -> R2 : M [in P1 key]",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocol(),
					REQUIRED_OUT_PARAMETER_IN_MESSAGES,
					"Out parameter must be defined in at least one message for protocol PROTO");
		}

		@Test
		@DisplayName("Out parameter not used")
		public void noOutArgumentForOutParameter() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"PROTO {",
					"  role R1, R2",
					"  parameter out P1",
					"  R1 -> R2 : M",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					REQUIRED_OUT_PARAMETER_IN_MESSAGES,
					"Out parameter P1 is not bound in the messages of the protocol PROTO");
		}

		@Test
		@DisplayName("Flexible offer protocol")
		public void noParameterModifier() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"FlexibleOffer {",
					"  role B, S",
					"  parameter in ID key, out item, price, out qID",
					"  B -> S : rfq [ ID, out item, nil price ]",
					"  B -> S : rfq [ ID, out item, in price ]",
					"  S -> B : quote [ ID, in item, out price, out qID ]",
					"  S -> B : quote [ ID, in item, in price, out qID ]",
					"}");
			validate(bspl).assertNoErrors();
		}

		@Test
		@DisplayName("Private parameter - out message")
		public void privateParameterOutMessage() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P0 : int key",
					"  private P1 : double",
					"  R1 -> R2 : M [in P0 key, out P1]",
					"}");
			validate(bspl).assertNoIssues();
		}

		@Test
		@DisplayName("Private parameter - in message")
		public void privateParameterInMessage() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P0 : int key",
					"  private P1 : double",
					"  parameter P2 : char",
					"  R2 -> R1 : M2 [out P2]",
					"  R1 -> R2 : M [in P0 key, in P1]",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					REQUIRED_OUT_MESSAGE_FOR_PRIVATE_PARAMETER,
					"Private parameter P1 must be specified as 'out' parameter in at least one message")
				.assertNoIssues();
		}

		@Test
		@DisplayName("Private parameter - any message")
		public void privateParameterAnyMessage() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P0 : int key",
					"  private P1 : double",
					"  parameter P2 : char",
					"  R2 -> R1 : M2 [out P2]",
					"  R1 -> R2 : M [in P0 key, any P1]",
					"}");
			validate(bspl).assertNoIssues();
		}

		@Test
		@DisplayName("Private parameter - nil message")
		public void privateParameterNilMessage() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P0 : int key",
					"  private P1 : double",
					"  parameter P2 : char",
					"  R2 -> R1 : M2 [out P2]",
					"  R1 -> R2 : M [in P0 key, nil P1]",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					REQUIRED_OUT_MESSAGE_FOR_PRIVATE_PARAMETER,
					"Private parameter P1 must be specified as 'out' parameter in at least one message")
				.assertNoIssues();
		}

		@Test
		@DisplayName("Private parameter - opt message")
		public void privateParameterOptMessage() throws Exception {
			var bspl = specification(
					"package io.sarl.extensions.bspl.lang.tests",
					"protocol PROTO {",
					"  role R1, R2",
					"  parameter P0 : int key",
					"  private P1 : double",
					"  parameter P2 : char",
					"  R2 -> R1 : M2 [out P2]",
					"  R1 -> R2 : M [in P0 key, opt P1]",
					"}");
			validate(bspl).assertError(
					BsplPackage.eINSTANCE.getBsplProtocolParameter(),
					REQUIRED_OUT_MESSAGE_FOR_PRIVATE_PARAMETER,
					"Private parameter P1 must be specified as 'out' parameter in at least one message")
				.assertNoIssues();
		}

	}

}
