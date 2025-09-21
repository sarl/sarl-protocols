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

package io.sarl.extensions.bspl.api.protocol.tests.impl;

import java.util.UUID;

import io.sarl.api.workingmemory.AbstractWorkingMemory;
import io.sarl.api.workingmemory.WorkingMemory;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.AtomicSkillReference;
import io.sarl.lang.core.Capacity;

import static org.mockito.Mockito.*;

/**
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 * @since 1.0
 */
public class AgentMock extends Agent {

	private final AbstractWorkingMemory memory;

	public AgentMock(AbstractWorkingMemory memory) {
		super(UUID.randomUUID(), UUID.randomUUID());
		this.memory = memory;
	}
	
	@Override
	public AtomicSkillReference $getSkill(Class<? extends Capacity> capacity) {
		if (capacity.equals(WorkingMemory.class)) {
			final var ref = mock(AtomicSkillReference.class);
			when(ref.get()).thenReturn(this.memory);
			return ref;
		}
		return super.$getSkill(capacity);
	}

}
