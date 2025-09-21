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

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.sarl.extensions.bspl.lang.bspl.impl.BsplProtocolArgumentImplCustom;
import io.sarl.extensions.bspl.lang.bspl.impl.BsplProtocolImplCustom;
import io.sarl.extensions.bspl.lang.bspl.impl.BsplProtocolMessageImplCustom;
import io.sarl.extensions.bspl.lang.bspl.impl.BsplProtocolParameterImplCustom;
import io.sarl.extensions.bspl.lang.bspl.impl.BsplProtocolProtocolCallImpl;
import io.sarl.extensions.bspl.lang.bspl.impl.BsplProtocolRoleImplCustom;
import io.sarl.extensions.bspl.lang.compiler.DefaultVisibilityProvider;

/** Test for {@link DefaultVisibilityProvider}.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 * @since 1.0
 */
@SuppressWarnings("all")
@DisplayName("DefaultVisibilityProvider")
public class DefaultVisibilityProviderTest {

	private DefaultVisibilityProvider test;

	@BeforeEach
	public void setUp() {
		this.test = new DefaultVisibilityProvider();
	}

	private static <T> EList<T> newList(T... element) {
		var list = new BasicEList<T>();
		list.addAll(Arrays.asList(element));
		return list;
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(public protocol)")
	public void getDefaultJvmVisibility_publicProtocol() {
		var protocol = mock(BsplProtocolImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList("public"));
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(protected protocol)")
	public void getDefaultJvmVisibility_protectedProtocol() {
		var protocol = mock(BsplProtocolImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList("protected"));
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(package protocol)")
	public void getDefaultJvmVisibility_packageProtocol() {
		var protocol = mock(BsplProtocolImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList("package"));
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(private protocol)")
	public void getDefaultJvmVisibility_privateProtocol() {
		var protocol = mock(BsplProtocolImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList("private"));
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(default protocol)")
	public void getDefaultJvmVisibility_defaultProtocol() {
		var protocol = mock(BsplProtocolImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList());
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(public role)")
	public void getDefaultJvmVisibility_publicRole() {
		var protocol = mock(BsplProtocolRoleImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList("public"));
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(protected role)")
	public void getDefaultJvmVisibility_protectedRole() {
		var protocol = mock(BsplProtocolRoleImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList("protected"));
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(package role)")
	public void getDefaultJvmVisibility_packageRole() {
		var protocol = mock(BsplProtocolRoleImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList("package"));
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(private role)")
	public void getDefaultJvmVisibility_privateRole() {
		var protocol = mock(BsplProtocolRoleImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList("private"));
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(default role)")
	public void getDefaultJvmVisibility_defaultRole() {
		var protocol = mock(BsplProtocolRoleImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList());
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(public argument)")
	public void getDefaultJvmVisibility_publicArgument() {
		var protocol = mock(BsplProtocolArgumentImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList("public"));
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(protected argument)")
	public void getDefaultJvmVisibility_protectedArgument() {
		var protocol = mock(BsplProtocolArgumentImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList("protected"));
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(package argument)")
	public void getDefaultJvmVisibility_packageArgument() {
		var protocol = mock(BsplProtocolArgumentImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList("package"));
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(private argument)")
	public void getDefaultJvmVisibility_privateArgument() {
		var protocol = mock(BsplProtocolArgumentImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList("private"));
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(default argument)")
	public void getDefaultJvmVisibility_defaultArgument() {
		var protocol = mock(BsplProtocolArgumentImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList());
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(public message)")
	public void getDefaultJvmVisibility_publicMessage() {
		var protocol = mock(BsplProtocolMessageImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList("public"));
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(protected message)")
	public void getDefaultJvmVisibility_protectedMessage() {
		var protocol = mock(BsplProtocolMessageImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList("protected"));
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(package message)")
	public void getDefaultJvmVisibility_packageMessage() {
		var protocol = mock(BsplProtocolMessageImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList("package"));
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(private message)")
	public void getDefaultJvmVisibility_privateMessage() {
		var protocol = mock(BsplProtocolMessageImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList("private"));
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(default message)")
	public void getDefaultJvmVisibility_defaultMessage() {
		var protocol = mock(BsplProtocolMessageImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList());
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(public parameter)")
	public void getDefaultJvmVisibility_publicParameter() {
		var protocol = mock(BsplProtocolParameterImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList("public"));
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(protected parameter)")
	public void getDefaultJvmVisibility_protectedParameter() {
		var protocol = mock(BsplProtocolParameterImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList("protected"));
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(package parameter)")
	public void getDefaultJvmVisibility_packageParameter() {
		var protocol = mock(BsplProtocolParameterImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList("package"));
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(private parameter)")
	public void getDefaultJvmVisibility_privateParameter() {
		var protocol = mock(BsplProtocolParameterImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList("private"));
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(default parameter)")
	public void getDefaultJvmVisibility_defaultParameter() {
		var protocol = mock(BsplProtocolParameterImplCustom.class);
		when(protocol.getModifiers()).thenReturn(newList());
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(public protocol call)")
	public void getDefaultJvmVisibility_publicProtocolCall() {
		var protocol = mock(BsplProtocolProtocolCallImpl.class);
		when(protocol.getModifiers()).thenReturn(newList("public"));
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(protected protocol call)")
	public void getDefaultJvmVisibility_protectedProtocolCall() {
		var protocol = mock(BsplProtocolProtocolCallImpl.class);
		when(protocol.getModifiers()).thenReturn(newList("protected"));
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(package protocol call)")
	public void getDefaultJvmVisibility_packageProtocolCall() {
		var protocol = mock(BsplProtocolProtocolCallImpl.class);
		when(protocol.getModifiers()).thenReturn(newList("package"));
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(private protocol call)")
	public void getDefaultJvmVisibility_privateProtocolCall() {
		var protocol = mock(BsplProtocolProtocolCallImpl.class);
		when(protocol.getModifiers()).thenReturn(newList("private"));
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}

	@Test
	@DisplayName("getDefaultJvmVisibility(default protocol call)")
	public void getDefaultJvmVisibility_defaultProtocolCall() {
		var protocol = mock(BsplProtocolProtocolCallImpl.class);
		when(protocol.getModifiers()).thenReturn(newList());
		when(protocol.getDefaultVisibility()).thenCallRealMethod();
		assertSame(JvmVisibility.PUBLIC, this.test.getDefaultJvmVisibility(protocol));
	}
	
}
