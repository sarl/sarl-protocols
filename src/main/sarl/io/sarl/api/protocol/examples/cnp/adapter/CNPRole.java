package io.sarl.api.protocol.examples.cnp.adapter;

import io.sarl.api.protocol.ProtocolCapacity;
import io.sarl.api.protocol.ProtocolRole;
import io.sarl.api.protocol.ProtocolSpace;
import io.sarl.api.protocol.examples.cnp.adapter.contractor.ContractorProtocolCapacity;
import io.sarl.api.protocol.examples.cnp.adapter.contractor.ContractorProtocolSkill;
import io.sarl.api.protocol.examples.cnp.adapter.contractor.ContractorReactiveBehavior;
import io.sarl.api.protocol.examples.cnp.adapter.participant.ParticipantProtocolCapacity;
import io.sarl.api.protocol.examples.cnp.adapter.participant.ParticipantProtocolSkill;
import io.sarl.api.protocol.examples.cnp.adapter.participant.ParticipantReactiveBehavior;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.Behavior;
import io.sarl.lang.core.Skill;

public enum CNPRole implements ProtocolRole {
	CONTRACTOR {
		@Override
		public Class<? extends ProtocolCapacity> getProtocolCapacity() {
			return ContractorProtocolCapacity.class;
		}		
		
		@Override
		public Skill getProtocolSkill(ProtocolSpace space) {
			return new ContractorProtocolSkill(space);
		}

		@Override
		public Behavior getProtocolBehavior(Agent ag) {
			return new ContractorReactiveBehavior(ag);
		}

		@Override
		public int getMaxCardinality() {
			return 1;
		}
	},
	PARTICIPANT {
		@Override
		public Class<? extends ProtocolCapacity> getProtocolCapacity() {
			return ParticipantProtocolCapacity.class;
		}	
		
		@Override
		public Skill getProtocolSkill(ProtocolSpace space) {
			return new ParticipantProtocolSkill(space);
		}

		@Override
		public Behavior getProtocolBehavior(Agent ag) {
			return new ParticipantReactiveBehavior(ag);
		}

		@Override
		public int getMaxCardinality() {
			return 10;
		}
		
		@Override 
		public int getMinCardinality() {
			return 3;
		}
	};

}
