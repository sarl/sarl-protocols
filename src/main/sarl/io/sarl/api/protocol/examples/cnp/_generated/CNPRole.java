package io.sarl.api.protocol.examples.cnp._generated;

import io.sarl.api.protocol.ProtocolRole;
import io.sarl.api.protocol.examples.cnp._generated.initiator.InitiatorBehavior;
import io.sarl.api.protocol.examples.cnp._generated.initiator.InitiatorProtocolCapacity;
import io.sarl.api.protocol.examples.cnp._generated.initiator.InitiatorProtocolSkill;
import io.sarl.api.protocol.examples.cnp._generated.participant.ParticipantBehavior;
import io.sarl.api.protocol.examples.cnp._generated.participant.ParticipantProtocolCapacity;
import io.sarl.api.protocol.examples.cnp._generated.participant.ParticipantProtocolSkill;
import io.sarl.api.protocol.examples.cnp._generated.space.CNPSpace;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.Behavior;
import io.sarl.lang.core.Capacity;
import io.sarl.lang.core.EventListener;
import io.sarl.lang.core.EventSpace;
import io.sarl.lang.core.Skill;

public enum CNPRole implements ProtocolRole {
	
	INITIATOR {
		@Override
		public Class<? extends Capacity> getProtocolCapacity() {
			return InitiatorProtocolCapacity.class;
		}

		@Override
		public Skill getProtocolSkill(EventSpace space) {
			return new InitiatorProtocolSkill(space);
		}

		@Override
		public Behavior getProtocolBehavior(Agent ag) {
			return new InitiatorBehavior(ag);
		}

		@Override
		public void registerRole(EventSpace sp, EventListener listener) {
			((CNPSpace) sp).registerInitiator(listener);			
		}

		@Override
		public void unregisterRole(EventSpace sp, EventListener listener) {
			((CNPSpace) sp).unregisterInitiator(listener);
		}
	},
	
	PARTICIPANT {
		@Override
		public Class<? extends Capacity> getProtocolCapacity() {
			return ParticipantProtocolCapacity.class;
		}

		@Override
		public Skill getProtocolSkill(EventSpace space) {
			return new ParticipantProtocolSkill(space);
		}

		@Override
		public Behavior getProtocolBehavior(Agent ag) {
			return new ParticipantBehavior(ag);
		}

		@Override
		public void registerRole(EventSpace sp, EventListener listener) {
			((CNPSpace) sp).registerParticipant(listener);	
		}

		@Override
		public void unregisterRole(EventSpace sp, EventListener listener) {
			((CNPSpace) sp).unregisterParticipant(listener);			
		}
	};

	
}
