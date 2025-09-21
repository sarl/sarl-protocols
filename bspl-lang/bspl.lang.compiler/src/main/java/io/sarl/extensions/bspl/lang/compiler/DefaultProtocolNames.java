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

package io.sarl.extensions.bspl.lang.compiler;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReference;
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReferenceFactory;
import org.eclipse.xtext.xbase.typesystem.references.StandardTypeReferenceOwner;
import org.eclipse.xtext.xbase.typesystem.util.CommonTypeComputationServices;

import com.google.inject.Inject;
import com.google.inject.Singleton;

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

/** Provider of names for protocols.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 * @since 1.0
 */
@Singleton
public class DefaultProtocolNames implements IProtocolNames {

	private static final String ANONYMOUS_NAME = "$Anonymous$"; //$NON-NLS-1$
	
	@Inject
	private CommonTypeComputationServices typeServices;

	/** Change the services for accessible the type system.
	 *
	 * @param typeServices the services.
	 */
	public void setCommonTypeComputationServices(CommonTypeComputationServices typeServices) {
		this.typeServices = typeServices;
	}

	/** Replies the services for accessible the type system.
	 *
	 * @return the services.
	 */
	public CommonTypeComputationServices getCommonTypeComputationServices() {
		return this.typeServices;
	}

	@Override
	public Class<?> getProtocolRoleGenericInterface() {
		return ProtocolRole.class;
	}

	@Override
	public Class<?> getProtocolCapacityGenericInterface() {
		return ProtocolCapacity.class;
	}

	@Override
	public Class<?> getProtocolSkillGenericInterface() {
		return ProtocolSkill.class;
	}
	
	@Override
	public Class<?> getProtocolEventGenericInterface() {
		return ProtocolEvent.class;
	}

	@Override
	public Class<?> getProtocolMessageGenericInterface() {
		return ProtocolMessage.class;
	}
	
	@Override
	public Class<?> getProtocolSpaceGenericInterface() {
		return ProtocolSpace.class;
	}

	@Override
	public Class<?> getProtocolSpaceSpecificationGenericInterface() {
		return AbstractProtocolSpaceSpecification.class;
	}

	@Override
	public Class<?> getProtocolBehaviorGenericInterface() {
		return ProtocolBehavior.class;
	}

	@Override
	public Class<?> getKnowledgeNameGenericInterface() {
		return ScopedDataName.class;
	}

	@Override
	public Class<?> getAgentWorkingMemoryGenericInterface() {
		return WorkingMemory.class;
	}

	private static void appendName(StringBuilder output, String name) {
		if (Strings.isEmpty(name)) {
			output.append(ANONYMOUS_NAME);
		} else {
			final var pn = name.trim(); 
			if (Strings.isEmpty(pn)) {
				output.append(ANONYMOUS_NAME);
			} else {
				output.append(pn);
			}
		}
	}
	
	@Override
	public String getProtocolRoleEnumerationName(String protocolName) {
		final var basename = new StringBuilder();
		appendName(basename, protocolName);
		basename.append("Role"); //$NON-NLS-1$
		return basename.toString();
	}
	
	@Override
	public LightweightTypeReference getProtocolRoleEnumeration(String packageName, BsplProtocol protocol) {
		String name;
		if (protocol == null) {
			name = ANONYMOUS_NAME;
		} else {
			name = protocol.getName();
			if (Strings.isEmpty(name)) {
				name = ANONYMOUS_NAME;
			} else {
				name = name.trim();
				if (Strings.isEmpty(name)) {
					name = ANONYMOUS_NAME;
				}
			}
 		}
		final var fullName = new StringBuilder();
		if (appendAdapterPackageName(false, packageName, name, fullName)) {
			fullName.append("."); //$NON-NLS-1$
		}
		fullName.append(getProtocolRoleEnumerationName(name));
		return ensureType(fullName.toString(), protocol);
	}

	@Override
	public String getProtocolSpaceSpecificationName(String protocolName) {
		final var basename = new StringBuilder();
		appendName(basename, protocolName);
		basename.append("SpaceSpecification"); //$NON-NLS-1$
		return basename.toString();
	}

	private static LightweightTypeReference toLightweightTypeReference(
			JvmType type, EObject context, CommonTypeComputationServices services) {
		if (type == null) {
			return null;
		}
		final var owner = new StandardTypeReferenceOwner(services, context);
		final var factory = new LightweightTypeReferenceFactory(owner, false);
		final var reference = factory.toLightweightReference(type);
		return reference;
	}

	private LightweightTypeReference ensureType(String typeName, EObject context) {
		final var references = this.typeServices.getTypeReferences();
		final var declaredType = references.findDeclaredType(typeName, context);
		if (declaredType != null) {
			return toLightweightTypeReference(declaredType, context, this.typeServices);
		}

		final var declaredType0 = this.typeServices.getTypesFactory().createJvmGenericType();
		final var idx = typeName.lastIndexOf("."); //$NON-NLS-1$
		if (idx >= 0) {
			declaredType0.setPackageName(typeName.substring(0, idx));
			declaredType0.setSimpleName(typeName.substring(idx + 1));
		} else {
			declaredType0.setSimpleName(typeName);
		}
		return toLightweightTypeReference(declaredType0, context, this.typeServices);
	}

	@Override
	public String getProtocolCapacityName(String packageName, String protocolName, BsplProtocolRole role) {
		final var basename = new StringBuilder();
		appendName(basename, role == null ? null : role.getName());
		basename.append("ProtocolCapacity"); //$NON-NLS-1$
		return basename.toString();
	}

	@Override
	public LightweightTypeReference getProtocolCapacity(String packageName, String protocolName, BsplProtocolRole role) {
		final var fullName = new StringBuilder();
		if (appendAdapterPackageName(false, packageName, protocolName, fullName)) {
			fullName.append("."); //$NON-NLS-1$
		}
		fullName.append(getProtocolCapacityName(packageName, protocolName, role));
		return ensureType(fullName.toString(), role);
	}

	@Override
	public String getProtocolSkillName(String packageName, String protocolName, BsplProtocolRole role) {
		final var basename = new StringBuilder();
		appendName(basename, role == null ? null : role.getName());
		basename.append("ProtocolSkill"); //$NON-NLS-1$
		return basename.toString();
	}

	@Override
	public LightweightTypeReference getProtocolSkill(String packageName, String protocolName, BsplProtocolRole role) {
		final var fullName = new StringBuilder();
		if (appendAdapterPackageName(false, packageName, protocolName, fullName)) {
			fullName.append("."); //$NON-NLS-1$
		}
		fullName.append(getProtocolSkillName(packageName, protocolName, role));
		return ensureType(fullName.toString(), role);
	}

	@Override
	public String getProtocolBehaviorName(String packageName, String protocolName, BsplProtocolRole role) {
		final var basename = new StringBuilder();
		appendName(basename, role == null ? null : role.getName());
		basename.append("ProtocolReactiveBehavior"); //$NON-NLS-1$
		return basename.toString();
	}

	@Override
	public LightweightTypeReference getProtocolBehavior(String packageName, String protocolName, BsplProtocolRole role) {
		final var fullName = new StringBuilder();
		if (appendAdapterPackageName(false, packageName, protocolName, fullName)) {
			fullName.append("."); //$NON-NLS-1$
		}
		fullName.append(getProtocolBehaviorName(packageName, protocolName, role));
		return ensureType(fullName.toString(), role);
	}

	@Override
	public String getEnabledMessageListFunctionName(String messageName) {
		final var fullName = new StringBuilder();
		fullName.append("getEnabled"); //$NON-NLS-1$
		appendName(fullName, messageName);
		fullName.append("Messages"); //$NON-NLS-1$
		return fullName.toString();
	}
	
	@Override
	public String getProtocolMessageQualifiedName(String packageName, String protocolName, String messageName) {
		final var fullName = new StringBuilder();
		appendAdapterPackageName(true, packageName, protocolName, fullName);
		fullName.append(".messages."); //$NON-NLS-1$
		appendName(fullName, messageName);
		return fullName.toString();
	}

	@Override
	public String getProtocolMessagePackageName(String packageName, String protocolName, String messageName) {
		final var fullName = new StringBuilder();
		appendAdapterPackageName(true, packageName, protocolName, fullName);
		fullName.append(".messages"); //$NON-NLS-1$
		return fullName.toString();
	}

	@Override
	public String getProtocolMessageName(String packageName, String protocolName, String messageName) {
		if (Strings.isEmpty(messageName)) {
			return ANONYMOUS_NAME;
		}
		final var mn = messageName.trim();
		if (Strings.isEmpty(mn)) {
			return ANONYMOUS_NAME;
		}
		return mn;
	}

	@Override
	public String getGetEnabledMessagesFunctionName(String messageName) {
		final var fullName = new StringBuilder();
		fullName.append("getEnabled"); //$NON-NLS-1$
		appendName(fullName, messageName);
		fullName.append("Messages"); //$NON-NLS-1$
		return fullName.toString();
	}

	@Override
	public String getSendMessageFunctionName(String messageName) {
		final var fullName = new StringBuilder();
		fullName.append("send"); //$NON-NLS-1$
		appendName(fullName, messageName);
		fullName.append("Message"); //$NON-NLS-1$
		return fullName.toString();
	}

	private static boolean appendAdapterPackageName(boolean mandatoryPackage, String packageName, String protocolName, StringBuilder receiver) {
		boolean packageNameAppended = false;
		if (packageName != null) {
			final var trimed = packageName.trim();
			if (!Strings.isEmpty(trimed)) {
				receiver.append(trimed);
				packageNameAppended = true;
			} else if (mandatoryPackage) {
				receiver.append(ANONYMOUS_NAME);
				packageNameAppended = true;
			}
		} else if (mandatoryPackage) {
			receiver.append(ANONYMOUS_NAME);
			packageNameAppended = true;
		}
		// Do not put the adapters in a subpackage to avoid the creation of "shadow" packages.
		// receiver.append(protocolName.toLowerCase()).append("_adapters"); //$NON-NLS-1$
		return packageNameAppended;
	}

	@Override
	public String getProtocolAdapterPackageName(String packageName, String protocolName) {
		final var fn = new StringBuilder();
		appendAdapterPackageName(false, packageName, protocolName, fn);
		return fn.toString();
	}
	
}
